package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_menu extends JFrame implements ActionListener {
    JButton b1,b2,b3,b4,b5;
    String pin;
    Main_menu(String pin){
        this.pin=pin;

        JLabel label = new JLabel("Please Select Your Transaction");
        label.setBounds(200,50,700,35);
        label.setForeground(Color.black);
        label.setFont(new Font("System",Font.BOLD,28));
        add(label);

        b1 = new JButton("DEPOSIT");
        b1.setForeground(Color.WHITE);
        b1.setBackground(new Color(65,125,128));
        b1.setBounds(200,200,150,35);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("WITHDRAW");
        b2.setForeground(Color.WHITE);
        b2.setBackground(new Color(65,125,128));
        b2.setBounds(500,200,150,35);
        b2.addActionListener(this);
        add(b2);

        b3 = new JButton("PIN CHANGE");
        b3.setForeground(Color.WHITE);
        b3.setBackground(new Color(65,125,128));
        b3.setBounds(200,300,150,35);
        b3.addActionListener(this);
        add(b3);

        b4 = new JButton("BALANCE ENQUIRY");
        b4.setForeground(Color.WHITE);
        b4.setBackground(new Color(65,125,128));
        b4.setBounds(500,300,150,35);
        b4.addActionListener(this);
        add(b4);

        b5 = new JButton("EXIT");
        b5.setForeground(Color.WHITE);
        b5.setBackground(new Color(65,125,128));
        b5.setBounds(350,400,150,35);
        b5.addActionListener(this);
        add(b5);

        getContentPane().setBackground(Color.white);

        setLayout(null);
        setSize(850,500);
        setLocation(450,200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==b1){
            new Deposit(pin);
            setVisible(false);
        }else if (e.getSource()==b2){
            new Withdraw(pin);
        } else if (e.getSource()==b3) {
            new PinChange("");
        } else if (e.getSource()==b4) {
            new BalanceEnquiry(pin);
            setVisible(false);
        } else if (e.getSource()==b5) {
            System.exit(0);
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new Main_menu("");
    }
}
