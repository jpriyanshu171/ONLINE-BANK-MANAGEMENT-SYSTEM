package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    private JLabel welcomeLabel, cardLabel, pinLabel;
    private JTextField cardField;
    private JPasswordField pinField;
    private JButton loginBtn, clearBtn, signupBtn;
    private JPanel mainPanel, formPanel;

    Login() {
        super("Bank Login");

        // Gradient background
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(0, 102, 204);
                Color color2 = new Color(0, 153, 255);
                GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());

        // Form panel
        formPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(255, 255, 255, 200));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            }
        };
        formPanel.setLayout(new GridBagLayout());
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        welcomeLabel = new JLabel("WELCOME BACK", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        welcomeLabel.setForeground(new Color(0, 51, 102));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        formPanel.add(welcomeLabel, gbc);

        cardLabel = new JLabel("Card Number:");
        cardLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        formPanel.add(cardLabel, gbc);

        cardField = new JTextField(16);
        cardField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        cardField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        gbc.gridx = 1;
        formPanel.add(cardField, gbc);

        pinLabel = new JLabel("PIN:");
        pinLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(pinLabel, gbc);

        pinField = new JPasswordField(16);
        pinField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        pinField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        gbc.gridx = 1;
        formPanel.add(pinField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);

        loginBtn = createButton("LOGIN", new Color(76, 175, 80), new Color(46, 125, 50));
        loginBtn.addActionListener(this);
        buttonPanel.add(loginBtn);

        clearBtn = createButton("CLEAR", new Color(239, 83, 80), new Color(183, 28, 28));
        clearBtn.addActionListener(this);
        buttonPanel.add(clearBtn);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);

        signupBtn = createButton("CREATE NEW ACCOUNT", new Color(66, 165, 245), new Color(21, 101, 192));
        signupBtn.addActionListener(this);
        gbc.gridy = 4;
        formPanel.add(signupBtn, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        add(mainPanel);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JButton createButton(String text, Color bgColor, Color hoverColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginBtn) {
            String CardNo = cardField.getText();
            String Pin = new String(pinField.getPassword());

            // Input validation
            if (CardNo.isEmpty() || Pin.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both Card Number and PIN", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // DB interaction
            DBConnection c = new DBConnection();
            PreparedStatement pstmt = null;
            ResultSet resultSet = null;

            try {
                String query = "SELECT * FROM LoginDetails WHERE CardNo = ? AND pin = ?";
                pstmt = c.getConnection().prepareStatement(query);
                pstmt.setString(1, CardNo);
                pstmt.setString(2, Pin);
                resultSet = pstmt.executeQuery();

                if (resultSet.next()) {
                    setVisible(false);
                    new Main_menu(Pin);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Card Number or PIN", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "System Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    if (resultSet != null) resultSet.close();
                    if (pstmt != null) pstmt.close();
                    if (c.getConnection() != null) c.getConnection().close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        } else if (e.getSource() == clearBtn) {
            cardField.setText("");
            pinField.setText("");
        } else if (e.getSource() == signupBtn) {
            new Signup();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
