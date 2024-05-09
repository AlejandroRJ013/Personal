import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrimeCounter {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Prime Counter");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 150);
            frame.setLayout(new BorderLayout());

            JLabel label = new JLabel("Enter a number:");
            JTextField textField = new JTextField(10);
            JButton button = new JButton("Count");
            JLabel resultLabel = new JLabel();

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int number = Integer.parseInt(textField.getText());
                    int count = countPrimesBelow(number);
                    resultLabel.setText("Number of primes below " + number + ": " + count);
                }
            });

            frame.add(label, BorderLayout.WEST);
            frame.add(textField, BorderLayout.CENTER);
            frame.add(button, BorderLayout.EAST);
            frame.add(resultLabel, BorderLayout.SOUTH);

            frame.setVisible(true);
        });
    }

    public static int countPrimesBelow(int number) {
        if (number < 2) {
            return 0;
        }
        int count = 0;
        for (int i = 2; i < number; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }

    public static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}