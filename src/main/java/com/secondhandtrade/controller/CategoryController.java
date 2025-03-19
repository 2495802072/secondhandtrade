package com.secondhandtrade.controller;

import com.secondhandtrade.model.Category;
import com.secondhandtrade.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    // 查询所有类别
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // 根据ID查询类别
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category);
    }

    // 新增或更新类别
    @PostMapping
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
        Category savedCategory = categoryService.saveCategory(category);
        return ResponseEntity.ok(savedCategory);
    }

    // 根据名称查询类别
    @GetMapping("/name/{name}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String name) {
        Category category = categoryService.getCategoryByName(name);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category);
    }

    // 根据名称模糊查询类别列表
    @GetMapping("/search/name")
    public List<Category> getCategoriesByNameContaining(@RequestParam String name) {
        return categoryService.getCategoriesByNameContaining(name);
    }

    // 根据描述模糊查询类别列表
    @GetMapping("/search/description")
    public List<Category> getCategoriesByDescriptionContaining(@RequestParam String description) {
        return categoryService.getCategoriesByDescriptionContaining(description);
    }

    // 删除类别
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok().build();
    }
}
