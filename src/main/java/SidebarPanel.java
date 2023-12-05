import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class SidebarPanel extends JPanel implements ActionListener {
    JButton home, logout;
    JFrame frame;
    JPanel homePanel, dashboardPanel, signPanel;
    JPanel buttonPanel;
    JLabel account;
    ImageIcon accountProfile;
    String userName;
    public SidebarPanel(JFrame frame, JPanel homePanel, JPanel dashboardPanel, JPanel signPanel, String userName){
        this.frame = frame;
        this.homePanel = homePanel;
        this.dashboardPanel = dashboardPanel;
        this.signPanel = signPanel;
        this.userName = userName;
        this.setBackground(new Color(200,215,215));
        this.setPreferredSize(new Dimension(ComHard.WIDTH/6,ComHard.LENGTH));
        this.setVisible(true);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        listButtons();
    }

    public void accountLabel(){
        account = new JLabel();
        account.setIcon(accountProfile);
        account.setText(userName);
        account.setFont(new Font("", Font.BOLD, 30));
    }
    public void listButtons(){
        accountLabel();
        this.add(Box.createVerticalStrut(50));
        this.add(account);
        this.add(Box.createVerticalStrut(50));
        buttonPanel = new JPanel();
        buttonPanel.setAlignmentX(CENTER_ALIGNMENT);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(null);

        home = new JButton("Home");
        home.setFont(new Font("", Font.BOLD, 20));
        home.setBorder(null);
        home.setAlignmentX(CENTER_ALIGNMENT);
        home.setPreferredSize(new Dimension(home.getWidth(),home.getHeight()+10));
        home.setBackground(null);
        home.setFocusPainted(false);
        home.addMouseListener(new ListenerClasses.UnderlinedText(home.getText(),home));
        home.addActionListener(this);

        logout = new JButton("Logout");
        logout.setFont(new Font("", Font.BOLD, 20));
        logout.setBorder(null);
        logout.setAlignmentX(CENTER_ALIGNMENT);
        logout.setBackground(null);
        logout.setPreferredSize(new Dimension(logout.getWidth(),logout.getHeight()+10));
        logout.setFocusPainted(false);
        logout.addMouseListener(new ListenerClasses.UnderlinedText(logout.getText(),logout));
        logout.addActionListener(this);

        buttonPanel.add(home);
        buttonPanel.add(Box.createVerticalStrut(45));
        buttonPanel.add(logout);
        this.add(buttonPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==home){

        }if(e.getSource()==logout){
            //frame.add
            frame.add(new SignPanel(frame));
            dashboardPanel.setVisible(false);
        }
    }

    class ChangeColorButton extends MouseAdapter{
        public ChangeColorButton(){

        }

    }
}
