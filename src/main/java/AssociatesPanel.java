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

public class AssociatesPanel extends JPanel {
    JPanel dashboardPanel;
    String userName;
    ArrayList<String> storeName;
    ArrayList<String> storeAddress;
    JPanel[] stores;
    JLabel[] imageLabels;
    JScrollPane scrollPane;
    Buttons[] viewButtons;

    public AssociatesPanel(JPanel dashboardPanel, String userName){
        this.dashboardPanel = dashboardPanel;
        this.userName = userName;
        this.setPreferredSize(new Dimension(ComHard.WIDTH,ComHard.LENGTH));
        this.setBackground(new Color(245,245,245));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        storePanels();
    }
    public void storePanels(){
        SwingUtilities.invokeLater(() -> {
            JPanel insidePanel = new JPanel();
            insidePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50 , 30));
            stores = new JPanel[getStoreName().size()];
            storeName = getStoreName();
            storeAddress = getStoreAddress();
            imageLabels = new JLabel[getStoreName().size()];
            viewButtons = new Buttons[getStoreName().size()];
            int storeSize = stores.length;
            for(int i = 1;i < stores.length; i++) if(storeName.get(i).equals(storeName.get(i-1))) storeSize--;
            int row = storeSize /3;
            if(storeSize%3!=0) row+=1;
            int gap = storeSize/6;
            if(storeSize%6!=0) gap+=1;
            int height = 327*row + gap*60;
            insidePanel.setPreferredSize(new Dimension(ComHard.WIDTH/3,height));
            for (int i = 0; i < stores.length; i++) {
                if( i!=0 && storeName.get(i).equals(storeName.get(i-1))) continue;
                stores[i] = new JPanel();
                stores[i].setBackground(Color.WHITE);
                stores[i].setPreferredSize(new Dimension(264, 325));
                stores[i].setLayout(new FlowLayout(FlowLayout.CENTER));
                imageLabels[i] = new JLabel();
                imageLabels[i].setIcon(new ImageIcon(DuplicateClasses.imageItems(storeName.get(i),"associates")));
                imageLabels[i].setHorizontalAlignment(SwingConstants.LEFT);
                imageLabels[i].setPreferredSize(new Dimension(DuplicateClasses.imageItems(storeName.get(i),"associates").getWidth(),
                        DuplicateClasses.imageItems(storeName.get(i),"associates").getHeight()));
                imageLabels[i].add(Box.createHorizontalGlue());

                stores[i].add(imageLabels[i]);

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
                stores[i].add(storeNames[i]);
                /*
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
                stores[i].add(storeAddresses[i]);*/
                stores[i].add(Box.createRigidArea(new Dimension(220,3)));
                viewButtons[i] = new Buttons("View");
                viewButtons[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
                viewButtons[i].setDimension(75, 25);
                viewButtons[i].setFontSize(12);
                viewButtons[i].setColor(new Color(53,118,172));
                viewButtons[i].setTextColor(new Color(250, 250, 250));
                viewButtons[i].setBorderRadius(25);
                viewButtons[i].addTextPosition(0, -2);
                viewButtons[i].setOpaque(false);
                stores[i].add(viewButtons[i]);
                insidePanel.add(stores[i]);
            }
            scrollPane = new JScrollPane(insidePanel,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollPane.getVerticalScrollBar().setUnitIncrement(10);
            this.add(scrollPane);
        });
    }
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
    public ArrayList<String> getStoreName(){
        ArrayList<String> arrayList = new ArrayList<>();
        String[] allStoreData = fileContent().split("\n");
        for(String storeData : allStoreData){
            arrayList.add(storeData.split(":")[0]);
        }
        return arrayList;
    }
    public ArrayList<String> getStoreAddress(){
        ArrayList<String> arrayList = new ArrayList<>();
        String[] allStoreData = fileContent().split("\n");
        for(String storeData : allStoreData){
            arrayList.add(storeData.split(":")[1]);
        }
        return arrayList;
    }
}
