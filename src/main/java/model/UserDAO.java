package model;

import entity.User;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends myDAO {

    public List<User> getAllUser() {
        List<User> t = new ArrayList<>();
        xSql = "select * from [user]";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xUserID;
            String xUserName, xPassword, xEmail, xFirstName, xLastName;
            Date xDob;
            int xSex, xRole;
            String xPhone, xUserImg;
            User x;
            while (rs.next()) {
                xUserID = rs.getInt("UserID");
                xUserName = rs.getString("UserName");
                xPassword = rs.getString("Password");
                xEmail = rs.getString("Email");
                xFirstName = rs.getString("FirstName");
                xLastName = rs.getString("LastName");
                xDob = rs.getDate("Dob");
                xSex = rs.getInt("Sex");
                xRole = rs.getInt("Role");
                xPhone = rs.getString("Phone");
                x = new User(xUserID, xUserName, xPassword, xEmail, xFirstName, xLastName, xDob, xSex, xRole, xPhone);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public User checkUser(String xEmail, String xPassword) {
        try {
            xSql = "select * from [user]\n"
                    + "where Email = ? and Password = ?";
            ps = con.prepareStatement(xSql);
            ps.setString(1, xEmail);
            ps.setString(2, xPassword);
            int xUserID;
            String xUserName, xFirstName, xLastName;
            Date xDob;
            int xSex, xRole;
            String xPhone;
            rs = ps.executeQuery();
            while (rs.next()) {
                xUserID = rs.getInt("UserID");
                xUserName = rs.getString("UserName");
                xPassword = rs.getString("Password");
                xEmail = rs.getString("Email");
                xFirstName = rs.getString("FirstName");
                xLastName = rs.getString("LastName");
                xDob = rs.getDate("Dob");
                xSex = rs.getInt("Sex");
                xRole = rs.getInt("Role");
                xPhone = rs.getString("Phone");
                return new User(xUserID, xUserName, xPassword, xEmail, xFirstName, xLastName, xDob, xSex, xRole, xPhone);

            }
        } catch (Exception e) {
            System.out.println("CheckUser: " + e.getMessage());
        }
        return null;
    }

    public boolean checkAccountExist(String xusername, String xemail) {
        try {
            xSql = "select *\n"
                    + "from [user]\n"
                    + "where [UserName] = ? and [Email] = ?";
            ps = con.prepareStatement(xSql);
            ps.setString(1, xusername);
            ps.setString(2, xemail);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("CheckAccountExist: " + e.getMessage());
        }
        return false;
    }
    
     public User getUserForget(String xusername, String xemail) {
        try {
            xSql = "select * "
                    + "from [user] "
                    + "where [UserName] = ? and [Email] = ?";
            ps = con.prepareStatement(xSql);
            ps.setString(1, xusername);
            ps.setString(2, xemail);
            rs = ps.executeQuery();
            String xUserName, xPassword, xEmail, xFirstName, xLastName;
            Date xDob;
            int xSex, xRole, uID;
            String xPhone;
            User u;
            if (rs.next()) {
                uID = rs.getInt("UserID");
                xUserName = rs.getString("UserName");
                xPassword = rs.getString("Password");
                xEmail = rs.getString("Email");
                xFirstName = rs.getString("FirstName");
                xLastName = rs.getString("LastName");
                xDob = rs.getDate("Dob");
                xSex = rs.getInt("Sex");
                xRole = rs.getInt("Role");
                xPhone = rs.getString("Phone");
                u = new User(uID, xUserName, xPassword, xEmail, xFirstName, xLastName, xDob, xSex, xRole, xPhone);
                return u;
            }
        } catch (Exception e) {
            System.out.println("getUserForget: " + e.getMessage());
        }
        return null;
    }

    public void addUser(String xUsername, String xPassword, String xEmail, int xRole, Date dob) {
        try {
            xSql = "INSERT INTO [user] (UserName,Password,Email,FirstName,LastName,Dob,Sex,Role, Phone)"
                    + " values (?, ?, ?, null, null, ?, null, ?, null)";
            ps = con.prepareStatement(xSql);
            ps.setString(1, xUsername);
            ps.setString(2, xPassword);
            ps.setString(3, xEmail);
            ps.setDate(4, dob);
            ps.setInt(5, xRole);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("addUser: " + e.getMessage());
        }
    }

    public User getUserById(int uID) {
        xSql = "select * from [user]\n"
                + "where [UserID] = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, uID);
            rs = ps.executeQuery();
            String xUserName, xPassword, xEmail, xFirstName, xLastName;
            Date xDob;
            int xSex, xRole;
            String xPhone;
            User u;
            while (rs.next()) {
                uID = rs.getInt("UserID");
                xUserName = rs.getString("UserName");
                xPassword = rs.getString("Password");
                xEmail = rs.getString("Email");
                xFirstName = rs.getString("FirstName");
                xLastName = rs.getString("LastName");
                xDob = rs.getDate("Dob");
                xSex = rs.getInt("Sex");
                xRole = rs.getInt("Role");
                xPhone = rs.getString("Phone");
                u = new User(uID, xUserName, xPassword, xEmail, xFirstName, xLastName, xDob, xSex, xRole, xPhone);
                return u;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void ChangePass(String xpassword, int userId) {
        try {
            xSql = "update [user] set [Password] = ? where [UserID] = ?";
            ps = con.prepareStatement(xSql);
            ps.setString(1, xpassword);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("ChangePass: " + e.getMessage());
        }
    }

    public List<User> getAllSale() {
        List<User> t = new ArrayList<>();
        xSql = "select * from user where [Role] = 2 ";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xUserID;
            String xUserName, xPassword, xEmail, xFirstName, xLastName;
            Date xDob;
            int xSex, xRole;
            String xPhone, xUserImg;
            User x;
            while (rs.next()) {
                xUserID = rs.getInt("UserID");
                xUserName = rs.getString("UserName");
                xPassword = rs.getString("Password");
                xEmail = rs.getString("Email");
                xFirstName = rs.getString("FirstName");
                xLastName = rs.getString("LastName");
                xDob = rs.getDate("Dob");
                xSex = rs.getInt("Sex");
                xRole = rs.getInt("Role");
                xPhone = rs.getString("Phone");
                x = new User(xUserID, xUserName, xPassword, xEmail, xFirstName, xLastName, xDob, xSex, xRole, xPhone);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public List<User> getUsersByRoleAndSort(String role) {
        List<User> sortedUserList = new ArrayList<>();
        if (role.equalsIgnoreCase("all")) {
            xSql = "select * from [user] where Role != 4 ";
        } else {
            xSql = "select * from [user] where Role = ?";
        }
        try {
            ps = con.prepareStatement(xSql);
            if (!role.equalsIgnoreCase("all")) {
                ps.setString(1, role);
            }
            rs = ps.executeQuery();
            int xUserID;
            String xUserName, xPassword, xEmail, xFirstName, xLastName;
            Date xDob;
            int xSex, xRole;
            String xPhone;
            User u;
            while ((rs.next())) {
                xUserID = rs.getInt("UserID");
                xUserName = rs.getString("UserName");
                xPassword = rs.getString("Password");
                xEmail = rs.getString("Email");
                xFirstName = rs.getString("FirstName");
                xLastName = rs.getString("LastName");
                xDob = rs.getDate("Dob");
                xSex = rs.getInt("Sex");
                xRole = rs.getInt("Role");
                xPhone = rs.getString("Phone");
                u = new User(xUserID, xUserName, xPassword, xEmail, xFirstName, xLastName, xDob, xSex, xRole, xPhone);
                sortedUserList.add(u);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sortedUserList;
    }

    public List<User> searchByName(String sName, String role) {
        if (role.equalsIgnoreCase("all")) {
            xSql = "select * from User where UserName like '%" + sName + "%' and Role != 4";
        } else {
            xSql = "select * from User where UserName like '%" + sName + "%' and Role = 4";
        }
        List<User> userList = new ArrayList<>();
        try {
            int xUserID;
            String xUserName, xPassword, xEmail, xFirstName, xLastName;
            Date xDob;
            int xSex, xRole;
            String xPhone;
            User u;
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                xUserID = rs.getInt("UserID");
                xUserName = rs.getString("UserName");
                xPassword = rs.getString("Password");
                xEmail = rs.getString("Email");
                xFirstName = rs.getString("FirstName");
                xLastName = rs.getString("LastName");
                xDob = rs.getDate("Dob");
                xSex = rs.getInt("Sex");
                xRole = rs.getInt("Role");
                xPhone = rs.getString("Phone");
                u = new User(xUserID, xUserName, xPassword, xEmail, xFirstName, xLastName, xDob, xSex, xRole, xPhone);
                userList.add(u);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    public User getRoleByEmail(String xemail) {
        User u = null;
        xSql = "select Role from [user] where Email=? ";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, xemail);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xRole = rs.getInt(1);
                u = new User(xRole);
            }
        } catch (Exception e) {
            System.out.println("getRoleByUser" + e);
        }
        return u;
    }

    public int CustomerTotal(String input) {
        if (input.equals("all")) {
            xSql = "select count(*) from [user] where Role = 4;";
        } else if (input.equals("male")) {
            xSql = "select count(*) from [user] where Role = 4 and Sex = 1;";
        } else if (input.equals("female")) {
            xSql = "select count(*) from [user] where Role = 4 and Sex = 2;";
        } else {
            xSql = "select count(*) from [user] where Role = 4 and (Sex != 1 and Sex != 2) ;";
        }
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int c = rs.getInt(1);
                return c;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

//    public List<User> selectAllSalesName() {
//        List<User> t = new ArrayList<>();
//        xSql = "SELECT FirstName + ' ' + LastName AS FullName\n"
//                + "FROM [user]\n"
//                + "WHERE Role = 2;";
//        try {
//            ps = con.prepareStatement(xSql);
//            rs = ps.executeQuery();
//            String xUserName;
//            User x;
//            while (rs.next()) {
//                xUserName = rs.getString("UserName");
//                t.add(x);
//            }
//            rs.close();
//            ps.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return t;
//    }
    public List<User> selectAllSalesName() {
        List<User> salesList = new ArrayList<>();
        String query = "SELECT UserID, FirstName + ' ' + LastName AS FullName FROM [user] WHERE Role = 2;";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("UserID");
                String fullName = rs.getString("FullName");
                User sale = new User();
                sale.setUserID(userId);
                sale.setUserName(fullName);
                salesList.add(sale);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salesList;
    }

    public static void main(String[] args) {
        UserDAO saleDAO = new UserDAO();
        List<User> sales = saleDAO.selectAllSalesName();

        // In ra danh sách các Sale
        for (User sale : sales) {
            System.out.println("ID: " + sale.getUserID() + ", Name: " + sale.getUserName());
        }
    }
}

