import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DuplicateClasses {
    static Font font;
    public static String toString(char[] a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) sb.append(a[i]);
        return sb.toString();
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
            textField.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
            placeholderLabel.setVisible(textField.getText().isEmpty());}
    }
    public static void addPlaceholder(JTextField textField, String placeholder) {
        JLabel placeholderLabel = new JLabel(placeholder);
        placeholderLabel.setForeground(Color.GRAY);
        placeholderLabel.setFont(new Font("",Font.PLAIN,16));

        textField.setLayout(new BorderLayout());
        textField.add(placeholderLabel);
        font = placeholderLabel.getFont();

        textField.getDocument().addDocumentListener(new DuplicateClasses.PlaceHolderListener(textField, placeholderLabel));
    }
    static class UnderlinedText extends MouseAdapter {
        String text;
        JButton button;
        public UnderlinedText(String text, JButton button){
            this.text = text;
            this.button = button;
        }
        @Override
        public void mouseEntered(MouseEvent e) {button.setText("<html><u>"+text+"</u></html>");}
        @Override
        public void mouseExited(MouseEvent e) {
            button.setText(text);
        }
    }
    static class UserFile {
        String userName;
        public UserFile(String userName){this.userName = userName;}
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
                reader.close();
                content = string.split("\n");
            }catch (IOException ignored){}
            return content;
        }
        public String getUserName(String[] string){return string[0];}
        public String getEmail(String[] string){return string[1];}
        public String getPassword(String[] string){return string[2];}
        public String getDateCreated(String[] string){return string[3];}
    }

}
