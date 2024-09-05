package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {
    String Pin;
    TextField textField;

    JButton b1, b2;
    Deposit(String Pin){
        this.Pin = Pin;

        JLabel label1 = new JLabel("ENTER THE AMOUNT YOU WANT TO DEPOSIT");
        label1.setForeground(Color.black);
        label1.setFont(new Font("System", Font.BOLD, 20));
        label1.setBounds(150,100,500,35);
        add(label1);

        textField = new TextField();
        textField.setBackground(new Color(65,125,128));
        textField.setForeground(Color.WHITE);
        textField.setBounds(230,180,320,25);
        textField.setFont(new Font("Raleway", Font.BOLD,22));
        add(textField);

        b1 = new JButton("DEPOSIT");
        b1.setBounds(300,300,150,35);
        b1.setBackground(new Color(0, 128, 0));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("System", Font.BOLD, 16));

        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(300,380,150,35);
        b2.setBackground(new Color(200,200,200));
        b2.setForeground(Color.BLACK);
        b2.setFont(new Font("System", Font.BOLD, 16));
        b2.addActionListener(this);
        add(b2);

        getContentPane().setBackground(Color.white);

        setLayout(null);
        setSize(850,480);
        setLocation(450,200);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String amount = textField.getText();
            Date date = new Date();
            if (e.getSource()==b1){
                if (textField.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Please enter the Amount you want to Deposit");
                }else {
                    DBConnection c = new DBConnection();
                    c.statement.executeUpdate("insert into bank values('"+Pin+"', '"+date+"','Deposit', '"+amount+"')");
                    JOptionPane.showMessageDialog(null,"Rs. "+amount+" Deposited Successfully");
                    setVisible(false);
                    new Main_menu(Pin);
                }
            }else if (e.getSource()==b2){
                setVisible(false);
                new Main_menu(Pin);
            }
        }catch (Exception E){
            E.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Deposit("");
    }
}
