import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class HomePanel extends JPanel {
    JPanel dashboardPanel, semiHomePanel;
    Buttons button;
    String userName;
    ItemListPanel itemListPanel;
    JComboBox<String> purpose, budget;
    JLabel backgroundImage;

    public HomePanel(JPanel dashboardPanel, String userName){
        this.dashboardPanel = dashboardPanel;
        this.userName = userName;
        this.setPreferredSize(new Dimension(ComHard.WIDTH,ComHard.LENGTH));
        this.setBackground(new Color(245,245,245));
        this.setLayout(new BorderLayout());
        searchBox();
        this.setDoubleBuffered(true);
        backGround();
    }


    public void backGround(){

        backgroundImage = new JLabel();
        backgroundImage.setIcon(new ImageIcon(new ImageIcon("resources/img/dashboard.jpg").
                getImage().getScaledInstance(1082, 700,Image.SCALE_SMOOTH)));
        backgroundImage.setBounds(0,0,ComHard.WIDTH/2*3,ComHard.LENGTH);
        semiHomePanel.add(backgroundImage);
    }

    public void searchBox(){
        semiHomePanel = new JPanel();
        semiHomePanel.setPreferredSize(new Dimension(ComHard.WIDTH,ComHard.LENGTH));
        semiHomePanel.setBackground(new Color(245,245,245));
        semiHomePanel.setLayout(null);
        semiHomePanel.setDoubleBuffered(true);

        JPanel searchBox = new JPanel();
        searchBox.setLayout(new FlowLayout(FlowLayout.CENTER));
        searchBox.setBounds(365,180,300,300);
        searchBox.setBorder(new RoundedBorder(50));
        searchBox.setBackground(new Color(236,236,236,0));
        searchBox.setOpaque(true);
        JLabel label = new JLabel("What are you looking for?");
        label.setFont(new Font("",Font.BOLD,24));
        label.setForeground(new Color(53,118,172));
        searchBox.add(label);
        searchBox.add(Box.createRigidArea(new Dimension(150,15)));

        purpose = new JComboBox<>();
        purpose.setPreferredSize(new Dimension(230,50));
        purpose.addItem("Gaming");
        purpose.addItem("School or Work");
        purpose.addItem("Graphics Design");
        purpose.addItem("Video Editing");
        purpose.setFocusable(false);
        purpose.setBackground(Color.WHITE);
        purpose.setOpaque(true);
        purpose.setUI(new ComboBox());
        purpose.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(53,118,172)),BorderFactory.createEmptyBorder(0,5,0,0)));
        purpose.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedItem = (String) purpose.getSelectedItem();
                updateSecondComboBoxModel(budget, selectedItem);
            }
        });
        budget = new JComboBox<>();
        budget.setPreferredSize(new Dimension(230,50));
        budget.setFocusable(false);
        budget.setBackground(Color.WHITE);
        budget.setOpaque(true);
        budget.setUI(new ComboBox());
        budget.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(53,118,172)),BorderFactory.createEmptyBorder(0,5,0,0)));


        budget.addItem("40,000 and Below");
        budget.addItem("41,000 - 60,000");
        budget.addItem("61,000 - 80,000");
        budget.addItem("81,000 - 100,000");
        budget.addItem("100,000 Above");


        searchBox.add(purpose);
        searchBox.add(Box.createRigidArea(new Dimension(150,25)));
        searchBox.add(budget);
        button = new Buttons("Apply");
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setDimension(125,45);
        button.setColor(new Color(53,118,172));
        button.setFontSize(14);
        button.setBorderRadius(30);
        button.setOpaque(false);
        button.setTextColor(new Color(236,236,236));
        button.addMouseListener(new ApplyButtonListener(semiHomePanel,this,userName,purpose,budget));
        searchBox.add(Box.createRigidArea(new Dimension(200,25)));
        searchBox.add(button);
        searchBox.revalidate();
        searchBox.repaint();
        semiHomePanel.add(searchBox);
        this.add(semiHomePanel,BorderLayout.CENTER);
    }
    public void updateSecondComboBoxModel(JComboBox<String> comboBox, String selectedItem) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        if ("Video Editing".equals(selectedItem) || "Graphics Design".equals(selectedItem)) {
            model.addElement("41,000 - 60,000");
            model.addElement("61,000 - 80,000");
            if("Graphics Design".equals(selectedItem)) model.addElement("81,000 - 100,000");
            model.addElement("100,000 Above");
        }else {
            for (String item : new String[]{"40,000 and Below", "41,000 - 60,000", "61,000 - 80,000", "81,000 - 100,000","100,000 Above"}) {
                model.addElement(item);
            }
        }
        comboBox.setModel(model);
    }

    class ApplyButtonListener extends MouseAdapter{
        JPanel semiHomePanel;
        JPanel homePanel;
        String userName;
        JComboBox<String> purpose;
        JComboBox<String> budget;

        public ApplyButtonListener(JPanel semiHomePanel, JPanel homePanel, String userName, JComboBox<String> purpose, JComboBox<String> budget){
            this.semiHomePanel = semiHomePanel;
            this.homePanel = homePanel;
            this.userName = userName;
            this.purpose = purpose;
            this.budget = budget;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            SwingUtilities.invokeLater(() -> {
                semiHomePanel.setVisible(false);
                itemListPanel = new ItemListPanel(semiHomePanel, homePanel, userName, purpose.getItemAt(purpose.getSelectedIndex()),budget.getItemAt(budget.getSelectedIndex()));
                homePanel.add(itemListPanel,BorderLayout.CENTER);
            });

        }
    }

    static class RoundedBorder extends AbstractBorder {
        private final int cornerRadius;

        public RoundedBorder(int cornerRadius) {
            this.cornerRadius = cornerRadius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();

            // Set rendering hints for smoother edges
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw a rounded rectangle border
            g2d.setColor(new Color(236,236,236,225));
            g2d.setClip(new RoundRectangle2D.Float(x-25,y-25,width+50,height+50,cornerRadius,cornerRadius));
            g2d.fillRoundRect(x-25, y-25, width+50, height+50, cornerRadius, cornerRadius);

            g2d.dispose();
        }

    }
    static class ComboBox extends BasicComboBoxUI{
        public ComboBox(){

        }

        @Override
        protected JButton createArrowButton() {
            JButton button = new JButton();
            button.setUI(new MetalButtonUI(){
                @Override
                protected void paintButtonPressed(Graphics g, AbstractButton b) {
                }
            });
            button.setBorder(null);
            button.setBackground(Color.WHITE);
            return button;
        }
    }
}
