import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BackgroundClass extends JPanel {
    final int panelWidth = ComHard.WIDTH/3+125;
    BufferedImage backgroundImage;
    public BackgroundClass() {
        this.setBackground(new Color(236,236,236));
        this.setPreferredSize(new Dimension(panelWidth,ComHard.LENGTH));
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderImage(g);
    }
    public void renderImage(Graphics g){
        try {
            backgroundImage = ImageIO.read(new File("resources/img/background.png"));
            //maintain aspect ratio
            /*
            if(backgroundImage.getWidth()>getWidth()) g.drawImage(backgroundImage,(getWidth()-backgroundImage.getWidth())/2,
                    0,backgroundImage.getWidth(),getHeight(), this);
            else g.drawImage(backgroundImage, (getWidth() - backgroundImage.getWidth()) / 2, 0, getWidth(),
                    backgroundImage.getHeight(), this);*/
            g.drawImage(backgroundImage,-20,-15,backgroundImage.getWidth()/2-100, backgroundImage.getHeight()/2-100,this);
        } catch (IOException ignored) {}
    }

}
