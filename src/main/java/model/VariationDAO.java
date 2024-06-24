package model;

import entity.Variation;

public class VariationDAO extends myDAO{
    public void createNewVariation( int xProductID, int xColor_ID, int xSize_ID, int xQty_in_stock, int xProduct_img_ID){
        try{
            xSql = "INSERT INTO variation (ProductID, color_ID, size_ID, qty_in_stock, product_img_ID) value (?,?,?,?,?)";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xProductID);
            ps.setInt(2, xColor_ID);
            ps.setInt(3, xSize_ID);
            ps.setInt(4, xQty_in_stock);
            ps.setInt(5, xProduct_img_ID);
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println("Create new Story: " + e.getMessage());
        }
    }

    public Variation getVariation(String ProductID, String ColorName, String SizeName){
        Variation v = null;
        xSql = "select distinct v.*\n" +
                "from variation v, product_img pi, product p , category c, color col, size s\n" +
                "where p.ProductID = '"+ProductID+"' and col.color_Name like '"+ColorName+"' and s.size_Name like '"+SizeName+"'\n" +
                "and p.ProductID = v.ProductID \n" +
                "and p.CategoryID = c.CategoryID\n" +
                "and v.product_img_ID = pi.product_img_ID\n" +
                "and v.color_ID = col.color_ID\n" +
                "and v.size_ID = s.size_ID;";
        try{
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int variationID, productID, color_ID, size_ID, qtu_in_stock, product_img_ID;
            while(rs.next()){
                variationID = rs.getInt("VariationID");
                productID = rs.getInt("ProductID");
                color_ID = rs.getInt("color_ID");
                size_ID = rs.getInt("size_ID");
                qtu_in_stock = rs.getInt("qty_in_stock");
                product_img_ID = rs.getInt("product_img_ID");
                v = new Variation(variationID, productID, color_ID, size_ID, qtu_in_stock, product_img_ID);
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return v;
    }

    public Variation getVariationByID(int VariationID){
        Variation v = null;
        xSql = "select distinct v.*\n" +
                "from variation v, product_img pi, product p , category c, color col, size s\n" +
                "where v.VariationID = '"+ VariationID +"'" +
                "and p.ProductID = v.ProductID \n" +
                "and p.CategoryID = c.CategoryID\n" +
                "and v.product_img_ID = pi.product_img_ID\n" +
                "and v.color_ID = col.color_ID\n" +
                "and v.size_ID = s.size_ID;";
        try{
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int variationID, productID, color_ID, size_ID, qtu_in_stock, product_img_ID;
            while(rs.next()){
                variationID = rs.getInt("VariationID");
                productID = rs.getInt("ProductID");
                color_ID = rs.getInt("color_ID");
                size_ID = rs.getInt("size_ID");
                qtu_in_stock = rs.getInt("qty_in_stock");
                product_img_ID = rs.getInt("product_img_ID");
                v = new Variation(variationID, productID, color_ID, size_ID, qtu_in_stock, product_img_ID);
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return v;
    }

    public Variation getVariationByImgID(String ImgID){
        Variation v = null;
        xSql = "select distinct v.*\n" +
                "from variation v, product_img pi, product p , category c, color col, size s\n" +
                "where pi.product_img_ID = '"+ImgID+"'\n" +
                "and p.ProductID = v.ProductID \n" +
                "and p.CategoryID = c.CategoryID\n" +
                "and v.product_img_ID = pi.product_img_ID\n" +
                "and v.color_ID = col.color_ID\n" +
                "and v.size_ID = s.size_ID;";
        try{
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int variationID, productID, color_ID, size_ID, qtu_in_stock, product_img_ID;
            while(rs.next()){
                variationID = rs.getInt("VariationID");
                productID = rs.getInt("ProductID");
                color_ID = rs.getInt("color_ID");
                size_ID = rs.getInt("size_ID");
                qtu_in_stock = rs.getInt("qty_in_stock");
                product_img_ID = rs.getInt("product_img_ID");
                v = new Variation(variationID, productID, color_ID, size_ID, qtu_in_stock, product_img_ID);
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return v;
    }

    public void updateVariation(int ProductID, int ColorID, int SizeID, int Qty_in_stock, int ProductimgID, int VariationID){
        try{
            xSql = "update variation set ProductID = ?, color_ID = ?, size_ID = ?, qty_in_stock = ?, product_img_ID = ?  where VariationID = ?";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, ProductID);
            ps.setInt(2, ColorID);
            ps.setInt(3, SizeID);
            ps.setInt(4, Qty_in_stock);
            ps.setInt(5, ProductimgID);
            ps.setInt(6, VariationID);
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println("Update: " + e.getMessage());
        }
    }
}
