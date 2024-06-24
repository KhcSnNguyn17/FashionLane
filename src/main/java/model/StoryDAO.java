package model;

import entity.Story;

import java.util.ArrayList;
import java.util.List;

public class StoryDAO extends myDAO{
    public List<Story> getAllStory(String xName) {
        List<Story> t = new ArrayList<>();
        if(xName.equals("all")){
            xSql = "select * from story";
        }else{
            xSql = "select * from story where title like '%"+ xName + "%'";
        }
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery(); 
            int xStory_ID;
            String xThumbnail, xTitle, xDesciption;
            Story s;
            while (rs.next()) {
                xStory_ID = rs.getInt("story_id");
                xThumbnail = rs.getString("thumbnail");
                xTitle = rs.getString("title");
                xDesciption = rs.getString("description");
                s = new Story(xStory_ID, xThumbnail, xTitle, xDesciption);
                t.add(s);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public Story getStoryByID(String id){
        Story s = null;
        int xId = Integer.parseInt(id);
        xSql = "select * from story where story_id = ?";
        try{
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xId);
            rs = ps.executeQuery();
            String xThumbnail, xTitle, xDescription;
            while (rs.next()){
                xId = rs.getInt("story_id");
                xThumbnail = rs.getString("thumbnail");
                xTitle = rs.getString("title");
                xDescription = rs.getString("description");
                s = new Story(xId, xThumbnail, xTitle, xDescription);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

}
