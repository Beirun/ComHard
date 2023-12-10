import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UpdatePasswordPanel extends JPanel {
    JPasswordField[] passwordFields = new JPasswordField[2];
    JPanel[] passwordPanels = new JPanel[3];
    String userName;
    String[] content;
    JFrame frame;
    JPanel panel,signPanel;
    JPanel dashboardPanel;
    Buttons submitButton;
    JPasswordField currentPasswordField;
    int passwordFieldWidth;
    int passwordFieldHeight;
    int space;
    Color color;

    public UpdatePasswordPanel(String[] content, String userName, JFrame frame, JPanel panel, JPanel signPanel, JPanel dashboardPanel, JPasswordField currentPasswordField, int passwordFieldWidth, int passwordFieldHeight, int space){
        this.content = content;
        this.userName = userName;
        this.frame = frame;
        this.panel = panel;
        this.signPanel = signPanel;
        this.dashboardPanel = dashboardPanel;
        this.currentPasswordField = currentPasswordField;
        this.passwordFieldWidth = passwordFieldWidth;
        this.passwordFieldHeight = passwordFieldHeight;
        this.space = space;
        this.setBackground(null);
        this.setMaximumSize(new Dimension(1050, 170));
        this.setMinimumSize(new Dimension(1050, 170));
        this.setPreferredSize(new Dimension(1050, 170));
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        passwordTextField();
    }
    public void setPasswordFieldDimension(int passwordFieldWidth, int passwordFieldHeight){
        this.passwordFieldWidth = passwordFieldWidth;
        this.passwordFieldHeight = passwordFieldHeight;

        for (JPasswordField passwordField : passwordFields) {
            passwordField.setMaximumSize(new Dimension(passwordFieldWidth, passwordFieldHeight));
            passwordField.setMinimumSize(new Dimension(passwordFieldWidth, passwordFieldHeight));
            passwordField.setPreferredSize(new Dimension(passwordFieldWidth, passwordFieldHeight));
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
        color = passwordFields[0].getForeground();
        passwordFields[0].addKeyListener(new PasswordListener(content,userName,frame, panel,signPanel,dashboardPanel,
                currentPasswordField,passwordFields[0],passwordFields[1]));
        passwordFields[1].addKeyListener(new PasswordListener(content,userName,frame, panel,signPanel,dashboardPanel,
                currentPasswordField,passwordFields[0],passwordFields[1]));
        currentPasswordField.addKeyListener(new PasswordListener(content,userName,frame, panel,signPanel,dashboardPanel,
                currentPasswordField,passwordFields[0],passwordFields[1]));
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
        submitButton.addTextPosition(-1,-1);
        submitButton.addMouseListener(new SubmitButtonListener(content, userName, frame, panel, signPanel, dashboardPanel, currentPasswordField, passwordFields[0], passwordFields[1]));
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

        textField.getDocument().addDocumentListener(new AccountPanel.PlaceHolderListener(textField, placeholderLabel,color));
    }
    static class SubmitButtonListener extends MouseAdapter{
        String[] content;
        String userName;
        JPasswordField currentPasswordField, newPasswordField, confirmPasswordField;
        JFrame frame;
        JPanel panel, signPanel;
        JPanel dashboardPanel;
        public SubmitButtonListener(String[] content, String userName, JFrame frame, JPanel panel, JPanel signPanel, JPanel dashboardPanel,
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
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            new UpdatePasswordOperation(content,userName,frame,panel,signPanel,dashboardPanel,
                    currentPasswordField,newPasswordField,confirmPasswordField);
        }
    }
    static class PasswordListener extends KeyAdapter {
        String[] content;
        String userName;
        JPasswordField currentPasswordField, newPasswordField, confirmPasswordField;
       JFrame frame;
        JPanel panel, signPanel;
        JPanel dashboardPanel;
        public PasswordListener(String[] content, String userName, JFrame frame, JPanel panel, JPanel signPanel, JPanel dashboardPanel,
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
        }
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode()==KeyEvent.VK_ENTER){
                new UpdatePasswordOperation(content,userName,frame, panel,signPanel,dashboardPanel,
                        currentPasswordField,newPasswordField,confirmPasswordField);
            }
        }
    }
}
