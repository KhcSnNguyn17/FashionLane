package model;

import entity.Address;

public class AddressDAO extends myDAO {
    public void insertAddress(String addressLine, String city, String postalCode, String countryID) {
        int xCountryID = Integer.parseInt(countryID);
        xSql = "insert into address (addressline, city, postalcode,CountryID) values (?, ?, ?,?);";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, addressLine);
            ps.setString(2, city);
            ps.setString(3, postalCode);
            ps.setInt(4, xCountryID);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("insertAddress: " + e.getMessage());
        }
    }

    public Address getAddress(String xAddressLine, String xCity, String xPostalCode, String xCountryID){
        xSql = "SELECT * FROM address where addressline = ? and city like ? and postalcode = ? and CountryID = ?";

        Address a = null;
        int xAddressID;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, xAddressLine);
            ps.setString(2, xCity);
            ps.setString(3, xPostalCode);
            ps.setString(4, xCountryID);
            rs = ps.executeQuery();
            while (rs.next()){
                xAddressID = rs.getInt("AddressID");
                a = new Address(xAddressID, xAddressLine, xCity, Integer.parseInt(xPostalCode),Integer.parseInt(xCountryID));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("getLatestOrder: " + e.getMessage());
        }
        return a;
    }

    public void setAddressIDtoShopOrder() {
        xSql = "UPDATE shop_order SET AddressID = (SELECT MAX(AddressID) FROM address) WHERE shop_orderID = (  SELECT max_shop_orderID FROM (    SELECT MAX(shop_orderID) AS max_shop_orderID FROM shop_order) AS tmp);";
        try {
            ps = con.prepareStatement(xSql);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("setAddressIDtoShopOrder: " + e.getMessage());
        }
    }
}