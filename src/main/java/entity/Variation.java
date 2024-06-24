package entity;

public class Variation {
    int variationID, productID, color_ID, size_ID, qtu_in_stock, product_img_ID;

    public Variation() {
    }

    public Variation(int variationID, int productID, int color_ID, int size_ID, int qtu_in_stock, int product_img_ID) {
        this.variationID = variationID;
        this.productID = productID;
        this.color_ID = color_ID;
        this.size_ID = size_ID;
        this.qtu_in_stock = qtu_in_stock;
        this.product_img_ID = product_img_ID;
    }

    public int getVariationID() {
        return variationID;
    }

    public void setVariationID(int variationID) {
        this.variationID = variationID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getColor_ID() {
        return color_ID;
    }

    public void setColor_ID(int color_ID) {
        this.color_ID = color_ID;
    }

    public int getSize_ID() {
        return size_ID;
    }

    public void setSize_ID(int size_ID) {
        this.size_ID = size_ID;
    }

    public int getQtu_in_stock() {
        return qtu_in_stock;
    }

    public void setQtu_in_stock(int qtu_in_stock) {
        this.qtu_in_stock = qtu_in_stock;
    }

    public int getProduct_img_ID() {
        return product_img_ID;
    }

    public void setProduct_img_ID(int product_img_ID) {
        this.product_img_ID = product_img_ID;
    }
}
