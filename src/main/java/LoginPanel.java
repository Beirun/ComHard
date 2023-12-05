import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginPanel extends JPanel implements ActionListener {
    JPanel panel;
    JTextField userName;
    JPasswordField password;
    Font emailPass;
    Buttons loginButton;

    JButton createAccount, resetPassword;
    JFrame frame;
    public LoginPanel(JFrame frame, JPanel panel){
        this.frame = frame;
        this.panel = panel;
        this.setPreferredSize(new Dimension(ComHard.WIDTH/2-100,ComHard.LENGTH));
        this.setBackground(new Color(236,236,236));
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        textFields();
    }

    public void textFields(){

        userName = new JTextField(10);
        addPlaceholder(userName,"Username");
        userName.setMaximumSize(new Dimension(300, 75));
        userName.setMinimumSize(new Dimension(300, 75));
        userName.setPreferredSize(new Dimension(300, 75));
        userName.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        userName.setFont(emailPass);
        userName.addKeyListener(new TextFieldListener(panel));


        password = new JPasswordField(10);
        addPlaceholder(password,"Password");
        password.setMaximumSize(new Dimension(300, 75));
        password.setMinimumSize(new Dimension(300, 75));
        password.setPreferredSize(new Dimension(300, 75));
        password.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        password.setFont(emailPass);
        password.addKeyListener(new TextFieldListener(panel));
        this.add(new LogoClass());
        this.add(Box.createVerticalStrut(50));
        this.add(userName);
        this.add(Box.createVerticalStrut(10));
        this.add(password);
        loginButtons();
    }
    public void loginButtons(){
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(null);
        buttonPanel.setPreferredSize(new Dimension(175,100));

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(null);
        leftPanel.setPreferredSize(new Dimension(20,20));

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(null);
        rightPanel.setPreferredSize(new Dimension(65,20));

        JPanel invisiblePanel = new JPanel();
        invisiblePanel.setPreferredSize(new Dimension(200,350));
        invisiblePanel.setBackground(null);

        JPanel betweenPanel = new JPanel();
        betweenPanel.setBackground(null);
        betweenPanel.setPreferredSize(new Dimension(10,20));
        loginButton = new Buttons("Sign In");
        loginButton.setColor(new Color(password.getBackground().getRGB()));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setDimension(100,50);
        loginButton.addMouseListener(new SubmitButton(panel));

        resetPassword = new JButton("Forgot Password?");
        resetPassword.setFont(new Font("",Font.BOLD,14));
        resetPassword.setBackground(new Color(this.getBackground().getRGB()));

        loginButton.setFont(resetPassword.getFont());

        resetPassword.setFocusPainted(false);
        resetPassword.setBorderPainted(false);
        resetPassword.setBorder(null);
        resetPassword.setPreferredSize(new Dimension(135,30));
        resetPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        resetPassword.addMouseListener(new ListenerClasses.UnderlinedText(resetPassword.getText(), resetPassword));
        resetPassword.addActionListener(this);

        JPanel signInPanel = new JPanel(new FlowLayout());
        signInPanel.setBackground(null);

        createAccount = new JButton("Don't have an Account? Sign Up Here!");
        createAccount.setFont(new Font("",Font.BOLD,14));
        createAccount.setBackground(new Color(this.getBackground().getRGB()));
        createAccount.setFocusPainted(false);
        createAccount.setBorderPainted(false);
        createAccount.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        createAccount.setPreferredSize(new Dimension(275,30));
        createAccount.setCursor(new Cursor(Cursor.HAND_CURSOR));
        createAccount.addMouseListener(new ListenerClasses.UnderlinedText(createAccount.getText(), createAccount));
        createAccount.addActionListener(this);
        signInPanel.add(createAccount);
        buttonPanel.add(leftPanel);
        buttonPanel.add(loginButton); buttonPanel.add(betweenPanel); buttonPanel.add(resetPassword);
        buttonPanel.add(rightPanel);
        this.add(Box.createVerticalStrut(15));
        this.add(buttonPanel);

        this.add(Box.createVerticalStrut(10));
        this.add(signInPanel);

        this.add(invisiblePanel);

    }

    public void addPlaceholder(JTextField textField, String placeholder) {
        JLabel placeholderLabel = new JLabel(placeholder);
        placeholderLabel.setForeground(Color.GRAY);
        placeholderLabel.setFont(new Font("",Font.PLAIN,16));

        textField.setLayout(new BorderLayout());
        textField.add(placeholderLabel);
        emailPass = placeholderLabel.getFont();

        textField.getDocument().addDocumentListener(new ListenerClasses.PlaceHolderListener(textField, placeholderLabel));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==createAccount){
            this.setVisible(false);
            panel.add(new RegisterPanel(frame,panel),BorderLayout.EAST);
        } if(e.getSource()==resetPassword){
            this.setVisible(false);
            panel.add(new ForgotPassPanel(frame, panel), BorderLayout.EAST);
        }
    }

    class TextFieldListener implements KeyListener{
        JPanel panel;
        public TextFieldListener(JPanel panel){this.panel = panel;}
        @Override
        public void keyTyped(KeyEvent e) {}
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode()==KeyEvent.VK_ENTER){
                LoginOperation loginOperation = new LoginOperation(frame, panel, userName,
                        password, userName.getText(), ListenerClasses.toString(password.getPassword()));
                loginOperation.fileReader();
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {}
    }
    class SubmitButton extends MouseAdapter {
        JPanel panel;
        public SubmitButton(JPanel panel){
            this.panel = panel;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            LoginOperation loginOperation = new LoginOperation(frame, panel, userName,
                    password, userName.getText(),ListenerClasses.toString(password.getPassword()));
            loginOperation.fileReader();
        }

    }

}
