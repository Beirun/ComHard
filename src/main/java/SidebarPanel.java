import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SidebarPanel extends JPanel implements ActionListener {
    JButton[] sidebarButtons = new JButton[5];
    String[] iconName = {"homeIcon", "accountIcon", "favoritesIcon", "associatesIcon", "signOutIcon"};
    String[] buttonNames = {"Dashboard","Account","Favorites","Associates","Sign Out"};
    int[] xCoordinateAdd = {2,15,7,0,15};
    JFrame frame;
    JPanel homePanel;
    JPanel accountPanel;
    JPanel favoritesPanel;
    JPanel associatesPanel;
    JPanel dashboardPanel;
    JPanel signPanel;
    JPanel[] panels = new JPanel[4];
    String userName;
    AccountLabel accountLabel;
    Color buttonFontColor = new Color(245, 245, 245);
    public SidebarPanel(JFrame frame, JPanel homePanel, JPanel accountPanel, JPanel favoritesPanel, JPanel associatesPanel, JPanel dashboardPanel, JPanel signPanel, String userName){
        this.frame = frame;
        this.homePanel = homePanel;
        this.accountPanel = accountPanel;
        this.favoritesPanel = favoritesPanel;
        this.associatesPanel = associatesPanel;
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
        panels[0] = homePanel;
        panels[1] = accountPanel;
        panels[2] = favoritesPanel;
        panels[3] = associatesPanel;
        this.add(new LogoClass(164,71,164,46, 0,15));
        accountLabel = new AccountLabel(userName,this,200,100,50,0,1,true);
        this.add(accountLabel);
        for (int i = 0; i< sidebarButtons.length; i++){
            int finalI = i;
            sidebarButtons[i] = new JButton(){
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    BufferedImage bufferedImage;
                    try {
                        bufferedImage = ImageIO.read(new File("resources/img/" + iconName[finalI] + ".png"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    g.drawImage(bufferedImage,75+xCoordinateAdd[finalI],34,bufferedImage.getWidth()/5,bufferedImage.getHeight()/5,null);
                    g.dispose();
                }
            };
            sidebarButtons[i].setText("  "+buttonNames[i]);
            sidebarButtons[i].setFont(new Font("", Font.BOLD, 20));
            sidebarButtons[i].setUI(new DisabledButton());
            sidebarButtons[i].setBorder(null);
            sidebarButtons[i].setAlignmentX(CENTER_ALIGNMENT);
            sidebarButtons[i].setPreferredSize(new Dimension(300, 89));
            sidebarButtons[i].setBackground(null);
            sidebarButtons[i].setFocusPainted(false);
            sidebarButtons[i].setForeground(buttonFontColor);
            //sidebarButtons[i].setIcon(new ImageIcon(new ImageIcon("resources/img/"+iconName[i]+".png").getImage().getScaledInstance(20,20,Image.SCALE_AREA_AVERAGING)));
            sidebarButtons[i].setIconTextGap(7);
            sidebarButtons[i].addActionListener(this);
            sidebarButtons[i].addMouseListener(new ChangeColorButton(sidebarButtons[i]));
            this.add(sidebarButtons[i]);
        }

    }
    public void isHomePanel(){
        sidebarButtons[0].setEnabled(false);
        sidebarButtons[0].setBackground(new Color(23,88,142));
    }
    public void isAccountPanel(){
        sidebarButtons[1].setEnabled(false);
        sidebarButtons[1].setBackground(new Color(23,88,142));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            for(int i = 0; i < sidebarButtons.length;i++){
                if (e.getSource() != sidebarButtons[i]) {
                    if(i!=4){
                        panels[i].setVisible(false);
                        dashboardPanel.remove(panels[i]);
                    }
                    sidebarButtons[i].setEnabled(true);
                    sidebarButtons[i].setBackground(null);
                } else {
                    if(i!=4) {
                        if(i==0) panels[i] = new HomePanel(dashboardPanel,userName);
                        else if(i==1)panels[i] = new AccountPanel(frame,signPanel,dashboardPanel,userName);
                        else if(i==2) panels[i] = new FavoritesPanel(sidebarButtons[0],this,userName);
                        dashboardPanel.add(panels[i], BorderLayout.CENTER);
                        panels[i].setVisible(true);
                    }else{
                        dashboardPanel.setVisible(false);
                        frame.add(new SignPanel(frame));
                    }
                    sidebarButtons[i].setEnabled(false);
                }
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
