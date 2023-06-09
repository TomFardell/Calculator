import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator {

    private double value = 0;

    private JFrame frame;

    private JPanel panel;
    private GridBagConstraints gbc = new GridBagConstraints();

    private JTextField text;
    private JButton[] numberButtons = new JButton[10];
    private JButton[] functionButtons = new JButton[8];
    private JButton clrButton, delButton, dotButton, eqlButton, addButton, subButton, mulButton,
            divButton;

    public Calculator() {
        frame = new JFrame();

        setupPanel();

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Calculator");
        frame.setPreferredSize(new Dimension(400, 600));
        frame.setLocation(760, 240);
        frame.pack();
        frame.setVisible(true);
    }

    // Sets up the panel with correct button layout
    private void setupPanel() {
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.setLayout(new GridBagLayout());

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 0.8;
        gbc.weighty = 0.8;

        // Text box
        text = new JTextField(value + "");
        addGB(text, 0, 0, 4);

        // --- Number buttons ---
        // Zero button is placed following a different rule from 1-9
        numberButtons[0] = new JButton("0");
        addGB(numberButtons[0], 0, 4);

        // 1-9 buttons
        for (int i = 1; i < 10; i++) {
            numberButtons[i] = new JButton(i + "");
            addGB(numberButtons[i], (i - 1) % 3, 3 - ((i - 1) / 3));
        }

        // --- Function buttons ---
        clrButton = new JButton("AC");
        delButton = new JButton("DEL");
        dotButton = new JButton(".");
        eqlButton = new JButton("=");
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("×");
        divButton = new JButton("÷");
        functionButtons = new JButton[] { clrButton, delButton, dotButton, eqlButton, addButton,
                subButton, mulButton, divButton };

        // AC and DEL buttons
        for (int i = 0; i < 2; i++) {
            gbc.gridx = i * 2;
            gbc.gridy = 5;
            gbc.gridwidth = 2;
            addGB(functionButtons[i], i * 2, 5, 2);
        }

        // . and = buttons
        for (int i = 2; i < 4; i++) {
            addGB(functionButtons[i], i - 1, 4);
        }

        // Operator buttons
        for (int i = 4; i < 8; i++) {
            addGB(functionButtons[i], 3, i - 3);
        }
    }

    // Given a component and x, y and width, places the component on the
    // GridBagLayout at this position
    private void addGB(Component component, int x, int y, int width) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        panel.add(component, gbc);
    }

    // Constructor for default width of 1
    private void addGB(Component component, int x, int y) {
        addGB(component, x, y, 1);
    }

    public static void main(String[] args) {
        new Calculator();
    }
}