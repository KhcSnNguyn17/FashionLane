package entity;

public class BestSeller {
    int ProductID;
    String colorName;
    int Quantity;

    public BestSeller() {
    }

    public BestSeller(int productID, String colorName, int quantity) {
        ProductID = productID;
        this.colorName = colorName;
        Quantity = quantity;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
