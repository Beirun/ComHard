import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AccountLabel extends JLabel {
    BufferedImage bufferedImage;
    JPanel panel;
    int panelWidth, panelHeight, xCoordinate, yCoordinate, imageRatio;



    public AccountLabel(String userName, JPanel panel,int panelWidth, int panelHeight, int xCoordinate, int yCoordinate, int imageRatio, boolean isSidebar){
        this.panel = panel;
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.imageRatio = imageRatio;
        this.setPreferredSize(new Dimension(80,50));
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setText(userName);
        this.setFont(new Font("", Font.BOLD, 30));
        panel.add(imageSection());
        if(isSidebar) panel.add(this);
    }
    public void profileSection(){

    }

    public JPanel imageSection(){
        JPanel panel = new JPanel(){

            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.drawImage(imageIcon(),xCoordinate,yCoordinate,null);
            }
        };
        panel.setPreferredSize(new Dimension(panelWidth,panelHeight));
        panel.setMaximumSize(new Dimension(panelWidth,panelHeight));
        panel.setMinimumSize(new Dimension(panelWidth,panelHeight));
        panel.setVisible(true);
        panel.setBackground(null);
        panel.setAlignmentX(CENTER_ALIGNMENT);
        return panel;
    }

    public BufferedImage imageIcon(){
        try {
            bufferedImage = ImageIO.read(new File("resources/img/whistle.jpg"));
        }catch (IOException ignored){}
        int diameter = 100, width, height;
        double ratio;
        if(Math.min(bufferedImage.getWidth(),bufferedImage.getHeight()) == bufferedImage.getWidth()){
            width = 100;
            ratio = 100.0/((float)(bufferedImage.getWidth()));
            height = (int) Math.floor(bufferedImage.getHeight() * ratio);
        }else {
            height = 100;
            ratio = 100.0/((float)(bufferedImage.getHeight()));
            width = (int) Math.floor(bufferedImage.getWidth() * ratio);
        }
        diameter*=imageRatio;
        width*=imageRatio;
        height*=imageRatio;
        BufferedImage circleBuffer = new BufferedImage(width, height , BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = circleBuffer.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        g2.setColor(new Color(230,230,230));
        g2.setStroke(new BasicStroke(10*imageRatio));
        g2.setClip(new Ellipse2D.Float(0, 0, diameter, diameter));
        g2.drawImage(bufferedImage, 0, 5, width, height, null);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.draw(new Ellipse2D.Float(0, 0, diameter, diameter));
        g2.setColor(new Color(51,51,51));
        g2.setStroke(new BasicStroke(7*imageRatio));
        g2.draw(new Ellipse2D.Float(0, 0, diameter, diameter));
        g2.dispose();
        return circleBuffer;
    }
}
