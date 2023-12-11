import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LogoClass extends JPanel {
    BufferedImage logoImage;
    int logoWidth, logoHeight, xCoordinate, yCoordinate;
    public LogoClass(int width, int height, int LogoWidth, int LogoHeight, int xCoordinate, int yCoordinate){
        logoWidth = LogoWidth;
        logoHeight = LogoHeight;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.setBackground(null);
        this.setPreferredSize(new Dimension(width,height));}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderLogo(g);
    }
    public void renderLogo(Graphics g){
        try {
            logoImage = ImageIO.read(new File("resources/img/fullLogo.png"));
            g.drawImage(logoImage,xCoordinate,yCoordinate,logoWidth,logoHeight,this);
            g.dispose();
        } catch (IOException ignored) {}
    }
}
