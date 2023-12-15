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
import java.io.IOException;
import java.util.Arrays;

public class ItemListPanel extends JPanel {
    JPanel dashboardPanel;
    JLabel backButton;
    JPanel homePanel;
    JPanel[] itemPanels;
    JLabel[] itemImage;
    String userName;
    JPanel forItems, forFilter, forItemsAndNavbar, forNavbar;
    Buttons[] saveButtons;

    String purpose, budget;
    JScrollPane scrollPane;
    BufferedImage bufferedImage;

    public ItemListPanel(JPanel dashboardPanel, JPanel homePanel, String userName, String purpose, String budget){
        this.dashboardPanel = dashboardPanel;
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
        forItems = new JPanel();
        forItems.setBackground(null);
        forItems.setPreferredSize(new Dimension(800,600));

        forNavbar = new JPanel();
        forNavbar.setBackground(null);
        forNavbar.setPreferredSize(new Dimension(ComHard.WIDTH,35));
        setForNavbar();
        forItemsAndNavbar = new JPanel();
        forItemsAndNavbar.setLayout(new BorderLayout());
        forItemsAndNavbar.setBackground(new Color(236,236,236));
        forItemsAndNavbar.add(forNavbar,BorderLayout.NORTH);
        forItemsAndNavbar.add(forItems,BorderLayout.CENTER);
        setForItems();
        this.add(forItemsAndNavbar,BorderLayout.CENTER);
    }

