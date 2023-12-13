import javax.swing.*;
import java.awt.*;


public class ComHardFrame extends JFrame {
   public ComHardFrame(){
      SwingUtilities.invokeLater(() -> {
      this.setSize(new Dimension(ComHard.WIDTH,ComHard.LENGTH));
      this.setLocationRelativeTo(null);
      this.setIconImage(new ImageIcon("resources/img/logo.png").getImage());
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setTitle("ComHard");
      this.setResizable(false);
      //this.add(new SignPanel(this));
      this.add(new DashboardPanel(this, new SignPanel(this),"Whistle"));
      this.setVisible(true);
      });
   }
}