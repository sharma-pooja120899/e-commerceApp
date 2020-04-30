package com.example.pooja.homymarket.Models;

public class CartFetch {


    String Product_ID;
    String Quantity;
    String Name;
    String Price;

    public CartFetch() {
    }

    public CartFetch(String product_ID, String quantity, String name, String price) {
        Product_ID = product_ID;
        Quantity = quantity;
        Name = name;
        Price = price;
    }

    public String getQuantity() { return Quantity;    }

    public String getPrice() { return Price;   }

    public String getProduct_ID() {
        return Product_ID;
    }

    public String getName() {
        return Name;
    }

}
