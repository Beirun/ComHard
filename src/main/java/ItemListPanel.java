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
import java.io.IOException;


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

        int totalItems = content.length;
        do {
            itemPanels = new JPanel[content.length];
            itemImage = new JLabel[content.length];
            saveButtons = new Buttons[content.length];

            for (int i = 0; i < itemPanels.length; i++) {
                fileContent = DuplicateClasses.fileContent(purpose,budget,content[i],set);
                itemPanels[i] = new JPanel();
                itemPanels[i].setBackground(Color.WHITE);
                itemPanels[i].setPreferredSize(new Dimension(264, 425));
                itemPanels[i].setLayout(new FlowLayout(FlowLayout.CENTER));
                itemImage[i] = new JLabel();
                itemImage[i].setIcon(new ImageIcon(DuplicateClasses.imageItems(DuplicateClasses.getItemName(fileContent),"items")));
                itemImage[i].setHorizontalAlignment(SwingConstants.LEFT);
                itemImage[i].setPreferredSize(new Dimension(DuplicateClasses.imageItems(DuplicateClasses.getItemName(fileContent),"items").getWidth(),
                        DuplicateClasses.imageItems(DuplicateClasses.getItemName(fileContent),"items").getHeight()));
                itemImage[i].add(Box.createHorizontalGlue());
                itemPanels[i].add(itemImage[i]);

                JTextPane[] itemNames = new JTextPane[content.length];
                itemNames[i] = new JTextPane();
                itemNames[i].add(Box.createHorizontalGlue());
                itemNames[i].setText(DuplicateClasses.getItemName(fileContent));
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
                itemPrice[i].setText(DuplicateClasses.getItemPrice(fileContent));
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
                storeAddresses[i].setText(DuplicateClasses.getItemAddress(fileContent));
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
                itemPanels[i].add(Box.createRigidArea(new Dimension(220,5)));
                saveButtons[i] = new Buttons("Save");
                saveButtons[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
                saveButtons[i].setDimension(75, 25);
                if(new File("assets/favorites/"+userName+"/"+purpose+"/"+set+". "+budget+"/"+content[i]+".txt").exists()){
                    saveButtons[i].setLabel("Unsave");
                    saveButtons[i].setColor(new Color(213, 17, 17));
                    saveButtons[i].setTextColor(new Color(250,250,250));
                }else {
                    saveButtons[i].setColor(new Color(53,118,172));
                    saveButtons[i].setTextColor(new Color(250, 250, 250));
                }
                saveButtons[i].setFontSize(12);
                saveButtons[i].setBorderRadius(25);
                saveButtons[i].addTextPosition(0, -2);
                saveButtons[i].setOpaque(false);
                saveButtons[i].addMouseListener(new DuplicateClasses.SaveButtonListener(saveButtons[i], userName,purpose,set,budget,content[i],
                        DuplicateClasses.getItemName(fileContent), DuplicateClasses.getItemPrice(fileContent), DuplicateClasses.getItemAddress(fileContent)));
                itemPanels[i].add(saveButtons[i]);
                insidePanel.add(itemPanels[i]);
            }
            set++;
            content = DuplicateClasses.listTxtFiles("assets/items/" + purpose + "/"+set+". " + budget);
            totalItems += content.length;
        }while (content.length!=0);
        int row = totalItems /3;
        if(totalItems%3!=0) row+=1;
        int gap = totalItems/6;
        if(totalItems%6!=0) gap+=1;
        int height = 426*row + 143/row + gap*60;
        insidePanel.setPreferredSize(new Dimension(ComHard.WIDTH/3, height));
        forItemsAndNavbar.add(insidePanel,BorderLayout.CENTER);
        forItemsAndNavbar.setPreferredSize(new Dimension((int) (insidePanel.getPreferredSize().getWidth()),
                (int) (insidePanel.getPreferredSize().getHeight()+forNavbar.getPreferredSize().getHeight())));
        scrollPane = new JScrollPane(forItemsAndNavbar,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);
        scrollPane.setBackground(new Color(236,236,236));
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        SwingUtilities.invokeLater(() -> scrollPane.getViewport().setViewPosition(new Point(0,0)));
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
