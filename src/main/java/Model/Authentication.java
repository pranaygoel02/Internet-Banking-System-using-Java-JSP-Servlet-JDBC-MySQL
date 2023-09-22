package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Connection.Conn;

public class Authentication {

    private static String getQueryString(Category type) {
        String queryString = String.format("select %s from %s where %s=?", type.getPin(), type.getTableName(), type.getPrimaryKey());;
        return queryString;
    }
    public static boolean login(Category type, String uid, String password) {
        try {
            boolean flag = false;
            PreparedStatement pst = Conn.getConnectionObj().prepareStatement(getQueryString(type));
            pst.setString(1,uid);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                byte[] passwordBytes = rs.getBytes(type.getPin());
                String dbPass = new String(passwordBytes).trim();
                if(dbPass.equals(password))
                    flag = true;
            }
            return flag;
        }
        catch (Exception e) {
            return false;
        }
    }
}
