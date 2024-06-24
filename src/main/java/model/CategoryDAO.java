package model;

import entity.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends myDAO {
    public List<Category> getAllCategory() {
        List<Category> t = new ArrayList<>();
        xSql = "select * from category;";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xId;
            String xName, xImg;
            Category c;
            while (rs.next()) {
                xId = rs.getInt("CategoryID");
                xName = rs.getString("CategoryName");
                xImg = rs.getString("Category_img");
                c = new Category(xId, xName, xImg);
                t.add(c);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public Category getCategory(String xId) {
        int i = Integer.parseInt(xId);
        xSql = "select * from category where CategoryID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, i);
            rs = ps.executeQuery();
            String xcateName;
            String xcateImg;
            Category x;
            while (rs.next()) {
                i = rs.getInt("CategoryID");
                xcateName = rs.getString("CategoryName");
                xcateImg = rs.getString("Category_img");
                x = new Category(i, xcateName, xcateImg);
                return x;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
