package model;

import entity.ProductForEdit;

import java.util.ArrayList;
import java.util.List;

public class ProductForEditDAO extends myDAO{
    public List<ProductForEdit> getAllProductForEdit(){
        List<ProductForEdit> t = new ArrayList<>();
        xSql = "select * from product";
        try{
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xProductId, xCategoryId;
            String xProductName;
            double xPrice;
            int xCollectionId;
            String xDescription;
            ProductForEdit p;
            while(rs.next()){
                xProductId = rs.getInt("ProductID");
                xCategoryId = rs.getInt("CategoryID");
                xProductName = rs.getString("ProductName");
                xPrice = rs.getDouble("Price");
                xCollectionId = rs.getInt("CollectionID");
                xDescription = rs.getString("description");
                p = new ProductForEdit(xProductId, xCategoryId, xProductName, xPrice, xCollectionId, xDescription);
                t.add(p);
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return (t);
    }

    public ProductForEdit getProduct(String ProductName){
        xSql = "select * from product where ProductName like '"+ProductName+"';";
        try{
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xProductId, xCategoryId;
            String xProductName;
            double xPrice;
            int xCollectionId;
            String xDescription;
            ProductForEdit p;
            while(rs.next()){
                xProductId = rs.getInt("ProductID");
                xCategoryId = rs.getInt("CategoryID");
                xProductName = rs.getString("ProductName");
                xPrice = rs.getDouble("Price");
                xCollectionId = rs.getInt("CollectionID");
                xDescription = rs.getString("description");
                p = new ProductForEdit(xProductId, xCategoryId, xProductName, xPrice, xCollectionId, xDescription);
                return p;
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public ProductForEdit getProductByID(String ProductID){
        int iProductID = Integer.parseInt(ProductID);
        xSql = "select distinct p.*\n" +
                "from variation v, product_img pi, product p , category c, color col, size s\n" +
                "where p.ProductID = "+iProductID+"\n" +
                "and p.ProductID = v.ProductID \n" +
                "and p.CategoryID = c.CategoryID\n" +
                "and v.product_img_ID = pi.product_img_ID\n" +
                "and v.color_ID = col.color_ID\n" +
                "and v.size_ID = s.size_ID;";
        try{
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xProductId, xCategoryId;
            String xProductName;
            double xPrice;
            int xCollectionId;
            String xDescription;
            ProductForEdit p;
            while(rs.next()){
                iProductID = rs.getInt("ProductID");
                xCategoryId = rs.getInt("CategoryID");
                xProductName = rs.getString("ProductName");
                xPrice = rs.getDouble("Price");
                xCollectionId = rs.getInt("CollectionID");
                xDescription = rs.getString("description");
                p = new ProductForEdit(iProductID, xCategoryId, xProductName, xPrice, xCollectionId, xDescription);
                return p;
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public ProductForEdit getProductByImageName(String ImageName){
        xSql = "select * from product where ProductName like '"+ImageName+"'";
        try{
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xProductId, xCategoryId;
            String xProductName;
            double xPrice;
            int xCollectionId;
            String xDescription;
            ProductForEdit p;
            while(rs.next()){
                xProductId = rs.getInt("ProductID");
                xCategoryId = rs.getInt("CategoryID");
                xProductName = rs.getString("ProductName");
                xPrice = rs.getDouble("Price");
                xCollectionId = rs.getInt("CollectionID");
                xDescription = rs.getString("description");
                p = new ProductForEdit(xProductId, xCategoryId, xProductName, xPrice, xCollectionId, xDescription);
                return p;
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void createNewProduct(int xCategoryID, String xProductName, double xPrice, int xCollectionID, String xDescription){
        try{
            xSql = "INSERT INTO product (CategoryID, ProductName, Price, CollectionID, description) value (?,?,?,?,?)";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xCategoryID);
            ps.setString(2, xProductName);
            ps.setDouble(3, xPrice);
            ps.setInt(4, xCollectionID);
            ps.setString(5, xDescription);
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println("Create new Story: " + e.getMessage());
        }
    }

    public void UpdateProductForEdit(int xCategoryID, String ProductName, double xPrice, int xCollectionID, String xDescription, int ProductID) {
        try {
            xSql = "update product set CategoryID = ?, ProductName = ?, Price = ?, CollectionID = ?, description = ?  where ProductID = ?";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xCategoryID);
            ps.setString(2, ProductName);
            ps.setDouble(3, xPrice);
            ps.setInt(4, xCollectionID);
            ps.setString(5, xDescription);
            ps.setInt(6, ProductID);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("UpdateAccount: " + e.getMessage());
        }
    }

    public void delete(String productID) {
        xSql = "delete from product where ProductID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, productID);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int PTotal(){
        xSql = "select count(*) from product;";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()){
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
}
