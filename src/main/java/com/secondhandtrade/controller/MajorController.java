package com.secondhandtrade.controller;

import com.secondhandtrade.model.Major;
import com.secondhandtrade.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/majors")
public class MajorController {
    @Autowired
    private MajorService majorService;

    // 获取所有专业
    @GetMapping
    public List<Major> getAllMajors() {
        return majorService.getAllMajors();
    }

    // 根据大学ID获取专业列表
    @GetMapping("/university/{universityId}")
    public List<Major> getMajorsByUniversityId(@PathVariable Integer universityId) {
        return majorService.getMajorsByUniversityId(universityId);
    }

    // 添加或更新专业
    @PostMapping
    public Major createOrUpdateMajor(@RequestBody Major major) {
        return majorService.saveOrUpdateMajor(major);
    }

    // 删除专业
    @DeleteMapping("/{id}")
    public void deleteMajor(@PathVariable Integer id) {
        majorService.deleteMajor(id);
    }
}
