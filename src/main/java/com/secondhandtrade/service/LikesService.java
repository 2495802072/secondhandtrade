package com.secondhandtrade.service;

import com.secondhandtrade.model.Likes;
import com.secondhandtrade.model.Product;
import com.secondhandtrade.model.User;
import com.secondhandtrade.repository.LikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LikesService {
    @Autowired
    private LikesRepository likesRepository;

    //新建Likes
    public Likes save(Likes likes) {
        return likesRepository.save(likes);
    }

    //查询所有Likes
    public List<Likes> findAll() {
        return likesRepository.findAll();
    }

    //通过id查找Likes
    public Likes findById(Long id) {return likesRepository.findByLikesId(id);}

    @Transactional
    //通过id删除Likes
    public void deleteByLikesId(Long id) {
        likesRepository.deleteByLikesId(id);
    }

    //通过Buyer查找Likes
    public List<Likes> findByBuyer(User buyer) {
        return likesRepository.findByBuyer(buyer);
    }

    //通过Receiver查找Likes
    public List<Likes> findByProduct(Product product) {
        return likesRepository.findByProduct(product);
    }

}
