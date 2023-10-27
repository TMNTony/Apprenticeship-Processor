package org.example.receiptprocessor.models;

public class Item {
    private String shortDescription;
    private double price;

    // Constructor
    public Item(String shortDescription, String price) {
        this.shortDescription = shortDescription;
        this.price = Double.parseDouble(price);
    }

    public Item() {
    }

    ;

    // Getters
    public String getShortDescription() {
        return shortDescription;
    }

    public double getPrice() {
        return price;
    }

    // Setters
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
