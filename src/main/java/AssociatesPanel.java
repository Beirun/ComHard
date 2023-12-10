import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
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

    public AssociatesPanel(JPanel dashboardPanel, String userName){
        this.dashboardPanel = dashboardPanel;
        this.userName = userName;
        this.setPreferredSize(new Dimension(ComHard.WIDTH,ComHard.LENGTH));
        this.setBackground(new Color(245,245,245));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 75,25));
        storePanels();
    }
    public void print(){
        for(String s : getStoreName()){
            System.out.println(s);
        }
    }
    public void storePanels(){
        stores = new JPanel[getStoreName().size()];
        storeName = getStoreName();
        storeAddress = getStoreAddress();
        for (int i = 0; i < stores.length; i++) {
            stores[i] = new JPanel();
            stores[i].setBackground(Color.green);
            stores[i].setPreferredSize(new Dimension(200, 300));
            stores[i].setLayout(new FlowLayout(FlowLayout.CENTER));

            JTextPane[] storeNames = new JTextPane[getStoreName().size()];
            storeNames[i] = new JTextPane();
            storeNames[i].setText(storeName.get(i));
            StyledDocument documentStyle = storeNames[i].getStyledDocument();
            SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
            StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
            documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);

            storeNames[i].setFocusable(false);
            storeNames[i].setEditable(false);
            storeNames[i].setLayout(new FlowLayout(FlowLayout.CENTER));
            storeNames[i].setFont(new Font("",Font.BOLD,14));
            storeNames[i].setPreferredSize(new Dimension(190, 50));
            stores[i].add(storeNames[i]);

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
            stores[i].add(storeAddresses[i]);

            this.add(stores[i]);


        }
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
