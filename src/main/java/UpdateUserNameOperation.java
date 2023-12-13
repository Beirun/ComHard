import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class UpdateUserNameOperation extends KeyAdapter {
    String userName;
    JPanel box;
    JPanel dashboardPanel, userPanel;
    DuplicateClasses.UserFile user;
    JFrame frame;
    JPanel signPanel;
    boolean isKeyPressed = false;
    JTextField userNameField;
    public UpdateUserNameOperation(JFrame frame, JPanel dashboardPanel, JPanel userPanel, JPanel signPanel, JPanel box, JTextField userNameField, String userName){
        this.frame = frame;
        this.userPanel = userPanel;
        this.signPanel = signPanel;
        this.box = box;
        this.userNameField = userNameField;
        this.userName = userName;
        this.dashboardPanel = dashboardPanel;
    }
    public void userNameCheck(){
        try {
            File newUser = new File("assets/info/" + userNameField.getText() + ".txt");
            if(newUser.exists() || userNameField.getText().isEmpty()){
                userNameField.setForeground(Color.RED);
                userNameField.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED),
                        BorderFactory.createEmptyBorder(0, 5, 0, 0)));
            }else {
                user = new DuplicateClasses.UserFile(userName);
                FileWriter writer = new FileWriter(newUser);
                writer.write(userNameField.getText() + "\n" + user.getEmail(user.fileContent()) +
                        "\n" + user.getPassword(user.fileContent()) + "\n" +
                        user.getDateCreated(user.fileContent()));
                writer.close();
                dashboardPanel.setVisible(false);
                DashboardPanel newDashboardPanel = new DashboardPanel(frame,signPanel,userNameField.getText());
                frame.add(new DashboardPanel(frame,signPanel,userNameField.getText()));
                newDashboardPanel.homePanel.setVisible(false);
                AccountPanel accountPanel = new AccountPanel(frame,signPanel, newDashboardPanel,userNameField.getText());
                newDashboardPanel.sidebarPanel.setVisible(false);
                SidebarPanel newSideBarPanel = new SidebarPanel(frame, newDashboardPanel.homePanel,accountPanel,newDashboardPanel.favoritesPanel,newDashboardPanel.associatesPanel,newDashboardPanel,signPanel,userNameField.getText());
                newSideBarPanel.sidebarButtons[0].setEnabled(true);
                newSideBarPanel.sidebarButtons[0].setBackground(null);
                newSideBarPanel.sidebarButtons[1].setBackground(new Color(23,88,142));
                newSideBarPanel.sidebarButtons[1].setEnabled(false);
                newDashboardPanel.add(newSideBarPanel,BorderLayout.WEST);
                newDashboardPanel.add(accountPanel,BorderLayout.CENTER);
                File oldUser = new File("assets/info/"+userName+".txt");
                Files.delete(Paths.get(oldUser.getPath()));
                String[] fileExtension = {".png",".jpeg",".jpg"};
                for(int i = 0; i < fileExtension.length; i++){
                    File imageFile = new File("resources/profiles/"+userName+fileExtension[i]);
                    if(imageFile.exists()){
                        //imageFile.renameTo(new File("resources/profiles/"+userNameField.getText()+fileExtension[i]));
                        Files.move(Paths.get(imageFile.getPath()),Paths.get("resources/profiles/"+userNameField.getText()+fileExtension[i]), StandardCopyOption.REPLACE_EXISTING);
                    }
                }
            }
        }catch (IOException ignored){}
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER) userNameCheck();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(!isKeyPressed && e.getKeyCode()!=KeyEvent.VK_ENTER && e.getKeyCode()!=KeyEvent.VK_BACK_SPACE) {
            Graphics graphics = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).getGraphics();
            graphics.setFont(new Font("", Font.BOLD, 32));
            FontMetrics fontMetrics = graphics.getFontMetrics();
            graphics.dispose();
            int userNameFieldWidth = fontMetrics.stringWidth(userNameField.getText()) + 25, textFieldHeight = 22;
            userNameField.setMaximumSize(new Dimension(userNameFieldWidth, textFieldHeight + 25));
            userNameField.setMinimumSize(new Dimension(userNameFieldWidth, textFieldHeight + 25));
            userNameField.setPreferredSize(new Dimension(userNameFieldWidth, textFieldHeight + 25));
            userNameField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 1, 0, userNameField.getForeground()),
                    BorderFactory.createEmptyBorder(0, 10, 0, 0)));
            box.setMaximumSize(new Dimension(510 - userNameFieldWidth / 2, 5));
            box.setPreferredSize(new Dimension(510 - userNameFieldWidth / 2, 5));
            box.setMinimumSize(new Dimension(510 - userNameFieldWidth / 2, 5));
            box.validate();
            box.repaint();
            userPanel.validate();
            userPanel.repaint();
            isKeyPressed = true;
        }

    }
}
