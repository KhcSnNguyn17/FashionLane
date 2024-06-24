/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author HP
 */
public class DailySaleRevenue {

    private int saleId;
    private String orderDate;
    private int totalRevenue;

    public DailySaleRevenue() {
    }

    public DailySaleRevenue(int saleId, String orderDate, int totalRevenue) {
        this.saleId = saleId;
        this.orderDate = orderDate;
        this.totalRevenue = totalRevenue;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(int totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

}
