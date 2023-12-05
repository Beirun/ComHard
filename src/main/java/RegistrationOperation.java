import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RegistrationOperation {

    String confirmPassword, password, userName, email;
    JFrame frame;
    JPanel panel, signPanel;
    JPasswordField passwordField;
    JPasswordField confirmPasswordField;
    JTextField userField, emailField;
    File file = new File("resources/"+userName+".txt");
    public RegistrationOperation(JFrame frame, JPanel signPanel, JPanel panel, JPasswordField passwordField, JPasswordField confirmPasswordField, JTextField userField,
                                 JTextField emailField, String userName, String email, String password, String confirmPassword){
        this.frame = frame;
        this.signPanel = signPanel;
        this.passwordField = passwordField;
        this.confirmPasswordField = confirmPasswordField;
        this.userField = userField;
        this.emailField = emailField;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.panel = panel;
    }

    public void fileCreator(){
        try {
            File userString = new File("assets/info/" + userName + ".txt");
            if(emailField.getText().isEmpty()){
                emailField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red),
                        BorderFactory.createEmptyBorder(0,10,0,0)));
            }if(password.isEmpty()){
                passwordField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red),
                        BorderFactory.createEmptyBorder(0,10,0,0)));
            }if(confirmPassword.isEmpty()){
                confirmPasswordField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red),
                        BorderFactory.createEmptyBorder(0,10,0,0)));
            } if(userField.getText().isEmpty()){
                userField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red),
                        BorderFactory.createEmptyBorder(0,10,0,0)));
            } else if(userString.exists()){
                //Error account already exists
                userField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red),
                        BorderFactory.createEmptyBorder(0,10,0,0)));

            }else {
                if(isPasswordCorrect()) {
                    FileWriter writer = new FileWriter(userString);
                    writer.write(email + "\n" + password);
                    writer.close();
                    signPanel.add(new LoginPanel(frame,signPanel), BorderLayout.EAST);
                    panel.setVisible(false);

                }
                else{
                    //Error password does not match
                    passwordField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red),
                            BorderFactory.createEmptyBorder(0,10,0,0)));
                    confirmPasswordField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red),
                            BorderFactory.createEmptyBorder(0,10,0,0)));
                }
            }
        }catch (IOException ignored){}
    }
    public boolean isPasswordCorrect(){
        return password.equals(confirmPassword);
    }

}
