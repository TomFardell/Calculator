import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator {

    private double value = 0;

    private JFrame frame;
    private JTextField text;
    private JPanel panel;
    private JButton[] numberButtons = new JButton[10];
    private JButton[] functionButtons = new JButton[8];
    private JButton clrButton, delButton, dotButton, eqlButton, addButton, subButton, mulButton,
            divButton;

    public Calculator() {
        frame = new JFrame();

        text = new JTextField(value + "");

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(6, 3));

        for (int i = 9; i >= 0; i--) {
            numberButtons[i] = new JButton(i + "");
            panel.add(numberButtons[i]);
        }

        JButton clrButton = new JButton("AC");
        JButton delButton = new JButton("DEL");
        JButton dotButton = new JButton(".");
        JButton eqlButton = new JButton("=");
        JButton addButton = new JButton("+");
        JButton subButton = new JButton("-");
        JButton mulButton = new JButton("×");
        JButton divButton = new JButton("÷");
        functionButtons = new JButton[] { clrButton, delButton, dotButton, eqlButton, addButton,
                subButton, mulButton, divButton };

        for (int i = 0; i < 8; i++) {
            panel.add(functionButtons[i]);
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Calculator");
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new Calculator();
    }
}