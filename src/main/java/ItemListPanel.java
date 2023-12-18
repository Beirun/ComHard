import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class ItemListPanel extends JPanel {
    JPanel semiHonePanel;
    JLabel backButton;
    JPanel homePanel;
    JPanel[] itemPanels;
    JLabel[] itemImage;
    String userName;
    JPanel semiItemListPanel;
    JPanel forItemsAndNavbar, forNavbar;
    Buttons[] saveButtons;
    JButton[] viewButtons;
    String purpose, budget;
    JScrollPane scrollPane;

    public ItemListPanel(JPanel semiHomePanel, JPanel homePanel, String userName, String purpose, String budget){
        this.semiHonePanel = semiHomePanel;
        this.homePanel = homePanel;
        this.userName = userName;
        this.purpose = purpose;
        this.budget = budget;
        this.setPreferredSize(new Dimension(ComHard.WIDTH,ComHard.LENGTH));
        this.setBackground(new Color(245,245,245));
        this.setBackground(Color.CYAN);
        this.setLayout(new BorderLayout());
        filterItemsPanel();
    }
    public void filterItemsPanel(){
        forNavbar = new JPanel();
        forNavbar.setBackground(null);
        forNavbar.setPreferredSize(new Dimension(ComHard.WIDTH,35));
        setForNavbar();
        forItemsAndNavbar = new JPanel();
        forItemsAndNavbar.setLayout(new BorderLayout());
        forItemsAndNavbar.setBackground(new Color(236,236,236));
        forItemsAndNavbar.add(forNavbar,BorderLayout.NORTH);
        setForItems();
    }

    public void setForItems(){
        int set = 1;
        semiItemListPanel = new JPanel();
        semiItemListPanel.setBackground(null);
        semiItemListPanel.setLayout(new BorderLayout());
        String[] content = DuplicateClasses.listTxtFiles("assets/items/" + purpose + "/"+set+". " + budget);
        String[] fileContent;
        JPanel insidePanel = new JPanel();
        insidePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 30));
        insidePanel.setBackground(new Color(236, 236, 236));
        insidePanel.setBorder(null);
        insidePanel.setPreferredSize(new Dimension(ComHard.WIDTH / 3, ComHard.LENGTH ));

        int totalItems = content.length;
        do {
            itemPanels = new JPanel[content.length];
            JPanel[] viewButtonPanel = new JPanel[content.length];
            itemImage = new JLabel[content.length];
            saveButtons = new Buttons[content.length];
            JTextPane[] itemNames = new JTextPane[content.length];
            JTextPane[] itemPart = new JTextPane[content.length];
            viewButtons = new JButton[content.length];
            for (int i = 0; i < itemPanels.length; i++) {
                fileContent = DuplicateClasses.fileContent(purpose,budget,content[i],set);

                itemPanels[i] = new JPanel();
                itemPanels[i].setBackground(Color.WHITE);
                itemPanels[i].setPreferredSize(new Dimension(264, 425));
                itemPanels[i].setLayout(new FlowLayout(FlowLayout.CENTER));
                itemPart[i] = new JTextPane();
                itemPart[i].setText(content[i]);
                StyledDocument documentStyle = itemPart[i].getStyledDocument();
                SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
                StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
                documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);
                itemPart[i].setFocusable(false);
                itemPart[i].setEditable(false);
                itemPart[i].setLayout(new FlowLayout(FlowLayout.CENTER));
                itemPart[i].setFont(new Font("", Font.BOLD, 24));
                itemPart[i].setPreferredSize(new Dimension(210, 45));
                itemPanels[i].add(itemPart[i]);

                itemImage[i] = new JLabel();
                itemImage[i].setIcon(new ImageIcon(DuplicateClasses.imageItems(DuplicateClasses.getItemName(fileContent),"items")));
                itemImage[i].setHorizontalAlignment(SwingConstants.LEFT);
                itemImage[i].setPreferredSize(new Dimension(DuplicateClasses.imageItems(DuplicateClasses.getItemName(fileContent),"items").getWidth(),
                        DuplicateClasses.imageItems(DuplicateClasses.getItemName(fileContent),"items").getHeight()));
                itemImage[i].add(Box.createHorizontalGlue());
                itemPanels[i].add(itemImage[i]);

                itemNames[i] = new JTextPane();
                itemNames[i].add(Box.createHorizontalGlue());
                itemNames[i].setText(DuplicateClasses.getItemName(fileContent));
                documentStyle = itemNames[i].getStyledDocument();
                documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);

                itemNames[i].setFocusable(false);
                itemNames[i].setEditable(false);
                itemNames[i].setFont(new Font("", Font.BOLD, 14));
                itemNames[i].setPreferredSize(new Dimension(210, 40));
                itemPanels[i].add(itemNames[i]);

                JTextPane[] itemPrice = new JTextPane[content.length];
                itemPrice[i] = new JTextPane();
                itemPrice[i].add(Box.createHorizontalGlue());
                itemPrice[i].setText(DuplicateClasses.getItemPrice(fileContent));
                documentStyle = itemPrice[i].getStyledDocument();
                documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);

                itemPrice[i].setFocusable(false);
                itemPrice[i].setEditable(false);
                itemPrice[i].setFont(new Font("", Font.BOLD, 14));
                itemPrice[i].setPreferredSize(new Dimension(210, 25));
                itemPanels[i].add(itemPrice[i]);

                itemPanels[i].add(Box.createRigidArea(new Dimension(220,5)));
                saveButtons[i] = new Buttons("Save");
                saveButtons[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
                saveButtons[i].setDimension(75, 25);
                if(new File("assets/favorites/"+userName+"/"+purpose+"/"+set+". "+budget+"/"+content[i]+".txt").exists()){
                    saveButtons[i].setLabel("Unsave");
                    saveButtons[i].setColor(new Color(213, 17, 17));
                    saveButtons[i].setTextColor(new Color(250,250,250));
                }else {
                    saveButtons[i].setColor(new Color(53,118,172));
                    saveButtons[i].setTextColor(new Color(250, 250, 250));
                }
                saveButtons[i].setFontSize(12);
                saveButtons[i].setBorderRadius(25);
                saveButtons[i].addTextPosition(0, -2);
                saveButtons[i].setOpaque(false);
                saveButtons[i].addMouseListener(new DuplicateClasses.SaveButtonListener(saveButtons[i], saveButtons[i],userName,purpose,set,budget,content[i],
                        DuplicateClasses.getItemName(fileContent), DuplicateClasses.getItemPrice(fileContent), DuplicateClasses.getItemAddress(fileContent)));
                itemPanels[i].add(saveButtons[i]);

                viewButtonPanel[i] = new JPanel();
                viewButtonPanel[i].setBackground(Color.WHITE);
                viewButtonPanel[i].setLayout(new FlowLayout(FlowLayout.CENTER));
                viewButtonPanel[i].setPreferredSize(new Dimension(210,24));
                viewButtons[i] = new JButton("View");
                viewButtons[i].setUI(new AccountPanel.DisableButtonPress());
                viewButtons[i].setBackground(null);
                viewButtons[i].setBorder(null);
                viewButtons[i].setForeground(new Color(51,51,51));
                viewButtons[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
                viewButtons[i].addMouseListener(new DuplicateClasses.UnderlinedText(viewButtons[i].getText(),viewButtons[i]));
                viewButtons[i].setFont(new Font("",Font.BOLD,14));
                viewButtons[i].addMouseListener(new ViewButtonListener(semiItemListPanel,this, saveButtons[i],userName,purpose,set,budget,content[i],
                        DuplicateClasses.getItemName(fileContent),DuplicateClasses.getItemPrice(fileContent),DuplicateClasses.getItemAddress(fileContent)));
                viewButtonPanel[i].add(viewButtons[i]);
                itemPanels[i].add(viewButtonPanel[i]);
                insidePanel.add(itemPanels[i]);
            }
            set++;
            content = DuplicateClasses.listTxtFiles("assets/items/" + purpose + "/"+set+". " + budget);
            totalItems += content.length;
        }while (content.length!=0);
        int row = totalItems /3;
        if(totalItems%3!=0) row+=1;
        int gap = totalItems/6;
        if(totalItems%6!=0) gap+=1;
        int height = 426*row + 143/row + gap*60;
        insidePanel.setPreferredSize(new Dimension(ComHard.WIDTH/3, height));
        forItemsAndNavbar.add(insidePanel,BorderLayout.CENTER);
        forItemsAndNavbar.setPreferredSize(new Dimension((int) (insidePanel.getPreferredSize().getWidth()),
                (int) (insidePanel.getPreferredSize().getHeight()+forNavbar.getPreferredSize().getHeight())));
        scrollPane = new JScrollPane(forItemsAndNavbar,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);
        scrollPane.setBackground(new Color(236,236,236));
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        SwingUtilities.invokeLater(() -> scrollPane.getViewport().setViewPosition(new Point(0,0)));
        semiItemListPanel.add(scrollPane,BorderLayout.CENTER);
        this.add(semiItemListPanel);
    }
    public void setForNavbar(){
        forNavbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        forNavbar.setBackground(new Color(236,236,236));
        backButton = new JLabel("     Back"){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                BufferedImage bufferedImage;
                try {
                    bufferedImage = ImageIO.read(new File("resources/img/backIcon.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                g.drawImage(bufferedImage,0,0,bufferedImage.getWidth()/4,bufferedImage.getHeight()/4,null);
                g.dispose();
            }
        };
        backButton.setFont(new Font("",Font.BOLD,20));
        backButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        backButton.addMouseListener(new BackButtonListener(this, homePanel, semiHonePanel,userName));
        JLabel purposeLabel = new JLabel("Purpose: "+purpose);
        JLabel budgetLabel = new JLabel("Budget: "+budget);
        purposeLabel.setForeground(new Color(51,51,51));
        purposeLabel.setFont(new Font("",Font.BOLD,24));
        budgetLabel.setForeground(new Color(51,51,51));
        budgetLabel.setFont(new Font("",Font.BOLD,24));

        forNavbar.add(backButton);
        forNavbar.add(Box.createHorizontalStrut(100));
        forNavbar.add(purposeLabel);
        forNavbar.add(Box.createHorizontalStrut(100));
        forNavbar.add(budgetLabel);
    }
    static class BackButtonListener extends MouseAdapter{
        JPanel panel, homePanel, semiHomePanel;
        String userName;

        public BackButtonListener(JPanel panel, JPanel homePanel, JPanel semiHomePanel, String userName){
            this.panel = panel;
            this.homePanel = homePanel;
            this.semiHomePanel = semiHomePanel;
            this.userName = userName;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            panel.setVisible(false);
            homePanel.add(semiHomePanel,BorderLayout.CENTER);
            semiHomePanel.setVisible(true);
        }
    }
    static class ViewButtonListener extends MouseAdapter{
        JPanel semiItemListPanel;
        JPanel itemListPanel;
        Buttons saveButton;
        String userName;
        String purpose;
        String budget;
        String part;
        String itemName;
        String itemPrice;
        String itemLocation;
        int set;

        public ViewButtonListener(JPanel semiItemListPanel, JPanel itemListPanel,Buttons saveButton, String userName, String purpose, int set, String budget, String part, String itemName, String itemPrice, String itemLocation){
            this.semiItemListPanel = semiItemListPanel;
            this.itemListPanel = itemListPanel;
            this.saveButton = saveButton;
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
            semiItemListPanel.setVisible(false);
            itemListPanel.add(new ViewItemPanel(itemListPanel, semiItemListPanel, saveButton, userName,purpose,set,budget,part,itemName,itemPrice,itemLocation),BorderLayout.CENTER);


        }
    }

}
