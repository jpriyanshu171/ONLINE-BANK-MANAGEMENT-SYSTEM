package bank.management.system;

import java.sql.*;

public class DBConnection {
        private Connection connection;
        private Statement statement;

        public DBConnection() {
                try {
                        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankSystem", "root", "");
                        statement = connection.createStatement();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        // Add these public methods
        public Connection getConnection() {
                return connection;
        }

        public Statement getStatement() {
                return statement;
        }

        public PreparedStatement prepareStatement(String sql) throws SQLException {
                return connection.prepareStatement(sql);
        }

        public static void main(String[] args) {
                new DBConnection();
        }
}