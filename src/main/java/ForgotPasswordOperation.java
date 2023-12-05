import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ForgotPasswordOperation {
    String userName, newPassword, tempString, confirmPassword, emailEntered, email;
    JFrame frame;
    JPanel panel, signPanel;
    JTextField userField, emailField;
    JPasswordField newPasswordField, confirmPasswordField;

    public ForgotPasswordOperation(JFrame frame, JPanel signPanel, JPanel panel, JTextField userField, JTextField emailField, JPasswordField newPasswordField,
                                   JPasswordField confirmPasswordField, String userName, String emailEntered, String newPassword, String confirmPassword){
        this.frame = frame;
        this.signPanel = signPanel;
        this.panel = panel;
        this.userField = userField;
        this.emailField = emailField;
        this.newPasswordField = newPasswordField;
        this.confirmPasswordField = confirmPasswordField;
        this.userName = userName;
        this.emailEntered = emailEntered;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }
    public void fileReader(){
        try {
            File fileCreated = new File("assets/info/"+userName+".txt");
            if(emailField.getText().isEmpty()){
                emailField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red),
                        BorderFactory.createEmptyBorder(0,10,0,0)));
            }if(newPassword.isEmpty()){
                newPasswordField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red),
                        BorderFactory.createEmptyBorder(0,10,0,0)));
            }if(confirmPassword.isEmpty()){
                confirmPasswordField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red),
                        BorderFactory.createEmptyBorder(0,10,0,0)));
            } if(userField.getText().isEmpty()){
                userField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red),
                        BorderFactory.createEmptyBorder(0,10,0,0)));
            } if(!fileCreated.exists()){
                //Error no account
                userField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED),
                        BorderFactory.createEmptyBorder(0,10,0,0)));
            }else {
                FileReader reader = new FileReader(fileCreated);
                int data = reader.read();
                tempString = Character.toString ((char) data);
                data = reader.read();
                while(data != -1){
                    tempString += Character.toString((char) data);
                    data = reader.read();
                }
                email = getEmail(tempString.split("\n"));
                if(isEmailCorrect()){
                   // if correct
                    if(isPasswordCorrect()){
                        FileWriter writer = new FileWriter(fileCreated);
                        writer.write(email + "\n" + newPassword);
                        writer.close();
                        signPanel.add(new LoginPanel(frame,signPanel), BorderLayout.EAST);
                        panel.setVisible(false);
                    }else{
                        //Error password does not match
                        newPasswordField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED),
                                BorderFactory.createEmptyBorder(0,10,0,0)));
                        confirmPasswordField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED),
                                BorderFactory.createEmptyBorder(0,10,0,0)));
                    }
                }else{
                    //error email not correct
                    emailField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED),
                            BorderFactory.createEmptyBorder(0,10,0,0)));
                }
            }
        }catch (IOException ignored){}
    }
    public boolean isEmailCorrect(){
        return email.equals(emailEntered);
    }
    //public boolean is
    public String getEmail(String[] string){
        return string[0];
    }
    public boolean isPasswordCorrect(){
        return newPassword.equals(confirmPassword);
    }

}
