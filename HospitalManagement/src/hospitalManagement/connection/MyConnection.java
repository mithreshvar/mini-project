package hospitalManagement.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public class MyConnection {
    public static Connection getConnection() {
        Connection con = null;
        try {
            ResourceBundle rb = ResourceBundle.getBundle("hospitalManagement.connection.database");
            String url = rb.getString("url");
            String uname = rb.getString("uname");
            String pwd = rb.getString("pwd");
            con = DriverManager.getConnection(url, uname, pwd);
            // System.out.println(con);
            // con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
