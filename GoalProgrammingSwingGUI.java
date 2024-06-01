import javax.swing.*; //include the entire `javax.swing` package
import java.awt.*;//includes the entire `java.awt` package
import java.awt.event.ActionEvent;//represents an event generated
import java.awt.event.ActionListener;//action event occurs.
import java.awt.event.WindowAdapter;//default implementations for window-related 
import java.awt.event.WindowEvent;//represents an event related to a window

public class GoalProgrammingSwingGUI extends JFrame {
    // The main window of the GUI application.
    private JTextField // input
    c1Field, c2Field, a11Field, a12Field, b1Field, a21Field, a22Field, b2Field, goalField;
    private JButton // button
    calculateButton;
    private JLabel // display text labels.
    solutionLabel, profitLabel, deviationLabel;
    private JPanel // container to hold info
    resultPanel;

    public GoalProgrammingSwingGUI() {
        setTitle("Goal Programming Example");

        setLayout(new BorderLayout());// gui window layout

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(0, 2));// arrangemnt of rows and columns

        inputPanel.add(new JLabel("Coefficient c1:"));// display
        c1Field = new JTextField();// input dabba
        inputPanel.add(c1Field);// adding dabba to input panel ka dispaly

        inputPanel.add(new JLabel("Coefficient c2:"));
        c2Field = new JTextField();
        inputPanel.add(c2Field);

        inputPanel.add(new JLabel("a11:"));
        a11Field = new JTextField();
        inputPanel.add(a11Field);

        inputPanel.add(new JLabel("a12:"));
        a12Field = new JTextField();
        inputPanel.add(a12Field);

        inputPanel.add(new JLabel("b1:"));
        b1Field = new JTextField();
        inputPanel.add(b1Field);

        inputPanel.add(new JLabel("a21:"));
        a21Field = new JTextField();
        inputPanel.add(a21Field);

        inputPanel.add(new JLabel("a22:"));
        a22Field = new JTextField();
        inputPanel.add(a22Field);

        inputPanel.add(new JLabel("b2:"));
        b2Field = new JTextField();
        inputPanel.add(b2Field);

        inputPanel.add(new JLabel("Goal 1:"));
        goalField = new JTextField();
        inputPanel.add(goalField);
        inputPanel.setBackground(new Color(255, 204, 153));// cutomization
        add(inputPanel, BorderLayout.NORTH);// placement top

        // Calculate" button
        JPanel buttonPanel = new JPanel();
        calculateButton = new JButton("Calculate");
        buttonPanel.add(calculateButton);
        add(buttonPanel, BorderLayout.CENTER);// placemnet centre
        buttonPanel.setBackground(new Color(255, 204, 153));
        // Result panel initially hidden
        resultPanel = new JPanel(new GridLayout(3, 1));
        resultPanel.setVisible(false); // Initially hidden
        add(resultPanel, BorderLayout.SOUTH);
        resultPanel.setBackground(new Color(255, 204, 153));
        solutionLabel = new JLabel("Optimal Solution: ");
        profitLabel = new JLabel("Optimal Profit (Z): ");
        deviationLabel = new JLabel("Deviation from Goal 1: ");

        resultPanel.add(solutionLabel);
        resultPanel.add(profitLabel);
        resultPanel.add(deviationLabel);

        calculateButton.addActionListener(new ActionListener()
        // calculate button when clicked calculates
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateOptimalSolution();
            }
        });

        addWindowListener(new WindowAdapter()
        // window close
        {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }

    private void calculateOptimalSolution() {
        try {
            double c1 = Double.parseDouble(c1Field.getText());
            double c2 = Double.parseDouble(c2Field.getText());
            double a11 = Double.parseDouble(a11Field.getText());
            double a12 = Double.parseDouble(a12Field.getText());
            double b1 = Double.parseDouble(b1Field.getText());
            double a21 = Double.parseDouble(a21Field.getText());
            double a22 = Double.parseDouble(a22Field.getText());
            double b2 = Double.parseDouble(b2Field.getText());
            double goal1 = Double.parseDouble(goalField.getText());

            double maxProfit = 0;
            double x1Optimal = 0;
            double x2Optimal = 0;
            double deviationFromGoal = Double.POSITIVE_INFINITY;

            for (int i = 0; i <= b1; i++) {
                for (int j = 0; j <= b2; j++) {
                    double profit = c1 * i + c2 * j;
                    double deviation = Math.abs(profit - goal1);

                    if (a11 * i + a12 * j <= b1 && a21 * i + a22 * j <= b2) {
                        if (deviation < deviationFromGoal) {
                            maxProfit = profit;
                            x1Optimal = i;
                            x2Optimal = j;
                            deviationFromGoal = deviation;
                        }
                    }
                }
            }

            solutionLabel.setText("Optimal Solution: x1 = " + x1Optimal + ", x2 = " + x2Optimal);
            profitLabel.setText("Optimal Profit (Z): " + maxProfit);
            deviationLabel.setText("Deviation from Goal 1: " + deviationFromGoal);

            // Show the result panel
            resultPanel.setVisible(true);
        } catch (NumberFormatException ex) {
            // Handle the case when input is not a valid number
            solutionLabel.setText("Optimal Solution: N/A");
            profitLabel.setText("Optimal Profit (Z): N/A");
            deviationLabel.setText("Deviation from Goal 1: N/A");
            resultPanel.setVisible(true); // Show the result panel with error message
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numeric values.", "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GoalProgrammingSwingGUI gui = new GoalProgrammingSwingGUI();
                gui.setVisible(true);

                // Set the button background color
                gui.calculateButton.setBackground(new Color(142, 140, 216, 255)); // Light Pink
                // Custom
                Font font = new Font("Poppins", Font.BOLD, 20);
                gui.setFont(font);
                gui.calculateButton.setFont(font);

                // Set the screen size
                int width = 350;
                int height = 310;
                gui.setSize(width, height);

                // Center the frame on the screen
                gui.setLocationRelativeTo(null);
            }
        });
    }
}