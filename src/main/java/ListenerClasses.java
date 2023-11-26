import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListenerClasses {
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
        private void updatePlaceholder() {placeholderLabel.setVisible(textField.getText().isEmpty());}
    }
    static class UnderlinedText extends MouseAdapter {
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

}
