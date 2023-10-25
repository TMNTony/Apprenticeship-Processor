package org.example.receiptprocessor.dao;

import org.example.receiptprocessor.models.Item;
import org.example.receiptprocessor.models.Receipt;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MemoryReceiptsDao implements ReceiptsDao {
    public Map<UUID, Receipt> receipts = new HashMap<>();

    // Returns all receipts
    @Override
    public Map<UUID, Receipt> getReceipts() {
        return receipts;
    }

    // Saves receipt with randomly generated ID
    @Override
    public void saveReceipt(UUID id, Receipt receipt) {
        receipts.put(id, receipt);
    }

    // Retrieves receipt based on ID key
    @Override
    public Receipt getReceiptById(UUID id) {
        return receipts.get(id);
    }

    // Set up dummy data for testing
    @Override
    public void setReceipts() {
        List<Item> items = new ArrayList<>();
        List<Item> items2 = new ArrayList<>();
        List<Item> items3 = new ArrayList<>();
        List<Item> items4 = new ArrayList<>();

        Item item1 = new Item("Pepsi - 12-oz", "1.25");
        Item item2 = new Item("Dasani", "1.40");
        Item item3 = new Item("Mountain Dew 12PK", "6.49");
        Item item4 = new Item("Emils Cheese Pizza", "12.25");
        Item item5 = new Item("Knorr Creamy Chicken", "1.26");
        Item item6 = new Item("Doritos Nacho Cheese", "3.35");
        Item item7 = new Item("Klarbrunn 12-PK 12 FL OZ", "12.00");
        Item item8 = new Item("Gatorade", "2.25");
        Item item9 = new Item("Gatorade", "2.25");
        Item item10 = new Item("Gatorade", "2.25");
        Item item11 = new Item("Gatorade", "2.25");

        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();
        UUID id4 = UUID.randomUUID();

        items.add(item1);
        items.add(item2);
        items2.add(item1);
        items3.add(item3);
        items3.add(item4);
        items3.add(item5);
        items3.add(item6);
        items3.add(item7);
        items4.add(item8);
        items4.add(item9);
        items4.add(item10);
        items4.add(item11);

        receipts.put(id1, new Receipt("Walgreens", "2022-01-02", "08:13", items, "2.65"));
        receipts.put(id2, new Receipt("Target", "2022-01-02", "13:13", items2, "1.25"));
        receipts.put(id3, new Receipt("Target", "2022-01-01", "13:01:00", items3, "35.35"));
        receipts.put(id4, new Receipt("M&M Corner Market", "2022-03-20", "14:33:00", items4, "9.00"));
    }
}
