package org.example.receiptprocessor;

import org.example.receiptprocessor.models.Item;
import org.example.receiptprocessor.models.Receipt;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ReceiptPointsTests {

    Receipt receipt;

    @Before
    public void createReceipt() {
        this.receipt = new Receipt();
    }

    @Test
    public void short_name() {
//        Arrange
        receipt.setRetailer("HBO");
//        Act
        int points = receipt.getPointsByName();
//        Assert
        Assert.assertEquals(3, points);

    }

    @Test
    public void long_name_with_space() {
//        Arrange
        receipt.setRetailer("Ashley Furniture");
//        Act
        int points = receipt.getPointsByName();
//        Assert
        Assert.assertEquals(15, points);

    }

    @Test
    public void name_with_spaces_numbers() {
//        Arrange
        receipt.setRetailer("Rooms 2 Go");
//        Act
        int points = receipt.getPointsByName();
//        Assert
        Assert.assertEquals(8, points);

    }

    @Test
    public void name_with_special_character() {
//        Arrange
        receipt.setRetailer("H&M");
//        Act
        int points = receipt.getPointsByName();
//        Assert
        Assert.assertEquals(2, points);

    }

    @Test
    public void round_dollar() {
//        Arrange
        receipt.setTotal("15.00");
//        Act
        int points = receipt.getPointsByTotal();
//        Assert
        Assert.assertEquals(75, points);

    }

    @Test
    public void multiple_of_quarter() {
//        Arrange
        receipt.setTotal("0.75");
//        Act
        int points = receipt.getPointsByTotal();
//        Assert
        Assert.assertEquals(25, points);
    }

    @Test
    public void one_item() {
//        Arrange
        List<Item> items = new ArrayList<>();
        Item item = new Item("Gatorade", "1.00");
        items.add(item);
        receipt.setItems(items);
//        Act
        int points = receipt.getPointsByItems();
//        Assert
        Assert.assertEquals(0, points);
    }

    @Test
    public void ten_items() {
//        Arrange
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Item item = new Item("Gatorade", "1.00");
            items.add(item);
        }
        receipt.setItems(items);
//        Act
        int points = receipt.getPointsByItems();
//        Assert
        Assert.assertEquals(25, points);
    }

    @Test
    public void seven_items() {
//        Arrange
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Item item = new Item("Gatorade", "1.00");
            items.add(item);
        }
        receipt.setItems(items);
//        Act
        int points = receipt.getPointsByItems();
//        Assert
        Assert.assertEquals(15, points);
    }

    @Test
    public void divisible_description() {
//        Arrange
        List<Item> items = new ArrayList<>();
        Item item = new Item("Klarbrunn 12-PK 12 FL OZ", "12.00");
        items.add(item);

        receipt.setItems(items);
//        Act
        int points = receipt.getPointsByDescription();
//        Assert
        Assert.assertEquals(3, points);
    }
    @Test
    public void indivisible_description() {
//        Arrange
        List<Item> items = new ArrayList<>();
        Item item = new Item("Juice", "2.00");
        items.add(item);

        receipt.setItems(items);
//        Act
        int points = receipt.getPointsByDescription();
//        Assert
        Assert.assertEquals(0, points);
    }
    @Test
    public void cheap_divisible_description() {
//        Arrange
        List<Item> items = new ArrayList<>();
        Item item = new Item("Gum", "1.00");
        items.add(item);

        receipt.setItems(items);
//        Act
        int points = receipt.getPointsByDescription();
//        Assert
        Assert.assertEquals(1, points);
    }

    @Test
    public void date_divisible_by_2() {
//        Arrange
        receipt.setPurchaseDate("2023-01-06");
//        Act
        int points = receipt.getPointsByDate();
//        Assert
        Assert.assertEquals(0, points);

    }

    @Test
    public void date_not_divisible_by_2() {
//        Arrange
        receipt.setPurchaseDate("2023-01-05");
//        Act
        int points = receipt.getPointsByDate();
//        Assert
        Assert.assertEquals(6, points);

    }

    @Test
    public void before_2() {
//        Arrange
        receipt.setPurchaseTime("11:00");
//        Act
        int points = receipt.getPointsByTime();
//        Assert
        Assert.assertEquals(0, points);

    }

    @Test
    public void after_4() {
//        Arrange
        receipt.setPurchaseTime("16:00");
//        Act
        int points = receipt.getPointsByTime();
//        Assert
        Assert.assertEquals(0, points);

    }

    @Test
    public void between_2_4() {
//        Arrange
        receipt.setPurchaseTime("15:00");
//        Act
        int points = receipt.getPointsByTime();
//        Assert
        Assert.assertEquals(10, points);

    }
    @Test
    public void at_2() {
//        Arrange
        receipt.setPurchaseTime("14:00");
//        Act
        int points = receipt.getPointsByTime();
//        Assert
        Assert.assertEquals(0, points);

    }
}
