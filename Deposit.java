package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

public class Deposit extends JFrame implements ActionListener {
    private String pin;
    private JTextField amountField;
    private JButton depositButton, backButton;

    public Deposit(String pin) {
        this.pin = pin;

        // Set modern look and feel

        // Frame setup
        setTitle("Deposit Money");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel with gradient background
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

        // Form panel with glass effect
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

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title label
        JLabel titleLabel = new JLabel("DEPOSIT MONEY");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(new Color(0, 51, 102));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(titleLabel, gbc);

        // Amount label
        JLabel amountLabel = new JLabel("Enter Amount:");
        amountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        formPanel.add(amountLabel, gbc);

        // Amount field with currency symbol and input validation
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
        amountField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        amountField.setBorder(BorderFactory.createEmptyBorder());
        amountField.setBackground(new Color(255, 255, 255, 0));
        amountField.setColumns(15);

        // Add document filter to allow only numbers and one decimal point
        PlainDocument doc = (PlainDocument) amountField.getDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                if (string.matches("[0-9.]*")) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (text.matches("[0-9.]*")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        amountPanel.add(amountField, BorderLayout.CENTER);

        // Add input validation label
        JLabel validationLabel = new JLabel("(Numbers only, max 2 decimal places)");
        validationLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        validationLabel.setForeground(new Color(100, 100, 100));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        formPanel.add(validationLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        formPanel.add(amountPanel, gbc);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        buttonPanel.setOpaque(false);

        depositButton = createButton("DEPOSIT", new Color(0, 128, 0)); // Green
        backButton = createButton("BACK", new Color(200, 200, 200)); // Gray

        buttonPanel.add(depositButton);
        buttonPanel.add(backButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(buttonPanel, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        add(mainPanel);

        setVisible(true);
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
            if (e.getSource() == depositButton) {
                String amountText = amountField.getText().trim();

                if (amountText.isEmpty()) {
                    showError("Please enter the amount you want to deposit");
                    return;
                }

                // Validate amount format
                if (!amountText.matches("^\\d+(\\.\\d{1,2})?$")) {
                    showError("Please enter a valid amount (e.g., 1000 or 1000.50)");
                    return;
                }

                double amount = Double.parseDouble(amountText);

                // Validate minimum deposit amount
                if (amount <= 0) {
                    showError("Deposit amount must be greater than 0");
                    return;
                }

                // Validate maximum deposit amount
                if (amount > 1000000) {
                    showError("Maximum deposit amount is ₹10,00,000");
                    return;
                }

                // Format current date and time
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = dateFormat.format(new Date());

                DBConnection connection = new DBConnection();

                // Use prepared statement to prevent SQL injection
                String query = "INSERT INTO bank VALUES (?, ?, 'Deposit', ?)";
                PreparedStatement pstmt = connection.getConnection().prepareStatement(query);
                pstmt.setString(1, pin);
                pstmt.setString(2, date);
                pstmt.setString(3, String.format("%.2f", amount));
                pstmt.executeUpdate();

                // Show success message with HTML formatting
                JOptionPane.showMessageDialog(this,
                        "<html><div style='text-align: center;'><b>Deposit Successful!</b><br><br>" +
                                "₹" + String.format("%,.2f", amount) + " has been deposited to your account.</div></html>",
                        "Success", JOptionPane.INFORMATION_MESSAGE);

                setVisible(false);
                new Main_menu(pin);
            }
            else if (e.getSource() == backButton) {
                setVisible(false);
                new Main_menu(pin);
            }
        }
        catch (NumberFormatException ex) {
            showError("Invalid amount format. Please enter numbers only");
        }
        catch (Exception ex) {
            ex.printStackTrace();
            showError("Error processing deposit: " + ex.getMessage());
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message,
                "Input Error", JOptionPane.ERROR_MESSAGE);
        amountField.requestFocus();
        amountField.selectAll();
    }

    public static void main(String[] args) {
        new Deposit("");
    }
}