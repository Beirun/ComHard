import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LogoClass extends JPanel {
    BufferedImage logoImage;
    public LogoClass(){
        this.setBackground(new Color(236,236,236));
        this.setPreferredSize(new Dimension(ComHard.WIDTH/2-200,300));}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderLogo(g);
    }
    public void renderLogo(Graphics g){
        try {
            logoImage = ImageIO.read(new File("resources/img/fullLogo.png"));
            g.drawImage(logoImage,69,50,logoImage.getWidth()/5,logoImage.getHeight()/5,this);
        } catch (IOException ignored) {}
    }
}
