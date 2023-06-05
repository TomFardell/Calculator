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
    private JButton clrButton, delButton, dotButton, eqlButton, addButton, subButton, mulButton, divButton;

    public Calculator() {
        frame = new JFrame();

        text = new JTextField(value + "");

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(4, 4));
    }

    public static void main(String[] args) {
        new Calculator();
    }
}