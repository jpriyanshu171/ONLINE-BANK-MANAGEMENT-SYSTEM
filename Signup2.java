package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Signup2 extends JFrame implements ActionListener {
    JComboBox comboBox1,comboBox2,comboBox3;
    JTextField textPan,textAadhar;
    JRadioButton r1,r2, e1,e2;
    JButton next;
    Signup2(){
        super("APPLICATION FORM");

        JLabel l1 = new JLabel("ADDITIONAL DETAILS");
        l1.setFont(new Font("AvantGarde", Font.BOLD, 50));
        l1.setBounds(180,90,600,40);
        add(l1);

        JLabel l2 = new JLabel("Income : ");
        l2.setFont(new Font("Raleway", Font.BOLD,20));
        l2.setBounds(100,220,100,30);
        add(l2);

        String income [] = {"Null","<1,50,000","<2,50,000", "5,00,000", "Uptp 10,00,000","Above 10,00,000"};
        comboBox1 = new JComboBox(income);
        comboBox1.setBackground(new Color(200,200,200));
        comboBox1.setFont(new Font("Raleway",Font.BOLD,14));
        comboBox1.setBounds(350,220,320,30);
        add(comboBox1);

        JLabel l3 = new JLabel("Educational : ");
        l3.setFont(new Font("Raleway", Font.BOLD,20));
        l3.setBounds(100,270,150,30);
        add(l3);

        String educational [] = {"Non-Graduate","Graduate","Post-Graduate", "Doctrate", "Others"};
        comboBox2 = new JComboBox(educational);
        comboBox2.setBackground(new Color(200,200,200));
        comboBox2.setFont(new Font("Raleway",Font.BOLD,14));
        comboBox2.setBounds(350,270,320,30);
        add(comboBox2);


        JLabel l4 = new JLabel("Occupation : ");
        l4.setFont(new Font("Raleway", Font.BOLD,20));
        l4.setBounds(100,340,150,30);
        add(l4);

        String Occupation [] = {"Salaried","Self-Employed","Business", "Student", "Retired", "Other"};
        comboBox3 = new JComboBox(Occupation);
        comboBox3.setBackground(new Color(200,200,200));
        comboBox3.setFont(new Font("Raleway",Font.BOLD,14));
        comboBox3.setBounds(350,340,320,30);
        add(comboBox3);

        JLabel l5 = new JLabel("PAN Number : ");
        l5.setFont(new Font("Raleway", Font.BOLD,20));
        l5.setBounds(100,390,150,30);
        add(l5);

        textPan = new JTextField();
        textPan.setFont(new Font("Raleway", Font.BOLD,20));
        textPan.setBounds(350,390,320,30);
        add(textPan);

        JLabel l6 = new JLabel("Aadhar Number : ");
        l6.setFont(new Font("Raleway", Font.BOLD,20));
        l6.setBounds(100,440,180,30);
        add(l6);

        textAadhar = new JTextField();
        textAadhar.setFont(new Font("Raleway", Font.BOLD,20));
        textAadhar.setBounds(350,440,320,30);
        add(textAadhar);


        JLabel l7 = new JLabel("Senior Citizen : ");
        l7.setFont(new Font("Raleway", Font.BOLD,20));
        l7.setBounds(100,490,180,30);
        add(l7);

        r1 = new JRadioButton("Yes");
        r1.setFont(new Font("Raleway", Font.BOLD,14));
        r1.setBackground(new Color(200,200,200));
        r1.setBounds(350,490,100,30);
        add(r1);
        r2 = new JRadioButton("No");
        r2.setFont(new Font("Raleway", Font.BOLD,14));
        r2.setBackground(new Color(200,200,200));
        r2.setBounds(460,490,100,30);
        add(r2);

        JLabel l11 = new JLabel("Existing Account : ");
        l11.setFont(new Font("Raleway", Font.BOLD,20));
        l11.setBounds(100,540,180,30);
        add(l11);

        e1 = new JRadioButton("Yes");
        e1.setFont(new Font("Raleway", Font.BOLD,14));
        e1.setBackground(new Color(200,200,200));
        e1.setBounds(350,540,100,30);
        add(e1);
        e2 = new JRadioButton("No");
        e2.setFont(new Font("Raleway", Font.BOLD,14));
        e2.setBackground(new Color(200,200,200));
        e2.setBounds(460,540,100,30);
        add(e2);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(e1);
        buttonGroup.add(e2);

        next = new JButton("Next");
        next.setFont(new Font("Raleway",Font.BOLD,20));
        next.setBackground(new Color(36,160,237));
        next.setForeground(Color.BLACK);
        next.setBounds(570,640,100,30);
        next.addActionListener(this);
        add(next);


        setLayout(null);
        setSize(850,750);
        setLocation(450,40);
        getContentPane().setBackground(Color.white);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String Income = (String) comboBox1.getSelectedItem();
        String Education = (String) comboBox2.getSelectedItem();
        String Occupation = (String) comboBox3.getSelectedItem();
        String Pan = textPan.getText();
        String Aadhar = textAadhar.getText();

        String S_Citizen = " ";
        if ((r1.isSelected())){
            S_Citizen = "Yes";
        } else if (r2.isSelected()) {
            S_Citizen ="No";
        }
        String eAccount = " ";
        if ((r1.isSelected())){
            eAccount = "Yes";
        } else if (r2.isSelected()) {
            eAccount ="No";
        }
        try{
            if (textPan.getText().equals("") || textAadhar.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Fill all the fields");
            }else {
                DBConnection c = new DBConnection();
                String query = "insert into Signup2Details values('"+Income+"','"+Education+"','"+Occupation+"','"+Pan+"','"+Aadhar+"','"+S_Citizen+"','"+eAccount+"')";
                c.statement.executeUpdate(query);
                new Signup3();
                setVisible(false);
            }
        }catch (Exception E){
            E.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Signup2();
    }
}
