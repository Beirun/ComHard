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
import java.io.FileWriter;
import java.io.IOException;

public class AccountPanel extends JPanel implements ActionListener {
    Component box;
    JFrame frame;
    UpdatePasswordPanel updatePasswordPanel;
    JButton[] buttons = new JButton[3];
    JLabel[] labels = new JLabel[3];
    JLabel dateCreated;
    String[] Texts = {"Email","Password","Account Created","Change Email","Update Password", "Edit"};
    JPanel dashboardPanel, signPanel, invisiblePanel, editPanel;
    JPanel[] panels = new JPanel[4];
    JTextField emailField, userNameField;
    JPasswordField passwordField;
    String userName;
    int textFieldWidth, userNameFieldWidth, passwordFieldWidth, textFieldHeight = 22, emptySpace;
    Graphics graphics;
    FontMetrics fontMetrics;
    Color color;
    DuplicateClasses.UserFile user;

    public AccountPanel(JFrame frame, JPanel signPanel, JPanel dashboardPanel, String userName){
        this.frame = frame;
        this.signPanel = signPanel;
        this.dashboardPanel = dashboardPanel;
        this.userName = userName;
        this.setPreferredSize(new Dimension(ComHard.WIDTH,ComHard.LENGTH));
        this.setBackground(new Color(236, 234, 236));
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        SwingUtilities.invokeLater(this::profilePanel);
    }

