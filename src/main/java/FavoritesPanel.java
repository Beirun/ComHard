import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class FavoritesPanel extends JPanel implements ActionListener {
    JButton noResultsButton, homeButton;
    JPanel dashboardPanel;
    String userName;
    Buttons[] saveButtons;
    JPanel[] itemPanels;
    JLabel[] itemImage;
    JScrollPane scrollPane;

    public FavoritesPanel(JButton homeButton, JPanel dashboardPanel, String userName){
        this.homeButton = homeButton;
        this.dashboardPanel = dashboardPanel;
        this.userName = userName;
        this.setPreferredSize(new Dimension(ComHard.WIDTH,ComHard.LENGTH));
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(245,245,245));
        favoritesPanel();
    }

    public void favoritesPanel(){
        String [] purpose = listDirectories("assets/favorites/"+userName);
        JPanel insidePanel = new JPanel();
        int totalItems = 0;
        insidePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 30));
        insidePanel.setBackground(new Color(236, 236, 236));
        insidePanel.setBorder(null);
        insidePanel.setPreferredSize(new Dimension(ComHard.WIDTH*2/3, ComHard.LENGTH));
        if(purpose.length==0){
            insidePanel.add(Box.createVerticalStrut(675));
            JPanel noResultsPanel = new JPanel();
            noResultsPanel.setBackground(null);
            noResultsPanel.setPreferredSize(new Dimension(500,200));
            noResultsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            JLabel noResultsLabel = new JLabel("No Favorites Saved  ");
            noResultsLabel.setForeground(new Color(100, 100, 100));
            noResultsLabel.setFont(new Font("", Font.BOLD,50));
            noResultsButton = new JButton("Look for items!");
            noResultsButton.setFont(new Font("", Font.BOLD,20));
            noResultsButton.setForeground(new Color(100, 100, 100));
            noResultsButton.setBackground(new Color(236,236,236));
            noResultsButton.setUI(new AccountPanel.DisableButtonPress());
            noResultsButton.addMouseListener(new DuplicateClasses.UnderlinedText(noResultsButton.getText(),noResultsButton));
            noResultsButton.setBorder(null);
            noResultsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            noResultsButton.addActionListener(this);

            noResultsPanel.add(noResultsLabel);
            noResultsPanel.add(noResultsButton);
            insidePanel.add(noResultsPanel);
            this.add(insidePanel);
        }
        else {
            for (int i = 0; i < purpose.length; i++) {
                String[] budget = listDirectories("assets/favorites/" + userName + "/" + purpose[i]);
                for (int j = 0; j < budget.length; j++) {
                    String[] content = DuplicateClasses.listTxtFiles("assets/favorites/" + userName + "/" + purpose[i] + "/" + budget[j]);
                    int set = 0;
                    totalItems += content.length;
                    String[] fileContent;
                    itemPanels = new JPanel[content.length];
                    itemImage = new JLabel[content.length];
                    saveButtons = new Buttons[content.length];

                    for (int k = 0; k < itemPanels.length; k++) {
                        fileContent = DuplicateClasses.fileContent(purpose[i], budget[j], content[k], set);
                        itemPanels[k] = new JPanel();
                        itemPanels[k].setBackground(Color.WHITE);
                        itemPanels[k].setPreferredSize(new Dimension(264, 425));
                        itemPanels[k].setLayout(new FlowLayout(FlowLayout.CENTER));
                        itemImage[k] = new JLabel();
                        itemImage[k].setIcon(new ImageIcon(DuplicateClasses.imageItems(DuplicateClasses.getItemName(fileContent), "items")));
                        itemImage[k].setHorizontalAlignment(SwingConstants.LEFT);
                        itemImage[k].setPreferredSize(new Dimension(DuplicateClasses.imageItems(DuplicateClasses.getItemName(fileContent), "items").getWidth(),
                                DuplicateClasses.imageItems(DuplicateClasses.getItemName(fileContent), "items").getHeight()));
                        itemImage[k].add(Box.createHorizontalGlue());

                        itemPanels[k].add(itemImage[k]);

                        JTextPane[] itemNames = new JTextPane[content.length];
                        itemNames[k] = new JTextPane();
                        itemNames[k].add(Box.createHorizontalGlue());
                        itemNames[k].setText(DuplicateClasses.getItemName(fileContent));
                        StyledDocument documentStyle = itemNames[k].getStyledDocument();
                        SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
                        StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
                        documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);

                        itemNames[k].setFocusable(false);
                        itemNames[k].setEditable(false);
                        itemNames[k].setFont(new Font("", Font.BOLD, 14));
                        itemNames[k].setPreferredSize(new Dimension(210, 45));
                        itemPanels[k].add(itemNames[k]);

                        JTextPane[] itemPrice = new JTextPane[content.length];
                        itemPrice[k] = new JTextPane();
                        itemPrice[k].add(Box.createHorizontalGlue());
                        itemPrice[k].setText(DuplicateClasses.getItemPrice(fileContent));
                        documentStyle = itemPrice[k].getStyledDocument();
                        centerAttribute = new SimpleAttributeSet();
                        StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
                        documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);

                        itemPrice[k].setFocusable(false);
                        itemPrice[k].setEditable(false);
                        itemPrice[k].setFont(new Font("", Font.BOLD, 14));
                        itemPrice[k].setPreferredSize(new Dimension(210, 25));
                        itemPanels[k].add(itemPrice[k]);

                        JTextPane[] storeAddresses = new JTextPane[content.length];
                        storeAddresses[k] = new JTextPane();
                        storeAddresses[k].setText(DuplicateClasses.getItemAddress(fileContent));
                        documentStyle = storeAddresses[k].getStyledDocument();
                        centerAttribute = new SimpleAttributeSet();
                        StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
                        documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);
                        storeAddresses[k].setFocusable(false);
                        storeAddresses[k].setEditable(false);
                        storeAddresses[k].setLayout(new FlowLayout(FlowLayout.CENTER));
                        storeAddresses[k].setFont(new Font("", Font.PLAIN, 14));
                        storeAddresses[k].setPreferredSize(new Dimension(210, 65));
                        itemPanels[k].add(storeAddresses[k]);

                        saveButtons[k] = new Buttons("Save");
                        saveButtons[k].setCursor(new Cursor(Cursor.HAND_CURSOR));
                        saveButtons[k].setDimension(75, 25);
                        if (new File("assets/favorites/" + userName + "/" + purpose[i] + "/" + budget[j] + "/" + content[k] + ".txt").exists()) {
                            saveButtons[k].setLabel("Unsave");
                            saveButtons[k].setColor(new Color(213, 17, 17));
                            saveButtons[k].setTextColor(new Color(250, 250, 250));
                        } else {
                            saveButtons[i].setColor(new Color(53, 118, 172));
                            saveButtons[i].setTextColor(new Color(250, 250, 250));
                        }
                        saveButtons[k].setFontSize(12);
                        saveButtons[k].setBorderRadius(25);
                        saveButtons[k].addTextPosition(0, -2);
                        saveButtons[k].setOpaque(false);
                        saveButtons[k].addMouseListener(new DuplicateClasses.SaveButtonListener(saveButtons[k], userName, purpose[i], set, budget[j], content[k],
                                DuplicateClasses.getItemName(fileContent), DuplicateClasses.getItemPrice(fileContent), DuplicateClasses.getItemAddress(fileContent)));
                        itemPanels[k].add(saveButtons[k]);
                        insidePanel.add(itemPanels[k]);
                    }
                }
            }
            int row = totalItems / 3;
            if (totalItems % 3 != 0) row += 1;
            int gap = totalItems / 6;
            if (totalItems % 6 != 0) gap += 1;
            int height = 424 * row + 148 / row + gap * 60;
            insidePanel.setPreferredSize(new Dimension(ComHard.WIDTH / 3, height));
            this.setPreferredSize(insidePanel.getPreferredSize());
            scrollPane = new JScrollPane(insidePanel,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setBorder(null);
            scrollPane.setBackground(new Color(236, 236, 236));
            scrollPane.getVerticalScrollBar().setUnitIncrement(10);
            SwingUtilities.invokeLater(() -> scrollPane.getViewport().setViewPosition(new Point(0, 0)));
            this.add(scrollPane, BorderLayout.CENTER);
        }
    }
    public String[] listDirectories(String directoryPath){
        File directory = new File(directoryPath);
        File[] directoryNames = directory.listFiles();
        String[] fileNames = new String[0];
        if (directoryNames != null) {
            fileNames = new String[directoryNames.length];
            for (int i = 0; i < directoryNames.length; i++) fileNames[i] = directoryNames[i].getName();
        }
        return fileNames;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==noResultsButton){
            homeButton.doClick();
            homeButton.setEnabled(false);
            homeButton.setBackground(new Color(23,88,142));
        }
    }
}
