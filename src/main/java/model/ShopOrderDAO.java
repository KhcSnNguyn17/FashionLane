package model;

import entity.DailySaleRevenue;
import entity.ShopOrder;
import entity.User;
import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopOrderDAO extends myDAO {

    public List<ShopOrder> getOrdersByUserID(int userID) {
        List<ShopOrder> so = new ArrayList<>();
        xSql = "select DISTINCT so.* from shop_order so, orderdetails od where UserID = ? and so.shop_orderID = od.OrderID";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            int xShop_orderID;
            int xUserID;
            int xAddressID;
            int xOrder_total;
            int xOrder_status;
            String xRecipient, xRecipent_phone;
            while (rs.next()) {
                xShop_orderID = rs.getInt("shop_orderID");
                xUserID = rs.getInt("UserID");
                xAddressID = rs.getInt("AddressID");
                xOrder_total = rs.getInt("Order_total");
                xOrder_status = rs.getInt("Order_status");
                xRecipient = rs.getString("recipient");
                xRecipent_phone = rs.getString("recipent_phone");
                so.add(new ShopOrder(xShop_orderID, xUserID, xAddressID, xOrder_total, xOrder_status, xRecipient, xRecipent_phone));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("getOrdersByUserID: " + e.getMessage());
        }
        return so;
    }

    public ShopOrder getLatestOrder() {
        xSql = "SELECT TOP 1 * FROM shop_order ORDER BY shop_orderID DESC";
        int xShop_orderID;
        int xUserID;
        int xAddressID;
        int xOrder_total;
        int xOrder_status;
        String xRecipient, xRecipent_phone;

        ShopOrder so = null;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                xShop_orderID = rs.getInt("shop_orderID");
                xUserID = rs.getInt("UserID");
                xAddressID = rs.getInt("AddressID");
                xOrder_total = rs.getInt("Order_total");
                xOrder_status = rs.getInt("Order_status");
                xRecipient = rs.getString("recipient");
                xRecipent_phone = rs.getString("recipent_phone");
                so = new ShopOrder(xShop_orderID, xUserID, xAddressID, xOrder_total, xOrder_status, xRecipient, xRecipent_phone);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("getLatestOrder: " + e.getMessage());
        }
        return so;
    }

    public int getSaleWithMinOrder() {
        int userId = 0;
        xSql = "WITH UserOrderCount AS (\n"
                + "    SELECT u.UserID, u.UserName, u.Role, COUNT(sa.orderId) AS AssignedOrders\n"
                + "    FROM [dbo].[user] u\n"
                + "    LEFT JOIN [dbo].[SaleAssignment] sa ON u.UserID = sa.userId\n"
                + "    WHERE u.Role = 2\n"
                + "    GROUP BY u.UserID, u.UserName, u.Role\n"
                + ")\n"
                + "SELECT TOP 1 UserID \n"
                + "FROM UserOrderCount\n"
                + "ORDER BY AssignedOrders ASC;";

        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("UserID");
                return userId;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("getLatestOrder: " + e.getMessage());
        }
        return -1;
    }

    public void insertOrder(ShopOrder so) {
        xSql = "insert into shop_order(UserID,Order_status,recipient,recipent_phone) values (?,?,?,?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, so.getUserID());
            ps.setInt(2, so.getOrder_status());
            ps.setString(3, so.getRecipient());
            ps.setString(4, so.getRecipent_phone());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void insertSaleAssignment(int orderId, int userId) {
        xSql = "INSERT INTO [dbo].[SaleAssignment]\n"
                + "           ([orderId]\n"
                + "           ,[userId])\n"
                + "     VALUES (?,?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, orderId);
            ps.setInt(2, userId);

            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getCurrentMaxOrderID() {
        xSql = "select max(shop_orderID) as maxOID from shop_order";
        int currMaxOID = 0;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            if (rs.next()) {
                currMaxOID = rs.getInt("maxOID");
            }
        } catch (Exception e) {
            System.out.println("getCurrMaxOID: " + e.getMessage());
        }
        return currMaxOID;
    }

    public void setOrderTotal() {
        xSql = "UPDATE so\n"
                + "SET so.Order_total = (\n"
                + "    SELECT SUM(od.Quantity * od.Price) + 36000\n"
                + "    FROM orderdetails AS od\n"
                + "    WHERE od.OrderID = so.shop_orderID\n"
                + ")\n"
                + "FROM shop_order AS so\n"
                + "WHERE so.Order_status = 1;";
        try {
            ps = con.prepareStatement(xSql);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("setOrderTotal: " + e.getMessage());
        }
    }

    public void updateStatusOrder(int status, int id) {
        xSql = "UPDATE  [shop_order] set [Order_status] = ?  WHERE [shop_orderID] = ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("setOrderTotal: " + e.getMessage());
        }
    }

    public void updateSaleAssignment(int orderId, int userId) {
        xSql = "Update SaleAssignment set userId = ? where orderId = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, userId);
            ps.setInt(2, orderId);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("setOrderTotal: " + e.getMessage());
        }
    }

    public List<ShopOrder> findAll() {
        List<ShopOrder> t = new ArrayList<>();
        xSql = "  select s.* ,(u.FirstName + ' ' + u.LastName) AS FullName , sa.userId as saleId from shop_order s  \n"
                + "  Left JOIN SaleAssignment sa ON s.shop_orderID = sa.orderId \n"
                + "  LEFT JOIN [user] u ON sa.userId = u.UserID";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xShop_orderID;
            int xUserID;
            int xAddressID;
            int xOrder_total;
            int xOrder_status;
            int xSaleID;
            String fullName;
            String xRecipient, xRecipent_phone;
            while (rs.next()) {
                xShop_orderID = rs.getInt("shop_orderID");
                xUserID = rs.getInt("UserID");
                xAddressID = rs.getInt("AddressID");
                xOrder_total = rs.getInt("Order_total");
                xOrder_status = rs.getInt("Order_status");
                xRecipient = rs.getString("recipient");
                xRecipent_phone = rs.getString("recipent_phone");
                fullName = rs.getString("FullName");
                xSaleID = rs.getInt("saleId");
                t.add(new ShopOrder(xShop_orderID, xUserID,
                        xAddressID, xOrder_total, xOrder_status,
                        xRecipient, xRecipent_phone, fullName, xSaleID));
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public List<ShopOrder> findAllForSale(int userId) {
        List<ShopOrder> t = new ArrayList<>();
        xSql = "select * from SaleAssignment sa\n"
                + "join shop_order so ON sa.orderId = so.shop_orderID\n"
                + "JOIN Order_Status os ON so.Order_status = os.id\n"
                + "Where sa.userId = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            int xShop_orderID;
            int xUserID;
            int xAddressID;
            int xOrder_total;
            int xOrder_status;
            String xRecipient, xRecipent_phone;
            while (rs.next()) {
                xShop_orderID = rs.getInt("shop_orderID");
                xUserID = rs.getInt("UserID");
                xAddressID = rs.getInt("AddressID");
                xOrder_total = rs.getInt("Order_total");
                xOrder_status = rs.getInt("Order_status");
                xRecipient = rs.getString("recipient");
                xRecipent_phone = rs.getString("recipent_phone");
                t.add(new ShopOrder(xShop_orderID, xUserID,
                        xAddressID, xOrder_total, xOrder_status,
                        xRecipient, xRecipent_phone));
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public List<DailySaleRevenue> getSuccessfullyCompletedOrders(int salerId, String startDate, String endDate) {
          String sql = "SELECT sa.userId AS sale_id, OD.order_date AS order_date, SUM(o.Order_total) AS total_revenue \n" +
                     "FROM saleAssignment sa \n" +
                     "INNER JOIN [shop_order] o ON sa.orderId = o.shop_orderID \n" +
                     "INNER JOIN (SELECT DISTINCT OrderID, order_date FROM orderdetails) OD ON OD.OrderID = sa.orderId \n" +
                     "INNER JOIN Order_Status os ON os.id = o.Order_status \n" +
                     "WHERE os.name = 'Successfully' AND sa.userId = ? \n";

        if (startDate != null && endDate != null) {
            sql += "AND OD.order_date BETWEEN ? AND ? \n";
        } else {
            sql += "AND OD.order_date >= DATEADD(DAY, -7, GETDATE()) \n";
        }

        sql += "GROUP BY sa.userId, OD.order_date";

        List<DailySaleRevenue> dailySales = new ArrayList<>();

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, salerId);

            if (startDate != null && endDate != null) {
                ps.setString(2, startDate);
                ps.setString(3, endDate);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("sale_id");
                String orderDate = rs.getString("order_date");
                int totalRevenue = rs.getInt("total_revenue");

                DailySaleRevenue sale = new DailySaleRevenue(userId, orderDate, totalRevenue);
                dailySales.add(sale);
            }
        } catch (SQLException e) {
            System.out.println("Get total_revenue by saler: " + e);
        } 
        return dailySales;
    }

    public static void main(String[] args) {
        ShopOrderDAO sodao = new ShopOrderDAO();
        sodao.updateSaleAssignment(16, 22);
    }
}
