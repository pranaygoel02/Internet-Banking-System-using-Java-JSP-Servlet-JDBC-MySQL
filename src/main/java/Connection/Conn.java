package Connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.lang.ClassNotFoundException;

public class Conn {

    private static Connection conn;
    private Conn() {
        if(conn == null) {
        	try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybank", "root", "2112");
                System.out.println("DB Connected");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }	
        }
    }
    
    public static Connection getConnectionObj() throws SQLException, ClassNotFoundException {
    	if(conn == null) {
    		synchronized (Conn.class) {
				if(conn == null) {
					new Conn();
				}
			}
    	}
    	return conn;
    }
}
