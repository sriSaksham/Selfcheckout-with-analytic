package com.reader.scanner.service;

import com.reader.scanner.model.Sale;
import com.reader.scanner.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    public void recordSale(Long userId, double totalPrice, double totalWeight) {
        // Check if userId is not null and other necessary validations
        if (userId == null) {
            throw new IllegalArgumentException("User ID must not be null.");
        }

        // Create a new Sale object
        Sale sale = new Sale();
        sale.setUserId(userId); // Ensure userId is of type Long
        sale.setSaleDate(new Date());
        sale.setTotalPrice(totalPrice);
        sale.setTotalWeight(totalWeight);

        // Save the sale
        saleRepository.save(sale);
    }

//    public void recordSale(Sale sale) {
//    }
}
