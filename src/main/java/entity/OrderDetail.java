package entity;

import java.util.Date;

public class OrderDetail {
    int orderDetailID;
    int productID;
    int orderID;
    int quantity;
    int price;
    Date order_date;
    int variationID;


    public OrderDetail(int orderDetailID, int productID, int orderID, int quantity, int price, Date order_date, int variationID) {
        this.orderDetailID = orderDetailID;
        this.productID = productID;
        this.orderID = orderID;
        this.quantity = quantity;
        this.price = price;
        this.order_date = order_date;
        this.variationID = variationID;
    }

    public OrderDetail(int orderDetailID, int productID, int orderID, int quantity, int price, Date order_date) {
        this.orderDetailID = orderDetailID;
        this.productID = productID;
        this.orderID = orderID;
        this.quantity = quantity;
        this.price = price;
        this.order_date = order_date;
    }

    public int getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public int getVariationID() {
        return variationID;
    }

    public void setVariationID(int variationID) {
        this.variationID = variationID;
    }
}
