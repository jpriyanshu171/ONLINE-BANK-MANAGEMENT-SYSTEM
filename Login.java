package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JLabel label1, label2, label3;
    JTextField textField2;
    JPasswordField passwordField3;

    JButton button1,button2,button3;
    Login(){
        super("LOGIN PAGE");

        label1 = new JLabel("WELCOME");
        label1.setForeground(Color.BLACK);
        label1.setFont(new Font("AvantGarde", Font.BOLD, 50));
        label1.setBounds(270,50,450,40);
        add(label1);

        label2 = new JLabel("Card No:");
        label2.setFont(new Font("Ralway", Font.BOLD, 28));
        label2.setForeground(Color.black);
        label2.setBounds(150,190,375,30);
        add(label2);

        textField2 = new JTextField(15);
        textField2.setBounds(325,190,230,30);
        textField2.setFont(new Font("Arial", Font.BOLD,14));
        add(textField2);

        label3 = new JLabel("PIN: ");
        label3.setFont(new Font("Ralway", Font.BOLD, 28));
        label3.setForeground(Color.black);
        label3.setBounds(150,250,375,30);
        add(label3);

        passwordField3 = new JPasswordField(15);
        passwordField3.setBounds(325,250,230,30);
        passwordField3.setFont(new Font("Arial", Font.BOLD, 14));
        add(passwordField3);

        button1 = new JButton("LOG IN");
        button1.setFont(new Font("Arial", Font.BOLD, 18));
        button1.setForeground(Color.BLACK);
        button1.setBackground(Color.GREEN);
        button1.setBounds(325,300,100, 30);
        button1.addActionListener(this);
        add(button1);

        button2 = new JButton("CLEAR");
        button2.setFont(new Font("Arial", Font.BOLD, 18));
        button2.setForeground(Color.WHITE);
        button2.setBackground(Color.RED);
        button2.setBounds(450,300,100, 30);
        button2.addActionListener(this);
        add(button2);

        button3 = new JButton("CREATE ACCOUNT");
        button3.setFont(new Font("Arial", Font.BOLD, 18));
        button3.setForeground(Color.BLACK);
        button3.setBackground(Color.CYAN);
        button3.setBounds(325,350,230, 30);
        button3.addActionListener(this);
        add(button3);

        getContentPane().setBackground(Color.white);

        setLayout(null);
        setSize(850,480);
        setLocation(450,200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if(e.getSource()==button1){
                DBConnection c = new DBConnection();
                String CardNo = textField2.getText();
                String Pin = passwordField3.getText();
                String q = "select * from LoginDetails where CardNo = '"+CardNo+"' and  pin = '"+Pin+"'";
                ResultSet resultSet = c.statement.executeQuery(q);
                if (resultSet.next()){
                    setVisible(false);
                    new Main_menu(Pin);
                }
            } else if (e.getSource()==button2) {
                textField2.setText("");
                passwordField3.setText("");
            } else if (e.getSource()==button3) {
                    new Signup();
                    setVisible(false);
                }
        }
        catch(Exception E){
            E.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}