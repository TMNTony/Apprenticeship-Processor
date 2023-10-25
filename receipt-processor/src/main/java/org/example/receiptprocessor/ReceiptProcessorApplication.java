package org.example.receiptprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.example.receiptprocessor")
public class ReceiptProcessorApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReceiptProcessorApplication.class, args);
    }
}
