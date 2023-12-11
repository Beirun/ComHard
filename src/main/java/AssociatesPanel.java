import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
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
    BufferedImage bufferedImage;


    public AssociatesPanel(JPanel dashboardPanel, String userName){
        this.dashboardPanel = dashboardPanel;
        this.userName = userName;
        this.setPreferredSize(new Dimension(ComHard.WIDTH,ComHard.LENGTH));
        this.setBackground(new Color(245,245,245));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        storePanels();
    }
    public void print(){
        for(String s : getStoreName()){
            System.out.println(s);
        }
    }
    public void storePanels(){
        SwingUtilities.invokeLater(() -> {
            JPanel insidePanel = new JPanel();
            insidePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50 , 30));
            insidePanel.setPreferredSize(new Dimension(ComHard.WIDTH/3,ComHard.LENGTH));

            stores = new JPanel[getStoreName().size()];
            storeName = getStoreName();
            storeAddress = getStoreAddress();
            imageLabels = new JLabel[getStoreName().size()];
            for (int i = 0; i < stores.length; i++) {
                int finalI = i;
                stores[i] = new JPanel();
                stores[i].setBackground(Color.green);
                stores[i].setPreferredSize(new Dimension(264, 176+300));
                stores[i].setLayout(new FlowLayout(FlowLayout.CENTER));
                //stores[i].add(Box.createVerticalStrut(10));
                imageLabels[i] = new JLabel();
                imageLabels[i].setIcon(new ImageIcon(storeImage(storeName.get(i))));
                imageLabels[i].setHorizontalAlignment(SwingConstants.LEFT);
                imageLabels[i].setPreferredSize(new Dimension(storeImage(storeName.get(finalI)).getWidth(),
                        storeImage(storeName.get(finalI)).getHeight()));
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
                storeAddresses[i].revalidate();
                storeAddresses[i].repaint();
                insidePanel.add(stores[i]);
                storeNames[i].revalidate();
                storeNames[i].repaint();

            }
            scrollPane = new JScrollPane(insidePanel,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
    public BufferedImage storeImage(String storeName){
        int width = 10, height = 10;
        try{
            bufferedImage = ImageIO.read(new File("resources/Associates/"+storeName+".jpg"));
            width = bufferedImage.getWidth()/2;
            height = bufferedImage.getHeight()/2;
        }catch (IOException ignored){}
        BufferedImage imageBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = imageBuffer.createGraphics();
        g2.setClip(new RoundRectangle2D.Float(0,0, width, height,25,25));
        g2.drawImage(bufferedImage,0,0,width,height,null);
        g2.dispose();
        return imageBuffer;
    }
}
