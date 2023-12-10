import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UpdatePasswordOperation {
    String[] content;
    String userName;
    JPasswordField currentPasswordField;
    JPasswordField newPasswordField;
    JPasswordField confirmPasswordField;
    JFrame frame;
    JPanel panel;
    JPanel signPanel;
    JPanel dashboardPanel;
    DuplicateClasses.UserFile user;
    public UpdatePasswordOperation(String[] content, String userName, JFrame frame, JPanel panel, JPanel signPanel, JPanel dashboardPanel,
                                   JPasswordField currentPasswordField, JPasswordField newPasswordField, JPasswordField confirmPasswordField){
        this.content = content;
        this.userName = userName;
        this.frame = frame;
        this.panel = panel;
        this.signPanel = signPanel;
        this.dashboardPanel = dashboardPanel;
        this.currentPasswordField = currentPasswordField;
        this.newPasswordField = newPasswordField;
        this.confirmPasswordField = confirmPasswordField;
        passwordCheck();
    }
    public void passwordCheck(){
        String newPassword = DuplicateClasses.toString(newPasswordField.getPassword());
        String confirmPassword = DuplicateClasses.toString(confirmPasswordField.getPassword());
        user = new DuplicateClasses.UserFile(userName);
        if(newPassword.isEmpty()){
            newPasswordField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder
                    (0,0,2,0,Color.RED),BorderFactory.createEmptyBorder(0,5,0,0)));
        }if(confirmPassword.isEmpty()){
            confirmPasswordField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder
                    (0,0,2,0,Color.RED),BorderFactory.createEmptyBorder(0,5,0,0)));
        }
        if(!(newPassword.equals(confirmPassword))){
            newPasswordField.setForeground(Color.RED);
            confirmPasswordField.setForeground(Color.RED);
            newPasswordField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder
                    (0,0,2,0,Color.RED),BorderFactory.createEmptyBorder(0,5,0,0)));
            confirmPasswordField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder
                    (0,0,2,0,Color.RED),BorderFactory.createEmptyBorder(0,5,0,0)));
        }
        if(user.getPassword(content).equals(DuplicateClasses.toString(currentPasswordField.getPassword()))){
            if(newPassword.length()>=8 || newPassword.equals(confirmPassword)){
                try{
                    File fileCreated = new File("assets/info/"+userName+".txt");
                    FileWriter writer = new FileWriter(fileCreated);
                    writer.write(userName+"\n"+user.getEmail(content)+"\n"+newPassword+"\n"+user.getDateCreated(content));
                    writer.close();
                    panel.setVisible(false);
                    dashboardPanel.add(new AccountPanel(frame,signPanel,dashboardPanel,userName), BorderLayout.CENTER);
                }catch (IOException ignored){}
            }else{
                //Error password does not match or less than 8
                newPasswordField.setForeground(Color.RED);
                confirmPasswordField.setForeground(Color.RED);
                newPasswordField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder
                        (0,0,2,0,Color.RED),BorderFactory.createEmptyBorder(0,5,0,0)));
                confirmPasswordField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder
                        (0,0,2,0,Color.RED),BorderFactory.createEmptyBorder(0,5,0,0)));
            }
        }
        else{
            //Error incorrect password
            currentPasswordField.setForeground(Color.RED);
            currentPasswordField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder
                    (0,0,2,0,Color.RED),BorderFactory.createEmptyBorder(0,5,0,0)));
        }
    }
}
