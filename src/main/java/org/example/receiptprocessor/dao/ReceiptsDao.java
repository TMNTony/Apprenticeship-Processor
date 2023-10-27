package org.example.receiptprocessor.dao;
import org.example.receiptprocessor.models.Receipt;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
@Component
public interface ReceiptsDao {
    Map<UUID, Receipt> getReceipts();

    Receipt getReceiptById(UUID id);

    void saveReceipt(UUID id, Receipt receipt);

    boolean isValidUuid(String input);
}
