import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class CambiarIconoMouse {
    
    public static void main(String[] args) {
        AtomicBoolean cursorGuapo = new AtomicBoolean(false);
        JFrame frame = new JFrame("Cambiar Icono del Mouse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        
        JPanel panel = new JPanel();
        JButton button = new JButton("CROSSHAIR_CURSOR");
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(button);
        frame.add(panel);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cursorGuapo.get()) {
                    cursorGuapo.set(false);
                } else {
                    cursorGuapo.set(true);
                }
            }
        });

        // Acción para cambiar el cursor cuando se pasa sobre la ventana
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (cursorGuapo.get()) {
                    frame.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                } else {
                    frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                frame.setCursor(Cursor.getDefaultCursor());
            }
        });
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
