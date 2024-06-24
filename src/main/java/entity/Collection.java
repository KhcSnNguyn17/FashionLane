package entity;

import java.sql.Timestamp;

public class Collection {
    int collectionID;
    String collectionName;
    String collectionImg;
    String collection_description;
    Timestamp create_date;
    int PromotionID;

    public Collection() {
    }

    public Collection(int collectionID, String collectionName, String collectionImg, String collection_description, Timestamp create_date, int promotionID) {
        this.collectionID = collectionID;
        this.collectionName = collectionName;
        this.collectionImg = collectionImg;
        this.collection_description = collection_description;
        this.create_date = create_date;
        PromotionID = promotionID;
    }

    public Timestamp getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Timestamp create_date) {
        this.create_date = create_date;
    }

    public int getCollectionID() {
        return collectionID;
    }

    public void setCollectionID(int collectionID) {
        this.collectionID = collectionID;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }


    public String getCollectionImg() {
        return collectionImg;
    }

    public void setCollectionImg(String collectionImg) {
        this.collectionImg = collectionImg;
    }

    public String getCollection_description() {
        return collection_description;
    }

    public void setCollection_description(String collection_description) {
        this.collection_description = collection_description;
    }

    public int getPromotionID() {
        return PromotionID;
    }

    public void setPromotionID(int promotionID) {
        PromotionID = promotionID;
    }
}
