import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Buttons extends JPanel {
    final private String Label;
    private int width = 100, height = 50, arc = 45, buttonX = 0, buttonY = 0, fontSize = 13;
    private Color colorSelect = new Color(117,210,104), colorNormal, colorEntered, foreground = new Color(38,38,38);
    boolean isMousePressed = false, isMouseReleased = false, isMouseEntered = false;
    int xTextCoordinate;
    int yTextCoordinate;

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
    public void setTextColor(Color foreground){
        this.foreground = foreground;
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
    public void addTextPosition(int xTextCoordinate, int yTextCoordinate){
        this.xTextCoordinate = xTextCoordinate;
        this.yTextCoordinate = yTextCoordinate;
    }
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void Draw(Graphics g){
        colorNormal = new Color(colorSelect.getRed(), colorSelect.getGreen(), colorSelect.getBlue());
        colorEntered = new Color(colorSelect.getRed(), colorSelect.getGreen(), colorSelect.getBlue(), 175);
        Color colorPressed = new Color(colorSelect.getRed(), colorSelect.getGreen(), colorSelect.getBlue(), 125);

        if(isMouseEntered || isMouseReleased) g.setColor(colorEntered);
        else if(isMousePressed) g.setColor(colorPressed);
        else g.setColor(colorNormal);
        Rectangle(g);
    }

    public void Rectangle(Graphics g){
        g.fillRoundRect(buttonX,buttonY,width,height,arc,arc);
        if(isMouseEntered || isMouseReleased)g.setColor(colorEntered);
        else if(isMousePressed) g.setColor(Color.GRAY.brighter());
        else g.setColor(colorNormal);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g.drawRoundRect(buttonX+1,buttonY+1,width-2,height-2,arc,arc);
        g.setColor(foreground);
        g.setFont( new Font("",Font.BOLD, fontSize));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString(Label,(width - metrics.stringWidth(Label))/2+xTextCoordinate,height/2 + g.getFont().getSize()/2+yTextCoordinate);
        g2.dispose();
    }

    class ButtonChanger extends MouseAdapter{

        public ButtonChanger( ){}
        public void mousePressed(MouseEvent e) {
            isMousePressed = true;
            isMouseEntered = false;
            isMouseReleased = false;
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            isMousePressed = false;
            isMouseEntered = false;
            isMouseReleased = true;
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            isMousePressed = false;
            isMouseEntered = true;
            isMouseReleased = false;
            repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            isMousePressed = false;
            isMouseEntered = false;
            isMouseReleased = false;
            repaint();
        }
    }
}