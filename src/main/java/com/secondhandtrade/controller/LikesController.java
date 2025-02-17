package com.secondhandtrade.controller;


import com.secondhandtrade.model.Likes;
import com.secondhandtrade.model.Product;
import com.secondhandtrade.model.User;
import com.secondhandtrade.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/likes")
public class LikesController {
    @Autowired
    private LikesService likesService;

    //查询所有Likes
    @GetMapping
    public List<Likes> getLikes() {
        return likesService.findAll();
    }

    //新建Likes
    @PostMapping
    public ResponseEntity<?> addLikes(@RequestBody Likes likes) {
        Likes saved = likesService.save(likes);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    //通过id查询Likes
    @GetMapping("/{id}")
    public Likes getLikes(@PathVariable Long id) {
        return likesService.findById(id);
    }


    //通过id删除Likes
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLikes(@PathVariable Long id) {
        Likes likes = likesService.findById(id);
        if (likes != null) {
            likesService.deleteByLikesId(id);
            return new ResponseEntity<>(likes.getLikesId()+"已删除", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("目标未找到",HttpStatus.NOT_FOUND);
        }
    }

    //通过发送方查询Likes
    @PostMapping("/buyer")
    public ResponseEntity<?> selectByBuyer(@RequestBody User buyer) {
        List<Likes> byBuyer = likesService.findByBuyer(buyer);
        return new ResponseEntity<>(byBuyer, HttpStatus.OK);
    }

    //通过接收查询Likes
    @PostMapping("/product")
    public ResponseEntity<?> selectByProduct(@RequestBody Product product) {
        List<Likes> byProduct = likesService.findByProduct(product);
        return new ResponseEntity<>(byProduct, HttpStatus.OK);
    }

}
