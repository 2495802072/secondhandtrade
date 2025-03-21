package com.secondhandtrade.service;

import com.secondhandtrade.model.Product;
import com.secondhandtrade.model.User;
import com.secondhandtrade.repository.ProductRepository;
import com.secondhandtrade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    // 查找全部
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    // 更新/增加product
    public Product save(Product product) {
        if (!productRepository.existsByProductId(product.getProductId())) {
            product.setCreatedAt(LocalDateTime.now());
        }
        product.setUpdatedAt(LocalDateTime.now());
        return productRepository.save(product);
    }

    // 通过id找product
    public Product findById(long id) {
        return productRepository.findByProductId(id);
    }

    // 删除product
    @Transactional
    public void deleteByProductId(long id) {
        productRepository.deleteByProductId(id);
    }

    // 通过title查找包含的product
    public List<Product> findByTitle(String title) {
        return productRepository.findByTitleContaining(title);
    }

    // 通过Seller查找包含的product
    public List<Product> findBySeller(User seller) {
        System.out.println("卖家: " + seller.getUserId() + "发起自己的商品查询");
        if (!userRepository.existsByUserId(seller.getUserId())) {
            System.out.println("User查询" + seller.getUserId() + "出错");
            throw new IllegalArgumentException("用户id不存在，可能是登录过期");
        }
        return productRepository.findBySeller(seller);
    }

    // 通过类别名称查找包含的product
    public List<Product> findByCategory(String category) {
        return productRepository.findByCategoryNameContaining(category);
    }

    // 查找首页推荐商品
    public List<Product> findForHome(User seller) {
        return productRepository.findForHome(seller.getUserId());
    }
}
