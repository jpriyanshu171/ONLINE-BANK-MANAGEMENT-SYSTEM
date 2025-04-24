package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;

public class PinChange extends JFrame implements ActionListener {
    private JPasswordField newPinField, confirmPinField;
    private JButton changeButton, backButton;
    private String currentPin;

    public PinChange(String currentPin) {
        this.currentPin = currentPin;

        // Frame setup
        setTitle("Change PIN");
        setSize(850, 600);
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
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("CHANGE YOUR PIN");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(new Color(0, 51, 102));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(titleLabel, gbc);

        JLabel newPinLabel = new JLabel("New PIN:");
        newPinLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        formPanel.add(newPinLabel, gbc);

        newPinField = new JPasswordField();
        newPinField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        newPinField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        newPinField.setEchoChar('•');
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        formPanel.add(newPinField, gbc);

        JLabel confirmPinLabel = new JLabel("Confirm PIN:");
        confirmPinLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        formPanel.add(confirmPinLabel, gbc);

        confirmPinField = new JPasswordField();
        confirmPinField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        confirmPinField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        confirmPinField.setEchoChar('•');
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        formPanel.add(confirmPinField, gbc);

        JLabel requirementsLabel = new JLabel("PIN must be 4-6 digits");
        requirementsLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        requirementsLabel.setForeground(new Color(100, 100, 100));
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(requirementsLabel, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        buttonPanel.setOpaque(false);

        changeButton = createButton("CHANGE", new Color(39, 174, 96)); // Green
        backButton = createButton("BACK", new Color(231, 76, 60)); // Red

        buttonPanel.add(changeButton);
        buttonPanel.add(backButton);

        gbc.gridx = 0;
        gbc.gridy = 4;
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
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(this);

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
            if (e.getSource() == changeButton) {
                String newPin = new String(newPinField.getPassword());
                String confirmPin = new String(confirmPinField.getPassword());

                if (newPin.isEmpty() || confirmPin.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter and confirm your new PIN",
                            "PIN Required", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (!newPin.equals(confirmPin)) {
                    JOptionPane.showMessageDialog(this, "PINs do not match",
                            "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!newPin.matches("\\d{4,6}")) {
                    JOptionPane.showMessageDialog(this, "PIN must be 4-6 digits",
                            "Invalid PIN", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (newPin.equals(currentPin)) {
                    JOptionPane.showMessageDialog(this, "New PIN cannot be same as current PIN",
                            "Invalid PIN", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                DBConnection connection = new DBConnection();

                String updateBank = "UPDATE bank SET pin = ? WHERE pin = ?";
                PreparedStatement pstmt1 = connection.getConnection().prepareStatement(updateBank);
                pstmt1.setString(1, newPin);
                pstmt1.setString(2, currentPin);
                pstmt1.executeUpdate();

                String updateLogin = "UPDATE LoginDetails SET pin = ? WHERE pin = ?";
                PreparedStatement pstmt2 = connection.getConnection().prepareStatement(updateLogin);
                pstmt2.setString(1, newPin);
                pstmt2.setString(2, currentPin);
                pstmt2.executeUpdate();

                String updateSignup = "UPDATE Signup3Details SET pin = ? WHERE pin = ?";
                PreparedStatement pstmt3 = connection.getConnection().prepareStatement(updateSignup);
                pstmt3.setString(1, newPin);
                pstmt3.setString(2, currentPin);
                pstmt3.executeUpdate();

                JOptionPane.showMessageDialog(this, "PIN changed successfully",
                        "Success", JOptionPane.INFORMATION_MESSAGE);

                setVisible(false);
                new Main_menu(newPin);
            } else if (e.getSource() == backButton) {
                setVisible(false);
                new Main_menu(currentPin);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error changing PIN: " + ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new PinChange("");
    }
}
