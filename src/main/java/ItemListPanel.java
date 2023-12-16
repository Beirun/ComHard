import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class ItemListPanel extends JPanel {
    JPanel semiHonePanel;
    JLabel backButton;
    JPanel homePanel;
    JPanel[] itemPanels;
    JLabel[] itemImage;
    String userName;
    JPanel forItemsAndNavbar, forNavbar;
    Buttons[] saveButtons;

    String purpose, budget;
    JScrollPane scrollPane;
    BufferedImage bufferedImage;

    public ItemListPanel(JPanel semiHomePanel, JPanel homePanel, String userName, String purpose, String budget){
        this.semiHonePanel = semiHomePanel;
        this.homePanel = homePanel;
        this.userName = userName;
        this.purpose = purpose;
        this.budget = budget;
        this.setPreferredSize(new Dimension(ComHard.WIDTH,ComHard.LENGTH));
        this.setBackground(new Color(245,245,245));
        this.setBackground(Color.CYAN);
        this.setLayout(new BorderLayout());
        filterItemsPanel();
    }
    public void filterItemsPanel(){
        forNavbar = new JPanel();
        forNavbar.setBackground(null);
        forNavbar.setPreferredSize(new Dimension(ComHard.WIDTH,35));
        setForNavbar();
        forItemsAndNavbar = new JPanel();
        forItemsAndNavbar.setLayout(new BorderLayout());
        forItemsAndNavbar.setBackground(new Color(236,236,236));
        forItemsAndNavbar.add(forNavbar,BorderLayout.NORTH);
        setForItems();
    }

    public void setForItems(){
        int set = 1;

        String[] content = DuplicateClasses.listTxtFiles("assets/items/" + purpose + "/"+set+". " + budget);
        String[] fileContent;
        JPanel insidePanel = new JPanel();
        insidePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 30));
        insidePanel.setBackground(new Color(236, 236, 236));
        insidePanel.setBorder(null);
        insidePanel.setPreferredSize(new Dimension(ComHard.WIDTH / 3, ComHard.LENGTH ));

        int length = 505 *(content.length/3);
        int remainder = content.length%3;
        if(remainder!=0) length+=500;
        do {
            insidePanel.setPreferredSize(new Dimension(ComHard.WIDTH / 3, length+50));
            itemPanels = new JPanel[content.length];
            itemImage = new JLabel[content.length];
            saveButtons = new Buttons[content.length];

            for (int i = 0; i < itemPanels.length; i++) {
                fileContent = DuplicateClasses.fileContent(purpose,budget,content[i],set);
                itemPanels[i] = new JPanel();
                itemPanels[i].setBackground(Color.green);
                itemPanels[i].setPreferredSize(new Dimension(264, 476));
                itemPanels[i].setLayout(new FlowLayout(FlowLayout.CENTER));
                itemImage[i] = new JLabel();
                itemImage[i].setIcon(new ImageIcon(imageItems(getItemName(fileContent))));
                itemImage[i].setHorizontalAlignment(SwingConstants.LEFT);
                itemImage[i].setPreferredSize(new Dimension(imageItems(getItemName(fileContent)).getWidth(),
                        imageItems(getItemName(fileContent)).getHeight()));
                itemImage[i].add(Box.createHorizontalGlue());

                itemPanels[i].add(itemImage[i]);

                JTextPane[] itemNames = new JTextPane[content.length];
                itemNames[i] = new JTextPane();
                itemNames[i].add(Box.createHorizontalGlue());
                itemNames[i].setText(getItemName(fileContent));
                StyledDocument documentStyle = itemNames[i].getStyledDocument();
                SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
                StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
                documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);

                itemNames[i].setFocusable(false);
                itemNames[i].setEditable(false);
                itemNames[i].setFont(new Font("", Font.BOLD, 14));
                itemNames[i].setPreferredSize(new Dimension(210, 45));
                itemPanels[i].add(itemNames[i]);

                JTextPane[] itemPrice = new JTextPane[content.length];
                itemPrice[i] = new JTextPane();
                itemPrice[i].add(Box.createHorizontalGlue());
                itemPrice[i].setText(getItemPrice(fileContent));
                documentStyle = itemPrice[i].getStyledDocument();
                centerAttribute = new SimpleAttributeSet();
                StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
                documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);

                itemPrice[i].setFocusable(false);
                itemPrice[i].setEditable(false);
                itemPrice[i].setFont(new Font("", Font.BOLD, 14));
                itemPrice[i].setPreferredSize(new Dimension(210, 25));
                itemPanels[i].add(itemPrice[i]);

                JTextPane[] storeAddresses = new JTextPane[content.length];
                storeAddresses[i] = new JTextPane();
                storeAddresses[i].setText(getItemAddress(fileContent));
                documentStyle = storeAddresses[i].getStyledDocument();
                centerAttribute = new SimpleAttributeSet();
                StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
                documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);
                storeAddresses[i].setFocusable(false);
                storeAddresses[i].setEditable(false);
                storeAddresses[i].setLayout(new FlowLayout(FlowLayout.CENTER));
                storeAddresses[i].setFont(new Font("", Font.PLAIN, 14));
                storeAddresses[i].setPreferredSize(new Dimension(210, 65));
                itemPanels[i].add(storeAddresses[i]);

                saveButtons[i] = new Buttons("Save");
                saveButtons[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
                saveButtons[i].setDimension(75, 25);
                if(new File("assets/favorites/"+userName+"/"+purpose+"/"+set+". "+budget+"/"+content[i]+".txt").exists()){
                    saveButtons[i].setLabel("Unsave");
                    saveButtons[i].setColor(new Color(213, 17, 17));
                    saveButtons[i].setTextColor(new Color(250,250,250));
                }else {
                    saveButtons[i].setColor(new Color(250, 250, 250));
                    saveButtons[i].setTextColor(new Color(51, 51, 51));
                }
                saveButtons[i].setFontSize(12);
                saveButtons[i].setBorderRadius(25);
                saveButtons[i].addTextPosition(0, -2);
                saveButtons[i].setOpaque(false);
                saveButtons[i].addMouseListener(new DuplicateClasses.SaveButtonListener(saveButtons[i], userName,purpose,set,budget,content[i],
                        getItemName(fileContent),getItemPrice(fileContent),getItemAddress(fileContent)));
                itemPanels[i].add(saveButtons[i]);
                insidePanel.add(itemPanels[i]);
            }
            set++;
            content = DuplicateClasses.listTxtFiles("assets/items/" + purpose + "/"+set+". " + budget);
            length += 506 * (content.length/3);
            remainder += content.length%3;
            if(remainder>3){
                remainder-=3;
                length+=506;
            }
        }while (content.length!=0);
        forItemsAndNavbar.add(insidePanel,BorderLayout.CENTER);
        forItemsAndNavbar.setPreferredSize(new Dimension((int) (insidePanel.getPreferredSize().getWidth()),
                (int) (insidePanel.getPreferredSize().getHeight()+forNavbar.getPreferredSize().getHeight())));
        scrollPane = new JScrollPane(forItemsAndNavbar,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);
        scrollPane.setBackground(new Color(236,236,236));
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        SwingUtilities.invokeLater(() -> {
            scrollPane.getViewport().setViewPosition(new Point(0,0));
        });
        this.add(scrollPane,BorderLayout.CENTER);
    }
    public void setForNavbar(){
        forNavbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        forNavbar.setBackground(new Color(236,236,236));
        backButton = new JLabel("     Back"){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                BufferedImage bufferedImage;
                try {
                    bufferedImage = ImageIO.read(new File("resources/img/backIcon.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                g.drawImage(bufferedImage,0,0,bufferedImage.getWidth()/4,bufferedImage.getHeight()/4,null);
                g.dispose();
            }
        };
        backButton.setFont(new Font("",Font.BOLD,20));
        backButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        backButton.addMouseListener(new BackButtonListener(this, homePanel, semiHonePanel,userName));
        forNavbar.add(backButton);
    }

    public String getItemName(String[] string){
        return string[0];
    }
    public String getItemPrice(String[] string){
        return string[1];
    }
    public String getItemAddress(String[] string){
        return string[2];
    }

    public BufferedImage imageItems(String itemName){
        int width = 10, height = 10;
        try{
            bufferedImage = ImageIO.read(new File("resources/items/"+itemName+".jpg"));
            width = bufferedImage.getWidth()/2;
            height = bufferedImage.getHeight()/2;
        }catch (IOException ignored){}
        BufferedImage imageBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = imageBuffer.createGraphics();
        g2.setClip(0,0, width, height);
        g2.drawImage(bufferedImage,0,0,width,height,null);
        g2.dispose();
        return imageBuffer;
    }

    static class BackButtonListener extends MouseAdapter{
        JPanel panel, homePanel, semiHomePanel;
        String userName;

        public BackButtonListener(JPanel panel, JPanel homePanel, JPanel semiHomePanel, String userName){
            this.panel = panel;
            this.homePanel = homePanel;
            this.semiHomePanel = semiHomePanel;
            this.userName = userName;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            panel.setVisible(false);
            homePanel.add(semiHomePanel,BorderLayout.CENTER);
            semiHomePanel.setVisible(true);
        }
    }
}
