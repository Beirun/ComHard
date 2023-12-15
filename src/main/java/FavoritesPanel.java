import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FavoritesPanel extends JPanel {
    JPanel dashboardPanel;
    String userName;
    ArrayList<String> storeName;
    ArrayList<String> storeAddress;
    JPanel[] favorites;
    JLabel[] imageLabels;
    JScrollPane scrollPane;
    BufferedImage bufferedImage;

    public FavoritesPanel(JPanel dashboardPanel, String userName){
        this.dashboardPanel = dashboardPanel;
        this.userName = userName;
        this.setPreferredSize(new Dimension(ComHard.WIDTH,ComHard.LENGTH));
        this.setBackground(new Color(245,245,245));
    }
/*
    public void storePanels(){
        SwingUtilities.invokeLater(() -> {
            JPanel insidePanel = new JPanel();
            insidePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50 , 30));
            insidePanel.setPreferredSize(new Dimension(ComHard.WIDTH/3,ComHard.LENGTH*3));

            favorites = new JPanel[getStoreName().size()];
            storeName = getStoreName();
            storeAddress = getStoreAddress();
            imageLabels = new JLabel[getStoreName().size()];
            for (int i = 0; i < favorites.length; i++) {
                favorites[i] = new JPanel();
                favorites[i].setBackground(Color.green);
                favorites[i].setPreferredSize(new Dimension(264, 176+300));
                favorites[i].setLayout(new FlowLayout(FlowLayout.CENTER));
                imageLabels[i] = new JLabel();
                imageLabels[i].setIcon(new ImageIcon(storeImage(storeName.get(i))));
                imageLabels[i].setHorizontalAlignment(SwingConstants.LEFT);
                imageLabels[i].setPreferredSize(new Dimension(storeImage(storeName.get(i)).getWidth(),
                        storeImage(storeName.get(i)).getHeight()));
                imageLabels[i].add(Box.createHorizontalGlue());

                favorites[i].add(imageLabels[i]);

                JTextPane[] storeNames = new JTextPane[getStoreName().size()];
                storeNames[i] = new JTextPane();
                storeNames[i].add(Box.createHorizontalGlue());
                storeNames[i].setText(storeName.get(i));
                StyledDocument documentStyle = storeNames[i].getStyledDocument();
                SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
                StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
                documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);

                storeNames[i].setFocusable(false);
                storeNames[i].setEditable(false);
                storeNames[i].setFont(new Font("",Font.BOLD,14));
                storeNames[i].setPreferredSize(new Dimension(190, 50));
                favorites[i].add(storeNames[i]);

                JTextPane[] storeAddresses = new JTextPane[getStoreName().size()];
                storeAddresses[i] = new JTextPane();
                storeAddresses[i].setText(storeAddress.get(i));
                documentStyle = storeAddresses[i].getStyledDocument();
                centerAttribute = new SimpleAttributeSet();
                StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
                documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);
                storeAddresses[i].setFocusable(false);
                storeAddresses[i].setEditable(false);
                storeAddresses[i].setLayout(new FlowLayout(FlowLayout.CENTER));
                storeAddresses[i].setFont(new Font("",Font.PLAIN,14));
                storeAddresses[i].setPreferredSize(new Dimension(190, 70));
                favorites[i].add(storeAddresses[i]);
                insidePanel.add(favorites[i]);
            }
            scrollPane = new JScrollPane(insidePanel,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollPane.getVerticalScrollBar().setUnitIncrement(5);
            this.add(scrollPane);
        });
    }
    */
    public String fileContent(){
        String string = null;
        File associateFile = new File("assets/misc/associates.txt");
        try{
            FileReader reader = new FileReader(associateFile);
            int data = reader.read();
            string = Character.toString ((char) data);
            data = reader.read();
            while(data != -1){
                string += Character.toString((char) data);
                data = reader.read();
            }
            reader.close();
        }catch (IOException ignored){}
        return string;
    }
}
