import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AccountLabel extends JLabel {
    BufferedImage bufferedImage;


    public AccountLabel(String userName){
        this.add(imageSection());
        this.setText(userName);
        this.setPreferredSize(new Dimension(100,166));
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setFont(new Font("", Font.BOLD, 30));
        this.setVerticalTextPosition(JLabel.BOTTOM);
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setIconTextGap(5);
        //this.setIcon(new ImageIcon(imageIcon()));
    }

    public JPanel imageSection(){
        JPanel panel = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(imageIcon(),0,0,null);
            }
        };
        panel.setVisible(true);
        panel.setBackground(new Color(255,255,255));
        return panel;
    }

    public BufferedImage imageIcon(){
        try {
            bufferedImage = ImageIO.read(new File("resources/img/whistle.jpg"));
        }catch (IOException ignored){}
        int diameter = Math.min(bufferedImage.getWidth(),bufferedImage.getHeight());
        diameter = 100;
        BufferedImage circleBuffer = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = circleBuffer.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(51,51,51));
        g2.setStroke(new BasicStroke(10));
        g2.setClip(new Ellipse2D.Float(0, 0, diameter, diameter));
        g2.drawImage(bufferedImage, 0, 5, diameter+20, diameter, null);
        g2.draw(new Ellipse2D.Float(0, 0, diameter, diameter));
        g2.dispose();
        return circleBuffer;
    }
}
