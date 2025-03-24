package com.secondhandtrade.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId; // 类别编号

    @Column(nullable = false, unique = true)
    private String name; // 类别名称

    private String description; // 类别描述

    @Column(name = "parent_id")
    private Long parentId; // 父类别ID（NULL 表示一级分类）

    private LocalDateTime createdAt; // 创建时间
    private LocalDateTime updatedAt; // 更新时间

    public Category() {
    }

    // 新增构造函数，支持 parentId
    public Category(String name, String description, Long parentId) {
        this.name = name;
        this.description = description;
        this.parentId = parentId;
    }

    // Getter & Setter
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
