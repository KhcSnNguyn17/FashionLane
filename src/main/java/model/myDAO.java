package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class myDAO extends DBContext {
    public Connection con = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;
    public String xSql = null;

    public myDAO() {
        con = connection;
    }
    @Override
    protected void finalize() {
        try {
            if(con != null) {
                con.close();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        myDAO myDAO = new myDAO();
        myDAO.finalize();
    }
}
