import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class SidebarPanel extends JPanel implements ActionListener {
    JButton home, account, logout;
    JFrame frame;
    JPanel homePanel, accountPanel, dashboardPanel, signPanel;
    JPanel buttonPanel;
    JLabel accountLabel;
    ImageIcon accountProfile;
    String userName;
    public SidebarPanel(JFrame frame, JPanel homePanel, JPanel accountPanel, JPanel dashboardPanel, JPanel signPanel, String userName){
        this.frame = frame;
        this.homePanel = homePanel;
        this.accountPanel = accountPanel;
        this.dashboardPanel = dashboardPanel;
        this.signPanel = signPanel;
        this.userName = userName;
        this.setBackground(new Color(215,215,215));
        this.setPreferredSize(new Dimension(ComHard.WIDTH/6,ComHard.LENGTH));
        this.setVisible(true);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        listButtons();
    }

    public void accountLabel(){
        accountLabel = new JLabel();
        accountLabel.setIcon(accountProfile);
        accountLabel.setText(userName);
        accountLabel.setFont(new Font("", Font.BOLD, 30));
    }
    public void listButtons(){
        accountLabel();
        this.add(Box.createVerticalStrut(50));
        this.add(accountLabel);
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

        account = new JButton("Account");
        account.setFont(new Font("", Font.BOLD, 20));
        account.setBorder(null);
        account.setAlignmentX(CENTER_ALIGNMENT);
        account.setPreferredSize(new Dimension(account.getWidth(),account.getHeight()+10));
        account.setBackground(null);
        account.setFocusPainted(false);
        account.addMouseListener(new ListenerClasses.UnderlinedText(account.getText(),account));
        account.addActionListener(this);

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
        buttonPanel.add(account);
        buttonPanel.add(Box.createVerticalStrut(45));
        buttonPanel.add(logout);
        this.add(buttonPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==home){
            //dashboardPanel.add()
            homePanel.setVisible(true);
            accountPanel.setVisible(false);
        }if(e.getSource()==logout){
            frame.add(new SignPanel(frame));
            dashboardPanel.setVisible(false);
        }if(e.getSource()==account){
            homePanel.setVisible(false);
            dashboardPanel.add(accountPanel, BorderLayout.CENTER);
            accountPanel.setVisible(true);
        }

    }

    class ChangeColorButton extends MouseAdapter{
        public ChangeColorButton(){

        }

    }
}
