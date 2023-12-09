import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AccountPanel extends JPanel implements ActionListener {
    UpdatePasswordPanel updatePasswordPanel;
    JButton[] buttons = new JButton[2];
    JLabel[] labels = new JLabel[2];
    String[] Texts = {"Email","Password","Change Email","Update Password"};
    JPanel dashboardPanel;
    JPanel[] panels = new JPanel[2];
    JTextField emailField;
    JPasswordField passwordField;
    String userName;
    int textFieldWidth, passwordFieldWidth, textFieldHeight = 22, emptySpace;
    Graphics graphics;
    FontMetrics fontMetrics;
    char passwordCharacter;

    public AccountPanel(JPanel dashboardPanel, String userName){
        this.dashboardPanel = dashboardPanel;
        this.userName = userName;
        this.setPreferredSize(new Dimension(ComHard.WIDTH,ComHard.LENGTH));
        this.setBackground(new Color(236, 234, 236));
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        profilePanel();
    }

    public void profilePanel(){
        graphics = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).getGraphics();
        graphics.setFont(new Font("",Font.BOLD,16));
        fontMetrics = graphics.getFontMetrics();
        textFieldWidth = fontMetrics.stringWidth(getEmail(fileContent()))+15;
        this.add(Box.createVerticalStrut(25));
        AccountLabel accountLabel = new AccountLabel(userName,this,500,225,
                155,10,2, false);
        accountLabel.panel.setAlignmentX(CENTER_ALIGNMENT);
        emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(textFieldWidth, textFieldHeight));
        emailField.setMinimumSize(new Dimension(textFieldWidth, textFieldHeight));
        emailField.setPreferredSize(new Dimension(textFieldWidth, textFieldHeight));
        emailField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK),
                BorderFactory.createEmptyBorder(0,5,0,0)));
        emailField.setEditable(false);
        emailField.setBackground(null);
        emailField.setFocusable(false);
        emailField.setText(getEmail(fileContent()));
        emailField.addKeyListener(new EmailListener(this, emailField,userName, getPassword(fileContent())));
        emailField.setFont(new Font("",Font.BOLD,16));
        this.add(Box.createVerticalStrut(30));

        for(int i = 0; i < panels.length; i++){
            panels[i] = new JPanel();
            panels[i].setBackground(null);
            panels[i].setMaximumSize(new Dimension(1050, textFieldHeight+25));
            panels[i].setMinimumSize(new Dimension(1050, textFieldHeight+25));
            panels[i].setPreferredSize(new Dimension(1050, textFieldHeight+25));
            panels[i].setLayout(new FlowLayout(FlowLayout.LEFT));
        }
        for(int i = 0; i < labels.length; i++){
            labels[i] = new JLabel(Texts[i]);
            labels[i].setFont(new Font("",Font.BOLD,20));
            labels[i].setVerticalAlignment(SwingConstants.BOTTOM);
            labels[i].setBackground(null);
        }
        for(int i = 0; i < buttons.length; i++){
            buttons[i] = new JButton(Texts[i+2]);
            buttons[i].setUI(new DisableButtonPress());
            buttons[i].setBackground(null);
            buttons[i].setFocusPainted(false);
            buttons[i].setPreferredSize(new Dimension(100,30));
            buttons[i].setMinimumSize(new Dimension(100,30));
            buttons[i].setMaximumSize(new Dimension(100,30));
            buttons[i].setBorder(null);
            buttons[i].setFont(new Font("",Font.BOLD,12));
            buttons[i].addMouseListener(new ListenerClasses.UnderlinedText(buttons[i].getText(),buttons[i]));
            buttons[i].addActionListener(this);
            buttons[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        panels[0].add(Box.createHorizontalStrut(420-textFieldWidth/2));
        panels[0].add(labels[0]);
        panels[0].add(Box.createHorizontalStrut(20));
        panels[0].add(emailField);
        panels[0].add(Box.createHorizontalStrut(20));
        panels[0].add(buttons[0]);
        this.add(panels[0]);

        emptySpace = textFieldWidth;
        passwordFieldWidth = fontMetrics.stringWidth(getPassword(fileContent()))+10;
        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(passwordFieldWidth, textFieldHeight));
        passwordField.setMinimumSize(new Dimension(passwordFieldWidth, textFieldHeight));
        passwordField.setPreferredSize(new Dimension(passwordFieldWidth, textFieldHeight));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK),
                BorderFactory.createEmptyBorder(0,10,0,0)));
        passwordField.setEditable(false);
        passwordField.setBackground(null);
        passwordField.setFocusable(false);
        passwordField.setText(getPassword(fileContent()));
        passwordCharacter = passwordField.getEchoChar();
        passwordField.setFont(new Font("",Font.BOLD,20));
        passwordField.setEchoChar('*');
        this.add(Box.createVerticalStrut(30));

        panels[1].add(Box.createHorizontalStrut(379-emptySpace/2));
        panels[1].add(labels[1]);
        panels[1].add(Box.createHorizontalStrut(20));
        panels[1].add(passwordField);
        panels[1].add(Box.createHorizontalStrut(20));
        panels[1].add(buttons[1]);

        JButton invisibleButton = new JButton();
        invisibleButton.setFocusPainted(false);
        invisibleButton.setFocusable(false);
        invisibleButton.setUI(new DisableButtonPress());
        invisibleButton.setBackground(null);
        invisibleButton.setPreferredSize(new Dimension(100,30));
        invisibleButton.setMinimumSize(new Dimension(100,30));
        invisibleButton.setMaximumSize(new Dimension(100,30));
        invisibleButton.setBorder(null);
        panels[1].add(invisibleButton);

        this.add(panels[1]);
        updatePasswordPanel = new UpdatePasswordPanel(fileContent(),ListenerClasses.toString(passwordField.getPassword()),userName, this,dashboardPanel,passwordFieldWidth,textFieldHeight, 493-emptySpace/2);
        updatePasswordPanel.setVisible(false);
        this.add(updatePasswordPanel);


    }
    public String[] fileContent(){
        String[] content = new String[0];
        try{
            File fileCreated = new File("assets/info/"+userName+".txt");
            FileReader reader = new FileReader(fileCreated);
            int data = reader.read();
            String string = Character.toString ((char) data);
            data = reader.read();
            while(data != -1){
                string += Character.toString((char) data);
                data = reader.read();
            }
            content = string.split("\n");
        }catch (IOException ignored){}
        return content;
    }
    public String getEmail(String[] string){return string[1];}
    public String getPassword(String[] string){return string[2];}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttons[0]){
            emailField.setEditable(true);
            emailField.setFont(new Font("",Font.BOLD,14));
            emailField.setFocusable(true);
            emailField.requestFocus();
            emailField.setBackground(new Color(220,220,220));
        }
        if(e.getSource()==buttons[1]){
            graphics.setFont(new Font("",Font.PLAIN,14));
            if(passwordFieldWidth<fontMetrics.stringWidth("Current Password")+15)
                passwordFieldWidth = fontMetrics.stringWidth("Current Password")+15;
            updatePasswordPanel = new UpdatePasswordPanel(fileContent(),ListenerClasses.toString(passwordField.getPassword()),userName,
                    this,dashboardPanel,passwordFieldWidth,textFieldHeight,493-emptySpace/2);
            this.add(updatePasswordPanel);
            updatePasswordPanel.setVisible(true);
            buttons[1].setVisible(false);
            updatePasswordPanel.setPasswordFieldDimension(passwordFieldWidth,22);
            passwordField.setMaximumSize(new Dimension(passwordFieldWidth, 22));
            passwordField.setMinimumSize(new Dimension(passwordFieldWidth, 22));
            passwordField.setPreferredSize(new Dimension(passwordFieldWidth, 22));
            passwordField.setEditable(true);
            passwordField.setFocusable(true);
            passwordField.setText("");
            passwordField.requestFocus();
            passwordField.setBackground(new Color(220,220,220));
            addPlaceholder(passwordField,"Current Password");
        }
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

        textField.getDocument().addDocumentListener(new PlaceHolderListener(textField, placeholderLabel));
    }
    static class PlaceHolderListener implements DocumentListener {
        JTextField textField;
        JLabel placeholderLabel;
        public PlaceHolderListener(JTextField textField, JLabel placeholderLabel){
            this.textField = textField;
            this.placeholderLabel = placeholderLabel;
        }
        @Override
        public void insertUpdate(DocumentEvent e) {updatePlaceholder();}
        @Override
        public void removeUpdate(DocumentEvent e) {updatePlaceholder();}
        @Override
        public void changedUpdate(DocumentEvent e) {updatePlaceholder();}
        private void updatePlaceholder() {
            textField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK),
                    BorderFactory.createEmptyBorder(0,5,0,0)));
            placeholderLabel.setVisible(textField.getText().isEmpty());}
    }

    static class DisableButtonPress extends MetalButtonUI{
        @Override
        protected void paintButtonPressed(Graphics g, AbstractButton b) {}
    }

    class EmailListener extends KeyAdapter{
        JTextField emailField;
        JPanel panel;
        String userName, password;
        public EmailListener(JPanel panel, JTextField emailField, String userName, String password){
            this.panel = panel;
            this.emailField = emailField;
            this.userName = userName;
            this.password = password;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            addPlaceholder(emailField,"New Email");
            if(e.getKeyCode()==KeyEvent.VK_ENTER) {
                emailField.setFont(new Font("", Font.BOLD, 16));
                emailField.setBackground(null);
                emailField.setEditable(false);
                emailField.setFocusable(false);
                try {
                    File userFile = new File("assets/info/"+userName+".txt");
                    FileWriter writer = new FileWriter(userFile);
                    writer.write(userName + "\n" + emailField.getText() + "\n" + password);
                    writer.close();
                }catch(IOException ignored){}
                panel.setVisible(false);
                dashboardPanel.add(new AccountPanel(dashboardPanel,userName), BorderLayout.CENTER);
            }
        }
    }
}
