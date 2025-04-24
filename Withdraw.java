package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Withdraw extends JFrame implements ActionListener {
    private String pin;
    private JTextField amountField;
    private JButton withdrawButton, backButton;
    private JLabel balanceLabel;

    private DBConnection db; // DBConnection instance for reuse

    public Withdraw(String pin) {
        this.pin = pin;
        db = new DBConnection(); // Initialize DB connection

        // Set modern look and feel
        setTitle("Withdraw Money");
        setSize(850, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel with gradient background
        JPanel mainPanel = createMainPanel();
        JPanel formPanel = createFormPanel();

        // Add components to formPanel
        formPanel.add(createTitleLabel(), createGridBagConstraints(0, 0, 2, GridBagConstraints.CENTER));
        formPanel.add(createAmountField(), createGridBagConstraints(1, 1, 1, GridBagConstraints.LINE_START));
        formPanel.add(balanceLabel, createGridBagConstraints(1, 2, 1, GridBagConstraints.LINE_START));
        formPanel.add(createButtonPanel(), createGridBagConstraints(0, 3, 2, GridBagConstraints.CENTER));

        mainPanel.add(formPanel, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);

        // Load current balance
        loadCurrentBalance();
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, new Color(0, 102, 204), 0, getHeight(), new Color(0, 153, 255));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        return mainPanel;
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(255, 255, 255, 200)); // Semi-transparent white
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            }
        };
        formPanel.setLayout(new GridBagLayout());
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        return formPanel;
    }

    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("WITHDRAW MONEY");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(new Color(0, 51, 102));
        return titleLabel;
    }

    private JPanel createAmountField() {
        JLabel amountLabel = new JLabel("Amount to Withdraw:");
        amountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        JPanel amountPanel = new JPanel(new BorderLayout());
        amountPanel.setBackground(new Color(255, 255, 255, 150));
        amountPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));

        JLabel rsLabel = new JLabel("₹");
        rsLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        rsLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        amountPanel.add(rsLabel, BorderLayout.WEST);

        amountField = new JTextField();
        amountField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        amountField.setBorder(BorderFactory.createEmptyBorder());
        amountField.setBackground(new Color(255, 255, 255, 0));
        amountField.setColumns(15);
        amountPanel.add(amountField, BorderLayout.CENTER);

        return amountPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        buttonPanel.setOpaque(false);

        withdrawButton = createButton("WITHDRAW", new Color(231, 76, 60)); // Red
        backButton = createButton("BACK", new Color(200, 200, 200)); // Gray

        buttonPanel.add(withdrawButton);
        buttonPanel.add(backButton);

        return buttonPanel;
    }

    private GridBagConstraints createGridBagConstraints(int x, int y, int gridwidth, int anchor) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = gridwidth;
        gbc.anchor = anchor;
        gbc.insets = new Insets(15, 15, 15, 15);
        return gbc;
    }

    private void loadCurrentBalance() {
        String query = "SELECT * FROM bank WHERE pin = ?";
        try (PreparedStatement pstmt = db.prepareStatement(query)) {
            pstmt.setString(1, pin);
            ResultSet rs = pstmt.executeQuery();

            int balance = 0;
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += rs.getInt("amount");
                } else {
                    balance -= rs.getInt("amount");
                }
            }

            NumberFormat format = NumberFormat.getCurrencyInstance();
            String formattedBalance = format.format(balance).replace("$", "₹");
            balanceLabel.setText("Current Balance: " + formattedBalance);
        } catch (SQLException e) {
            e.printStackTrace();
            balanceLabel.setText("Unable to load current balance");
        }
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setForeground(bgColor.equals(new Color(200, 200, 200)) ? Color.BLACK : Color.WHITE);
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
        try {
            if (e.getSource() == withdrawButton) {
                String amountText = amountField.getText().trim();

                if (amountText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter the amount to withdraw",
                            "Amount Required", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (!amountText.matches("\\d+(\\.\\d{1,2})?")) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid amount (numbers only)",
                            "Invalid Amount", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                double amount = Double.parseDouble(amountText);

                // Validate minimum withdrawal amount
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Withdrawal amount must be greater than 0",
                            "Invalid Amount", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Check account balance
                String balanceQuery = "SELECT SUM(CASE WHEN type = 'Deposit' THEN amount ELSE -amount END) AS balance FROM bank WHERE pin = ?";
                try (PreparedStatement pstmt = db.prepareStatement(balanceQuery)) {
                    pstmt.setString(1, pin);
                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                        double balance = rs.getDouble("balance");
                        if (balance < amount) {
                            JOptionPane.showMessageDialog(this, "Insufficient balance for this withdrawal",
                                    "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }

                // Process withdrawal
                String insertQuery = "INSERT INTO bank VALUES (?, ?, 'Withdrawal', ?)";
                try (PreparedStatement pstmt = db.prepareStatement(insertQuery)) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    pstmt.setString(1, pin);
                    pstmt.setString(2, dateFormat.format(new Date()));
                    pstmt.setString(3, String.format("%.2f", amount));
                    pstmt.executeUpdate();
                }

                // Show success message
                NumberFormat format = NumberFormat.getCurrencyInstance();
                String formattedAmount = format.format(amount).replace("$", "₹");
                JOptionPane.showMessageDialog(this,
                        "<html><div style='text-align: center;'><b>Withdrawal Successful!</b><br><br>" +
                                formattedAmount + " has been withdrawn from your account.</div></html>",
                        "Success", JOptionPane.INFORMATION_MESSAGE);

                setVisible(false);
                new Main_menu(pin);
            }
            else if (e.getSource() == backButton) {
                setVisible(false);
                new Main_menu(pin);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount format. Please enter numbers only",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            amountField.requestFocus();
            amountField.selectAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error processing withdrawal: " + ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new Withdraw("");
    }
}
