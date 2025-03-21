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
    private ProductService productService;

    // 查询所有商品
    @GetMapping
    public List<Product> getProducts() {
        return productService.findAll();
    }

    // 新增或更新商品
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    // 通过id查询商品
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.findById(id);
    }

    // 通过id删除商品
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product == null) {
            return new ResponseEntity<>("目标未找到", HttpStatus.NOT_FOUND);
        } else {
            productService.deleteByProductId(id);
            return new ResponseEntity<>(product.getTitle() + "已删除", HttpStatus.OK);
        }
    }

    // 通过卖家查询商品
    @PostMapping("/bySeller")
    public ResponseEntity<?> findBySeller(@RequestBody User seller) {
        try {
            List<Product> bySeller = productService.findBySeller(seller);
            return new ResponseEntity<>(bySeller, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    // 通过标题查询商品
    @GetMapping("/byTitle/{title}")
    public List<Product> findByTitle(@PathVariable String title) {
        return productService.findByTitle(title);
    }

    // 通过类别查询商品
    @GetMapping("/byCategory/{category}")
    public List<Product> findByCategory(@PathVariable String category) {
        return productService.findByCategory(category);
    }

    // 登录后首页过滤自己的商品
    @PostMapping("/findAll/home")
    public List<Product> findAllHome(@RequestBody User seller) {
        return productService.findForHome(seller);
    }
}
