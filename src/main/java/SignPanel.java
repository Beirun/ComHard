import javax.swing.*;
import java.awt.*;

public class SignPanel extends JPanel {
    JFrame frame;
    public SignPanel(JFrame frame){
        this.frame = frame;
        this.setBounds(0,0,ComHard.WIDTH,ComHard.LENGTH);
        this.setBackground(new Color(162,221,164));
        this.setLayout(new BorderLayout());
        this.setFocusable(true);
        this.add(new BackgroundClass(),BorderLayout.CENTER);
        this.add(new LoginPanel(frame,this),BorderLayout.EAST);
    }
}
