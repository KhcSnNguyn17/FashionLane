package entity;

public class CartItem {
    int cart_itemID;
    int cartID;
    int productID;
    int quantity;
    int variationID;

    public CartItem(int cart_itemID, int cartID, int productID, int quantity, int variationID) {
        this.cart_itemID = cart_itemID;
        this.cartID = cartID;
        this.productID = productID;
        this.quantity = quantity;
        this.variationID = variationID;
    }

    public int getVariationID() {
        return variationID;
    }

    public void setVariationID(int variationID) {
        this.variationID = variationID;
    }

    public int getCart_itemID() {
        return cart_itemID;
    }

    public void setCart_itemID(int cart_itemID) {
        this.cart_itemID = cart_itemID;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
