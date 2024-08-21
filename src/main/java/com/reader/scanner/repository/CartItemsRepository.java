package com.reader.scanner.repository;

import com.reader.scanner.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByProductId(String productId);
}
