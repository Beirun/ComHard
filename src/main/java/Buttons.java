import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Buttons extends JPanel {
    final private String Label;
    private int width = 100;
    private int height = 50;
    private int arc = 45;
    private int buttonX = 0;
    private int buttonY = 0;
    private int fontSize = 13;
    private Color colorSelect = new Color(117,210,104);
    boolean mousePressed = false, mouseReleased = false, mouseEntered = false;
    public Buttons(String Label){
        this.Label = Label;
        this.setPreferredSize(new Dimension(width,height));
        this.setBackground(null);
        this.addMouseListener(new ButtonChanger());
    }
    public void setColor(Color color){
        colorSelect = color;
    }
    public void setPosition(int buttonX, int buttonY){
        this.buttonX = buttonX;
        this.buttonY = buttonY;
    }
    public void setDimension(int width, int height){
        this.width = width;
        this.height = height;
        this.setPreferredSize(new Dimension(width, height));
    }
    public void setBorderRadius(int arc){
        this.arc = arc;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Draw(g);

    }
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void Draw(Graphics g){
        Color colorNormal = new Color(colorSelect.getRed(), colorSelect.getGreen(), colorSelect.getBlue());
        Color colorPressed = new Color(colorSelect.getRed(), colorSelect.getGreen(), colorSelect.getBlue(), 125);
        Color colorEntered = new Color(colorSelect.getRed(), colorSelect.getGreen(), colorSelect.getBlue(), 175);

        if(mouseEntered || mouseReleased) g.setColor(colorEntered);
        else if(mousePressed) g.setColor(colorPressed);
        else g.setColor(colorNormal);
        Rectangle(g);
    }

    public void RectangleBorder(){

    }

    public void Rectangle(Graphics g){
        g.fillRoundRect(buttonX,buttonY,width,height,arc,arc);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g.setColor(Color.LIGHT_GRAY);
        g.drawRoundRect(buttonX+1,buttonY+1,width-2,height-2,arc,arc);
        g.setColor(new Color(38,38,38));
        g.setFont( new Font("",Font.BOLD, fontSize));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString(Label,(width - metrics.stringWidth(Label))/2,height/2 + g.getFont().getSize()/2);

    }

    class ButtonChanger extends MouseAdapter{

        public ButtonChanger( ){}
        public void mousePressed(MouseEvent e) {
            mousePressed = true;
            mouseEntered = false;
            mouseReleased = false;
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            mousePressed = false;
            mouseEntered = false;
            mouseReleased = true;
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            mousePressed = false;
            mouseEntered = true;
            mouseReleased = false;
            repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            mousePressed = false;
            mouseEntered = false;
            mouseReleased = false;
            repaint();
        }
    }
}
