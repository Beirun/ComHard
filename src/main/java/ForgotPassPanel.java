import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ForgotPassPanel extends JPanel implements ActionListener {
    JPanel panel;
    //JPanel this;
    JTextField email, userName;
    JPasswordField newPassword, confirmPassword;
    Buttons signupButton;

    JButton aHAccount;
    JFrame frame;
    public ForgotPassPanel(JFrame frame, JPanel panel){
        this.panel = panel;
        /*this.setBounds(0,0,ComHard.WIDTH,ComHard.LENGTH);
        this.setBackground(new Color(162,221,164));
        this.setLayout(new BorderLayout());
        this.add(new BackgroundClass(),BorderLayout.CENTER);
        this.setFocusable(true);*/
        this.frame = frame;

        this.setPreferredSize(new Dimension(ComHard.WIDTH/2-100,ComHard.LENGTH));
        this.setBackground(new Color(236,236,236));
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        textFields();
    }

    /*public void this(){
        this = new JPanel();
        this.setPreferredSize(new Dimension(ComHard.WIDTH/2-100,ComHard.LENGTH));
        this.setBackground(new Color(236,236,236));
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        textFields();
        this.add(this, BorderLayout.EAST);
    }*/

    public void textFields(){
        userName = new JTextField(10);
        ListenerClasses.addPlaceholder(userName,"Username");
        userName.setMaximumSize(new Dimension(300, 75));
        userName.setMinimumSize(new Dimension(300, 75));
        userName.setPreferredSize(new Dimension(300, 75));
        userName.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        userName.setFont(ListenerClasses.font);

        email = new JTextField(10);
        ListenerClasses.addPlaceholder(email,"Email");
        email.setMaximumSize(new Dimension(300, 75));
        email.setMinimumSize(new Dimension(300, 75));
        email.setPreferredSize(new Dimension(300, 75));
        email.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        email.setFont(ListenerClasses.font);

        newPassword = new JPasswordField(10);
        ListenerClasses.addPlaceholder(newPassword,"New Password");
        newPassword.setMaximumSize(new Dimension(300, 75));
        newPassword.setMinimumSize(new Dimension(300, 75));
        newPassword.setPreferredSize(new Dimension(300, 75));
        newPassword.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        newPassword.setFont(ListenerClasses.font);

        confirmPassword = new JPasswordField(10);
        ListenerClasses.addPlaceholder(confirmPassword,"Confirm Password");
        confirmPassword.setMaximumSize(new Dimension(300, 75));
        confirmPassword.setMinimumSize(new Dimension(300, 75));
        confirmPassword.setPreferredSize(new Dimension(300, 75));
        confirmPassword.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        confirmPassword.setFont(ListenerClasses.font);

        this.add(Box.createRigidArea(new Dimension(0,10)));
        this.add(Box.createVerticalStrut(100));
        this.add(userName);
        this.add(Box.createVerticalStrut(10));
        this.add(email);
        this.add(Box.createVerticalStrut(10));
        this.add(newPassword);
        this.add(Box.createVerticalStrut(10));
        this.add(confirmPassword);
        signupButtons();
    }
    public void signupButtons(){
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(null);
        buttonPanel.setPreferredSize(new Dimension(175,100));

        JPanel invisiblePanel = new JPanel();
        invisiblePanel.setPreferredSize(new Dimension(200,150));
        invisiblePanel.setBackground(null);

        signupButton = new Buttons("Submit");
        signupButton.setColor(new Color(newPassword.getBackground().getRGB()));
        signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signupButton.setDimension(200,50);
        signupButton.addMouseListener(new SubmitButton(panel, this));

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(null);
        leftPanel.setPreferredSize(new Dimension((this.getWidth()-signupButton.getWidth())/2,20));

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
        aHAccount.addMouseListener(new ListenerClasses.UnderlinedText(aHAccount.getText(), aHAccount));
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
            panel.add(new LoginPanel(frame,panel), BorderLayout.EAST);
        }
    }
    class SubmitButton extends MouseAdapter {
        JPanel panel, signPanel;
        public SubmitButton(JPanel signPanel, JPanel panel){
            this.panel = panel;
            this.signPanel = signPanel;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            ForgotPasswordOperation forgotPasswordOperation = new ForgotPasswordOperation(frame, signPanel, panel, userName, email, newPassword, confirmPassword,
                    userName.getText(), email.getText(),toString(newPassword.getPassword()),toString(confirmPassword.getPassword()));
            forgotPasswordOperation.fileReader();
        }
        public String toString(char[] a) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < a.length; i++) sb.append(a[i]);
            return sb.toString();
        }
    }

}
