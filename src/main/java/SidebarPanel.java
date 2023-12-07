import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SidebarPanel extends JPanel implements ActionListener {
    JButton[] sidebarButtons = new JButton[5];
    String[] buttonNames = {"Dashboard","Account","Favorites","Associates","Sign Out"};
    JFrame frame;
    JPanel homePanel, accountPanel, dashboardPanel, signPanel;
    String userName;
    Color buttonFontColor = new Color(245, 245, 245);
    public SidebarPanel(JFrame frame, JPanel homePanel, JPanel accountPanel, JPanel dashboardPanel, JPanel signPanel, String userName){
        this.frame = frame;
        this.homePanel = homePanel;
        this.accountPanel = accountPanel;
        this.dashboardPanel = dashboardPanel;
        this.signPanel = signPanel;
        this.userName = userName;
        this.setBackground(new Color(53,118,172));
        this.setPreferredSize(new Dimension(ComHard.WIDTH/6,ComHard.LENGTH));
        this.setVisible(true);
        this.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        listButtons();
    }
    public void listButtons(){
        this.add(new LogoClass(164,51,164,46, 0,5));
        this.add(new AccountLabel(userName));
        for (int i = 0; i< sidebarButtons.length; i++){
            sidebarButtons[i] = new JButton();
            sidebarButtons[i].setText(buttonNames[i]);
            sidebarButtons[i].setFont(new Font("", Font.BOLD, 20));
            sidebarButtons[i].setUI(new DisabledButton());
            sidebarButtons[i].setBorder(null);
            sidebarButtons[i].setAlignmentX(CENTER_ALIGNMENT);
            sidebarButtons[i].setPreferredSize(new Dimension(300, 90));
            sidebarButtons[i].setBackground(null);
            sidebarButtons[i].setFocusPainted(false);
            sidebarButtons[i].setForeground(buttonFontColor);
            sidebarButtons[i].addActionListener(this);
            sidebarButtons[i].addMouseListener(new ChangeColorButton(sidebarButtons[i]));
            this.add(sidebarButtons[i]);
        }
        sidebarButtons[0].setBackground(new Color(23,88,142));
        sidebarButtons[0].setEnabled(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==sidebarButtons[0]){
            homePanel.setVisible(true);
            accountPanel.setVisible(false);
            sidebarButtons[0].setEnabled(false);
            sidebarButtons[1].setEnabled(true);
            sidebarButtons[1].setBackground(null);
        }if(e.getSource()== sidebarButtons[4]){
            frame.add(new SignPanel(frame));
            dashboardPanel.setVisible(false);
        }if(e.getSource()==sidebarButtons[1]){
            homePanel.setVisible(false);
            dashboardPanel.add(accountPanel, BorderLayout.CENTER);
            accountPanel.setVisible(true);
            sidebarButtons[1].setEnabled(false);
            sidebarButtons[0].setEnabled(true);
            sidebarButtons[0].setBackground(null);
        }
    }
    class DisabledButton extends MetalButtonUI{
        @Override
        protected Color getDisabledTextColor() {return buttonFontColor;}
        @Override
        protected void paintButtonPressed(Graphics g, AbstractButton b) {}
    }

    static class ChangeColorButton extends MouseAdapter{
        JButton button;
        public ChangeColorButton(JButton button){this.button = button;}
        @Override
        public void mouseClicked(MouseEvent e) {button.setBackground(new Color(23,88,142));}
        @Override
        public void mouseEntered(MouseEvent e) {if(button.isEnabled()) button.setBackground(new Color(43,108,162));}
        @Override
        public void mouseExited(MouseEvent e) {if(button.isEnabled()) button.setBackground(null);}
    }
}
