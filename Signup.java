package bank.management.system;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Signup extends JFrame implements ActionListener {
    private JRadioButton maleRadio, femaleRadio, marriedRadio, unmarriedRadio;
    private JButton nextButton;
    private JComboBox<String> religionCombo, categoryCombo;
    private JTextField textName, textFname, textEmail, textAdd, textCity, textState, textPin;
    private JDateChooser dateChooser;
    private JPanel mainPanel, formPanel;

    public Signup() {
        super("Account Registration");

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

        formPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(255, 255, 255, 220));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            }
        };
        formPanel.setLayout(new GridBagLayout());
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel titleLabel = new JLabel("ACCOUNT REGISTRATION", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(new Color(0, 51, 102));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(titleLabel, gbc);

        JLabel sectionLabel = new JLabel("Personal Details", SwingConstants.LEFT);
        sectionLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        sectionLabel.setForeground(new Color(70, 70, 70));
        gbc.gridy = 1;
        formPanel.add(sectionLabel, gbc);

        addFormField("Full Name:", 2, formPanel, gbc);
        textName = createTextField();
        gbc.gridx = 1;
        formPanel.add(textName, gbc);

        addFormField("Father's Name:", 3, formPanel, gbc);
        textFname = createTextField();
        gbc.gridx = 1;
        formPanel.add(textFname, gbc);

        addFormField("Date of Birth:", 4, formPanel, gbc);
        dateChooser = new JDateChooser();
        dateChooser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        dateChooser.getCalendarButton().setBackground(new Color(66, 165, 245));
        dateChooser.getCalendarButton().setForeground(Color.WHITE);
        JTextField dateText = ((JTextField)dateChooser.getDateEditor().getUiComponent());
        dateText.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        gbc.gridx = 1;
        formPanel.add(dateChooser, gbc);

        addFormField("Gender:", 5, formPanel, gbc);
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        genderPanel.setOpaque(false);
        maleRadio = createRadioButton("Male");
        femaleRadio = createRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        gbc.gridx = 1;
        formPanel.add(genderPanel, gbc);

        addFormField("Email Address:", 6, formPanel, gbc);
        textEmail = createTextField();
        gbc.gridx = 1;
        formPanel.add(textEmail, gbc);

        addFormField("Marital Status:", 7, formPanel, gbc);
        JPanel maritalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        maritalPanel.setOpaque(false);
        marriedRadio = createRadioButton("Married");
        unmarriedRadio = createRadioButton("Unmarried");
        ButtonGroup maritalGroup = new ButtonGroup();
        maritalGroup.add(marriedRadio);
        maritalGroup.add(unmarriedRadio);
        maritalPanel.add(marriedRadio);
        maritalPanel.add(unmarriedRadio);
        gbc.gridx = 1;
        formPanel.add(maritalPanel, gbc);

        addFormField("Religion:", 8, formPanel, gbc);
        String[] religions = {"Hindu", "Muslim", "Sikh", "Christian", "Other"};
        religionCombo = new JComboBox<>(religions);
        gbc.gridx = 1;
        formPanel.add(religionCombo, gbc);

        addFormField("Category:", 9, formPanel, gbc);
        String[] categories = {"General", "OBC", "SC", "ST", "Other"};
        categoryCombo = new JComboBox<>(categories);
        gbc.gridx = 1;
        formPanel.add(categoryCombo, gbc);

        addFormField("Address:", 10, formPanel, gbc);
        textAdd = createTextField();
        gbc.gridx = 1;
        formPanel.add(textAdd, gbc);

        addFormField("City:", 11, formPanel, gbc);
        textCity = createTextField();
        gbc.gridx = 1;
        formPanel.add(textCity, gbc);

        addFormField("State:", 12, formPanel, gbc);
        textState = createTextField();
        gbc.gridx = 1;
        formPanel.add(textState, gbc);

        addFormField("Pin Code:", 13, formPanel, gbc);
        textPin = createTextField();
        gbc.gridx = 1;
        formPanel.add(textPin, gbc);

        // NEXT button
        nextButton = new JButton("NEXT");
        nextButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nextButton.setForeground(Color.WHITE);
        nextButton.setBackground(new Color(0, 102, 204));
        nextButton.setFocusPainted(false);
        nextButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        nextButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        nextButton.addActionListener(this);

        gbc.gridy = 14;
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(nextButton, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        add(mainPanel);

        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setVisible(true);
    }

    private void addFormField(String labelText, int y, JPanel panel, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        label.setForeground(Color.DARK_GRAY);
        panel.add(label, gbc);
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField(20);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return textField;
    }

    private JRadioButton createRadioButton(String text) {
        JRadioButton rb = new JRadioButton(text);
        rb.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        rb.setOpaque(false);
        return rb;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            // Example: just show a message (replace with DB logic or next form later)
            new Signup2();
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}
