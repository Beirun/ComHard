import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ComHardFrame extends JFrame{
   JPanel loginPane, loginBox;
   JTextField email, password;
   JButton loginButton, createAcc, resetPassword;
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
      loginBox.setLayout(new FlowLayout());
      textFields();
      loginPane.add(loginBox, BorderLayout.EAST);
   }

   public void textFields(){
      email = new JTextField(10);
      addPlaceholder(email,"Email");
      email.setPreferredSize(new Dimension(300,30));
      email.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
      //email.addFocusListener(new TextFieldFocusListener(email));

      password = new JTextField(10);
      addPlaceholder(password,"Password");
      password.setPreferredSize(new Dimension(300,30));
      password.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));

      //password.addFocusListener(new TextFieldFocusListener(password));
      loginBox.add(email);
      loginBox.add(password);
   }

   private static void addPlaceholder(JTextField textField, String placeholder) {
      JLabel placeholderLabel = new JLabel(placeholder);
      placeholderLabel.setForeground(Color.GRAY);

      textField.setLayout(new BorderLayout());
      textField.add(placeholderLabel);

      textField.getDocument().addDocumentListener(new DocumentListener() {
         @Override
         public void insertUpdate(DocumentEvent e) {updatePlaceholder();}

         @Override
         public void removeUpdate(DocumentEvent e) {updatePlaceholder();}

         @Override
         public void changedUpdate(DocumentEvent e) {updatePlaceholder();}
         private void updatePlaceholder() {placeholderLabel.setVisible(textField.getText().isEmpty());}
      });
   }

}