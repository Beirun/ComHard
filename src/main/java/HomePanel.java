import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HomePanel extends JPanel {
    JPanel dashboardPanel;
    String userName;
    BufferedImage backgroundImage;

    public HomePanel(JPanel dashboardPanel, String userName){
        this.dashboardPanel = dashboardPanel;
        this.userName = userName;
        this.setPreferredSize(new Dimension(ComHard.WIDTH,ComHard.LENGTH));
        this.setBackground(new Color(245,245,245));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        backGround(g);
    }
    public void backGround(Graphics g){
        try {
            backgroundImage = ImageIO.read(new File("resources/img/dashboard.jpg"));
            Graphics2D g2 = backgroundImage.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.drawImage(backgroundImage,0,0,backgroundImage.getWidth()/2, backgroundImage.getHeight()/2,this);
            g.dispose();
            g2.dispose();
        } catch (IOException ignored) {}
    }
}
