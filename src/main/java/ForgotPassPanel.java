import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ForgotPassPanel extends JPanel implements ActionListener {
    JPanel signupBox;
    JTextField email, userName;
    JPasswordField newPassword, confirmPassword;
    Buttons signupButton;

    JButton aHAccount;
    JFrame frames;
    public ForgotPassPanel(JFrame frames){
        this.setBounds(0,0,ComHard.WIDTH,ComHard.LENGTH);
        this.setBackground(new Color(162,221,164));
        this.setLayout(new BorderLayout());
        this.setFocusable(true);
        this.frames = frames;
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
        userName = new JTextField(10);
        ListenerClasses.addPlaceholder(userName,"Username");
        userName.setMaximumSize(new Dimension(300, 75));
        userName.setMinimumSize(new Dimension(300, 75));
        userName.setPreferredSize(new Dimension(300, 75));
        userName.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        userName.setFont(ListenerClasses.font);

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

        signupBox.add(Box.createRigidArea(new Dimension(0,10)));
        signupBox.add(Box.createVerticalStrut(100));
        signupBox.add(userName);
        signupBox.add(Box.createVerticalStrut(10));
        signupBox.add(newPassword);
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

        signupButton = new Buttons("Submit");
        signupButton.setColor(new Color(newPassword.getBackground().getRGB()));
        signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signupButton.setDimension(200,50);
        signupButton.addMouseListener(new ListenerClasses.SubmitButtonListener(this, frames));

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(null);
        leftPanel.setPreferredSize(new Dimension((signupBox.getWidth()-signupButton.getWidth())/2,20));

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(null);
        rightPanel.setPreferredSize(new Dimension((signupBox.getWidth()-signupButton.getWidth())/2,20));

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
        aHAccount.addActionListener(this);
        signInPanel.add(aHAccount);
        buttonPanel.add(leftPanel);
        buttonPanel.add(signupButton);
        buttonPanel.add(rightPanel);
        signupBox.add(Box.createVerticalStrut(15));
        signupBox.add(buttonPanel);
        signupBox.add(Box.createVerticalStrut(10));
        signupBox.add(signInPanel);
        signupBox.add(inviPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==aHAccount){
            this.setVisible(false);
            frames.add(new LoginPanel(frames));
        }
    }

}
