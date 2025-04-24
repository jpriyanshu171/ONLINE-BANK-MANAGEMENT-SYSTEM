package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.sql.PreparedStatement;

public class Signup3 extends JFrame implements ActionListener {
        private JRadioButton savingAccount, fixedDeposit, currentAccount;
        private JCheckBox atmCard, internetBanking, chequeBook, eStatement;
        private JButton submitButton, cancelButton;
        private String formno;

        public Signup3() {
                // Set modern look and feel

                // Frame setup
                setTitle("Account Setup - Final Step");
                setSize(850, 800);
                setLocationRelativeTo(null);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Main panel with card layout
                JPanel mainPanel = new JPanel(new BorderLayout());
                mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                mainPanel.setBackground(new Color(240, 245, 250));

                // Header panel
                JPanel headerPanel = new JPanel();
                headerPanel.setBackground(new Color(240, 245, 250));
                JLabel titleLabel = new JLabel("ACCOUNT DETAILS");
                titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
                titleLabel.setForeground(new Color(0, 51, 102));
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
                gbc.insets = new Insets(15, 15, 15, 15);
                gbc.anchor = GridBagConstraints.WEST;
                gbc.fill = GridBagConstraints.HORIZONTAL;

                // Account Type Section
                JLabel accountTypeLabel = new JLabel("Account Type:");
                accountTypeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
                gbc.gridx = 0;
                gbc.gridy = 0;
                formPanel.add(accountTypeLabel, gbc);

                JPanel accountTypePanel = new JPanel();
                accountTypePanel.setLayout(new BoxLayout(accountTypePanel, BoxLayout.Y_AXIS));
                accountTypePanel.setBackground(Color.WHITE);

                savingAccount = createRadioButton("Savings Account");
                fixedDeposit = createRadioButton("Fixed Deposit Account");
                currentAccount = createRadioButton("Current Account");

                ButtonGroup accountTypeGroup = new ButtonGroup();
                accountTypeGroup.add(savingAccount);
                accountTypeGroup.add(fixedDeposit);
                accountTypeGroup.add(currentAccount);

                accountTypePanel.add(savingAccount);
                accountTypePanel.add(Box.createRigidArea(new Dimension(0, 10)));
                accountTypePanel.add(currentAccount);
                accountTypePanel.add(Box.createRigidArea(new Dimension(0, 10)));
                accountTypePanel.add(fixedDeposit);

                gbc.gridx = 1;
                gbc.gridy = 0;
                formPanel.add(accountTypePanel, gbc);

                // Services Section
                JLabel servicesLabel = new JLabel("Services Required:");
                servicesLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
                gbc.gridx = 0;
                gbc.gridy = 1;
                formPanel.add(servicesLabel, gbc);

                JPanel servicesPanel = new JPanel();
                servicesPanel.setLayout(new BoxLayout(servicesPanel, BoxLayout.Y_AXIS));
                servicesPanel.setBackground(Color.WHITE);

                atmCard = createCheckBox("ATM Card");
                internetBanking = createCheckBox("Internet Banking");
                chequeBook = createCheckBox("Cheque Book");
                eStatement = createCheckBox("E-Statement");

                servicesPanel.add(atmCard);
                servicesPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                servicesPanel.add(internetBanking);
                servicesPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                servicesPanel.add(chequeBook);
                servicesPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                servicesPanel.add(eStatement);

                gbc.gridx = 1;
                gbc.gridy = 1;
                formPanel.add(servicesPanel, gbc);

                // Buttons panel
                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
                buttonPanel.setBackground(Color.WHITE);
                buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

                submitButton = createButton("SUBMIT", new Color(39, 174, 96)); // Green
                cancelButton = createButton("CANCEL", new Color(231, 76, 60)); // Red

                buttonPanel.add(submitButton);
                buttonPanel.add(cancelButton);

                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = 2;
                gbc.anchor = GridBagConstraints.CENTER;
                formPanel.add(buttonPanel, gbc);

                mainPanel.add(formPanel, BorderLayout.CENTER);
                add(mainPanel);

                setVisible(true);
        }

        private JRadioButton createRadioButton(String text) {
                JRadioButton radioButton = new JRadioButton(text);
                radioButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                radioButton.setBackground(Color.WHITE);
                radioButton.setFocusPainted(false);
                return radioButton;
        }

