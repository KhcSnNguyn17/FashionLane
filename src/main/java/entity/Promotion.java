package entity;

import java.sql.Date;

public class Promotion {
    int promotionID;
    String promotionName, promotionDescription;
    double discountRate;
    Date startDate, endDate;
    String background_color;

    public Promotion() {
    }

    public Promotion(int promotionID, String promotionName, String promotionDescription, double discountRate, Date startDate, Date endDate, String background_color) {
        this.promotionID = promotionID;
        this.promotionName = promotionName;
        this.promotionDescription = promotionDescription;
        this.discountRate = discountRate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.background_color = background_color;
    }

    public int getPromotionID() {
        return promotionID;
    }

    public void setPromotionID(int promotionID) {
        this.promotionID = promotionID;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public String getPromotionDescription() {
        return promotionDescription;
    }

    public void setPromotionDescription(String promotionDescription) {
        this.promotionDescription = promotionDescription;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getBackground_color() {
        return background_color;
    }

    public void setBackground_color(String background_color) {
        this.background_color = background_color;
    }
}
