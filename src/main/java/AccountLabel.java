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
    String userName;
    Color color = new Color(51,51,51);

    public AccountLabel(String userName, JPanel panel,int panelWidth, int panelHeight, int xCoordinate, int yCoordinate, int imageRatio, boolean isSidebar){
        this.panel = panel;
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.userName = userName;
        this.imageRatio = imageRatio*5;
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setText(userName);
        this.setBackground(Color.GREEN);
        this.setFont(new Font("", Font.BOLD, 25));
        panel.add(imageSection());
        if(isSidebar){
            Graphics graphics = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).getGraphics();
            graphics.setFont(new Font("",Font.BOLD,25));
            FontMetrics fontMetrics = graphics.getFontMetrics();
            int textWidth = fontMetrics.stringWidth(userName)+5, fontSize = 25;
            while(textWidth>200) {
                graphics.setFont(new Font("",Font.BOLD,fontSize--));
                fontMetrics = graphics.getFontMetrics();
                textWidth = fontMetrics.stringWidth(userName);
            }
            this.setFont(new Font("",Font.BOLD,fontSize));
            this.setPreferredSize(new Dimension(textWidth,50));
            this.setMinimumSize(new Dimension(textWidth,50));
            this.setMaximumSize(new Dimension(textWidth,50));
            panel.add(this);
        }
    }
    public JPanel imageSection(){
        JPanel panel = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.drawImage(imageIcon(),xCoordinate,yCoordinate,imageIcon().getWidth()/5,imageIcon().getHeight()/5,null);
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

    public void setCircularColor(Color color){

        this.color = color;
    }
    public BufferedImage imageIcon(){
        try {
            String[] fileExtension = {".png",".jpeg",".jpg"};
            boolean isBufferedImageNull = true;
            for(int i = 0; i < fileExtension.length; i++){
                if(new File("resources/profiles/"+userName+fileExtension[i]).exists()){
                    bufferedImage = ImageIO.read(new File("resources/profiles/"+userName+fileExtension[i]));
                    isBufferedImageNull = false;
                }
            }
            if (isBufferedImageNull) bufferedImage = ImageIO.read(new File("resources/profiles/default.jpg"));

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
        if(width/imageRatio==100 && height/imageRatio==100) g2.drawImage(bufferedImage,0,0,width,height,null);
        else if(width/imageRatio==100) g2.drawImage(bufferedImage, 7*imageRatio-imageRatio*2,
                (-height/2+diameter/2)+2*imageRatio, width-7*imageRatio, height-7*imageRatio, null);
        else if(height/imageRatio==100) g2.drawImage(bufferedImage, (-width/2+diameter/2)+2*imageRatio,
                7*imageRatio-imageRatio*2, width-7*imageRatio, height-7*imageRatio, null);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.draw(new Ellipse2D.Float(0, 0, diameter, diameter));
        g2.setColor(color);
        g2.setStroke(new BasicStroke(7*imageRatio));
        g2.draw(new Ellipse2D.Float(0, 0, diameter, diameter));
        g2.dispose();
        return circleBuffer;
    }
}
