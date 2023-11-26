import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPanel extends JPanel implements ActionListener {
    JPanel signPane, signupBox;
    JTextField email, userName;
    JPasswordField password, confirmPassword;
    Font emailPass;
    Buttons signupButton;

    JButton aHAccount;
    public RegisterPanel(){
        this.setBounds(0,0,ComHard.WIDTH,ComHard.LENGTH);
        this.setBackground(new Color(162,221,164));
        this.setLayout(new BorderLayout());
        this.setFocusable(true);
        signupBox();
    }

    public void signupBox(){
        signupBox = new JPanel();
        signupBox.setPreferredSize(new Dimension(ComHard.WIDTH/2-100,ComHard.LENGTH));
        signupBox.setBackground(new Color(236,236,236));
        signupBox.setLayout(new BoxLayout(signupBox,BoxLayout.Y_AXIS));
        textFields();
        this.add(signupBox, BorderLayout.EAST);
    }

    public void textFields(){
        email = new JTextField(10);
        addPlaceholder(email,"Email");
        email.setMaximumSize(new Dimension(300, 75));
        email.setMinimumSize(new Dimension(300, 75));
        email.setPreferredSize(new Dimension(300, 75));
        //email.setPreferredSize(new Dimension(250,30));
        //email.setBackground(null);
        email.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        //email.setBorder(new LineBorder(new Color(255,255,255),30,true));
        //email.addFocusListener(new TextFieldFocusListener(email));
        email.setFont(emailPass);

        userName = new JTextField(10);
        addPlaceholder(userName,"Username");
        userName.setMaximumSize(new Dimension(300, 75));
        userName.setMinimumSize(new Dimension(300, 75));
        userName.setPreferredSize(new Dimension(300, 75));
        //email.setPreferredSize(new Dimension(250,30));
        //email.setBackground(null);
        userName.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        //email.setBorder(new LineBorder(new Color(255,255,255),30,true));
        //email.addFocusListener(new TextFieldFocusListener(email));
        userName.setFont(emailPass);

        password = new JPasswordField(10);
        addPlaceholder(password,"Password");
        password.setMaximumSize(new Dimension(300, 75));
        password.setMinimumSize(new Dimension(300, 75));
        password.setPreferredSize(new Dimension(300, 75));
        //password.setBounds();
        password.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        password.setFont(emailPass);

        confirmPassword = new JPasswordField(10);
        addPlaceholder(confirmPassword,"Confirm Password");
        confirmPassword.setMaximumSize(new Dimension(300, 75));
        confirmPassword.setMinimumSize(new Dimension(300, 75));
        confirmPassword.setPreferredSize(new Dimension(300, 75));
        //password.setBounds();
        confirmPassword.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        confirmPassword.setFont(emailPass);

        signupBox.add(Box.createRigidArea(new Dimension(0,10)));
        signupBox.add(Box.createVerticalStrut(100));
        signupBox.add(email);
        signupBox.add(Box.createVerticalStrut(10));
        signupBox.add(userName);
        signupBox.add(Box.createVerticalStrut(10));
        signupBox.add(password);
        signupBox.add(Box.createVerticalStrut(10));
        signupBox.add(confirmPassword);
        signupButtons();
    }
    public void signupButtons(){
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(null);
        buttonPanel.setPreferredSize(new Dimension(175,100));

        JPanel inviPanel = new JPanel();
        inviPanel.setPreferredSize(new Dimension(200,150));
        inviPanel.setBackground(null);

        signupButton = new Buttons("Sign Up");
        signupButton.setColor(new Color(password.getBackground().getRGB()));
        signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signupButton.setDimension(200,50);

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(null);
        leftPanel.setPreferredSize(new Dimension((signupBox.getWidth()-signupButton.getWidth())/2,20));

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(null);
        rightPanel.setPreferredSize(new Dimension((signupBox.getWidth()-signupButton.getWidth())/2,20));
        //inviPanel.setVisible(false);
        //buttonPanel.setLayout(new FlowLayout());
        //buttonPanel.add(loginButton);
        //loginButton.setPreferredSize(new Dimension(100,50));
        //loginBox.add(Box.createVerticalStrut(10));
        //loginBox.add(Box.createRigidArea(new Dimension(50,10)));
        //loginBox.add(loginButton);
        //loginBox.add(Box.createHorizontalStrut(loginBox.getWidth()/2));


        JPanel signInPanel = new JPanel(new FlowLayout());
        signInPanel.setBackground(null);

        aHAccount = new JButton("Already have an Account? Sign In Here!");
        aHAccount.setFont(new Font("",Font.BOLD,14));

        signupButton.setFont(aHAccount.getFont());

        aHAccount.setBackground(new Color(signupBox.getBackground().getRGB()));
        aHAccount.setFocusPainted(false);
        aHAccount.setBorderPainted(false);
        aHAccount.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        aHAccount.setPreferredSize(new Dimension(290,30));
        aHAccount.setCursor(new Cursor(Cursor.HAND_CURSOR));
        aHAccount.addMouseListener(new ListenerClasses.UnderlinedText(aHAccount.getText(), aHAccount));
        signInPanel.add(aHAccount);
        //loginBox.add(resetPassword);
        buttonPanel.add(leftPanel);
        buttonPanel.add(signupButton);
        buttonPanel.add(rightPanel);
        signupBox.add(Box.createVerticalStrut(15));
        signupBox.add(buttonPanel);

        signupBox.add(Box.createVerticalStrut(10));
        signupBox.add(signInPanel);

        signupBox.add(inviPanel);

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
        if(e.getSource()==aHAccount){
            ComHard.isLoginPanelClicked =true;
            ComHard.isRegisterPanelClicked = false;
            repaint();
            validate();
        }
    }
}
