import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends JPanel {
    JPanel RegisterBox;
    JTextField login;
    public RegisterPanel(){
        this.setBounds(0,0,ComHard.WIDTH,ComHard.LENGTH);
        this.setBackground(new Color(162,221,164));
        this.setLayout(new BorderLayout());
        registerBox();
    }

    public void registerBox(){
        RegisterBox = new JPanel();
        RegisterBox.setPreferredSize(new Dimension(ComHard.WIDTH/3,ComHard.LENGTH*5/6));
        RegisterBox.setBackground(new Color(236,236,236));

        this.add(RegisterBox, BorderLayout.EAST);
    }
}
