package com.secondhandtrade.service;

import com.secondhandtrade.model.Likes;
import com.secondhandtrade.model.Product;
import com.secondhandtrade.model.User;
import com.secondhandtrade.repository.LikesRepository;
import com.secondhandtrade.repository.ProductRepository;
import com.secondhandtrade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LikesService {
    @Autowired
    private LikesRepository likesRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    //新建Likes
    public Likes save(Likes likes) {
        System.out.println("=================添加收藏商品==============");
        if(!userRepository.existsByUserId(likes.getBuyer().getUserId())){
            throw new IllegalArgumentException("用户id不存在，可能是登录过期");
        }
        if(!productRepository.existsByProductId(likes.getProduct().getProductId())){
            throw new IllegalArgumentException("商品不存在，请刷新网页");
        }
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
    @Transactional
    public void removeByUserIdAndProductId(Likes likes) {
        System.out.println("=================移除收藏商品==============");
        if(!userRepository.existsByUserId(likes.getBuyer().getUserId())){
            throw new IllegalArgumentException("用户id不存在，可能是登录过期");
        }
        if(!productRepository.existsByProductId(likes.getProduct().getProductId())){
            throw new IllegalArgumentException("商品不存在，请刷新网页");
        }
        likesRepository.deleteByBuyerAndProduct(likes.getBuyer(), likes.getProduct());
    }

    //通过Buyer查找Likes
    public List<Likes> findByBuyer(User buyer) {
        System.out.println("User: " + buyer.getUserId() + "发起Likes查询");
        if(!userRepository.existsByUserId(buyer.getUserId())){
            System.out.println("User查询"+buyer.getUserId()+"出错");
            throw new IllegalArgumentException("用户id不存在，可能是登录过期");
        }
        return likesRepository.findByBuyer(buyer);
    }

    //通过Receiver查找Likes
    public List<Likes> findByProduct(Product product) {
        if(productRepository.existsByProductId(product.getProductId())){
            throw new IllegalArgumentException("商品不存在，请刷新网页");
        }
        return likesRepository.findByProduct(product);
    }

}
