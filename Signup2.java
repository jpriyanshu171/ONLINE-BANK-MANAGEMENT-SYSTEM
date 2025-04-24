package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Signup2 extends JFrame implements ActionListener {
        private JComboBox<String> incomeCombo, educationCombo, occupationCombo;
        private JTextField panField, aadharField;
        private JRadioButton seniorYes, seniorNo, existingYes, existingNo;
        private JButton nextButton;
        private ButtonGroup seniorGroup, existingGroup;

        public Signup2() {
                super("Application Form - Additional Details");

                // Set FlatLaf for modern UI
//        FlatLightLaf.setup();

                // Main panel with card-like appearance
                JPanel mainPanel = new JPanel();
                mainPanel.setLayout(new BorderLayout());
                mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                mainPanel.setBackground(new Color(240, 245, 250)); // Light blue background

                // Header panel
                JPanel headerPanel = new JPanel();
                headerPanel.setBackground(new Color(240, 245, 250));
                JLabel titleLabel = new JLabel("ADDITIONAL DETAILS");
                titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
                titleLabel.setForeground(new Color(0, 51, 102)); // Dark blue
                headerPanel.add(titleLabel);
                mainPanel.add(headerPanel, BorderLayout.NORTH);

                // Form panel
                JPanel formPanel = new JPanel();
                formPanel.setLayout(new GridBagLayout());
                formPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
                formPanel.setBackground(Color.WHITE);
                formPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(200, 200, 200)),
                        BorderFactory.createEmptyBorder(20, 20, 20, 20)
                ));

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(10, 10, 10, 10);
                gbc.anchor = GridBagConstraints.WEST;
                gbc.fill = GridBagConstraints.HORIZONTAL;

                // Income field
                addFormLabel("Income:", formPanel, gbc, 0, 0);
                String[] incomeOptions = {"Select Income", "<1,50,000", "<2,50,000", "<5,00,000", "Upto 10,00,000", "Above 10,00,000"};
                incomeCombo = createComboBox(incomeOptions);
                addComponent(incomeCombo, formPanel, gbc, 1, 0);

                // Education field
                addFormLabel("Educational Qualification:", formPanel, gbc, 0, 1);
                String[] educationOptions = {"Select Education", "Non-Graduate", "Graduate", "Post-Graduate", "Doctorate", "Others"};
                educationCombo = createComboBox(educationOptions);
                addComponent(educationCombo, formPanel, gbc, 1, 1);

                // Occupation field
                addFormLabel("Occupation:", formPanel, gbc, 0, 2);
                String[] occupationOptions = {"Select Occupation", "Salaried", "Self-Employed", "Business", "Student", "Retired", "Other"};
                occupationCombo = createComboBox(occupationOptions);
                addComponent(occupationCombo, formPanel, gbc, 1, 2);

                // PAN Number field
                addFormLabel("PAN Number:", formPanel, gbc, 0, 3);
                panField = createTextField();
                addComponent(panField, formPanel, gbc, 1, 3);

                // Aadhar Number field
                addFormLabel("Aadhar Number:", formPanel, gbc, 0, 4);
                aadharField = createTextField();
                addComponent(aadharField, formPanel, gbc, 1, 4);

                // Senior Citizen radio buttons
                addFormLabel("Senior Citizen:", formPanel, gbc, 0, 5);
                JPanel seniorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
                seniorPanel.setBackground(Color.WHITE);
                seniorYes = createRadioButton("Yes");
                seniorNo = createRadioButton("No");
                seniorGroup = new ButtonGroup();
                seniorGroup.add(seniorYes);
                seniorGroup.add(seniorNo);
                seniorPanel.add(seniorYes);
                seniorPanel.add(seniorNo);
                addComponent(seniorPanel, formPanel, gbc, 1, 5);

                // Existing Account radio buttons
                addFormLabel("Existing Account:", formPanel, gbc, 0, 6);
                JPanel existingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
                existingPanel.setBackground(Color.WHITE);
                existingYes = createRadioButton("Yes");
                existingNo = createRadioButton("No");
                existingGroup = new ButtonGroup();
                existingGroup.add(existingYes);
                existingGroup.add(existingNo);
                existingPanel.add(existingYes);
                existingPanel.add(existingNo);
                addComponent(existingPanel, formPanel, gbc, 1, 6);

                // Next button
                nextButton = new JButton("Next");
                nextButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
                nextButton.setForeground(Color.WHITE);
                nextButton.setBackground(new Color(0, 102, 204)); // Blue
                nextButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
                nextButton.setFocusPainted(false);
                nextButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                nextButton.addActionListener(this);

                // Button panel
                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                buttonPanel.setBackground(Color.WHITE);
                buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
                buttonPanel.add(nextButton);
                addComponent(buttonPanel, formPanel, gbc, 0, 7, 2, 1);

                mainPanel.add(formPanel, BorderLayout.CENTER);
                add(mainPanel);

                // Frame settings
                setSize(800, 700);
                setLocationRelativeTo(null);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setVisible(true);
        }

        public static void main(String[] args) {
                new Signup2();
        }

        private void addFormLabel(String text, JPanel panel, GridBagConstraints gbc, int x, int y) {
                JLabel label = new JLabel(text);
                label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                gbc.gridx = x;
                gbc.gridy = y;
                panel.add(label, gbc);
        }

        private void addComponent(Component comp, JPanel panel, GridBagConstraints gbc, int x, int y) {
                gbc.gridx = x;
                gbc.gridy = y;
                panel.add(comp, gbc);
        }

        private void addComponent(Component comp, JPanel panel, GridBagConstraints gbc, int x, int y, int width, int height) {
                gbc.gridx = x;
                gbc.gridy = y;
                gbc.gridwidth = width;
                gbc.gridheight = height;
                panel.add(comp, gbc);
                // Reset gridwidth and gridheight
                gbc.gridwidth = 1;
                gbc.gridheight = 1;
        }

        private JComboBox<String> createComboBox(String[] items) {
                JComboBox<String> comboBox = new JComboBox<>(items);
                comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                comboBox.setBackground(Color.WHITE);
                comboBox.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(200, 200, 200)),
                        BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));
                comboBox.setRenderer(new DefaultListCellRenderer() {
                        @Override
                        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                                if (index == -1 && value == null) {
                                        setForeground(Color.GRAY);
                                }
                                return this;
                        }
                });
                return comboBox;
        }

        private JTextField createTextField() {
                JTextField textField = new JTextField();
                textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                textField.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(200, 200, 200)),
                        BorderFactory.createEmptyBorder(8, 10, 8, 10)
                ));
                return textField;
        }

        private JRadioButton createRadioButton(String text) {
                JRadioButton radioButton = new JRadioButton(text);
                radioButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                radioButton.setBackground(Color.WHITE);
                radioButton.setFocusPainted(false);
                return radioButton;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
                if (e.getSource() == nextButton) {
                        String income = (String) incomeCombo.getSelectedItem();
                        String education = (String) educationCombo.getSelectedItem();
                        String occupation = (String) occupationCombo.getSelectedItem();
                        String pan = panField.getText().trim();
                        String aadhar = aadharField.getText().trim();

                        String seniorCitizen = seniorYes.isSelected() ? "Yes" : "No";
                        String existingAccount = existingYes.isSelected() ? "Yes" : "No";

                        // Validation
                        if (income.equals("Select Income") || education.equals("Select Education") ||
                                occupation.equals("Select Occupation") || pan.isEmpty() || aadhar.isEmpty()) {
                                JOptionPane.showMessageDialog(this, "Please fill all required fields",
                                        "Incomplete Form", JOptionPane.WARNING_MESSAGE);
                                return;
                        }

                        if (!isValidPan(pan)) {
                                JOptionPane.showMessageDialog(this, "Please enter a valid PAN number (10 characters)",
                                        "Invalid PAN", JOptionPane.WARNING_MESSAGE);
                                return;
                        }

                        if (!isValidAadhar(aadhar)) {
                                JOptionPane.showMessageDialog(this, "Please enter a valid Aadhar number (12 digits)",
                                        "Invalid Aadhar", JOptionPane.WARNING_MESSAGE);
                                return;
                        }
                        try {
                                DBConnection c = new DBConnection();
                                String query = "INSERT INTO Signup2Details VALUES (?, ?, ?, ?, ?, ?, ?)";
                                java.sql.PreparedStatement pstmt = c.prepareStatement(query);
                                pstmt.setString(1, income);
                                pstmt.setString(2, education);
                                pstmt.setString(3, occupation);
                                pstmt.setString(4, pan);
                                pstmt.setString(5, aadhar);
                                pstmt.setString(6, seniorCitizen);
                                pstmt.setString(7, existingAccount);

                                pstmt.executeUpdate();
                                new Signup3();
                                setVisible(false);
                        } catch (Exception ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(this, "Error saving data: " + ex.getMessage(),
                                        "Database Error", JOptionPane.ERROR_MESSAGE);
                        }
                }
        }

        private boolean isValidPan(String pan) {
                return pan.length() == 10; // Basic validation
        }

        private boolean isValidAadhar(String aadhar) {
                return aadhar.matches("\\d{12}"); // 12 digits
        }
}