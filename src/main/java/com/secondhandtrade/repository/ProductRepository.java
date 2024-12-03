package com.secondhandtrade.repository;

import com.secondhandtrade.model.Product;
import com.secondhandtrade.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByProductId(long productId);

    void deleteByProductId(long productId);

    List<Product> findByTitleContaining(String title);
    List<Product> findBySeller(User seller);
    List<Product> findByCategoryContaining(String Category);
}
