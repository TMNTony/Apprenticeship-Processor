package org.example.receiptprocessor.models;

import java.time.LocalTime;
import java.util.List;

public class Receipt {
    private String retailer;
    private String purchaseDate;
    private String purchaseTime;
    private List<Item> items;
    private double total;

    //     Constructors
    public Receipt(String retailer, String purchaseDate, String purchaseTime, List<Item> items, String total) {
        this.retailer = retailer;
        this.purchaseDate = purchaseDate;
        this.purchaseTime = purchaseTime;
        this.items = items;
        this.total = Double.parseDouble(total);
    }

    public Receipt() {
    }

    ;

    // Getters
    public String getRetailer() {
        return retailer;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public List<Item> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    // Setters
    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public void setPurchaseDate(String dateString) {
        this.purchaseDate = dateString;
    }

    public void setPurchaseTime(String timeString) {

        this.purchaseTime = timeString;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setTotal(String totalString) {
        this.total = Double.parseDouble(totalString);
    }

    // Calculates points based on parameters specified in ReadMe
    public int getPoints() {
        int points = 0;

        points += getPointsByDate();
        points += getPointsByTime();
        points += getPointsByName();
        points += getPointsByTotal();
        points += getPointsByItems();
        points += getPointsByDescription();

        return points;
    }

    public int getPointsByDate() {
        int points = 0;
        String[] dateParts = purchaseDate.split("-");
        int dayOfMonth = Integer.parseInt(dateParts[2]);

        if (dayOfMonth % 2 != 0) {
            points += 6;
        }

        return points;
    }

    public int getPointsByTime() {
        int points = 0;
        String[] timeParts = purchaseTime.split(":");
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);
        LocalTime checkTime = LocalTime.of(hour, minute);
        LocalTime start = LocalTime.of(14, 0);
        LocalTime end = LocalTime.of(16, 0);


        if (checkTime.isAfter(start) && checkTime.isBefore(end)) {
            points += 10;
        }

        return points;
    }

    public int getPointsByName() {
        int points = 0;
        String retailerShort = retailer.replaceAll("[^a-zA-Z0-9]", "");
        points += retailerShort.length();

        return points;
    }

    public int getPointsByTotal() {
        int points = 0;

        if ((int) total == total) {
            points += 50;
        }
        if (total % 0.25 == 0) {
            points += 25;
        }

        return points;
    }

    public int getPointsByItems() {
        int points = 0;

        points += (items.size() / 2) * 5;

        return points;
    }

    public int getPointsByDescription() {
        int points = 0;

        for (Item item : items) {
            String trimmed = item.getShortDescription().trim();
            if (trimmed.length() % 3 == 0) {
                double addPoints = (item.getPrice() * 0.2);
                int rounded = (int) Math.ceil(addPoints);
                points += rounded;
            }
        }

        return points;
    }
}
