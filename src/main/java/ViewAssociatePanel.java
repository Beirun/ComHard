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

public class ViewAssociatePanel extends JPanel {
    JPanel associatesPanel, semiAssociates, forNavbar, semiView;
    String storeName;
    JPanel storeAndName;

    public ViewAssociatePanel(JPanel associatesPanel, JPanel semiAssociates, String storeName){
        this.associatesPanel = associatesPanel;
        this.semiAssociates = semiAssociates;
        this.storeName = storeName;
        this.setPreferredSize(new Dimension(ComHard.WIDTH,ComHard.LENGTH));
        this.setBackground(new Color(0,245,245));
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        this.setVisible(true);
        storeView();
    }
    public void setForNavbar(){
        forNavbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        forNavbar.setBackground(new Color(236,236,236));
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
        backButton.addMouseListener(new BackButtonListener(associatesPanel,semiAssociates,this));
        forNavbar.add(backButton);

    }
    public void storeView(){
        semiView = new JPanel();
        semiView.setPreferredSize(new Dimension(ComHard.WIDTH,ComHard.LENGTH));
        semiView.setLayout(new BoxLayout(semiView, BoxLayout.X_AXIS));


        forNavbar = new JPanel();
        forNavbar.setBackground(null);
        forNavbar.setPreferredSize(new Dimension(ComHard.WIDTH,35));
        setForNavbar();
        this.add(forNavbar,BorderLayout.NORTH);

        storeAndName = new JPanel();
        storeAndName.setLayout(new FlowLayout(FlowLayout.CENTER));
        storeAndName.setPreferredSize(new Dimension(300,ComHard.LENGTH));
        JTextPane nameLabel = new JTextPane();
        nameLabel.setText(storeName);
        nameLabel.setBackground(null);
        nameLabel.setForeground(new Color(51,51,51));
        StyledDocument documentStyle = nameLabel.getStyledDocument();
        SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
        documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);
        nameLabel.setFocusable(false);
        nameLabel.setEditable(false);
        nameLabel.setLayout(new FlowLayout(FlowLayout.CENTER));
        nameLabel.setFont(new Font("",Font.BOLD,36));
        nameLabel.setPreferredSize(new Dimension(450, 150));
        JLabel iconLabel = new JLabel(storeName);
        iconLabel.setIcon(new ImageIcon(storeImage(storeName,"associates")));
        iconLabel.setPreferredSize(new Dimension(storeImage(storeName,"associates").getWidth(),storeImage(storeName,"associates").getHeight()));
        storeAndName.add(Box.createRigidArea(new Dimension(400,50)));
        storeAndName.add(iconLabel);
        storeAndName.add(nameLabel);
        semiView.add(storeAndName);


        JPanel locationPanel = new JPanel();
        locationPanel.setPreferredSize(new Dimension(300,ComHard.LENGTH));
        locationPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        int height = 150;
        JPanel box = new JPanel();
        box.setBackground(null);
        box.setPreferredSize(new Dimension(400,height));
        locationPanel.add(box);
        JLabel locationLabel = new JLabel("Available Branches");
        locationLabel.setForeground(new Color(75,75,75));
        locationLabel.setFont(new Font("",Font.BOLD,36));
        locationPanel.add(locationLabel);
        locationPanel.add(Box.createRigidArea(new Dimension(400,35)));
        String[] stores = AssociatesPanel.fileContent().split("\n");
        JTextPane[] storeLocations = new JTextPane[stores.length];

        for(int i = 0, j = 1; i < stores.length; i++){
            String storeName = stores[i].split(":")[0];
            if(storeName.equals(this.storeName)){
                height-=25;
                storeLocations[i] = new JTextPane();
                storeLocations[i].setBackground(null);
                storeLocations[i].setText((j++)+".) "+stores[i].split(":")[1]);
                documentStyle = storeLocations[i].getStyledDocument();
                SimpleAttributeSet justifiedAttribute = new SimpleAttributeSet();
                StyleConstants.setAlignment(justifiedAttribute, StyleConstants.ALIGN_JUSTIFIED);
                documentStyle.setParagraphAttributes(0, documentStyle.getLength(), justifiedAttribute, false);
                storeLocations[i].setFocusable(false);
                storeLocations[i].setEditable(false);
                storeLocations[i].setLayout(new FlowLayout(FlowLayout.CENTER));
                storeLocations[i].setFont(new Font("",Font.BOLD,14));
                storeLocations[i].setPreferredSize(new Dimension(400, 70));
                locationPanel.add(storeLocations[i]);
                locationPanel.add(Box.createVerticalStrut(8));
            }
        }

        box.setPreferredSize(new Dimension(400,height));
        semiView.add(locationPanel);
        this.add(semiView,BorderLayout.CENTER);
    }
    public static BufferedImage storeImage(String storeName, String folder){
        storeName = storeName.trim();
        int width = 10, height = 10;
        BufferedImage bufferedImage = null;
        try{
            bufferedImage = ImageIO.read(new File("resources/"+folder+"/"+storeName+".jpg"));
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
    class BackButtonListener extends MouseAdapter{
        JPanel associatesPanel, semiAssociates, viewAssociates;

        public BackButtonListener(JPanel associatesPanel, JPanel semiAssociates, JPanel viewAssociates){

            this.associatesPanel = associatesPanel;
            this.semiAssociates = semiAssociates;
            this.viewAssociates = viewAssociates;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            associatesPanel.add(semiAssociates);
            semiAssociates.setVisible(true);
            viewAssociates.setVisible(false);
        }
    }
}