    public void profilePanel(){
        user = new DuplicateClasses.UserFile(userName);
        graphics = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).getGraphics();
        graphics.setFont(new Font("",Font.BOLD,16));
        fontMetrics = graphics.getFontMetrics();
        textFieldWidth = fontMetrics.stringWidth(user.getEmail(user.fileContent()))+15;
        this.add(Box.createVerticalStrut(20));
        AccountLabel accountLabel = new AccountLabel(userName,this,500,210,
                155,7,2, false);
        accountLabel.panel.setAlignmentX(CENTER_ALIGNMENT);
        accountLabel.setCircularColor(new Color(53,118,172));
        emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(textFieldWidth, textFieldHeight));
        emailField.setMinimumSize(new Dimension(textFieldWidth, textFieldHeight));
        emailField.setPreferredSize(new Dimension(textFieldWidth, textFieldHeight));
        emailField.setFont(new Font("",Font.BOLD,16));
        emailField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK),
                BorderFactory.createEmptyBorder(0,5,0,0)));
        emailField.setEditable(false);
        emailField.setBackground(null);
        emailField.setFocusable(false);
        emailField.setText(user.getEmail(user.fileContent()));
        color = emailField.getForeground();
        emailField.addKeyListener(new EmailListener(user.fileContent(),this, emailField,userName, user.getPassword(user.fileContent())));

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
            buttons[i] = new JButton(Texts[i+3]);
            buttons[i].setUI(new DisableButtonPress());
            buttons[i].setBackground(null);
            buttons[i].setFocusPainted(false);
            buttons[i].setPreferredSize(new Dimension(100,30));
            buttons[i].setMinimumSize(new Dimension(100,30));
            buttons[i].setMaximumSize(new Dimension(100,30));
            buttons[i].setBorder(null);
            buttons[i].setFont(new Font("",Font.BOLD,12));
            buttons[i].addMouseListener(new DuplicateClasses.UnderlinedText(buttons[i].getText(),buttons[i]));
            buttons[i].addActionListener(this);
            buttons[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        panels[3].setMaximumSize(new Dimension(1050, textFieldHeight+40));
        panels[3].setMinimumSize(new Dimension(1050, textFieldHeight+40));
        panels[3].setPreferredSize(new Dimension(1050, textFieldHeight+40));
        panels[3].setLayout(new FlowLayout(FlowLayout.LEFT));

        userNameField = new JTextField();
        graphics.setFont(new Font("",Font.BOLD,32));
        fontMetrics = graphics.getFontMetrics();
        userNameFieldWidth = fontMetrics.stringWidth(userName)+10;

        invisiblePanel = new JPanel();
        invisiblePanel.setBackground(null);
        invisiblePanel.setPreferredSize(new Dimension(510-userNameFieldWidth/2,5));
        invisiblePanel.setMinimumSize(new Dimension(510-userNameFieldWidth/2,5));
        invisiblePanel.setMaximumSize(new Dimension(510-userNameFieldWidth/2,5));

        userNameField.setMaximumSize(new Dimension(userNameFieldWidth, textFieldHeight+25));
        userNameField.setMinimumSize(new Dimension(userNameFieldWidth, textFieldHeight+25));
        userNameField.setPreferredSize(new Dimension(userNameFieldWidth, textFieldHeight+25));
        userNameField.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
        userNameField.setText(userName);
        userNameField.setEditable(false);
        userNameField.setBackground(null);
        userNameField.setFocusable(false);
        userNameField.setFont(new Font("",Font.BOLD,32));

        editPanel = new JPanel();
        editPanel.setBackground(null);
        editPanel.setMaximumSize(new Dimension(30, textFieldHeight+40));
        editPanel.setMinimumSize(new Dimension(30, textFieldHeight+40));
        editPanel.setPreferredSize(new Dimension(30, textFieldHeight+40));
        editPanel.setLayout(new BoxLayout(editPanel,BoxLayout.Y_AXIS));
        editPanel.add(Box.createVerticalStrut(26));

        buttons[2].setPreferredSize(new Dimension(30,20));
        buttons[2].setMinimumSize(new Dimension(30,20));
        buttons[2].setMaximumSize(new Dimension(30,20));
        panels[3].add(invisiblePanel);
        panels[3].add(userNameField);
        editPanel.add(buttons[2]);
        panels[3].add(editPanel);
        this.add(panels[3]);

        this.add(Box.createVerticalStrut(25));
        panels[0].add(Box.createHorizontalStrut(420-textFieldWidth/2));
        panels[0].add(labels[0]);
        panels[0].add(Box.createHorizontalStrut(20));
        panels[0].add(emailField);
        panels[0].add(Box.createHorizontalStrut(20));
        panels[0].add(buttons[0]);
        this.add(panels[0]);

        graphics.setFont(new Font("",Font.BOLD,16));
        fontMetrics = graphics.getFontMetrics();
        emptySpace = textFieldWidth;
        passwordFieldWidth = fontMetrics.stringWidth(user.getPassword(user.fileContent()))+10;
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
        passwordField.setText(user.getPassword(user.fileContent()));
        passwordField.setFont(new Font("",Font.BOLD,20));
        passwordField.setEchoChar('*');
        this.add(Box.createVerticalStrut(25));
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
        box = Box.createVerticalStrut(25);
        this.add(box);

        dateCreated = new JLabel(user.getDateCreated(user.fileContent()));
        dateCreated.setFont(new Font("",Font.BOLD,16));
        dateCreated.setBorder(null);
        panels[2].add(Box.createHorizontalStrut(315-emptySpace/2));
        panels[2].add(labels[2]);
        panels[2].add(Box.createHorizontalStrut(20));
        panels[2].add(dateCreated);
        this.add(panels[2]);
        graphics.dispose();
    }
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
            updatePasswordPanel = new UpdatePasswordPanel(user.fileContent(),userName, frame,
                    this,signPanel,dashboardPanel, passwordField,passwordFieldWidth,textFieldHeight,493-emptySpace/2);
            panels[2].setVisible(false);
            box.setVisible(false);
            this.add(updatePasswordPanel);
            this.add(box);
            this.add(panels[2]);
            panels[2].setVisible(true);
            updatePasswordPanel.setVisible(true);
            buttons[1].setVisible(false);
            updatePasswordPanel.setPasswordFieldDimension(passwordFieldWidth,textFieldHeight);
            passwordField.setMaximumSize(new Dimension(passwordFieldWidth, textFieldHeight));
            passwordField.setMinimumSize(new Dimension(passwordFieldWidth, textFieldHeight));
            passwordField.setPreferredSize(new Dimension(passwordFieldWidth, textFieldHeight));
            passwordField.setEditable(true);
            passwordField.setFocusable(true);
            passwordField.setText("");
            passwordField.requestFocus();
            passwordField.setBackground(new Color(220,220,220));
            addPlaceholder(passwordField,"Current Password");
            graphics.dispose();
        }
        if(e.getSource()==buttons[2]){
            userNameField.setEditable(true);
            userNameField.setFocusable(true);
            userNameField.requestFocus();
            panels[3].validate();
            panels[3].repaint();
            userNameField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0,0,1,0,userNameField.getForeground()),
                    BorderFactory.createEmptyBorder(0,5,0,0)));
            userNameField.addKeyListener(new UpdateUserNameOperation(frame,dashboardPanel,panels[3],signPanel,
                    invisiblePanel,userNameField,userName));
            userNameField.getDocument().addDocumentListener(new UserNameListener(userNameField,panels[3],invisiblePanel, color));
            buttons[2].setVisible(false);
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

        textField.getDocument().addDocumentListener(new PlaceHolderListener(textField, placeholderLabel,color));
    }
    static class PlaceHolderListener implements DocumentListener {
        JTextField textField;
        JLabel placeholderLabel;
        Color color;

        public PlaceHolderListener(JTextField textField, JLabel placeholderLabel, Color color){
            this.textField = textField;
            this.placeholderLabel = placeholderLabel;
            this.color = color;
        }
        @Override
        public void insertUpdate(DocumentEvent e) {updatePlaceholder();}
        @Override
        public void removeUpdate(DocumentEvent e) {updatePlaceholder();}
        @Override
        public void changedUpdate(DocumentEvent e) {updatePlaceholder();}
        private void updatePlaceholder() {
            textField.setForeground(color);
            textField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK),
                    BorderFactory.createEmptyBorder(0,5,0,0)));
            placeholderLabel.setVisible(textField.getText().isEmpty());}
    }

    static class UserNameListener implements DocumentListener {
        JTextField userNameField;
        JPanel userPanel;
        JPanel box;
        Color color;

        public UserNameListener(JTextField userNameField,JPanel userPanel, JPanel box, Color color){
            this.userNameField = userNameField;
            this.userPanel = userPanel;
            this.box = box;
            this.color = color;
        }
        @Override
        public void insertUpdate(DocumentEvent e) {
            updateLength();}
        @Override
        public void removeUpdate(DocumentEvent e) {
            updateLength();}
        @Override
        public void changedUpdate(DocumentEvent e) {
            updateLength();}
        private void updateLength() {
            userNameField.setForeground(color);
            Graphics graphics = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).getGraphics();
            graphics.setFont(new Font("", Font.BOLD, 32));
            FontMetrics fontMetrics = graphics.getFontMetrics();
            int userNameFieldWidth = fontMetrics.stringWidth(userNameField.getText()) + 10, textFieldHeight = 22;
            userNameField.setMaximumSize(new Dimension(userNameFieldWidth, textFieldHeight + 25));
            userNameField.setMinimumSize(new Dimension(userNameFieldWidth, textFieldHeight + 25));
            userNameField.setPreferredSize(new Dimension(userNameFieldWidth, textFieldHeight + 25));
            userNameField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 1, 0, userNameField.getForeground()),
                    BorderFactory.createEmptyBorder(0, 5, 0, 0)));
            box.setMaximumSize(new Dimension(510 - userNameFieldWidth / 2, 5));
            box.setPreferredSize(new Dimension(510 - userNameFieldWidth / 2, 5));
            box.setMinimumSize(new Dimension(510 - userNameFieldWidth / 2, 5));
            graphics.dispose();
            userPanel.repaint();
            userPanel.validate();
            box.validate();
            box.repaint();
        }
    }

    static class DisableButtonPress extends MetalButtonUI{
        @Override
        protected void paintButtonPressed(Graphics g, AbstractButton b) {}
    }

    class EmailListener extends KeyAdapter{
        JTextField emailField;
       String[] fileContent;
        JPanel panel;
        String userName, password;
        public EmailListener(String[] fileContent, JPanel panel, JTextField emailField, String userName, String password){
            this.fileContent = fileContent;
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
                    writer.write(userName + "\n" + emailField.getText() + "\n" + password+"\n"+ user.getDateCreated(fileContent));
                    writer.close();
                }catch(IOException ignored){}
                panel.setVisible(false);
                dashboardPanel.add(new AccountPanel(frame,signPanel,dashboardPanel,userName), BorderLayout.CENTER);
            }
        }
    }
}
