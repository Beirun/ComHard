import javax.swing.*;
import java.awt.*;

public class ItemListPanel extends JPanel {
    JPanel dashboardPanel;
    String userName;
    JPanel forItems, forFilter, forItemsAndNavbar, forNavbar;

    String purpose, budget;

    public ItemListPanel(JPanel dashboardPanel, String userName, String purpose, String budget){
        this.dashboardPanel = dashboardPanel;
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
        forItems = new JPanel();
        forItems.setBackground(null);
        forItems.setPreferredSize(new Dimension(800,600));

        forFilter = new JPanel();
        forFilter.setBackground(null);
        forFilter.setPreferredSize(new Dimension(100,600));
        this.add(forFilter,BorderLayout.EAST);

        forNavbar = new JPanel();
        forNavbar.setBackground(null);
        forNavbar.setPreferredSize(new Dimension(ComHard.WIDTH,50));

        forItemsAndNavbar = new JPanel();
        forItemsAndNavbar.setLayout(new BorderLayout());
        forItemsAndNavbar.setBackground(new Color(236,236,236));
        forItemsAndNavbar.add(forNavbar,BorderLayout.NORTH);
        forItemsAndNavbar.add(forItems,BorderLayout.CENTER);
        this.add(forItemsAndNavbar,BorderLayout.CENTER);
    }

    public void setForItems(){
        forFilter.setBackground(new Color(245,245,245));

    }
    public void setForFilter(){


    }
    public void setForNavbar(){

    }
}
