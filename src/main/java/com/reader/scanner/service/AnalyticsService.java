package com.reader.scanner.service;

import com.reader.scanner.model.Cart;
import com.reader.scanner.model.CartItem;
import com.reader.scanner.model.User;
import com.reader.scanner.repository.CartRepository;
import com.reader.scanner.repository.CartItemsRepository;
import com.reader.scanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    private UserRepository userRepository;

    // Calculate total sales
    public double calculateTotalSales() {
        List<Cart> carts = cartRepository.findAll();
        return carts.stream().mapToDouble(Cart::getTotal).sum();
    }

    // Calculate average order value
    public double calculateAverageOrderValue() {
        List<Cart> carts = cartRepository.findAll();
        return carts.isEmpty() ? 0 : carts.stream().mapToDouble(Cart::getTotal).average().orElse(0);
    }

    // Get frequent buyers
    public List<User> getFrequentBuyers() {
        return userRepository.findAll().stream()
                .filter(user -> cartRepository.findByUserId(user.getId()).size() > 1)
                .collect(Collectors.toList());
    }

    // Get popular products
    public List<Long> getPopularProducts() {
        return cartItemsRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        cartItem -> Long.parseLong(cartItem.getProductId()), // Convert String to Long
                        Collectors.counting()))
                .entrySet().stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
