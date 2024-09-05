package bank.management.system;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PinChange extends JFrame implements ActionListener {
    JButton b1,b2;
    JPasswordField p1,p2;
    String Pin;
    PinChange(String Pin){
        this.Pin =Pin;

        JLabel label1 = new JLabel("CHANGE YOUR PIN");
        label1.setForeground(Color.BLACK);
        label1.setFont(new Font("System", Font.BOLD, 30));
        label1.setBounds(100,50,400,35);
        add(label1);


        JLabel label2 = new JLabel("New PIN: ");
        label2.setForeground(Color.BLACK);
        label2.setFont(new Font("System", Font.BOLD, 20));
        label2.setBounds(350,200,150,35);
        add(label2);

        p1 = new JPasswordField();
        p1.setBackground(new Color(65,125,128));
        p1.setForeground(Color.WHITE);
        p1.setBounds(500,200,180,25);
        p1.setFont(new Font("Raleway", Font.BOLD,22));
        add(p1);

        JLabel label3 = new JLabel("Re-Enter PIN: ");
        label3.setForeground(Color.BLACK);
        label3.setFont(new Font("System", Font.BOLD, 20));
        label3.setBounds(350,250,400,35);
        add(label3);

        p2 = new JPasswordField();
        p2.setBackground(new Color(65,125,128));
        p2.setForeground(Color.WHITE);
        p2.setBounds(500,250,180,25);
        p2.setFont(new Font("Raleway", Font.BOLD,22));
        add(p2);



        b1 = new JButton("CHANGE");
        b1.setBounds(350,400,150,35);
        b1.setBackground(Color.green);
        b1.setForeground(Color.BLACK);
        b1.setFont(new Font("Raleway", Font.BOLD,20));
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(550,400,150,35);
        b2.setBackground(Color.red);
        b2.setForeground(Color.BLACK);
        b2.setFont(new Font("Raleway", Font.BOLD,20));
        b2.addActionListener(this);
        add(b2);

        getContentPane().setBackground(Color.white);

        setSize(850,500);
        setLayout(null);
        setLocation(450,200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try{

            String pin1 = p1.getText();
            String pin2 = p2.getText();

            if (!pin1.equals(pin2)){
                JOptionPane.showMessageDialog(null,"Entered PIN does not match");
                return;
            }
            if (e.getSource()==b1){
                if (p1.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Enter New PIN");
                    return;
                }
                if (p2.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Re-Enter New PIN");
                    return;
                }

                DBConnection c = new DBConnection();
                String q1 = "update bank set pin = '"+pin1+"' where pin = '"+Pin+"'";
                String q2 = "update LoginDetails set pin = '"+pin1+"' where pin = '"+Pin+"'";
                String q3 = "update Signup3Details set pin = '"+pin1+"' where pin = '"+Pin+"'";

                c.statement.executeUpdate(q1);
                c.statement.executeUpdate(q2);
                c.statement.executeUpdate(q3);

                JOptionPane.showMessageDialog(null,"PIN changed successfully");
                setVisible(false);
                new Main_menu(Pin);

            } else if (e.getSource()==b2) {
                new Main_menu(Pin);
                setVisible(false);
            }


        }catch (Exception E){
            E.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new PinChange("");
    }
}