package practice_7_exception.exception.checked;

import java.sql.*;

public class SQLExceptionExample {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbs:mysql://localhost/testdb", "username", "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT  * FROM users");

            while (rs.next()) {
                System.out.println("User: " + rs.getString("username"));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Error Closing Connection: " + e.getMessage());
            }
        }
    }
}
