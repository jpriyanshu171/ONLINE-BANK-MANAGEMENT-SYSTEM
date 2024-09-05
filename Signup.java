package bank.management.system;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
public class Signup extends JFrame implements ActionListener {
    JRadioButton r1,r2,m1,m2,m3;
    JButton next;
    JComboBox comboBox1,comboBox2;

    JTextField textName ,textFname, textEmail,textAdd,textcity,textState,textPin,textPan,textAadhar;

    JDateChooser dateChooser;
    Signup(){
        super("SIGN UP PAGE"); // ye frame ki heading pe jayega

        JLabel label3 = new JLabel("Personal Details");
        label3.setFont(new Font("Raleway", Font.BOLD,22));
        label3.setBounds(290,90,600,30);
        add(label3);

        JLabel labelName = new JLabel("Name :");
        labelName.setFont(new Font("Raleway", Font.BOLD, 20));
        labelName.setBounds(100,160,100,30);
        add(labelName);

        textName = new JTextField();
        textName.setFont(new Font("Raleway",Font.BOLD, 14));
        textName.setBounds(300,160,400,30);
        add(textName);

        JLabel labelfName = new JLabel("Father's Name :");
        labelfName.setFont(new Font("Raleway", Font.BOLD, 20));
        labelfName.setBounds(100,200,200,30);
        add(labelfName);

        textFname = new JTextField();
        textFname.setFont(new Font("Raleway",Font.BOLD, 14));
        textFname.setBounds(300,200,400,30);
        add(textFname);

        JLabel DOB = new JLabel("Date of Birth");
        DOB.setFont(new Font("Raleway", Font.BOLD, 20));
        DOB.setBounds(100,240,200,30);
        add(DOB);

        dateChooser = new JDateChooser();
        dateChooser.setForeground(new Color(105,105,105));
        dateChooser.setBounds(300,240,400,30);
        add(dateChooser);

        JLabel labelG = new JLabel("Gender");
        labelG.setFont(new Font("Raleway", Font.BOLD, 20));
        labelG.setBounds(100,280,200,30);
        add(labelG);

        r1 = new JRadioButton("Male");
        r1.setFont(new Font("Raleway", Font.BOLD,14));
        r1.setBackground(Color.white);
        r1.setBounds(300,280,60,30);
        add(r1);

        r2 = new JRadioButton("Female");
        r2.setBackground(Color.white);
        r2.setFont(new Font("Raleway", Font.BOLD,14));
        r2.setBounds(450,280,90,30);
        add(r2);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(r1);
        buttonGroup.add(r2);

        JLabel labelEmail = new JLabel("Email address :");
        labelEmail.setFont(new Font("Raleway", Font.BOLD, 20));
        labelEmail.setBounds(100,320,200,30);
        add(labelEmail);

        textEmail = new JTextField();
        textEmail.setFont(new Font("Raleway",Font.BOLD, 14));
        textEmail.setBounds(300,320,400,30);
        add(textEmail);


        JLabel labelMs = new JLabel("Marital Status :");
        labelMs.setFont(new Font("Raleway", Font.BOLD, 20));
        labelMs.setBounds(100,360,200,30);
        add(labelMs);

        m1 = new JRadioButton("Married");
        m1.setBounds(300,360,100,30);
        m1.setBackground(Color.white);
        m1.setFont(new Font("Raleway", Font.BOLD,14));
        add(m1);

        m2 = new JRadioButton("Unmarried");
        m2.setBackground(Color.white);
        m2.setBounds(450,360,100,30);
        m2.setFont(new Font("Raleway", Font.BOLD,14));
        add(m2);

        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(m1);
        buttonGroup1.add(m2);

        JLabel l3 = new JLabel("Religion :");
        l3.setFont(new Font("Raleway", Font.BOLD,20));
        l3.setBounds(100,405,200,30);
        add(l3);

        String religion[] = {"Hindu","Muslim","Sikh", "Christian", "Other"};
        comboBox1 = new JComboBox(religion);
        comboBox1.setBackground(new Color(200,200,200));
        comboBox1.setFont(new Font("Raleway",Font.BOLD,14));
        comboBox1.setBounds(300,400,400,30);
        add(comboBox1);

        JLabel l4 = new JLabel("Category : ");
        l4.setFont(new Font("Raleway", Font.BOLD,20));
        l4.setBounds(100,455,200,30);
        add(l4);

        String Category [] = {"General","OBC","SC", "ST", "Other"};
        comboBox2 = new JComboBox(Category);
        comboBox2.setBackground(new Color(200,200,200));
        comboBox2.setFont(new Font("Raleway",Font.BOLD,14));
        comboBox2.setBounds(300,460,400,30);
        add(comboBox2);

        JLabel labelAdd = new JLabel("Address :");
        labelAdd.setFont(new Font("Raleway", Font.BOLD, 20));
        labelAdd.setBounds(100,500,200,30);
        add(labelAdd);

        textAdd = new JTextField();
        textAdd.setFont(new Font("Raleway",Font.BOLD, 14));
        textAdd.setBounds(300,500,400,30);
        add(textAdd);

        JLabel labelCity = new JLabel("City :");
        labelCity.setFont(new Font("Raleway", Font.BOLD, 20));
        labelCity.setBounds(100,540,200,30);
        add(labelCity);

        textcity = new JTextField();
        textcity.setFont(new Font("Raleway",Font.BOLD, 14));
        textcity.setBounds(300,540,400,30);
        add(textcity);

        JLabel labelPin = new JLabel("Pin Code :");
        labelPin.setFont(new Font("Raleway", Font.BOLD, 20));
        labelPin.setBounds(100,580,200,30);
        add(labelPin);

        textPin = new JTextField();
        textPin.setFont(new Font("Raleway",Font.BOLD, 14));
        textPin.setBounds(300,580,400,30);
        add(textPin);

        JLabel labelstate = new JLabel("State :");
        labelstate.setFont(new Font("Raleway", Font.BOLD, 20));
        labelstate.setBounds(100,620,200,30);
        add(labelstate);

        textState = new JTextField();
        textState.setFont(new Font("Raleway",Font.BOLD, 14));
        textState.setBounds(300,620,400,30);
        add(textState);

        next = new JButton("Next ->");
        next.setFont(new Font("Raleway",Font.BOLD, 14));
        next.setBackground(new Color(36,160,237));
        next.setForeground(Color.WHITE);
        next.setBounds(620,710,80,30);
        next.addActionListener(this);
        add(next);


        getContentPane().setBackground(Color.white);
        JLabel label1=new JLabel("REGISTRATION FORM");
        label1.setBounds(160,20,600,40);
        label1.setFont(new Font("AvantGarde", Font.BOLD, 50));
        add(label1);
        setLayout(null);
        setSize(850,800); // frame ka size
        setLocation(400,20); // frame ki location
        setVisible(true); // frame ki visibility
    }
    public void actionPerformed(ActionEvent e) {

        String Name = textName.getText();
        String FName = textFname.getText();
        String DOB = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        String Gender = null;
        if(r1.isSelected()){
            Gender = "Male";
        }else if (r2.isSelected()){
            Gender = "Female";
        }
        String Email = textEmail.getText();
        String Marital_Status =null;
        if (m1.isSelected()){
            Marital_Status = "Married";
        } else if (m2.isSelected()) {
            Marital_Status = "Unmarried";
        }

        String Address = textAdd.getText();
        String City = textcity.getText();
        String Pincode = textPin.getText();
        String State = textState.getText();
        String Religion = (String) comboBox1.getSelectedItem();
        String Category = (String) comboBox2.getSelectedItem();

        try{
            if (textName.getText().equals("")){
                JOptionPane.showMessageDialog(null, "All the fiels are neccesary");

            }else {
                DBConnection c=new DBConnection();
                String query="insert into SignupDetails values('"+Name+"','"+FName+"','"+DOB+"','"+Gender+"','"+Email+"','"+Marital_Status+"','"+Address+"','"+City+"','"+Pincode+"','"+State+"','"+Religion+"','"+Category+"')";
                c.statement.executeUpdate(query);
                new Signup2();
                setVisible(false);
            }

        }catch (Exception E){
            E.printStackTrace();
        }

    }
    public static void main(String[] args) {
        new Signup();
    }
}
