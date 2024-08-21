package com.reader.scanner.repository;

import com.reader.scanner.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    // Additional query methods can be defined here if needed
    long countByUserId(Long userId);
}
