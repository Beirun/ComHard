import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RegistrationOperation {

    String confirmPassword, password, userName, email;
    JFrame frame;
    JPanel panel;
    JPasswordField passwordField1, passwordField2;
    JTextField userField;
    File file = new File("resources/"+userName+".txt");
    public RegistrationOperation(JFrame frame, JPanel panel, JPasswordField passwordField1, JPasswordField passwordField2, JTextField userField,
                                 String userName, String email, String password, String confirmPassword){
        this.frame = frame;
        this.passwordField1 = passwordField1;
        this.passwordField2 = passwordField2;
        this.userField = userField;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.panel = panel;
    }

    public void fileCreator(){
        try {
            File userString = new File("resources/" + userName + ".txt");
            if(userString.exists()){
                //Error account already exists
                userField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red),
                        BorderFactory.createEmptyBorder(0,10,0,0)));

            }else {
                if(isPasswordCorrect()) {
                    FileWriter writer = new FileWriter(userString);
                    writer.write(email + "\n" + password);
                    writer.close();
                    frame.add(new LoginPanel(frame));
                    panel.setVisible(false);

                }
                else{
                    //Error password does not match
                    passwordField1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red),
                            BorderFactory.createEmptyBorder(0,10,0,0)));
                    passwordField2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red),
                            BorderFactory.createEmptyBorder(0,10,0,0)));
                }
            }
        }catch (IOException ignored){}
    }
    public boolean isPasswordCorrect(){
        return password.equals(confirmPassword);
    }

}
