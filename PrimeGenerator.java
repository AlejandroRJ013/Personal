import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PrimeGenerator {
    private JFrame frame;
    private JTextField textField;
    private JLabel resultLabel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PrimeGenerator window = new PrimeGenerator();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public PrimeGenerator() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Prime Generator");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setBackground(Color.gray);
    
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.setBackground(Color.gray); // gris claro
        frame.getContentPane().add(panel);
    
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.setBackground(new Color(255, 255, 255)); // blanco
        panel.add(inputPanel);
    
        JLabel titleLabel = new JLabel("Prime Generator");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(153, 50, 204)); // morado
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        inputPanel.add(titleLabel);
    
        textField = new JTextField();
        textField.setColumns(10);
        inputPanel.add(textField);
    
        JButton generateButton = new JButton("Generate");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = Integer.parseInt(textField.getText());
                List<Integer> primes = getPrimesBelow(num);
                resultLabel.setText("Primes below " + num + ": " + primes);
            }
        });
        generateButton.setBackground(new Color(0, 120, 0)); // verde oscuro
        generateButton.setForeground(Color.WHITE);
        inputPanel.add(generateButton);
    
        resultLabel = new JLabel("Primes below:");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 18));
        resultLabel.setHorizontalAlignment(JLabel.CENTER);
        resultLabel.setForeground(new Color(0, 50, 200)); // azul
        panel.add(resultLabel);
    
        frame.setVisible(true);
    }

    private List<Integer> getPrimesBelow(int num) {
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= num; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
    }

    private boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}