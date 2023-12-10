import javax.swing.*;
import java.awt.*;
import java.io.*;

public class LoginOperation {
    String userName, password, passwordEntered;
    JFrame frame;
    JPanel panel;
    JTextField userField;
    JPasswordField passField;
    DuplicateClasses.UserFile user;
    public LoginOperation(JFrame frame, JPanel panel, JTextField userField,
                          JPasswordField passField, String userName, String passwordEntered){
        this.frame = frame;
        this.panel = panel;
        this.userField = userField;
        this.passField = passField;
        this.userName = userName;
        this.passwordEntered = passwordEntered;
    }

    public void fileReader(){
        user = new DuplicateClasses.UserFile(userName);
        File fileCreated = new File("assets/info/"+userName+".txt");
        if(!fileCreated.exists()){
            //Error no account
            userField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED),
                    BorderFactory.createEmptyBorder(0,10,0,0)));
            passField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED),
                    BorderFactory.createEmptyBorder(0,10,0,0)));
        }else {
            password = user.getPassword(user.fileContent());
            if(isPasswordCorrect()){
                frame.add(new DashboardPanel(frame,panel,user.getUserName(user.fileContent())));
                panel.setVisible(false);
            }else{
                userField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED),
                        BorderFactory.createEmptyBorder(0,10,0,0)));
                passField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED),
                        BorderFactory.createEmptyBorder(0,10,0,0)));
            }
        }
    }
    public boolean isPasswordCorrect(){
        return passwordEntered.equals(password);
    }
}
