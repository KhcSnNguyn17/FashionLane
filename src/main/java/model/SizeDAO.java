package model;

import entity.Size;

import java.util.ArrayList;
import java.util.List;

public class SizeDAO extends  myDAO{
    public List<Size> getAllSizes() {
        List<Size> t = new ArrayList<>();
        xSql = "select * from size;";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xSize_ID;
            String xSize_Name;
            Size x;
            while (rs.next()) {
                xSize_ID = rs.getInt("size_ID");
                xSize_Name = rs.getString("size_Name");
                x = new Size(xSize_ID, xSize_Name);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public List<Size> getSizesOfThatProduct(String pid, String colname) {
        List<Size> t = new ArrayList<>();
        xSql = "select  s.*\n" +
                "from variation v, product p , size s, color col\n" +
                "where p.ProductID = ? \n" +
                "and col.color_Name like '%" + colname + "%'\n" +
                "and p.ProductID = v.ProductID \n" +
                "and v.size_ID = s.size_ID\n" +
                "and v.color_ID = col.color_ID;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, pid);
            rs = ps.executeQuery();
            int xSize_ID;
            String xSize_Name;
            Size x;
            while (rs.next()) {
                xSize_ID = rs.getInt("size_ID");
                xSize_Name = rs.getString("size_Name");
                x = new Size(xSize_ID, xSize_Name);
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
