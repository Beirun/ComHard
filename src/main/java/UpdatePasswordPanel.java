import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class UpdatePasswordPanel extends JPanel {
    JPasswordField[] passwordFields = new JPasswordField[2];
    JPanel[] passwordPanels = new JPanel[3];
    String userName;
    String[] content;
    String currentPassword;
    JPanel panel, dashboardPanel;
    Buttons submitButton;
    int passwordFieldWidth, passwordFieldHeight, space;

    public UpdatePasswordPanel(String[] content, String currentPassword, String userName, JPanel panel, JPanel dashboardPanel, int passwordFieldWidth, int passwordFieldHeight, int space){
        this.content = content;
        this.currentPassword = currentPassword;
        this.userName = userName;
        this.panel = panel;
        this.dashboardPanel = dashboardPanel;
        this.passwordFieldWidth = passwordFieldWidth;
        this.passwordFieldHeight = passwordFieldHeight;
        this.space = space;
        this.setBackground(null);
        this.setMaximumSize(new Dimension(1050, 200));
        this.setMinimumSize(new Dimension(1050, 200));
        this.setPreferredSize(new Dimension(1050, 200));
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        passwordTextField();
    }
    public void setPasswordFieldDimension(int passwordFieldWidth, int passwordFieldHeight){
        this.passwordFieldWidth = passwordFieldWidth;
        this.passwordFieldHeight = passwordFieldHeight;

        for(int i = 0; i < passwordFields.length; i++){
            passwordFields[i].setMaximumSize(new Dimension(passwordFieldWidth, passwordFieldHeight));
            passwordFields[i].setMinimumSize(new Dimension(passwordFieldWidth, passwordFieldHeight));
            passwordFields[i].setPreferredSize(new Dimension(passwordFieldWidth, passwordFieldHeight));
        }
    }
    public void passwordTextField(){
        for(int i = 0; i < passwordPanels.length; i++){
            passwordPanels[i] = new JPanel();
            passwordPanels[i].setBackground(null);
            passwordPanels[i].setMaximumSize(new Dimension(1050, passwordFieldHeight+25));
            passwordPanels[i].setMinimumSize(new Dimension(1050, passwordFieldHeight+25));
            passwordPanels[i].setPreferredSize(new Dimension(1050, passwordFieldHeight+25));
            passwordPanels[i].setLayout(new FlowLayout(FlowLayout.LEFT));
        }
        for(int i = 0; i < passwordFields.length; i++) {
            passwordFields[i] = new JPasswordField();
            passwordFields[i].setMaximumSize(new Dimension(passwordFieldWidth, passwordFieldHeight));
            passwordFields[i].setMinimumSize(new Dimension(passwordFieldWidth, passwordFieldHeight));
            passwordFields[i].setPreferredSize(new Dimension(passwordFieldWidth, passwordFieldHeight));
            passwordFields[i].setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
                    BorderFactory.createEmptyBorder(0, 5, 0, 0)));
            passwordFields[i].setBackground(new Color(220, 220, 220));
            passwordFields[i].setFont(new Font("",Font.BOLD,20));
            passwordFields[i].setEchoChar('*');
        }
        addPlaceholder(passwordFields[0],"New Password");
        addPlaceholder(passwordFields[1],"Confirm Password");
        this.add(Box.createVerticalStrut(30));
        passwordPanels[0].add(Box.createHorizontalStrut(space));
        passwordPanels[0].add(passwordFields[0]);
        this.add(passwordPanels[0]);
        this.add(Box.createVerticalStrut(30));
        passwordPanels[1].add(Box.createHorizontalStrut(space));
        passwordPanels[1].add(passwordFields[1]);
        this.add(passwordPanels[1]);

        submitButton = new Buttons("Submit");
        submitButton.setColor(new Color(250,250,250));
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitButton.setDimension(80,25);
        submitButton.setBorderRadius(15);
        submitButton.setFontSize(12);
        submitButton.addMouseListener(new SubmitButtonListener(content,userName,panel,dashboardPanel,currentPassword,
                ListenerClasses.toString(passwordFields[0].getPassword()), ListenerClasses.toString(passwordFields[1].getPassword())));
        this.add(Box.createVerticalStrut(30));
        passwordPanels[2].add(Box.createHorizontalStrut(passwordFieldWidth/2-40+space));
        passwordPanels[2].add(submitButton);
        this.add(passwordPanels[2]);


    }
    public void addPlaceholder(JTextField textField, String placeholder) {
        JLabel placeholderLabel = new JLabel(placeholder);
        placeholderLabel.setForeground(Color.GRAY);
        placeholderLabel.setFont(new Font("",Font.PLAIN,12));

        textField.setLayout(new BorderLayout());
        textField.add(placeholderLabel);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK),
                BorderFactory.createEmptyBorder(0,5,0,0)));

        textField.getDocument().addDocumentListener(new AccountPanel.PlaceHolderListener(textField, placeholderLabel));
    }
    class SubmitButtonListener extends MouseAdapter{
        String[] content;
        String userName;
        String currentPassword;
        String newPassword;
        String confirmPassword;
        JPanel panel, dashboardPanel;
        public SubmitButtonListener(String[] content, String userName, JPanel panel, JPanel dashboardPanel, String currentPassword, String newPassword, String confirmPassword){
            this.content = content;
            this.userName = userName;
            this.panel = panel;
            this.dashboardPanel = dashboardPanel;
            this.currentPassword = currentPassword;
            this.newPassword = newPassword;
            this.confirmPassword = confirmPassword;
        }

        public String getEmail(String[] string){return string[1];}
        public String getPassword(String[] string){return string[2];}

        @Override
        public void mouseClicked(MouseEvent e) {
            if(getPassword(content).equals(currentPassword)){
                if(newPassword.equals(confirmPassword)){
                    try{
                        File fileCreated = new File("assets/info/"+userName+".txt");
                        FileWriter writer = new FileWriter(fileCreated);
                        System.out.println(Arrays.toString(content));
                        System.out.println(newPassword+" text");
                        writer.write(userName+"\n"+getEmail(content)+"\n"+newPassword+"\n"+content[3]);
                        writer.close();
                        panel.setVisible(false);
                        dashboardPanel.add(new AccountPanel(dashboardPanel,userName), BorderLayout.CENTER);
                    }catch (IOException ignored){}
                    panel.setVisible(false);
                    dashboardPanel.add(new AccountPanel(dashboardPanel,userName), BorderLayout.CENTER);
                }else{
                    //Error password does not match
                }
            }
            else{
                //Error incorrect password
            }

        }
    }
}
