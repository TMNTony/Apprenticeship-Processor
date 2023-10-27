package org.example.receiptprocessor.models;

import java.util.UUID;

public class ReceiptIdDto {
    private UUID id;

    //    Constructors
    public ReceiptIdDto(UUID id) {
        this.id = id;
    }
    public ReceiptIdDto(){};

    //    Getter
    public UUID getId() {
        return id;
    }

    //    Setter
    public void setId(UUID id) {
        this.id = id;
    }
}
