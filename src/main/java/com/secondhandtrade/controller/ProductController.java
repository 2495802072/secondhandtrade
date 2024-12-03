package com.secondhandtrade.controller;

import com.secondhandtrade.model.Product;
import com.secondhandtrade.model.User;
import com.secondhandtrade.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService productService;

    // TODO 报错，序列化失败
    @GetMapping
    public List<Product> getProducts() {
        return productService.findAll();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product == null) {
            return new ResponseEntity<>("目标未找到", HttpStatus.NOT_FOUND);
        }else{
            productService.deleteByProductId(id);
            return new ResponseEntity<>(product.getTitle()+"已删除", HttpStatus.OK);
        }
    }

    @PostMapping("/bySeller")
    public List<Product> findBySeller(@RequestBody User seller) {
        return productService.findBySeller(seller);
    }

    @GetMapping("/byTitle/{title}")
    public List<Product> findByTitle(@PathVariable String title) {
        return productService.findByTitle(title);
    }

    @GetMapping("/byCategory/{category}")
    public List<Product> findByCategory(@PathVariable String category) {
        return productService.findByCategory(category);
    }


}
