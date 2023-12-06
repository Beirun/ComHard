import javax.swing.*;
import java.awt.*;

public class AccountPanel extends JPanel {
    JPanel dashboardPanel;
    String userName;
    public AccountPanel(JPanel dashboardPanel, String userName){
        this.dashboardPanel = dashboardPanel;
        this.userName = userName;
        this.setPreferredSize(new Dimension(ComHard.WIDTH,ComHard.LENGTH));
        this.setBackground(new Color(0,245,245));

    }

}
