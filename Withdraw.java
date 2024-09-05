package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class Withdraw extends JFrame implements ActionListener {

    String pin;
    TextField textField;

    JButton b1, b2;
    Withdraw(String pin){
        this.pin=pin;

        JLabel label2 = new JLabel("PLEASE ENTER AMOUNT TO BE WITHDRAWN");
        label2.setForeground(Color.black);
        label2.setFont(new Font("System", Font.BOLD, 30));
        label2.setBounds(100,100,700,35);
        add(label2);

        textField = new TextField();
        textField.setBackground(new Color(65,125,128));
        textField.setForeground(Color.black);
        textField.setBounds(250,200,320,25);
        textField.setFont(new Font("Raleway", Font.BOLD,22));
        add(textField);

        b1 = new JButton("WITHDRAW");
        b1.setBounds(350,340,150,35);
        b1.setBackground(new Color(0, 128, 0));
        b1.setFont(new Font("System", Font.BOLD, 16));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(350,400,150,35);
        b2.setBackground(new Color(200,200,200));
        b2.setForeground(Color.black);
        b2.setFont(new Font("System", Font.BOLD, 16));
        b2.addActionListener(this);
        add(b2);

        getContentPane().setBackground(Color.white);
        setLayout(null);
        setSize(850,500);
        setLocation(450,200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1) {
            try {
                String amount = textField.getText();
                Date date = new Date();
                if (textField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Empty! Enter again");
                } else {
                     DBConnection c= new DBConnection();
                    ResultSet resultSet = c.statement.executeQuery("select * from bank where pin = '" + pin + "'");
                    int balance = 0;
                    while (resultSet.next()) {
                        if (resultSet.getString("type").equals("Deposit")) {
                            balance += Integer.parseInt(resultSet.getString("amount"));
                        } else {
                            balance -= Integer.parseInt(resultSet.getString("amount"));
                        }
                    }
                    if (balance < Integer.parseInt(amount)) {
                        JOptionPane.showMessageDialog(null, "Insuffient Balance");
                        return;
                    }
                    c.statement.executeUpdate("insert into bank values('" + pin + "', '" + date + "', 'Withdrawl', '" + amount + "' )");
                    JOptionPane.showMessageDialog(null, "Rs. " + amount + " Withdrawn Successfully");
                    setVisible(false);
                    new Main_menu(pin);

                }
            } catch (Exception E) {

            }
        } else if (e.getSource()==b2) {
            setVisible(false);
            new Main_menu(pin);
        }
    }

    public static void main(String[] args) {
        new Withdraw("");
    }
}