import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {
    JFrame frame;
    JPanel signPanel;
    String userName;
    public DashboardPanel(JFrame frame, JPanel signPanel, String userName){
        this.signPanel = signPanel;
        this.setBounds(0,0,ComHard.WIDTH,ComHard.LENGTH);
        this.setBackground(new Color(162,221,164));
        this.setLayout(new BorderLayout());
        this.frame = frame;
        this.userName = userName;
        this.setLayout(new BorderLayout());
        panels();
    }

    public void panels(){
        this.add(new SidebarPanel(frame,this, this, signPanel,userName),BorderLayout.WEST);
    }
}