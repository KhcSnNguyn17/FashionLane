package model;

import entity.OrderDetail;
import entity.Product;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDetailDAO extends myDAO {

    public List<Product> getUserOrderDetail(String userID, String OrderID) {
        int xUserID = Integer.parseInt(userID);
        xSql = "Select DISTINCT od.VariationID, v.ProductID, pi.thumbnail, pi.product_img_1, pi.product_img_2, pi.product_img_3, ca.CategoryName, p.CollectionID, p.ProductName, col.color_Name, s.size_Name, p.Price, od.Quantity from variation v, product_img pi, product p , category ca, color col, size s, orderdetails od, shop_order so where p.ProductID = v.ProductID and p.CategoryID = ca.CategoryID and v.product_img_ID = pi.product_img_ID and od.OrderID = so.shop_orderID and v.color_ID = col.color_ID and v.size_ID = s.size_ID and p.ProductID = od.ProductID and v.VariationID = od.VariationID and so.UserID = ?;";
        int xProductID;
        String xThumbnail, xProduct_img_1, xProduct_img_2, xProduct_img_3, xCategoryName;
        int xCollectionID;
        String xProductName, xColor_Name, xSize_Name;
        double xPrice;
        int xQty_in_cart, xVariationID;
        Product x;
        List<Product> ci = new ArrayList<>();
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xUserID);
            rs = ps.executeQuery();
            while (rs.next()) {
                xProductID = rs.getInt("ProductID");
                xThumbnail = rs.getString("thumbnail");
                xProduct_img_1 = rs.getString("product_img_1");
                xProduct_img_2 = rs.getString("product_img_2");
                xProduct_img_3 = rs.getString("product_img_3");
                xCategoryName = rs.getString("CategoryName");
                xCollectionID = rs.getInt("CollectionID");
                xProductName = rs.getString("ProductName");
                xColor_Name = rs.getString("color_Name");
                xSize_Name = rs.getString("size_Name");
                xPrice = rs.getDouble("Price");
                xQty_in_cart = rs.getInt("Quantity");
                xVariationID = rs.getInt("variationID");
                x = new Product(xProductID, xThumbnail, xProduct_img_1, xProduct_img_2, xProduct_img_3, xCategoryName, xCollectionID, xProductName, xColor_Name, xSize_Name, xPrice, xQty_in_cart, xVariationID);
                ci.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("getUserOrder" + e.getMessage());
        }
        return ci;
    }

    public List<Product> getUserOrder(String orderID) {
        int xOrderID = Integer.parseInt(orderID);
        xSql = "  Select DISTINCT  \n"
                + "	od.order_date,\n"
                + "	od.VariationID,\n"
                + "	v.ProductID,\n"
                + "	pi.thumbnail,\n"
                + "	pi.product_img_1,\n"
                + "	pi.product_img_2,\n"
                + "	pi.product_img_3,\n"
                + "	ca.CategoryName,\n"
                + "	p.CollectionID,\n"
                + "	p.ProductName,\n"
                + "	col.color_Name,\n"
                + "	s.size_Name,\n"
                + "	p.Price,\n"
                + "	od.Quantity\n"
                + "from\n"
                + "	variation v,\n"
                + "	product_img pi,\n"
                + "	product p ,\n"
                + "	category ca,\n"
                + "	color col,\n"
                + "	size s,\n"
                + "	orderdetails od,\n"
                + "	shop_order so\n"
                + "where \n"
                + "	p.ProductID = v.ProductID\n"
                + "	and p.CategoryID = ca.CategoryID\n"
                + "	and v.product_img_ID = pi.product_img_ID\n"
                + "	and od.OrderID = so.shop_orderID\n"
                + "	and v.color_ID = col.color_ID\n"
                + "	and v.size_ID = s.size_ID\n"
                + "	and p.ProductID = od.ProductID\n"
                + "	and v.VariationID = od.VariationID\n"
                + "	and OrderID = ?;";
        int xProductID;
        String xThumbnail, xProduct_img_1, xProduct_img_2, xProduct_img_3, xCategoryName;
        int xCollectionID;
        String xProductName, xColor_Name, xSize_Name;
        double xPrice;
        int xQty_in_cart, xVariationID;
        Timestamp xOrderDate;
        Product x;
        List<Product> ci = new ArrayList<>();
        try {
            ps = con.prepareStatement(xSql);
//            ps.setInt(1, xUserID);
            ps.setInt(1, xOrderID);
            rs = ps.executeQuery();
            while (rs.next()) {
                xProductID = rs.getInt("ProductID");
                xThumbnail = rs.getString("thumbnail");
                xProduct_img_1 = rs.getString("product_img_1");
                xProduct_img_2 = rs.getString("product_img_2");
                xProduct_img_3 = rs.getString("product_img_3");
                xCategoryName = rs.getString("CategoryName");
                xCollectionID = rs.getInt("CollectionID");
                xProductName = rs.getString("ProductName");
                xColor_Name = rs.getString("color_Name");
                xSize_Name = rs.getString("size_Name");
                xPrice = rs.getDouble("Price");
                xQty_in_cart = rs.getInt("Quantity");
                xVariationID = rs.getInt("variationID");
                xOrderDate = rs.getTimestamp("order_date");
                x = new Product(xProductID, xThumbnail, xProduct_img_1, xProduct_img_2, xProduct_img_3, xCategoryName, xCollectionID, xProductName, xColor_Name, xSize_Name, xPrice, xQty_in_cart, xVariationID, xOrderDate);
                ci.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("getUserOrder" + e.getMessage());
        }
        return ci;
    }

    public List<OrderDetail> getOrderItems(String orderID) {
        int xOrderID = Integer.parseInt(orderID);
        List<OrderDetail> od = new ArrayList<>();
        xSql = "select * from orderdetails od, product p where od.OrderID = ? and od.ProductID = p.ProductID";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xOrderID);
            rs = ps.executeQuery();
            int xOrder_detailID;
            int xProductID;
            int xQuantity;
            int xPrice;
            Date xOrderDate;
            while (rs.next()) {
                xOrder_detailID = rs.getInt("OrderDetailID");
                xProductID = rs.getInt("ProductID");
                xOrderID = rs.getInt("OrderID");
                xQuantity = rs.getInt("Quantity");
                xPrice = rs.getInt("Price");
                xOrderDate = rs.getDate("OrderDate");
                od.add(new OrderDetail(xOrder_detailID, xProductID, xOrderID, xQuantity, xPrice, xOrderDate));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return od;
    }

    public void insert(int orderID, String productID, String variationID, int quan, double price) {
        int xProductID = Integer.parseInt(productID);
        int xVariationID = Integer.parseInt(variationID);
        xSql = "insert into orderdetails(OrderID, ProductID, VariationID,Quantity,Price,order_date) values (?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, orderID);
            ps.setInt(2, xProductID);
            ps.setInt(3, xVariationID);
            ps.setInt(4, quan);
            ps.setDouble(5, price);
            Date currDate = new Date();
            Timestamp ts = new Timestamp(currDate.getTime());
            ps.setTimestamp(6, ts);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("insertOrderDetail: " + e.getMessage());
        }
    }

    public int SaleTotal() {
        xSql = "select sum(Order_total) FROM shop_order";
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

    public String[] getDashboarDate() {
        String[] date = new String[10];
        int count = 0;
        xSql = "select distinct CAST(o.order_date as CHAR) from shop_order s, orderdetails o where s.shop_orderID = o.OrderID;";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                date[count] = rs.getString(1);
                count++;
            }
            rs.close();
            ps.close();
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] getDashboarOrTotal() {
        String[] sum = new String[10];
        int count = 0;
        xSql = "select sum(s.Order_total) from shop_order s, orderdetails o where s.shop_orderID = o.OrderID group by o.order_date;";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                sum[count] = rs.getString(1);
                count++;
            }
            rs.close();
            ps.close();
            return sum;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
