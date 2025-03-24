package com.secondhandtrade.service;

import com.secondhandtrade.model.University;
import com.secondhandtrade.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityService {
    @Autowired
    private UniversityRepository universityRepository;

    // 获取所有大学
    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    // 根据ID获取大学
    public Optional<University> getUniversityById(Integer id) {
        return universityRepository.findById(id);
    }

    // 添加或更新大学
    public University saveOrUpdateUniversity(University university) {
        return universityRepository.save(university);
    }

    // 删除大学
    public void deleteUniversity(Integer id) {
        universityRepository.deleteById(id);
    }
}
