import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HomePanel extends JPanel {
    JPanel dashboardPanel;
    JPanel searchDialog;
    JLabel backgroundLabel;
    JComboBox<String> purpose, budget;
    String userName;
    BufferedImage backgroundImage;

    public HomePanel(JPanel dashboardPanel, String userName){
        this.dashboardPanel = dashboardPanel;
        this.userName = userName;
        this.setPreferredSize(new Dimension(ComHard.WIDTH,ComHard.LENGTH));
        this.setBackground(new Color(245,245,245));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        searchBox();
    }

   /* @Override
    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
        backGround(g);
    }*/
    public void backGround(Graphics g){
        try {
            backgroundImage = ImageIO.read(new File("resources/img/dashboard.jpg"));
            g.drawImage(backgroundImage,0,0,backgroundImage.getWidth()/2, backgroundImage.getHeight()/2,this);
            g.dispose();
        } catch (IOException ignored) {}
    }

    public void searchBox(){
        backgroundLabel = new JLabel();
        backgroundLabel.setIcon(new ImageIcon("resources/img/dashboard.jpg"));

        searchDialog = new JPanel();
        searchDialog.setBackground(new Color(200,200,200));
        searchDialog.setPreferredSize(new Dimension(190,200));

        purpose = new JComboBox<>();

        purpose.addItem("Test");
        purpose.addItem("Test");
        purpose.addItem("Test");

        JButton button = new JButton("Button");
        button.addActionListener(e -> {
            if(e.getSource()==button){
                dashboardPanel.add(new ItemListPanel(dashboardPanel,userName),BorderLayout.CENTER);
                this.setVisible(false);
            }
        });
        searchDialog.add(button);
        searchDialog.add(purpose);
        backgroundLabel.add(searchDialog);
        this.add(backgroundLabel);

    }
}
