import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class RoundedButton extends JButton {

    private Color borderColor = Color.BLUE;
    private int arco = 0;

    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);
    }

    public RoundedButton(String text, int arco) {
        super(text);

        this.arco=arco;
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray);
        } else {
            g.setColor(getBackground());
        }
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arco, arco);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(borderColor);
        g2.draw(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, arco, arco));
        g2.dispose();
    }

    @Override
    public boolean contains(int x, int y) {
        return new Rectangle(0, 0, getWidth(), getHeight()).contains(x, y);
    }

    public void setBorderColor(Color color) {
        borderColor = color;
        repaint();
    }
}