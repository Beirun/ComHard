import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterPanel extends JPanel implements ActionListener {
    JPanel panel;
    JTextField email, userName;
    JPasswordField password, confirmPassword;
    Buttons signupButton;

    JButton aHAccount;
    JFrame frame;
    public RegisterPanel(JFrame frame, JPanel panel){
        this.panel = panel;
        this.frame = frame;
        this.setPreferredSize(new Dimension(ComHard.WIDTH/2-100,ComHard.LENGTH));
        this.setBackground(new Color(236,236,236));
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        textFields();
    }

    public void textFields(){
        email = new JTextField(10);
        DuplicateClasses.addPlaceholder(email,"Email");
        email.setMaximumSize(new Dimension(300, 75));
        email.setMinimumSize(new Dimension(300, 75));
        email.setPreferredSize(new Dimension(300, 75));
        email.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        email.setFont(DuplicateClasses.font);
        email.addKeyListener(new TextFieldListener(panel, this));

        userName = new JTextField(10);
        DuplicateClasses.addPlaceholder(userName,"Username");
        userName.setMaximumSize(new Dimension(300, 75));
        userName.setMinimumSize(new Dimension(300, 75));
        userName.setPreferredSize(new Dimension(300, 75));
        userName.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        userName.setFont(DuplicateClasses.font);
        userName.addKeyListener(new TextFieldListener(panel, this));

        password = new JPasswordField(10);
        DuplicateClasses.addPlaceholder(password,"Password");
        password.setMaximumSize(new Dimension(300, 75));
        password.setMinimumSize(new Dimension(300, 75));
        password.setPreferredSize(new Dimension(300, 75));
        password.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        password.setFont(DuplicateClasses.font);
        password.addKeyListener(new TextFieldListener(panel, this));

        confirmPassword = new JPasswordField(10);
        DuplicateClasses.addPlaceholder(confirmPassword,"Confirm Password");
        confirmPassword.setMaximumSize(new Dimension(300, 75));
        confirmPassword.setMinimumSize(new Dimension(300, 75));
        confirmPassword.setPreferredSize(new Dimension(300, 75));
        confirmPassword.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        confirmPassword.setFont(DuplicateClasses.font);
        confirmPassword.addKeyListener(new TextFieldListener(panel, this));

        this.add(new LogoClass(ComHard.WIDTH/2-200,300, 360, 101,69,50));
        this.add(Box.createVerticalStrut(25));
        this.add(email);
        this.add(Box.createVerticalStrut(10));
        this.add(userName);
        this.add(Box.createVerticalStrut(10));
        this.add(password);
        this.add(Box.createVerticalStrut(10));
        this.add(confirmPassword);
        signupButtons();
    }
    public void signupButtons(){
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(null);
        buttonPanel.setPreferredSize(new Dimension(200,100));

        JPanel invisiblePanel = new JPanel();
        invisiblePanel.setPreferredSize(new Dimension(200,50));
        invisiblePanel.setBackground(null);

        signupButton = new Buttons("Sign Up");
        signupButton.setColor(new Color(password.getBackground().getRGB()));
        signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signupButton.setDimension(200,50);
        signupButton.addMouseListener(new SubmitButton(panel,this));

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(null);
        leftPanel.setPreferredSize(new Dimension((this.getWidth()-signupButton.getWidth())/2 - 20,20));

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(null);
        rightPanel.setPreferredSize(new Dimension((this.getWidth()-signupButton.getWidth())/2,20));

        JPanel signInPanel = new JPanel(new FlowLayout());
        signInPanel.setBackground(null);

        aHAccount = new JButton("Already have an Account? Sign In Here!");
        aHAccount.setFont(new Font("",Font.BOLD,14));

        signupButton.setFont(aHAccount.getFont());

        aHAccount.setBackground(new Color(this.getBackground().getRGB()));
        aHAccount.setFocusPainted(false);
        aHAccount.setBorderPainted(false);
        aHAccount.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        aHAccount.setPreferredSize(new Dimension(290,30));
        aHAccount.setCursor(new Cursor(Cursor.HAND_CURSOR));
        aHAccount.addMouseListener(new DuplicateClasses.UnderlinedText(aHAccount.getText(), aHAccount));
        aHAccount.addActionListener(this);
        signInPanel.add(aHAccount);
        buttonPanel.add(leftPanel);
        buttonPanel.add(signupButton);
        buttonPanel.add(rightPanel);
        this.add(Box.createVerticalStrut(15));
        this.add(buttonPanel);
        this.add(Box.createVerticalStrut(10));
        this.add(signInPanel);
        this.add(invisiblePanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==aHAccount){
            this.setVisible(false);
            panel.add(new LoginPanel(frame, panel), BorderLayout.EAST);
        }
    }

    class SubmitButton extends MouseAdapter {
        JPanel signPanel;
        JPanel panel;
        public SubmitButton(JPanel signPanel,JPanel panel){
            this.signPanel = signPanel;
            this.panel = panel;
        }

        @Override
        public void mouseClicked(MouseEvent e) {

            RegistrationOperation registrationOperation = new RegistrationOperation(frame, signPanel, panel, password,confirmPassword,userName, email,
                    userName.getText(),email.getText(), DuplicateClasses.toString(password.getPassword()), DuplicateClasses.toString(confirmPassword.getPassword()));
            registrationOperation.fileCreator();
        }
    }

    class TextFieldListener implements KeyListener {
        JPanel signPanel, panel;
        public TextFieldListener(JPanel signPanel, JPanel panel){
            this.signPanel = signPanel;
            this.panel = panel;}
        @Override
        public void keyTyped(KeyEvent e) {}
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode()==KeyEvent.VK_ENTER){
                RegistrationOperation registrationOperation = new RegistrationOperation(frame, signPanel, panel, password,confirmPassword,userName, email,
                        userName.getText(),email.getText(), DuplicateClasses.toString(password.getPassword()), DuplicateClasses.toString(confirmPassword.getPassword()));
                registrationOperation.fileCreator();
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {}
    }

}
