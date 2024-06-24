package entity;

public class Cart {
    int cartID;
    int userID;

    public Cart(int cartID, int userID) {
        this.cartID = cartID;
        this.userID = userID;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
