package com.secondhandtrade.model;

import jakarta.persistence.*;

@Entity
@Table(name = "likes")
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long likesId;

    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "buyer_id",referencedColumnName = "user_id")
    private User buyer;

    public Likes(Long likesId, Product product, User buyer) {
        this.likesId = likesId;
        this.product = product;
        this.buyer = buyer;
    }

    public Likes() {}

    public Long getLikesId() {
        return likesId;
    }

    public void setLikesId(Long likesId) {
        this.likesId = likesId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }
}
