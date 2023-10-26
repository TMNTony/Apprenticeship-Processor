package org.example.receiptprocessor.controller;

import org.example.receiptprocessor.dao.ReceiptsDao;
import org.example.receiptprocessor.models.PointsDto;
import org.example.receiptprocessor.models.Receipt;
import org.example.receiptprocessor.models.ReceiptIdDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<?> processReceipts(@Valid @RequestBody Receipt receipt) {
        try {
            UUID id = UUID.randomUUID();
            receiptsDao.saveReceipt(id, receipt);
            return ResponseEntity.ok(new ReceiptIdDto(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to process receipt");
        }
    }

    // Uses key/value pair with the id/corresponding receipt and returns point value
    @RequestMapping(path = "/{id}/points", method = RequestMethod.GET)
    public ResponseEntity<?> getPoints(@PathVariable String id) {
        try {
            if (!isValidUUID(id)) {
                return ResponseEntity.notFound().build();
            }
            UUID uuid = UUID.fromString(id);
            Receipt requestedReceipt = receiptsDao.getReceiptById(uuid);
            int points = requestedReceipt.getPoints();
            return ResponseEntity.ok(new PointsDto(points));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public ResponseEntity<String> test(@RequestParam String test) {
        return ResponseEntity.ok("Complete");
    }

    private boolean isValidUUID(String input) {
        try {
            UUID.fromString(input);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}

