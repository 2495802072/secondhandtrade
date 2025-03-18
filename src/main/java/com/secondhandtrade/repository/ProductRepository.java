package com.secondhandtrade.repository;

import com.secondhandtrade.model.Product;
import com.secondhandtrade.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByProductId(long productId);

    void deleteByProductId(long productId);

    boolean existsByProductId(long productId);

    List<Product> findByTitleContaining(String title);
    List<Product> findBySeller(User seller);
    List<Product> findByCategoryContaining(String Category);

    @Query("select p from Product p where p.seller.userId != ?1 and p.status = '在售'")
    List<Product> findForHome(long userId);
}
