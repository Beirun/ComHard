import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AccountPanel extends JPanel {
    JPanel dashboardPanel;
    JTextField emailField, userNameField;
    String userName, string, email;
    public AccountPanel(JPanel dashboardPanel, String userName){
        this.dashboardPanel = dashboardPanel;
        this.userName = userName;
        this.setPreferredSize(new Dimension(ComHard.WIDTH,ComHard.LENGTH));
        this.setBackground(new Color(0,245,245));
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        profilePanel();
    }

    public void profilePanel(){
        this.add(Box.createVerticalStrut(50));
        this.add(new AccountLabel(userName));
        emailField = new JTextField(10);
        emailField.setMaximumSize(new Dimension(300, 75));
        emailField.setMinimumSize(new Dimension(300, 75));
        emailField.setPreferredSize(new Dimension(300, 75));
        emailField.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        emailField.setEditable(false);
        emailField.setText(getEmail());

        //email.setFont(emailPass);
        //email.addKeyListener(new RegisterPanel.TextFieldListener(panel, this));

        userNameField = new JTextField(10);
        userNameField.setMaximumSize(new Dimension(300, 75));
        userNameField.setMinimumSize(new Dimension(300, 75));
        userNameField.setPreferredSize(new Dimension(300, 75));
        userNameField.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        userNameField.setEditable(false);
        userNameField.setText(userName);
        this.add(Box.createVerticalStrut(75));
        this.add(emailField);
        this.add(Box.createVerticalStrut(10));
        this.add(userNameField);
        //userNameField.setFont(emailPass);
        //userNameField.addKeyListener(new RegisterPanel.TextFieldListener(panel, this));
    /*
        password = new JPasswordField(10);
        password.setMaximumSize(new Dimension(300, 75));
        password.setMinimumSize(new Dimension(300, 75));
        password.setPreferredSize(new Dimension(300, 75));
        password.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        password.setFont(emailPass);
        password.addKeyListener(new RegisterPanel.TextFieldListener(panel, this));

     */
    }
    public String getEmail(){
        try{
        File fileCreated = new File("assets/info/"+userName+".txt");
        FileReader reader = new FileReader(fileCreated);
        int data = reader.read();
        string = Character.toString ((char) data);
        data = reader.read();
        while(data != -1){
            string += Character.toString((char) data);
            data = reader.read();
        }
        email = returnEmail(string.split("\n"));
        }catch (IOException ignored){}
        return email;
    }
    public String returnEmail(String[] string){return string[0];}

}
