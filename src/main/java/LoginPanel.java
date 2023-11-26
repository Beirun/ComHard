import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel implements ActionListener {
    JPanel loginBox;
    JTextField email;
    JPasswordField password;
    Font emailPass;
    Buttons loginButton;

    JButton createAccount, resetPassword;
    JFrame frame;
    public LoginPanel(JFrame frame){
        this.setBounds(0,0,ComHard.WIDTH,ComHard.LENGTH);
        this.setBackground(new Color(162,221,164));
        this.setLayout(new BorderLayout());
        loginBox();
        this.frame = frame;
    }

    public void loginBox(){
        loginBox = new JPanel();
        loginBox.setPreferredSize(new Dimension(ComHard.WIDTH/2-100,ComHard.LENGTH));
        loginBox.setBackground(new Color(236,236,236));
        loginBox.setLayout(new BoxLayout(loginBox,BoxLayout.Y_AXIS));
        textFields();
        this.add(loginBox, BorderLayout.EAST);
    }

    public void textFields(){
        email = new JTextField(10);
        addPlaceholder(email,"Email");
        email.setMaximumSize(new Dimension(300, 75));
        email.setMinimumSize(new Dimension(300, 75));
        email.setPreferredSize(new Dimension(300, 75));
        email.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        email.setFont(emailPass);


        password = new JPasswordField(10);
        addPlaceholder(password,"Password");
        password.setMaximumSize(new Dimension(300, 75));
        password.setMinimumSize(new Dimension(300, 75));
        password.setPreferredSize(new Dimension(300, 75));
        password.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        password.setFont(emailPass);
        loginBox.add(Box.createRigidArea(new Dimension(0,10)));
        loginBox.add(Box.createVerticalStrut(100));
        loginBox.add(email);
        loginBox.add(Box.createVerticalStrut(10));
        loginBox.add(password);
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

        JPanel inviPanel = new JPanel();
        inviPanel.setPreferredSize(new Dimension(200,350));
        inviPanel.setBackground(null);

        JPanel betweenPanel = new JPanel();
        betweenPanel.setBackground(null);
        betweenPanel.setPreferredSize(new Dimension(10,20));
        loginButton = new Buttons("Sign In");
        loginButton.setColor(new Color(password.getBackground().getRGB()));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setDimension(100,50);

        resetPassword = new JButton("Forgot Password?");
        resetPassword.setFont(new Font("",Font.BOLD,14));
        resetPassword.setBackground(new Color(loginBox.getBackground().getRGB()));

        loginButton.setFont(resetPassword.getFont());

        resetPassword.setFocusPainted(false);
        resetPassword.setBorderPainted(false);
        resetPassword.setBorder(null);
        resetPassword.setPreferredSize(new Dimension(135,30));
        resetPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        resetPassword.addMouseListener(new ListenerClasses.UnderlinedText(resetPassword.getText(), resetPassword));

        JPanel signInPanel = new JPanel(new FlowLayout());
        signInPanel.setBackground(null);

        createAccount = new JButton("Don't have an Account? Sign Up Here!");
        createAccount.setFont(new Font("",Font.BOLD,14));
        createAccount.setBackground(new Color(loginBox.getBackground().getRGB()));
        createAccount.setFocusPainted(false);
        createAccount.setBorderPainted(false);
        createAccount.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        createAccount.setPreferredSize(new Dimension(275,30));
        createAccount.setCursor(new Cursor(Cursor.HAND_CURSOR));
        createAccount.addMouseListener(new ListenerClasses.UnderlinedText(createAccount.getText(), createAccount));
        createAccount.addActionListener(this);
        signInPanel.add(createAccount);
        //loginBox.add(resetPassword);
        buttonPanel.add(leftPanel);
        buttonPanel.add(loginButton); buttonPanel.add(betweenPanel); buttonPanel.add(resetPassword);
        buttonPanel.add(rightPanel);
        loginBox.add(Box.createVerticalStrut(15));
        loginBox.add(buttonPanel);

        loginBox.add(Box.createVerticalStrut(10));
        loginBox.add(signInPanel);

        loginBox.add(inviPanel);

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
            frame.add(new RegisterPanel(frame));
        }
    }

}
