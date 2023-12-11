import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {
    JFrame frame;
    JPanel signPanel;
    String userName;
    SidebarPanel sidebarPanel;
    AccountPanel accountPanel;
    HomePanel homePanel;
    FavoritesPanel favoritesPanel;
    AssociatesPanel associatesPanel;


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
        SwingUtilities.invokeLater(() -> {
                associatesPanel = new AssociatesPanel(this, userName);
                favoritesPanel = new FavoritesPanel(this,userName);
                homePanel = new HomePanel(this,userName);
                accountPanel = new AccountPanel(frame,signPanel,this,userName);
                sidebarPanel = new SidebarPanel(frame, homePanel,  accountPanel, favoritesPanel, associatesPanel, this, signPanel,userName);
                this.add(sidebarPanel,BorderLayout.WEST);
                this.add(homePanel,BorderLayout.CENTER);
                homePanel.setVisible(true);
                accountPanel.setVisible(false);
                favoritesPanel.setVisible(false);
                associatesPanel.setVisible(false);
            });

    }
}