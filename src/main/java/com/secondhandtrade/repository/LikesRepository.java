package com.secondhandtrade.repository;

import com.secondhandtrade.model.Likes;
import com.secondhandtrade.model.Product;
import com.secondhandtrade.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    // 根据收藏人返回
    List<Likes> findByBuyer(User buyer);

    // 根据接商品查找
    List<Likes> findByProduct(Product product);

    void deleteByLikesId(Long messageId);

    Likes findByLikesId(Long id);

    void deleteByBuyerAndProduct(User buyer, Product product);
}
