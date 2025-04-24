package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

public class BalanceEnquiry extends JFrame implements ActionListener {
        private String pin;
        private JLabel balanceLabel;
        private JButton backButton;
        private JLabel lastUpdateLabel;

        public BalanceEnquiry(String pin) {
                this.pin = pin;

                // Set modern look and feel

                // Frame setup
                setTitle("Account Balance");
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

                // Content panel
                JPanel contentPanel = new JPanel();
                contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
                contentPanel.setOpaque(false);
                contentPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

                // Title label
                JLabel titleLabel = new JLabel("ACCOUNT BALANCE");
                titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
                titleLabel.setForeground(Color.WHITE);
                titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                contentPanel.add(titleLabel);

                // Add spacing
                contentPanel.add(Box.createRigidArea(new Dimension(0, 40)));

                // Balance display panel
                JPanel balancePanel = new JPanel();
                balancePanel.setLayout(new BoxLayout(balancePanel, BoxLayout.Y_AXIS));
                balancePanel.setOpaque(false);
                balancePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

                JLabel balanceTextLabel = new JLabel("Your Current Balance");
                balanceTextLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                balanceTextLabel.setForeground(Color.WHITE);
                balanceTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                balancePanel.add(balanceTextLabel);

                balanceLabel = new JLabel();
                balanceLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
                balanceLabel.setForeground(Color.WHITE);
                balanceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                balancePanel.add(balanceLabel);

                // Last update label
                lastUpdateLabel = new JLabel();
                lastUpdateLabel.setFont(new Font("Segoe UI", Font.ITALIC, 14));
                lastUpdateLabel.setForeground(new Color(220, 220, 220));
                lastUpdateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                balancePanel.add(lastUpdateLabel);

                contentPanel.add(balancePanel);

                // Add spacing
                contentPanel.add(Box.createRigidArea(new Dimension(0, 60)));

                // Back button
                backButton = new JButton("BACK TO MENU");
                backButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
                backButton.setForeground(Color.BLACK);
                backButton.setBackground(Color.WHITE);
                backButton.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
                backButton.setFocusPainted(false);
                backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                backButton.addActionListener(this);

                // Hover effect
                backButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                                backButton.setBackground(new Color(240, 240, 240));
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                                backButton.setBackground(Color.WHITE);
                        }
                });

                contentPanel.add(backButton);

                mainPanel.add(contentPanel, BorderLayout.CENTER);
                add(mainPanel);

                // Load balance data
                loadBalanceData();

                setVisible(true);
        }

        public static void main(String[] args) {
                new BalanceEnquiry("");
        }

        private void loadBalanceData() {
                int balance = 0;
                Date lastUpdate = null;

                try {
                        DBConnection connection = new DBConnection();

                        // Use prepared statement to prevent SQL injection
                        String query = "SELECT * FROM bank WHERE pin = ? ORDER BY date DESC";
                        PreparedStatement pstmt = connection.getConnection().prepareStatement(query); // Use getConnection()
                        pstmt.setString(1, pin);
                        ResultSet resultSet = pstmt.executeQuery();

                        while (resultSet.next()) {
                                if (resultSet.getString("type").equals("Deposit")) {
                                        balance += Integer.parseInt(resultSet.getString("amount"));
                                } else {
                                        balance -= Integer.parseInt(resultSet.getString("amount"));
                                }

                                // Get the most recent transaction date
                                if (lastUpdate == null) {
                                        Timestamp timestamp = resultSet.getTimestamp("date");
                                        if (timestamp != null) {
                                                lastUpdate = new Date(timestamp.getTime());
                                        }
                                }
                        }

                        // Format balance with currency symbol and thousand separators
                        NumberFormat format = NumberFormat.getCurrencyInstance();
                        String formattedBalance = format.format(balance).replace("$", "₹");
                        balanceLabel.setText(formattedBalance);

                        // Set last update time if available
                        if (lastUpdate != null) {
                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy 'at' hh:mm a");
                                lastUpdateLabel.setText("Last updated: " + dateFormat.format(lastUpdate));
                        }

                } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this,
                                "Error retrieving balance information",
                                "Database Error", JOptionPane.ERROR_MESSAGE);
                        balanceLabel.setText("₹0");
                }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
                if (e.getSource() == backButton) {
                        setVisible(false);
                        new Main_menu(pin);
                }
        }
}
