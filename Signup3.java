package bank.management.system;
import javax.print.attribute.standard.JobHoldUntil;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.server.ExportException;
import java.util.Random;

public class Signup3 extends JFrame implements ActionListener {

    JRadioButton r1,r2,r3;
    JCheckBox c1,c2,c3,c4,c5;
    JButton s,c;
    String formno;
    Signup3(){

        JLabel l1 = new JLabel("Account Details");
        l1.setFont(new Font("Raleway",Font.BOLD,40));
        l1.setBounds(280,50,400,40);
        add(l1);

        JLabel l2 = new JLabel("Account Type:");
        l2.setFont(new Font("Raleway",Font.BOLD,20));
        l2.setBounds(100,140,200,30);
        add(l2);

        r1 = new JRadioButton("Saving Account");
        r1.setFont(new Font("Raleway",Font.BOLD,16));
        r1.setBackground(Color.white);
        r1.setBounds(330,180,150,30);
        add(r1);

        r2 = new JRadioButton("Fixed Deposit Account");
        r2.setFont(new Font("Raleway",Font.BOLD,16));
        r2.setBackground(Color.white);
        r2.setBounds(330,260,300,30);
        add(r2);

        r3 = new JRadioButton("Current Account");
        r3.setFont(new Font("Raleway",Font.BOLD,16));
        r3.setBackground(Color.WHITE);
        r3.setBounds(330,220,250,30);
        add(r3);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(r1);
        buttonGroup.add(r2);
        buttonGroup.add(r3);

        JLabel l7 = new JLabel("Services Required:");
        l7.setFont(new Font("Raleway",Font.BOLD,20));
        l7.setBounds(100,440,200,30);
        add(l7);

        c1 = new JCheckBox("ATM CARD");
        c1.setBackground(Color.white);
        c1.setFont(new Font("Raleway",Font.BOLD,16));
        c1.setBounds(330,480,200,30);
        add(c1);

        c2 = new JCheckBox("Internet Banking");
        c2.setBackground(Color.white);
        c2.setFont(new Font("Raleway",Font.BOLD,16));
        c2.setBounds(330,520,200,30);
        add(c2);

        c3 = new JCheckBox("Cheque Book");
        c3.setBackground(Color.white);
        c3.setFont(new Font("Raleway",Font.BOLD,16));
        c3.setBounds(330,560,200,30);
        add(c3);

        c4 = new JCheckBox("E-Statement");
        c4.setBackground(Color.WHITE);
        c4.setFont(new Font("Raleway",Font.BOLD,16));
        c4.setBounds(330,600,200,30);
        add(c4);


        s = new JButton("Submit");
        s.setFont(new Font("Raleway", Font.BOLD,16));
        s.setBackground(new Color(0, 128, 0));
        s.setForeground(Color.WHITE);
        s.setBounds(250,720,100,30);
        s.addActionListener(this);
        add(s);

        c = new JButton("Cancel");
        c.setFont(new Font("Raleway", Font.BOLD,16));
        c.setBackground(new Color(255,0,0));
        c.setForeground(Color.WHITE);
        c.setBounds(420,720,100,30);
        c.addActionListener(this);
        add(c);

        getContentPane().setBackground(Color.white);
        setSize(850,800);
        setLayout(null);
        setLocation(400,20);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String AccountType = null;
        if (r1.isSelected()){
            AccountType = "Saving Account";
        } else if (r2.isSelected()) {
            AccountType ="Fixed Deposit Account";
        }else if (r3.isSelected()){
            AccountType ="Current Account";
        }
        Random ran = new Random();
        long first7 = (ran.nextLong() % 90000000L) + 1409963000000000L;
        String CardNo = "" + Math.abs(first7);

        long first3 = (ran.nextLong() % 9000L)+ 1000L;
        String Pin = "" + Math.abs(first3);

        String Requirements = "";
        if(c1.isSelected()){
            Requirements = Requirements+"ATM CARD ";
        } else if (c2.isSelected()) {
            Requirements = Requirements+"Internet Banking";
        } else if (c3.isSelected()) {
            Requirements = Requirements+"Mobile Banking";
        } else if (c4.isSelected()) {
            Requirements = Requirements+"EMAIL Alerts";
        } else if (c5.isSelected()) {
            Requirements=Requirements+"Cheque Book";
        }

        try {
            if (e.getSource()==s){
                if (AccountType.equals("")){
                    JOptionPane.showMessageDialog(null,"Fill all the fields");
                }else {
                    DBConnection c1 = new DBConnection();
                    String q1 = "insert into Signup3Details values('"+AccountType+"','"+CardNo+"','"+Pin+"','"+Requirements+"')";
                    String q2 = "insert into LoginDetails values('"+CardNo+"','"+Pin+"')";
                    c1.statement.executeUpdate(q1);
                    c1.statement.executeUpdate(q2);
                    JOptionPane.showMessageDialog(null,"Card Number : "+CardNo+"\n Pin : "+Pin );
                    new Deposit(Pin);
                    setVisible(false);
                }
            } else if (e.getSource()==c) {
                System.exit(0);
            }

        }catch (Exception E){
            E.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Signup3();
    }
}