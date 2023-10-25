package org.example.receiptprocessor.controller;
import org.example.receiptprocessor.dao.ReceiptsDao;
import org.example.receiptprocessor.models.PointsDto;
import org.example.receiptprocessor.models.Receipt;
import org.example.receiptprocessor.models.ReceiptIdDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {
    //    Receipts are stored in an artificial database MemoryReceiptsDao
    private final ReceiptsDao receiptsDao;

    public ReceiptController(ReceiptsDao receiptsDao) {
        this.receiptsDao = receiptsDao;
    }

    //    Receives the JSON and converts different strings to other file types
//    Returns ID
    @RequestMapping(path = "/process", method = RequestMethod.POST)
    public ReceiptIdDto processReceipts(@RequestBody Receipt receipt) {
        UUID id = UUID.randomUUID();
        receiptsDao.saveReceipt(id, receipt);
        return new ReceiptIdDto(id);
    }

    // Uses key/value pair with the id/corresponding receipt and returns point value
    @RequestMapping(path = "/{id}/points", method = RequestMethod.GET)
    public PointsDto getPoints(@PathVariable UUID id) {
        Receipt requestedReceipt = receiptsDao.getReceiptById(id);
        int points = requestedReceipt.getPoints();
        return new PointsDto(points);
    }

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public ResponseEntity<String> test(@RequestParam String test) {
        return ResponseEntity.ok("Complete");
    }
}

