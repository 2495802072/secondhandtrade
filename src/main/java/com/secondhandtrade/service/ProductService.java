package com.secondhandtrade.service;

import com.secondhandtrade.model.Product;
import com.secondhandtrade.model.User;
import com.secondhandtrade.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    //查找全部
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    //更新/增加product
    public Product save(Product product) {
        if (!productRepository.existsByProductId(product.getProductId())) {
            product.setCreatedAt(LocalDateTime.now());
        }
        product.setUpdatedAt(LocalDateTime.now());
        return productRepository.save(product);
    }

    //通过id找product
    public Product findById(long id) {
        return productRepository.findByProductId(id);
    }

    //删除product
    @Transactional
    public void deleteByProductId(long id) {
        productRepository.deleteByProductId(id);
    }

    //通过title查找包含的product
    public List<Product> findByTitle(String title) {
        return productRepository.findByTitleContaining(title);
    }

    //通过Seller查找包含的product
    public List<Product> findBySeller(User seller) {
        return productRepository.findBySeller(seller);
    }

    //通过类别查找包含的product
    public List<Product> findByCategory(String category) {
        return  productRepository.findByCategoryContaining(category);
    }

    public List<Product> findForHome(User seller){
        return productRepository.findForHome(seller.getUserId());
    }
}
