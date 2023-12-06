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
        this.frame = frame;
        this.userName = userName;
        this.setLayout(new BorderLayout());
        panels();
    }

    public void panels(){
        HomePanel homePanel = new HomePanel(this,userName);
        AccountPanel accountPanel = new AccountPanel(this, userName);
        SidebarPanel sidebarPanel = new SidebarPanel(frame, homePanel,  accountPanel,this, signPanel,userName);
        this.add(sidebarPanel,BorderLayout.WEST);
        this.add(homePanel,BorderLayout.CENTER);
    }
}