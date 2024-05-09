import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PrimeCounter2 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Prime Counter");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 400);
            frame.setLayout(new BorderLayout());

            JLabel label = new JLabel("Enter a number:");
            JTextField textField = new JTextField(10);
            JButton button = new JButton("Count");
            JLabel resultLabel = new JLabel();

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int number = Integer.parseInt(textField.getText());
                    List<Integer> primes = countPrimesBelow(number);
                    resultLabel.setText("Number of primes below " + number + ": " + primes.size());
                    JPanel primeListPanel = new JPanel();
                    primeListPanel.setLayout(new BoxLayout(primeListPanel, BoxLayout.Y_AXIS));
                    for (int prime : primes) {
                        JLabel primeLabel = new JLabel(String.valueOf(prime));
                        primeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        primeListPanel.add(primeLabel);
                    }
                    frame.getContentPane().remove(resultLabel);
                    frame.getContentPane().add(primeListPanel, BorderLayout.CENTER);
                    frame.getContentPane().revalidate();
                    frame.getContentPane().repaint();
                }
            });

            frame.add(label, BorderLayout.WEST);
            frame.add(textField, BorderLayout.CENTER);
            frame.add(button, BorderLayout.EAST);
            frame.add(resultLabel, BorderLayout.SOUTH);

            frame.setVisible(true);
        });
    }

    public static List<Integer> countPrimesBelow(int number) {
        if (number < 2) {
            return new ArrayList<>();
        }
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i < number; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
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