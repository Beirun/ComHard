import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class FavoritesPanel extends JPanel {
    JPanel dashboardPanel;
    String userName;
    Buttons[] saveButtons;
    JPanel[] itemPanels;
    JLabel[] itemImage;
    JLabel[] imageLabels;
    JScrollPane scrollPane;
    BufferedImage bufferedImage;

    public FavoritesPanel(JPanel dashboardPanel, String userName){
        this.dashboardPanel = dashboardPanel;
        this.userName = userName;
        this.setPreferredSize(new Dimension(ComHard.WIDTH,ComHard.LENGTH));
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(245,245,245));
        favoritesPanel();
    }

    public void favoritesPanel(){
        String [] purpose = listDirectories("assets/favorites/"+userName);
        System.out.println(Arrays.toString(purpose));
        JPanel insidePanel = new JPanel();
        insidePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 30));
        insidePanel.setBackground(new Color(236, 236, 236));
        insidePanel.setBorder(null);
        insidePanel.setPreferredSize(new Dimension(ComHard.WIDTH*2/3, ComHard.LENGTH));
        int length =0, remainder= 0;
        for(int i = 0; i < purpose.length; i++){
            String[] budget = listDirectories("assets/favorites/"+userName+"/"+purpose[i]);
            System.out.println(Arrays.toString(budget));
            for(int j = 0; j < budget.length; j++){
                String[] content = DuplicateClasses.listTxtFiles("assets/favorites/"+userName+"/"+ purpose[i] + "/"+ budget[j]);
                System.out.println(Arrays.toString(content));
                int set = 0;
                String[] fileContent;
                length += 505 *(content.length/3);
                remainder += content.length%3;
                if(j==0 && i==0 && remainder!=0) length+=506;
                if(remainder>3){
                    remainder-=3;
                    length+=506;
                }
                insidePanel.setPreferredSize(new Dimension(ComHard.WIDTH*2/3, length+50));
                    itemPanels = new JPanel[content.length];
                    itemImage = new JLabel[content.length];
                    saveButtons = new Buttons[content.length];

                    for (int k = 0; k < itemPanels.length; k++) {
                        fileContent = DuplicateClasses.fileContent(purpose[i],budget[j],content[k],set);
                        itemPanels[k] = new JPanel();
                        itemPanels[k].setBackground(Color.green);
                        itemPanels[k].setPreferredSize(new Dimension(264, 476));
                        itemPanels[k].setLayout(new FlowLayout(FlowLayout.CENTER));
                        itemImage[k] = new JLabel();
                        itemImage[k].setIcon(new ImageIcon(imageItems(getItemName(fileContent))));
                        itemImage[k].setHorizontalAlignment(SwingConstants.LEFT);
                        itemImage[k].setPreferredSize(new Dimension(imageItems(getItemName(fileContent)).getWidth(),
                                imageItems(getItemName(fileContent)).getHeight()));
                        itemImage[k].add(Box.createHorizontalGlue());

                        itemPanels[k].add(itemImage[k]);

                        JTextPane[] itemNames = new JTextPane[content.length];
                        itemNames[k] = new JTextPane();
                        itemNames[k].add(Box.createHorizontalGlue());
                        itemNames[k].setText(getItemName(fileContent));
                        StyledDocument documentStyle = itemNames[k].getStyledDocument();
                        SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
                        StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
                        documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);

                        itemNames[k].setFocusable(false);
                        itemNames[k].setEditable(false);
                        itemNames[k].setFont(new Font("", Font.BOLD, 14));
                        itemNames[k].setPreferredSize(new Dimension(210, 45));
                        itemPanels[k].add(itemNames[k]);

                        JTextPane[] itemPrice = new JTextPane[content.length];
                        itemPrice[k] = new JTextPane();
                        itemPrice[k].add(Box.createHorizontalGlue());
                        itemPrice[k].setText(getItemPrice(fileContent));
                        documentStyle = itemPrice[k].getStyledDocument();
                        centerAttribute = new SimpleAttributeSet();
                        StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
                        documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);

                        itemPrice[k].setFocusable(false);
                        itemPrice[k].setEditable(false);
                        itemPrice[k].setFont(new Font("", Font.BOLD, 14));
                        itemPrice[k].setPreferredSize(new Dimension(210, 25));
                        itemPanels[k].add(itemPrice[k]);

                        JTextPane[] storeAddresses = new JTextPane[content.length];
                        storeAddresses[k] = new JTextPane();
                        storeAddresses[k].setText(getItemAddress(fileContent));
                        documentStyle = storeAddresses[k].getStyledDocument();
                        centerAttribute = new SimpleAttributeSet();
                        StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
                        documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);
                        storeAddresses[k].setFocusable(false);
                        storeAddresses[k].setEditable(false);
                        storeAddresses[k].setLayout(new FlowLayout(FlowLayout.CENTER));
                        storeAddresses[k].setFont(new Font("", Font.PLAIN, 14));
                        storeAddresses[k].setPreferredSize(new Dimension(210, 65));
                        itemPanels[k].add(storeAddresses[k]);

                        saveButtons[k] = new Buttons("Save");
                        saveButtons[k].setCursor(new Cursor(Cursor.HAND_CURSOR));
                        saveButtons[k].setDimension(75, 25);
                        if(new File("assets/favorites/"+userName+"/"+purpose[i]+"/"+budget[j]+"/"+content[k]+".txt").exists()){
                            saveButtons[k].setLabel("Unsave");
                            saveButtons[k].setColor(new Color(213, 17, 17));
                            saveButtons[k].setTextColor(new Color(250,250,250));
                        }else {
                            saveButtons[k].setColor(new Color(250, 250, 250));
                            saveButtons[k].setTextColor(new Color(51, 51, 51));
                        }
                        saveButtons[k].setFontSize(12);
                        saveButtons[k].setBorderRadius(25);
                        saveButtons[k].addTextPosition(0, -2);
                        saveButtons[k].setOpaque(false);
                        saveButtons[k].addMouseListener(new DuplicateClasses.SaveButtonListener(saveButtons[k], userName,purpose[i],set,budget[j],content[k],
                                getItemName(fileContent),getItemPrice(fileContent),getItemAddress(fileContent)));
                        itemPanels[k].add(saveButtons[k]);
                        insidePanel.add(itemPanels[k]);
                    }
            }
        }

        this.setPreferredSize(insidePanel.getPreferredSize());
        scrollPane = new JScrollPane(insidePanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);
        scrollPane.setBackground(new Color(236,236,236));
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        SwingUtilities.invokeLater(() -> scrollPane.getViewport().setViewPosition(new Point(0,0)));
        this.add(scrollPane,BorderLayout.CENTER);
    }
    public String[] listDirectories(String directoryPath){
        File directory = new File(directoryPath);
        File[] directoryNames = directory.listFiles();
        String[] fileNames = new String[0];
        if (directoryNames != null) {
            fileNames = new String[directoryNames.length];
            for (int i = 0; i < directoryNames.length; i++) fileNames[i] = directoryNames[i].getName();
        }
        return fileNames;
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
    public String getItemName(String[] string){
        return string[0];
    }
    public String getItemPrice(String[] string){
        return string[1];
    }
    public String getItemAddress(String[] string){
        return string[2];
    }
}
