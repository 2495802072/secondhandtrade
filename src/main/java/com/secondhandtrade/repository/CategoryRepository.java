package com.secondhandtrade.repository;

import com.secondhandtrade.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // 根据名称查询类别
    Category findByName(String name);

    // 根据名称模糊查询类别列表
    List<Category> findByNameContaining(String name);

    // 根据描述模糊查询类别列表
    List<Category> findByDescriptionContaining(String description);

    // 根据父类别ID查询子分类（支持 parentId 为 null）
    List<Category> findByParentId(Long parentId);

    // 查询 parentId 为 null 的分类（一级分类）
    List<Category> findByParentIdIsNull();
}
