package org.example.receiptprocessor.dao;

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

    @Override
    public boolean isValidUuid(String input) {
        try {
            UUID.fromString(input);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
