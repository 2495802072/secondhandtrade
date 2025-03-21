package com.secondhandtrade.repository;

import com.secondhandtrade.model.Product;
import com.secondhandtrade.model.Transaction;
import com.secondhandtrade.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findTransactionByBuyer(User buyer);
    List<Transaction> findTransactionBySeller(User seller);

    @Query("select t from Transaction t where t.product.productId in ?1 ")
    List<Transaction> findByProductIdIn(List<Long> productIds);

    List<Transaction> findByProduct(Product product);

}
