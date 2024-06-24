package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CollectionDAO extends myDAO {

    public List<entity.Collection> getAllCollections() {
        List<entity.Collection> list = new ArrayList<>();
        xSql = "select * from [collection]";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xCollectionID;
            String xCollectionName;
            String xGetCollectionImg;
            String xCollection_description;
            Timestamp xDate;
            int xPromotionID;
            while (rs.next()) {
                xCollectionID = rs.getInt("CollectionID");
                xCollectionName = rs.getString("CollectionName");
                xGetCollectionImg = rs.getString("collectionImg");
                xCollection_description = rs.getString("collection_description");
                xDate = rs.getTimestamp("create_date");
                xPromotionID = rs.getInt("PromotionID");
                entity.Collection x = new entity.Collection(xCollectionID, xCollectionName, xGetCollectionImg, xCollection_description, xDate, xPromotionID);
                list.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
