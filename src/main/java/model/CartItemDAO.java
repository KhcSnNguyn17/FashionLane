package model;

import entity.*;
import java.util.ArrayList;
import java.util.List;

public class CartItemDAO extends myDAO {

    public void insert(String cartID, String proID, String varID) {
        int xCartID = Integer.parseInt(cartID);
        int xProID = Integer.parseInt(proID);
        int xVarID = Integer.parseInt(varID);
        xSql = "insert into cart_item (CartID, ProductID, Quantity, VariationID) values (?, ?, 1, ?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xCartID);
            ps.setInt(2, xProID);
            ps.setInt(3, xVarID);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("insert: " + e.getMessage());
        }
    }

    public void setQuantity(String proID, String varID) {
        int xProID = Integer.parseInt(proID);
        int xVarID = Integer.parseInt(varID);
        xSql = "update cart_item \n"
                + "set Quantity = '1' WHERE productID = " + proID + " AND variationID = " + varID + ";";
        try {
            ps = con.prepareStatement(xSql);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("setQuantity: " + e.getMessage());
        }
    }

    public void setCartID(String proID, String varID, String userID) {
        int xProID = Integer.parseInt(proID);
        int xVarID = Integer.parseInt(varID);
        int xUserID = Integer.parseInt(userID);
        xSql = "UPDATE cart_item\n"
                + "SET CartID = (SELECT MAX(CartID) FROM cart c WHERE c.UserID = ?)\n"
                + "WHERE ProductID = ? AND variationID = ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xUserID);
            ps.setInt(2, xProID);
            ps.setInt(3, xVarID);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("setCartID: " + e.getMessage());
        }
    }

    public List<Product> getUserItem(int userID) {
        xSql = "Select DISTINCT ci.VariationID, v.ProductID, pi.thumbnail, pi.product_img_1, pi.product_img_2, pi.product_img_3, ca.CategoryName, p.CollectionID, p.ProductName, col.color_Name, s.size_Name, p.Price, ci.Quantity, (p.Price-(p.Price*pro.DiscountRate/100)) 'DiscountPrice' from variation v, product_img pi, product p , category ca, color col, size s, cart c, cart_item ci, promotion pro, collection collec where p.ProductID = v.ProductID and p.CategoryID = ca.CategoryID and v.product_img_ID = pi.product_img_ID and v.color_ID = col.color_ID and v.size_ID = s.size_ID and p.ProductID = ci.ProductID and c.CartID = ci.CartID and v.VariationID = ci.variationID and  collec.PromotionID = pro.PromotionID and p.CollectionID = collec.CollectionID and c.UserID = ?";
        int xProductID;
        String xThumbnail, xProduct_img_1, xProduct_img_2, xProduct_img_3, xCategoryName;
        int xCollectionID;
        String xProductName, xColor_Name, xSize_Name;
        double xPrice, xDiscountPrice;
        int xQty_in_cart, xVariationID;
        Product x;
        List<Product> ci = new ArrayList<>();
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, userID);
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
                xDiscountPrice = rs.getDouble("DiscountPrice");
                x = new Product(xProductID, xThumbnail, xProduct_img_1, xProduct_img_2, xProduct_img_3, xCategoryName, xCollectionID, xProductName, xColor_Name, xSize_Name, xPrice, xQty_in_cart, xVariationID, xDiscountPrice);
                ci.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ci;
    }

    public CartItem getCartItem(String proID, String variID) {
        int xProID = Integer.parseInt(proID);
        int xVariID = Integer.parseInt(variID);
        xSql = "select ci.*, c.UserID from cart_item ci, product p, cart c, variation v where ci.ProductID = p.ProductID and ci.variationID = v.VariationID and c.CartID = ci.CartID and ci.ProductID = ? and ci.variationID = ?;";
        int xCart_itemID;
        int xCartID;
        int xProductID;
        int xQuantity;
        int xVariationID;
        CartItem c = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xProID);
            ps.setInt(2, xVariID);
            rs = ps.executeQuery();
            while (rs.next()) {
                xCart_itemID = rs.getInt("cart_itemID");
                xCartID = rs.getInt("CartID");
                xProductID = rs.getInt("ProductID");
                xQuantity = rs.getInt("Quantity");
                xVariationID = rs.getInt("variationID");
                c = new CartItem(xCart_itemID, xCartID, xProductID, xQuantity, xVariationID);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    public CartItem getCartQuantity(String proID, String variID, String cartID) {
        int xProID = Integer.parseInt(proID);
        int xVariID = Integer.parseInt(variID);
        int xCart_itemID = Integer.parseInt(cartID);
        xSql = "select ci.*, c.UserID from cart_item ci, product p, cart c, variation v where ci.ProductID = p.ProductID and ci.variationID = v.VariationID and c.CartID = ci.CartID and ci.ProductID = ? and ci.variationID = ? and ci.CartID = ?;";
        int xCartID;
        int xProductID;
        int xQuantity;
        int xVariationID;
        CartItem c = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xProID);
            ps.setInt(2, xVariID);
            ps.setInt(3, xCart_itemID);
            rs = ps.executeQuery();
            while (rs.next()) {
                xCart_itemID = rs.getInt("cart_itemID");
                xCartID = rs.getInt("CartID");
                xProductID = rs.getInt("ProductID");
                xQuantity = rs.getInt("Quantity");
                xVariationID = rs.getInt("variationID");
                c = new CartItem(xCart_itemID, xCartID, xProductID, xQuantity, xVariationID);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    public void plusQuantity(String proID, String variID, String cardID) {
        int xProID = Integer.parseInt(proID);
        int xVariID = Integer.parseInt(variID);
        int xCartID = Integer.parseInt(cardID);
        xSql = "update cart_item set Quantity = Quantity + 1 where ProductID = ? and variationID = ? and CartID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xProID);
            ps.setInt(2, xVariID);
            ps.setInt(3, xCartID);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("plusQuantity: " + e.getMessage());
        }
    }

    public void minusQuantity(String proID, String variID, String cardID) {
        int xProID = Integer.parseInt(proID);
        int xVariID = Integer.parseInt(variID);
        int xCartID = Integer.parseInt(cardID);
        xSql = "update cart_item set Quantity = Quantity - 1 where ProductID = ? and variationID = ? and CartID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xProID);
            ps.setInt(2, xVariID);
            ps.setInt(3, xCartID);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("minusQuantity: " + e.getMessage());
        }
    }

    public void buy(int userID) {
        xSql = "delete from cart where UserID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, userID);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCartItem(String ProductID, String VariationID, String CartItemID) {
        int xCartItemID = Integer.parseInt(CartItemID);
        int xProductID = Integer.parseInt(ProductID);
        int xVariationID = Integer.parseInt(VariationID);
        xSql = "delete from cart_item where ProductID=? and variationID=? and CartID=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xProductID);
            ps.setInt(2, xVariationID);
            ps.setInt(3, xCartItemID);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCartItemByProdID(String ProductID, String VariationID) {
        int xProductID = Integer.parseInt(ProductID);
        int xVariationID = Integer.parseInt(VariationID);
        xSql = "delete from cart_item where ProductID=? and variationID=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xProductID);
            ps.setInt(2, xVariationID);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("deleteCartItemByProdID: " + e.getMessage());
        }
    }

    public boolean checkCartItemExist(String proID, String varID, String userID) {
        int xProID = Integer.parseInt(proID);
        int xVarID = Integer.parseInt(varID);
        int xUserID = Integer.parseInt(userID);
        xSql = "SELECT COUNT(*) AS count FROM cart_item ci INNER JOIN cart c ON ci.CartID = c.CartID WHERE ci.ProductID = ? AND ci.variationID = ? AND c.UserID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xProID);
            ps.setInt(2, xVarID);
            ps.setInt(3, xUserID);
            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<CartItem> getListCartItemByUserId(int userID) {
        String sql = "Select DISTINCT ci.* from variation v, product_img pi, product p , category ca, color col, size s, cart c, cart_item as ci, promotion pro, collection collec where p.ProductID = v.ProductID and p.CategoryID = ca.CategoryID and v.product_img_ID = pi.product_img_ID and v.color_ID = col.color_ID and v.size_ID = s.size_ID and p.ProductID = ci.ProductID and c.CartID = ci.CartID and v.VariationID = ci.variationID and  collec.PromotionID = pro.PromotionID and p.CollectionID = collec.CollectionID and c.UserID = ?";
        List<CartItem> list = new ArrayList<>();
        int xCart_itemID;
        int xCartID;
        int xProductID;
        int xQuantity;
        int xVariationID;
        CartItem c = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                xCart_itemID = rs.getInt("cart_itemID");
                xCartID = rs.getInt("CartID");
                xProductID = rs.getInt("ProductID");
                xQuantity = rs.getInt("Quantity");
                xVariationID = rs.getInt("variationID");
                c = new CartItem(xCart_itemID, xCartID, xProductID, xQuantity, xVariationID);
                list.add(c);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
