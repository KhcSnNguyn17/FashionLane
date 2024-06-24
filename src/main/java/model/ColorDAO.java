package model;

import entity.Color;

import java.util.ArrayList;
import java.util.List;

public class ColorDAO extends myDAO{

    public List<Color> getAllColors() {
        List<Color> t = new ArrayList<>();
        xSql = "select * from color;";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xColor_ID;
            String xColor_Name;
            Color x;
            while (rs.next()) {
                xColor_ID = rs.getInt("color_ID");
                xColor_Name = rs.getString("color_Name");
                x = new Color(xColor_ID, xColor_Name);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public List<Color> getColorsByProductID(String pid) {
        List<Color> t = new ArrayList<>();
        xSql = "select DISTINCT col.*\n" +
                "from variation v, product p , color col\n" +
                "where p.ProductID = ? \n" +
                "and p.ProductID = v.ProductID \n" +
                "and v.color_ID = col.color_ID";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, pid);
            rs = ps.executeQuery();
            int xColor_ID;
            String xColor_Name;
            Color x;
            while (rs.next()) {
                xColor_ID = rs.getInt("color_ID");
                xColor_Name = rs.getString("color_Name");
                x = new Color(xColor_ID, xColor_Name);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

}
