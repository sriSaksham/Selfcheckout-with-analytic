package com.reader.scanner.controller;

import com.reader.scanner.model.User;
import com.reader.scanner.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/sales/total")
    public ResponseEntity<Double> getTotalSales() {
        double totalSales = analyticsService.calculateTotalSales();
        return ResponseEntity.ok(totalSales);
    }

    @GetMapping("/sales/average")
    public ResponseEntity<Double> getAverageOrderValue() {
        double averageOrderValue = analyticsService.calculateAverageOrderValue();
        return ResponseEntity.ok(averageOrderValue);
    }

    @GetMapping("/customers/frequent")
    public ResponseEntity<List<User>> getFrequentBuyers() {
        List<User> frequentBuyers = analyticsService.getFrequentBuyers();
        return ResponseEntity.ok(frequentBuyers);
    }

    @GetMapping("/products/popular")
    public ResponseEntity<List<Long>> getPopularProducts() {
        List<Long> popularProducts = analyticsService.getPopularProducts();
        return ResponseEntity.ok(popularProducts);
    }
}
