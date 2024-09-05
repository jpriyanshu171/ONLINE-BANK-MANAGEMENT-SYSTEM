package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class BalanceEnquiry extends JFrame implements ActionListener {

    String pin;
    JLabel label2;
    JButton b1;
    BalanceEnquiry(String pin){
        this.pin =pin;

        JLabel label1 = new JLabel("Your Current Balance is Rs ");
        label1.setForeground(Color.black);
        label1.setFont(new Font("System", Font.BOLD, 30));
        label1.setBounds(100,50,700,35);
        add(label1);

        label2 = new JLabel();
        label2.setBackground(new Color(200,200,200));
        label2.setForeground(Color.black);
        label2.setFont(new Font("System", Font.BOLD, 30));
        label2.setBounds(200,200,400,35);
        add(label2);

        b1 = new JButton("Back");
        b1.setBounds(500,400,150,35);
        b1.setBackground(new Color(200,200,200));
        b1.setForeground(Color.black);
        b1.setFont(new Font("System", Font.BOLD, 16));
        b1.addActionListener(this);
        add(b1);

        int balance =0;
        try{
            DBConnection c = new DBConnection();
            ResultSet resultSet = c.statement.executeQuery("Select * from bank where pin = '"+pin+"'");
            while (resultSet.next()){
                if (resultSet.getString("type").equals("Deposit")){
                    balance += Integer.parseInt(resultSet.getString("amount"));
                }else {
                    balance -= Integer.parseInt(resultSet.getString("amount"));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        label2.setText(""+balance);

        getContentPane().setBackground(Color.white);
        setLayout(null);
        setSize(850,500);
        setLocation(450,200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new Main_menu(pin);
    }

    public static void main(String[] args) {
        new BalanceEnquiry("");
    }
}