    public void setForItems(){
        String[] content = listTxtFiles("assets/items/"+purpose+"/1. "+budget);
        System.out.println(Arrays.toString(content));
        itemPanels = new JPanel[content.length];
        itemImage = new JLabel[content.length];
        saveButtons = new Buttons[content.length];
        System.out.println((fileContent(content[1])[1]));
        JPanel insidePanel = new JPanel();
        insidePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50 , 30));
        insidePanel.setBackground(new Color(236,236,236));
        insidePanel.setBorder(null);
        insidePanel.setPreferredSize(new Dimension(ComHard.WIDTH/3,ComHard.LENGTH*3));
        for (int i = 0; i < itemPanels.length; i++) {
            itemPanels[i] = new JPanel();
            itemPanels[i].setBackground(Color.green);
            itemPanels[i].setPreferredSize(new Dimension(264, 176+300));
            itemPanels[i].setLayout(new FlowLayout(FlowLayout.CENTER));
            itemImage[i] = new JLabel();
            itemImage[i].setIcon(new ImageIcon(imageItems(getItemName(fileContent(content[i])))));
            itemImage[i].setHorizontalAlignment(SwingConstants.LEFT);
            itemImage[i].setPreferredSize(new Dimension(imageItems(getItemName(fileContent(content[i]))).getWidth(),
                    imageItems(getItemName(fileContent(content[i]))).getHeight()));
            itemImage[i].add(Box.createHorizontalGlue());

            itemPanels[i].add(itemImage[i]);

            JTextPane[] itemNames = new JTextPane[content.length];
            itemNames[i] = new JTextPane();
            itemNames[i].add(Box.createHorizontalGlue());
            itemNames[i].setText(getItemName(fileContent(content[i])));
            StyledDocument documentStyle = itemNames[i].getStyledDocument();
            SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
            StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
            documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);

            itemNames[i].setFocusable(false);
            itemNames[i].setEditable(false);
            itemNames[i].setFont(new Font("",Font.BOLD,14));
            itemNames[i].setPreferredSize(new Dimension(210, 45));
            itemPanels[i].add(itemNames[i]);

            JTextPane[] itemPrice = new JTextPane[content.length];
            itemPrice[i] = new JTextPane();
            itemPrice[i].add(Box.createHorizontalGlue());
            itemPrice[i].setText(getItemPrice(fileContent(content[i])));
            documentStyle = itemPrice[i].getStyledDocument();
            centerAttribute = new SimpleAttributeSet();
            StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
            documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);

            itemPrice[i].setFocusable(false);
            itemPrice[i].setEditable(false);
            itemPrice[i].setFont(new Font("",Font.BOLD,14));
            itemPrice[i].setPreferredSize(new Dimension(210, 25));
            itemPanels[i].add(itemPrice[i]);

            JTextPane[] storeAddresses = new JTextPane[content.length];
            storeAddresses[i] = new JTextPane();
            storeAddresses[i].setText(getItemAddress(fileContent(content[i])));
            documentStyle = storeAddresses[i].getStyledDocument();
            centerAttribute = new SimpleAttributeSet();
            StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
            documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);
            storeAddresses[i].setFocusable(false);
            storeAddresses[i].setEditable(false);
            storeAddresses[i].setLayout(new FlowLayout(FlowLayout.CENTER));
            storeAddresses[i].setFont(new Font("",Font.PLAIN,14));
            storeAddresses[i].setPreferredSize(new Dimension(210, 65));
            itemPanels[i].add(storeAddresses[i]);

            saveButtons[i] = new Buttons("Save");
            saveButtons[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            saveButtons[i].setDimension(75,25);
            saveButtons[i].setColor(new Color(53,118,172));
            saveButtons[i].setFontSize(12);
            saveButtons[i].setBorderRadius(25);
            saveButtons[i].addTextPosition(0,-2);
            saveButtons[i].setOpaque(false);
            saveButtons[i].setTextColor(new Color(236,236,236));
            itemPanels[i].add(saveButtons[i]);

        insidePanel.add(itemPanels[i]);
        }
        scrollPane = new JScrollPane(insidePanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);
        scrollPane.setBackground(new Color(236,236,236));
        scrollPane.getVerticalScrollBar().setUnitIncrement(5);
        SwingUtilities.invokeLater(() -> {
            scrollPane.getViewport().setViewPosition(new Point(0,0));
        });
        forItemsAndNavbar.add(scrollPane);

    }
    public void setForFilter(){
        forFilter.setBackground(new Color(245,245,245));


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
        //backButton.setIcon(new ImageIcon(new ImageIcon("resources/img/backIcon.png").
        //        getImage().getScaledInstance(20, 20,Image.SCALE_SMOOTH)));
        backButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        backButton.addMouseListener(new BackButtonListener(this, homePanel, dashboardPanel,userName));
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

    public String[] listTxtFiles(String directoryPath) {
        File directory = new File(directoryPath);
        File[] txtFiles = directory.listFiles();
        String[] txtFileNames = new String[0];
        if (txtFiles != null) {
            txtFileNames = new String[txtFiles.length];
            for (int i = 0; i < txtFiles.length; i++) txtFileNames[i] = txtFiles[i].getName().substring(0,txtFiles[i].getName().length()-4);
        }
        return txtFileNames;
    }

    public String[] fileContent(String part){
        String[] content = new String[0];
        try{
            File fileCreated = new File("assets/items/"+purpose+"/1. "+budget+"/"+part+".txt");
            FileReader reader = new FileReader(fileCreated);
            int data = reader.read();
            String string = Character.toString ((char) data);
            data = reader.read();
            while(data != -1){
                string += Character.toString((char) data);
                data = reader.read();
            }
            reader.close();
            content = string.split("\n");
        }catch (IOException ignored){}
        //System.out.println(Arrays.toString(content));
        return content;
    }

    static class BackButtonListener extends MouseAdapter{
        JPanel panel;
        JPanel homePanel;
        JPanel dashboardPanel;
        String userName;

        public BackButtonListener(JPanel panel, JPanel homePanel, JPanel dashboardPanel, String userName){
            this.panel = panel;
            this.homePanel = homePanel;
            this.dashboardPanel = dashboardPanel;
            this.userName = userName;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            panel.setVisible(false);
            dashboardPanel.add(homePanel,BorderLayout.CENTER);
            homePanel.setVisible(true);
        }
    }
}
