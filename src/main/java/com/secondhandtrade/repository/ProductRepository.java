package com.secondhandtrade.repository;

import com.secondhandtrade.model.Product;
import com.secondhandtrade.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductId(long productId);

    void deleteByProductId(long productId);

    boolean existsByProductId(long productId);

    List<Product> findByTitleContaining(String title);

    List<Product> findBySeller(User seller);

    // 通过 Category 的 name 字段进行模糊查询
    @Query("SELECT p FROM Product p JOIN p.category c WHERE c.name LIKE %:category%")
    List<Product> findByCategoryNameContaining(@Param("category") String category);

    @Query("select p from Product p where p.seller.userId != ?1 and p.status = '在售'")
    List<Product> findForHome(long userId);

}
