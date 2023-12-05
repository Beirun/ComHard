import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class SidebarPanel extends JPanel implements ActionListener {
    JButton home, logout;
    JFrame frame;
    JPanel homePanel, dashboardPanel;
    JLabel account;
    ImageIcon accountProfile;
    String userName;
    public SidebarPanel(JFrame frame, JPanel homePanel, JPanel dashboardPanel, String userName){
        this.frame = frame;
        this.homePanel = homePanel;
        this.dashboardPanel = dashboardPanel;
        this.userName = userName;
        this.setBackground(new Color(200,215,215));
        this.setPreferredSize(new Dimension(ComHard.WIDTH/3,ComHard.LENGTH));
    }

    public void accountLabel(){
        account = new JLabel();
        account.setIcon(accountProfile);
        account.setText(userName);
    }
    public void listButtons(){
        accountLabel();
        this.add(account);

        home = new JButton("Home");
        home.setBorder(null);
        home.setBackground(null);
        home.setFocusPainted(false);
        home.addMouseListener(new ListenerClasses.UnderlinedText(home.getText(),home));

        logout = new JButton("Logout");
        logout.setBorder(null);
        logout.setBackground(null);
        logout.setFocusPainted(false);
        logout.addMouseListener(new ListenerClasses.UnderlinedText(home.getText(),home));

        this.add(home);
        this.add(Box.createVerticalStrut(15));
        this.add(logout);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==home){

        }if(e.getSource()==logout){
            //frame.add
            frame.add(null);
            dashboardPanel.setVisible(false);
        }
    }

    class ChangeColorButton extends MouseAdapter{
        public ChangeColorButton(){

        }

    }
}
