import javax.swing.*;

public class Test {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Rounded Button Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        RoundedButton button = new RoundedButton("Click me!", 25);

        frame.getContentPane().add(button);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}