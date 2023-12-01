import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListenerClasses {
    static Font font;
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

        textField.getDocument().addDocumentListener(new ListenerClasses.PlaceHolderListener(textField, placeholderLabel));
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

    static class SubmitButtonListener extends MouseAdapter{
        JPanel panel;
        JFrame frame;
        public SubmitButtonListener(JPanel panel, JFrame frame){
            this.frame = frame;
            this.panel = panel;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            panel.setVisible(false);
            frame.add(new LoginPanel(frame));
        }
    }

}
