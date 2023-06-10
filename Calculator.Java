import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {

    static final Font BUTTON_FONT = new Font("Consolas", Font.BOLD, 40);
    static final Font TEXT_FONT = new Font("Consolas", Font.BOLD, 30);
    static final Color COMPONENT_COLOR = new Color(240, 241, 243);
    static final Color COMPONENT_TEXT_COLOR = new Color(13, 12, 16);
    static final Color BACKGROUND_COLOR = new Color(54, 54, 56);

    private enum Operator {
        ADD, SUB, MUL, DIV
    };

    private double storedValue = 0;
    private Operator queuedOperator;

    private JFrame frame;

    private JPanel panel = new JPanel();
    private GridBagConstraints gbc = new GridBagConstraints();

    private JTextField textField;
    private JButton[] numberButtons = new JButton[10];
    private JButton[] functionButtons = new JButton[8];
    private JButton clrButton, delButton, dotButton, eqlButton, addButton, subButton, mulButton,
            divButton;

    public Calculator() {
        frame = new JFrame();

        setupButtons();
        setupPanel();

        frame.add(panel, BorderLayout.CENTER);

        frame.setTitle("Calculator");
        frame.setPreferredSize(new Dimension(400, 600));
        frame.setLocation(760, 240);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    // Sets up the buttons and their ActionListeners
    private void setupButtons() {
        // --- Number Buttons ---
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(i + "");
            numberButtons[i].addActionListener(this);
            setupAppearance(numberButtons[i]);
        }

        // --- Function Buttons ---
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

        // Adds an ActionListener to each function buttons
        for (int i = 0; i < 8; i++) {
            functionButtons[i].addActionListener(this);
            setupAppearance(functionButtons[i]);
        }
    }

    // Sets up the panel with correct button layout
    private void setupPanel() {
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(BACKGROUND_COLOR);

        panel.setLayout(new GridBagLayout());

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 0.8;
        gbc.weighty = 0.8;

        // Text box
        textField = new JTextField("");
        setupAppearance(textField);
        addGB(textField, 0, 0, 4);

        // --- Number buttons ---
        // Zero button is placed following a different rule from 1-9
        addGB(numberButtons[0], 0, 4);

        // 1-9 buttons
        for (int i = 1; i < 10; i++) {
            addGB(numberButtons[i], (i - 1) % 3, 3 - ((i - 1) / 3));
        }

        // --- Function buttons ---

        // AC and DEL buttons
        for (int i = 0; i < 2; i++) {
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

    // Method to set the appearance of a calculator button
    private void setupAppearance(JButton button) {
        button.setFont(BUTTON_FONT);
        button.setFocusable(false);
        button.setBackground(COMPONENT_COLOR);
        button.setForeground(COMPONENT_TEXT_COLOR);
    }

    // Method to set the appearnce of the text field
    private void setupAppearance(JTextField textField) {
        textField.setFont(TEXT_FONT);
        textField.setFocusable(false);
        textField.setBackground(COMPONENT_COLOR);
        textField.setForeground(COMPONENT_TEXT_COLOR);
    }

    // Method for if a number button is pressed where n is the number
    private void numberPressed(int n) {
        textField.setText(textField.getText() + n);
    }

    // Method for if the clear button is pressed
    private void clrPressed() {
        textField.setText("");
        storedValue = 0;
        queuedOperator = null;
    }

    // Method for if the delete button is pressed
    private void delPressed() {
        String text = textField.getText();
        if (text.length() > 0) {
            textField.setText(text.substring(0, text.length() - 1));
        }
    }

    // Method for if the dot button is pressed
    private void dotPressed() {
        if (!textField.getText().contains(".")) {
            textField.setText(textField.getText() + ".");
        }
    }

    // Method for if the equals button is pressed
    private void eqlPressed() {
        double currentValue = Double.parseDouble(textField.getText());

        if (queuedOperator == Operator.ADD) {
            textField.setText(storedValue + currentValue + "");
        } else if (queuedOperator == Operator.SUB) {
            textField.setText(storedValue - currentValue + "");
        } else if (queuedOperator == Operator.MUL) {
            textField.setText(storedValue * currentValue + "");
        } else if (queuedOperator == Operator.DIV) {
            if (currentValue == 0) {
                // Bit of a bodge for now
                textField.setText("0");
            } else {
                textField.setText(storedValue / currentValue + "");
            }
        }
    }

    // Method for if an operator button (+-*/) is pressed
    private void operatorPressed(Operator operator) {
        storedValue = Double.parseDouble(textField.getText());
        queuedOperator = operator;
        textField.setText("");
    }

    public static void main(String[] args) {
        new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        // Checks the source to see if it is a number button
        for (int i = 0; i < 10; i++) {
            if (source == numberButtons[i]) {
                numberPressed(i);
            }
        }

        // Checks the source against each function button
        if (source == clrButton) {
            clrPressed();
        }
        if (source == delButton) {
            delPressed();
        }
        if (source == dotButton) {
            dotPressed();
        }
        if (source == eqlButton) {
            eqlPressed();
        }
        if (source == addButton) {
            operatorPressed(Operator.ADD);
        }
        if (source == subButton) {
            operatorPressed(Operator.SUB);
        }
        if (source == mulButton) {
            operatorPressed(Operator.MUL);
        }
        if (source == divButton) {
            operatorPressed(Operator.DIV);
        }
    }
}