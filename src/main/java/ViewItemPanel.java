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

public class ViewItemPanel extends JPanel {
    JPanel itemListPanel, semiItemList, forNavbar, semiView,outerSemiView;
    Buttons previousButton;
    String userName;
    String purpose;
    int set;
    String budget;
    String part;
    String itemName;
    String itemPrice, itemLocation;
    JPanel itemAndImage;
    Buttons saveButton;
    public ViewItemPanel(JPanel itemListPanel, JPanel semiItemList,Buttons previousButton, String userName, String purpose, int set, String budget,String part, String itemName, String itemPrice, String itemLocation){
        this.itemListPanel = itemListPanel;
        this.semiItemList = semiItemList;
        this.previousButton = previousButton;
        this.userName = userName;
        this.purpose = purpose;
        this.set = set;
        this.budget = budget;
        this.part = part;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemLocation = itemLocation;
        this.setPreferredSize(new Dimension(ComHard.WIDTH,ComHard.LENGTH));
        this.setBackground(new Color(0,245,245));
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        this.setVisible(true);
        itemView();
    }
    public void setForNavbar(){
        forNavbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        forNavbar.setBackground(Color.WHITE);
        JLabel backButton = new JLabel("     Back"){
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
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addMouseListener(new BackButtonListener(itemListPanel,semiItemList,this));
        forNavbar.add(backButton);

    }
    public void itemView(){
        outerSemiView = new JPanel();
        outerSemiView.setPreferredSize(new Dimension(ComHard.WIDTH,ComHard.LENGTH));
        outerSemiView.setLayout(new BorderLayout());
        semiView = new JPanel();
        semiView.setPreferredSize(new Dimension(ComHard.WIDTH,ComHard.LENGTH));
        semiView.setLayout(new BoxLayout(semiView, BoxLayout.X_AXIS));


        forNavbar = new JPanel();
        forNavbar.setBackground(Color.WHITE);
        forNavbar.setPreferredSize(new Dimension(ComHard.WIDTH,35));
        setForNavbar();
        this.add(forNavbar,BorderLayout.NORTH);

        JTextPane itemNamePane = new JTextPane();
        itemNamePane.setText(itemName);
        SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
        SimpleAttributeSet justifiedAttribute = new SimpleAttributeSet();
        StyleConstants.setAlignment(justifiedAttribute, StyleConstants.ALIGN_JUSTIFIED);
        StyledDocument documentStyle = itemNamePane.getStyledDocument();
        documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);
        itemNamePane.setFocusable(false);
        itemNamePane.setEditable(false);
        itemNamePane.setBackground(Color.WHITE);
        itemNamePane.setFont(new Font("",Font.BOLD,36));
        itemNamePane.setForeground(new Color(51,51,51));
        itemNamePane.setPreferredSize(new Dimension(1010, 90));
        outerSemiView.add(itemNamePane,BorderLayout.NORTH);

        itemAndImage = new JPanel();
        itemAndImage.setLayout(new FlowLayout(FlowLayout.CENTER));
        itemAndImage.setPreferredSize(new Dimension(300,ComHard.LENGTH));
        itemAndImage.setBackground(Color.WHITE);
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(new ImageIcon(storeImage(itemName,"items")));
        iconLabel.setPreferredSize(new Dimension(storeImage(itemName,"items").getWidth(),storeImage(itemName,"items").getHeight()));
        itemAndImage.add(iconLabel);
        itemAndImage.add(Box.createRigidArea(new Dimension(400,20)));
        saveButton = new Buttons("Save");
        saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveButton.setDimension(150, 50);
        if(new File("assets/favorites/"+userName+"/"+purpose+"/"+set+". "+budget+"/"+part+".txt").exists()){
            saveButton.setLabel("Unsave");
            saveButton.setColor(new Color(213, 17, 17));
            saveButton.setTextColor(new Color(250,250,250));
        }else {
            saveButton.setColor(new Color(53,118,172));
            saveButton.setTextColor(new Color(250, 250, 250));
        }
        saveButton.setFontSize(25);
        saveButton.setBorderRadius(50);
        saveButton.addTextPosition(0, -4);
        saveButton.setOpaque(false);
        saveButton.addMouseListener(new DuplicateClasses.SaveButtonListener(saveButton,previousButton,userName,purpose,set,budget,part,
                itemName, itemPrice, itemLocation));
        itemAndImage.add(saveButton);
        semiView.add(itemAndImage);


        JPanel itemsPanel = new JPanel();
        itemsPanel.setPreferredSize(new Dimension(300,ComHard.LENGTH));
        itemsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        itemsPanel.setBackground(Color.WHITE);
        JPanel box = new JPanel();
        box.setBackground(null);
        box.setPreferredSize(new Dimension(400,50));
        itemsPanel.add(box);

        JTextPane nameLabel = new JTextPane();
        nameLabel.setText(part);
        nameLabel.setBackground(null);
        nameLabel.setForeground(new Color(51,51,51));
        documentStyle = nameLabel.getStyledDocument();
        documentStyle.setParagraphAttributes(0, documentStyle.getLength(), justifiedAttribute, false);
        nameLabel.setFocusable(false);
        nameLabel.setEditable(false);
        nameLabel.setLayout(new FlowLayout(FlowLayout.CENTER));
        nameLabel.setFont(new Font("",Font.BOLD,48));
        nameLabel.setPreferredSize(new Dimension(450, 125));
        itemsPanel.add(nameLabel);

        JTextPane itemPricePane = new JTextPane();
        itemPricePane.setText(itemPrice);
        documentStyle = itemPricePane.getStyledDocument();
        documentStyle.setParagraphAttributes(0, documentStyle.getLength(), justifiedAttribute, false);
        itemPricePane.setFocusable(false);
        itemPricePane.setEditable(false);
        itemPricePane.setBackground(null);
        itemPricePane.setFont(new Font("", Font.BOLD, 24));
        itemPricePane.setPreferredSize(new Dimension(450, 65));
        itemsPanel.add(itemPricePane);

        JTextPane itemLocationPane = new JTextPane();
        itemLocationPane.setText(itemLocation);
        documentStyle = itemLocationPane.getStyledDocument();
        documentStyle.setParagraphAttributes(0, documentStyle.getLength(), justifiedAttribute, false);
        itemLocationPane.setFocusable(false);
        itemLocationPane.setEditable(false);
        itemLocationPane.setBackground(null);
        itemLocationPane.setFont(new Font("",Font.BOLD,24));
        itemLocationPane.setForeground(new Color(51,51,51));
        itemLocationPane.setPreferredSize(new Dimension(450, 200));
        itemsPanel.add(itemLocationPane);


        semiView.add(itemsPanel);
        outerSemiView.add(semiView,BorderLayout.CENTER);
        this.add(outerSemiView,BorderLayout.CENTER);
    }
    class BackButtonListener extends MouseAdapter{
        JPanel itemListPanel,semiItemList, viewItemList;

        public BackButtonListener(JPanel itemListPanel, JPanel semiItemList, JPanel viewItemList){

            this.itemListPanel = itemListPanel;
            this.semiItemList = semiItemList;
            this.viewItemList = viewItemList;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            itemListPanel.add(semiItemList);
            semiItemList.validate();
            semiItemList.repaint();
            semiItemList.setVisible(true);
            viewItemList.setVisible(false);
        }
    }
    public static BufferedImage storeImage(String itemName, String folder){
        itemName = itemName.trim();
        int width = 10, height = 10;
        BufferedImage bufferedImage = null;
        try{
            bufferedImage = ImageIO.read(new File("resources/"+folder+"/"+itemName+".jpg"));
            width = bufferedImage.getWidth();
            height = bufferedImage.getHeight();
        }catch (IOException ignored){}
        BufferedImage imageBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = imageBuffer.createGraphics();
        g2.setClip(0,0, width, height);
        g2.drawImage(bufferedImage,0,0,width,height,null);
        g2.dispose();
        return imageBuffer;
    }
}
