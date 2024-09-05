package bank.management.system;
import java.sql.*;

public class DBConnection {
    Connection connection;
    Statement statement;
    DBConnection(){
        try {
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/bankSystem","root","");
            statement=connection.createStatement();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new DBConnection();
    }
}