        private JCheckBox createCheckBox(String text) {
                JCheckBox checkBox = new JCheckBox(text);
                checkBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                checkBox.setBackground(Color.WHITE);
                checkBox.setFocusPainted(false);
                return checkBox;
        }

        private JButton createButton(String text, Color bgColor) {
                JButton button = new JButton(text);
                button.setFont(new Font("Segoe UI", Font.BOLD, 14));
                button.setForeground(Color.WHITE);
                button.setBackground(bgColor);
                button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
                button.setFocusPainted(false);
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
                button.addActionListener(this);

                // Hover effect
                button.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                                button.setBackground(bgColor.darker());
                        }
                        @Override
                        public void mouseExited(MouseEvent e) {
                                button.setBackground(bgColor);
                        }
                });

                return button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
                if (e.getSource() == submitButton) {
                        String accountType = "";
                        if (savingAccount.isSelected()) {
                                accountType = "Savings Account";
                        } else if (fixedDeposit.isSelected()) {
                                accountType = "Fixed Deposit Account";
                        } else if (currentAccount.isSelected()) {
                                accountType = "Current Account";
                        }

                        if (accountType.isEmpty()) {
                                JOptionPane.showMessageDialog(this, "Please select an account type",
                                        "Selection Required", JOptionPane.WARNING_MESSAGE);
                                return;
                        }

                        if (!atmCard.isSelected() && !internetBanking.isSelected() &&
                                !chequeBook.isSelected() && !eStatement.isSelected()) {
                                JOptionPane.showMessageDialog(this, "Please select at least one service",
                                        "Selection Required", JOptionPane.WARNING_MESSAGE);
                                return;
                        }

                        Random random = new Random();
                        long first7 = (random.nextLong() % 90000000L) + 1409963000000000L;
                        String cardNo = "" + Math.abs(first7);

                        long first3 = (random.nextLong() % 9000L) + 1000L;
                        String pin = "" + Math.abs(first3);

                        StringBuilder requirements = new StringBuilder();
                        if (atmCard.isSelected()) requirements.append("ATM Card, ");
                        if (internetBanking.isSelected()) requirements.append("Internet Banking, ");
                        if (chequeBook.isSelected()) requirements.append("Cheque Book, ");
                        if (eStatement.isSelected()) requirements.append("E-Statement, ");

                        // Remove trailing comma
                        String services = requirements.substring(0, requirements.length() - 2);

                        try {
                                DBConnection connection = new DBConnection();

                                // Use the public prepareStatement method instead of accessing connection directly
                                String insertSignup3 = "INSERT INTO Signup3Details VALUES (?, ?, ?, ?)";
                                PreparedStatement pstmt1 = connection.prepareStatement(insertSignup3);
                                pstmt1.setString(1, accountType);
                                pstmt1.setString(2, cardNo);
                                pstmt1.setString(3, pin);
                                pstmt1.setString(4, services);
                                pstmt1.executeUpdate();

                                String insertLogin = "INSERT INTO LoginDetails VALUES (?, ?)";
                                PreparedStatement pstmt2 = connection.prepareStatement(insertLogin);
                                pstmt2.setString(1, cardNo);
                                pstmt2.setString(2, pin);
                                pstmt2.executeUpdate();

                                // Show success message with HTML formatting
                                JOptionPane.showMessageDialog(this,
                                        "<html><div style='text-align: center;'><b>Account Created Successfully!</b><br><br>" +
                                                "<table border='0' cellpadding='5'>" +
                                                "<tr><td align='right'><b>Card Number:</b></td><td>" + cardNo + "</td></tr>" +
                                                "<tr><td align='right'><b>PIN:</b></td><td>" + pin + "</td></tr>" +
                                                "</table><br>Please keep this information secure.</div></html>",
                                        "Success", JOptionPane.INFORMATION_MESSAGE);

                                new Deposit(pin);
                                setVisible(false);
                        } catch (Exception ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(this,
                                        "Error creating account: " + ex.getMessage(),
                                        "Database Error", JOptionPane.ERROR_MESSAGE);
                        }
                } else if (e.getSource() == cancelButton) {
                        System.exit(0);
                }
        }

        public static void main(String[] args) {
                new Signup3();
        }
}