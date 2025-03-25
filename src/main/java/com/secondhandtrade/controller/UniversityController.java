package com.secondhandtrade.controller;

import com.secondhandtrade.model.University;
import com.secondhandtrade.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/universities")
public class UniversityController {
    @Autowired
    private UniversityService universityService;

    // 获取所有大学
    @GetMapping
    public List<University> getAllUniversities() {
        return universityService.getAllUniversities();
    }

    // 根据ID获取大学
    @GetMapping("/{id}")
    public ResponseEntity<University> getUniversityById(@PathVariable Integer id) {
        Optional<University> university = universityService.getUniversityById(id);
        return university.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 添加或更新大学
    @PostMapping
    public University createOrUpdateUniversity(@RequestBody University university) {
        return universityService.saveOrUpdateUniversity(university);
    }

    // 删除大学
    @DeleteMapping("/{id}")
    public void deleteUniversity(@PathVariable Integer id) {
        universityService.deleteUniversity(id);
    }
}
