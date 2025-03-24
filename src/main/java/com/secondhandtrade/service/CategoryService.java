package com.secondhandtrade.service;

import com.secondhandtrade.model.Category;
import com.secondhandtrade.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    // 查询所有类别
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // 根据ID查询类别
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    // 新增或更新类别
    public Category saveCategory(Category category) {
        if (category.getCategoryId() == null) {
            category.setCreatedAt(LocalDateTime.now());
        }
        category.setUpdatedAt(LocalDateTime.now());
        return categoryRepository.save(category);
    }

    // 根据名称查询类别
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    // 根据名称模糊查询类别列表
    public List<Category> getCategoriesByNameContaining(String name) {
        return categoryRepository.findByNameContaining(name);
    }

    // 根据描述模糊查询类别列表
    public List<Category> getCategoriesByDescriptionContaining(String description) {
        return categoryRepository.findByDescriptionContaining(description);
    }

    // 根据父类别ID查询子分类（支持 parentId 为 -1）
    public List<Category> getCategoriesByParentId(Long parentId) {
        if (parentId == -1) {
            return categoryRepository.findByParentIdIsNull();
        }
        return categoryRepository.findByParentId(parentId);
    }

    // 删除类别
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }
}
