import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class CustomCursorExample {
    public static void main(String[] args) {
        try {
            File cursorImageFile = new File("Iconos\\tarjeta.png");
            BufferedImage cursorImg = ImageIO.read(cursorImageFile);

            // Obtener el objeto Toolkit para crear el cursor personalizado
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Cursor customCursor = toolkit.createCustomCursor(cursorImg, new Point(0, 0), "CustomCursor");

            JFrame frame = new JFrame("Cursor");
            frame.setSize(200, 200);
            frame.setCursor(customCursor); // Establecer el cursor personalizado

            ImageIcon icono = new ImageIcon("Iconos\\nfc75.png");
            JLabel label = new JLabel();
            label.setIcon(icono);
            frame.add(label, BorderLayout.CENTER);

            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
