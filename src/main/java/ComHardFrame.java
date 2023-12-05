import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ComHardFrame extends JFrame {
   public ComHardFrame(){
      this.setSize(new Dimension(ComHard.WIDTH,ComHard.LENGTH));
      this.setLocationRelativeTo(null);
      this.setIconImage(new ImageIcon("resources/img/logo.png").getImage());
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setTitle("ComHard");
      this.setResizable(false);
      this.add(new SignPanel(this));
      this.setVisible(true);
   }
}