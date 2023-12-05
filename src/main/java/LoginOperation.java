import javax.swing.*;
import java.awt.*;
import java.io.*;

public class LoginOperation {
    String userName, password, tempString, passwordEntered;
    JFrame frame;
    JPanel panel;
    JTextField userField;
    JPasswordField passField;
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
        try {
            File fileCreated = new File("assets/info/"+userName+".txt");
            if(!fileCreated.exists()){
                //Error no account
                userField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED),
                        BorderFactory.createEmptyBorder(0,10,0,0)));
                passField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED),
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
                password = getPassword(tempString.split("\n"));

                if(isPasswordCorrect()){
                    frame.add(new DashboardPanel(frame));
                    panel.setVisible(false);
                }else{
                    userField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED),
                            BorderFactory.createEmptyBorder(0,10,0,0)));
                    passField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED),
                            BorderFactory.createEmptyBorder(0,10,0,0)));
                }
            }
        }catch (IOException ignored){}
    }
    public boolean isPasswordCorrect(){
        return passwordEntered.equals(password);
    }
    public String getPassword(String[] string){
        return string[1];
    }
}
