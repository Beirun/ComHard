import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ForgotPasswordOperation {
    String userName, newPassword, tempString, confirmPassword, emailEntered, email;
    JFrame frame;
    JPanel panel;
    JTextField userField, emailField;
    JPasswordField passField1, passField2;

    public ForgotPasswordOperation(JFrame frame, JPanel panel, JTextField userField, JTextField emailField, JPasswordField passField1,
                                   JPasswordField passField2, String userName, String emailEntered, String newPassword, String confirmPassword){
        this.frame = frame;
        this.panel = panel;
        this.userField = userField;
        this.emailField = emailField;
        this.passField1 = passField1;
        this.passField2 = passField2;
        this.userName = userName;
        this.emailEntered = emailEntered;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }
    public void fileReader(){
        try {
            File fileCreated = new File("resources/"+userName+".txt");
            if(!fileCreated.exists()){
                //Error no account
                /*userField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED),
                        BorderFactory.createEmptyBorder(0,10,0,0)));
                passField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED),
                        BorderFactory.createEmptyBorder(0,10,0,0)));*/
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
                        frame.add(new LoginPanel(frame));
                        panel.setVisible(false);
                    }else{

                    }
                }else{
                    //error email not correct
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
