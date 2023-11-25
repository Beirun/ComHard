import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BoxView;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ComHardFrame extends JFrame{
   JPanel loginPane, loginBox;
   JTextField email;
   JPasswordField password;
   Font emailPass;
   Buttons loginButton;

   JButton createAccount, resetPassword;
   public ComHardFrame(){
      this.setSize(new Dimension(ComHard.WIDTH,ComHard.LENGTH));
      this.setLocationRelativeTo(null);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setTitle("ComHard");
      this.setResizable(false);
      //this.add(new RegisterPanel());
      loginPanel();
      this.setVisible(true);
   }


   public void loginPanel(){
      loginPane = new JPanel();
      loginPane.setBounds(0,0,ComHard.WIDTH,ComHard.LENGTH);
      loginPane.setBackground(new Color(162,221,164));
      loginPane.setLayout(new BorderLayout());
      loginBox();
      this.add(loginPane);
   }

   public void loginBox(){
      loginBox = new JPanel();
      loginBox.setPreferredSize(new Dimension(ComHard.WIDTH/2-100,ComHard.LENGTH));
      loginBox.setBackground(new Color(236,236,236));
      loginBox.setLayout(new BoxLayout(loginBox,BoxLayout.Y_AXIS));
      textFields();
      loginPane.add(loginBox, BorderLayout.EAST);
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


      password = new JPasswordField(10);
      addPlaceholder(password,"Password");
      password.setMaximumSize(new Dimension(300, 75));
      password.setMinimumSize(new Dimension(300, 75));
      password.setPreferredSize(new Dimension(300, 75));
      //password.setBounds();
      password.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
      password.setFont(emailPass);
      //password.addFocusListener(new TextFieldFocusListener(password));
      //password.add(Box.createRigidArea(new Dimension(0,10)));
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
      //loginButton.setSize(email.getWidth(),email.getHeight());

      //inviPanel.setVisible(false);
      //buttonPanel.setLayout(new FlowLayout());
      //buttonPanel.add(loginButton);
      //loginButton.setPreferredSize(new Dimension(100,50));
      //loginBox.add(Box.createVerticalStrut(10));
      //loginBox.add(Box.createRigidArea(new Dimension(50,10)));
      //loginBox.add(loginButton);
      //loginBox.add(Box.createHorizontalStrut(loginBox.getWidth()/2));

      resetPassword = new JButton("Forgot Password?");
      resetPassword.setFont(new Font("",Font.BOLD,14));
      resetPassword.setBackground(new Color(loginBox.getBackground().getRGB()));
      resetPassword.setFocusPainted(false);
      resetPassword.setBorderPainted(false);
      resetPassword.setBorder(null);
      resetPassword.setPreferredSize(new Dimension(135,30));
      resetPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
      resetPassword.addMouseListener(new UnderlinedText(resetPassword.getText(), resetPassword));

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
      createAccount.addMouseListener(new UnderlinedText(createAccount.getText(), createAccount));
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
   static class UnderlinedText extends MouseAdapter{
      String text;
      JButton button;
      public UnderlinedText(String text, JButton button){
         this.text = text;
         this.button = button;
      }

      @Override
      public void mouseEntered(MouseEvent e) {
         button.setText("<html><u>"+text+"</u></html>");
      }
      @Override
      public void mouseExited(MouseEvent e) {
         button.setText(text);
      }
   }
   public void addPlaceholder(JTextField textField, String placeholder) {
      JLabel placeholderLabel = new JLabel(placeholder);
      placeholderLabel.setForeground(Color.GRAY);
      placeholderLabel.setFont(new Font("",Font.PLAIN,16));

      textField.setLayout(new BorderLayout());
      textField.add(placeholderLabel);
      emailPass = placeholderLabel.getFont();

      textField.getDocument().addDocumentListener(new PlaceHolderListener(textField, placeholderLabel));
   }
   static class PlaceHolderListener implements DocumentListener{
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
      private void updatePlaceholder() {placeholderLabel.setVisible(textField.getText().isEmpty());}
   }
}