import javax.swing.*;
import java.awt.*;

public class FavoritesPanel extends JPanel {
    JPanel dashboardPanel;
    String userName;

    public FavoritesPanel(JPanel dashboardPanel, String userName){
        this.dashboardPanel = dashboardPanel;
        this.userName = userName;
        this.setPreferredSize(new Dimension(ComHard.WIDTH,ComHard.LENGTH));
        this.setBackground(new Color(245,245,245));
    }
}