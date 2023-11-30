import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {
    JFrame frame;
    public DashboardPanel(JFrame frame){
        this.setBounds(0,0,ComHard.WIDTH,ComHard.LENGTH);
        this.setBackground(new Color(162,221,164));
        this.setLayout(new BorderLayout());
        this.frame = frame;
    }
}