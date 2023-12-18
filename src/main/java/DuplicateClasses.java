import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

    static class SaveButtonListener extends MouseAdapter{
        Buttons button, previousButton;
        String userName, purpose, budget, part, itemName, itemPrice, itemLocation;
        int set;

        public SaveButtonListener(Buttons button, Buttons previousButton, String userName, String purpose, int set, String budget,
                                  String part, String itemName, String itemPrice, String itemLocation){
            this.button = button;
            this.previousButton = previousButton;
            this.userName = userName;
            this.purpose = purpose;
            this.set = set;
            this.budget = budget;
            this.part = part;
            this.itemName = itemName;
            this.itemPrice = itemPrice;
            this.itemLocation = itemLocation;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            File directory;
            if(set!=0) directory = new File("assets/favorites/"+userName+"/"+purpose+"/"+set+". "+budget);
            else directory = new File("assets/favorites/"+userName+"/"+purpose+"/"+budget);
            if(button.getText().equals("Save")) {
                button.setLabel("Unsave");
                button.setColor(new Color(213, 17, 17));
                button.setTextColor(new Color(250,250,250));
                previousButton.setLabel("Unsave");
                previousButton.setColor(new Color(213, 17, 17));
                previousButton.setTextColor(new Color(250,250,250));
                directory.mkdirs();
                File favoriteFile = new File(directory+"/"+part+".txt");
                try(FileWriter writer = new FileWriter(favoriteFile)){
                    writer.write(itemName+"\n"+itemPrice+"\n"+itemLocation);
                }catch (IOException ignored){}
            }else{
                button.setLabel("Save");
                button.setTextColor(new Color(250,250,250));
                button.setColor(new Color(53,118,172));
                previousButton.setLabel("Save");
                previousButton.setTextColor(new Color(250,250,250));
                previousButton.setColor(new Color(53,118,172));
                File favoriteFile = new File(directory+"/"+part+".txt");
                try{
                    Files.delete(Paths.get(favoriteFile.getPath()));
                    Files.delete(Paths.get(directory.getPath()));
                    Files.delete(Paths.get("assets/favorites/"+userName+"/"+purpose));
                }catch (IOException ignored){}
            }
            previousButton.revalidate();
            previousButton.repaint();
            button.revalidate();
            button.repaint();
        }
    }
    public static String[] fileContent(String purpose, String budget, String part, int set){
        String[] content = new String[0];
        try{
            File fileCreated;
            if(set!=0) fileCreated = new File("assets/items/"+purpose+"/"+set+". "+budget+"/"+part+".txt");
            else fileCreated = new File("assets/items/"+purpose+"/"+budget+"/"+part+".txt");
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
    public static String[] listTxtFiles(String directoryPath) {
        File directory = new File(directoryPath);
        File[] txtFiles = directory.listFiles();
        String[] txtFileNames = new String[0];
        if (txtFiles != null) {
            txtFileNames = new String[txtFiles.length];
            for (int i = 0; i < txtFiles.length; i++) txtFileNames[i] = txtFiles[i].getName().substring(0,txtFiles[i].getName().length()-4);
        }
        return txtFileNames;
    }

    public static BufferedImage imageItems(String itemName, String folder){
        itemName = itemName.trim();
        int width = 10, height = 10;
        BufferedImage bufferedImage = null;
        try{
            bufferedImage = ImageIO.read(new File("resources/"+folder+"/"+itemName+".jpg"));
            width = bufferedImage.getWidth()/2;
            height = bufferedImage.getHeight()/2;
        }catch (IOException ignored){}
        BufferedImage imageBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = imageBuffer.createGraphics();
        g2.setClip(0,0, width, height);
        g2.drawImage(bufferedImage,0,0,width,height,null);
        g2.dispose();
        return imageBuffer;
    }
    public static String getItemName(String[] string){
        return string[0];
    }
    public static String getItemPrice(String[] string){
        return string[1];
    }
    public static String getItemAddress(String[] string){
        return string[2];
    }


}

