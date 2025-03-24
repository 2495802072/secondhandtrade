package com.secondhandtrade.service;

import com.secondhandtrade.model.Major;
import com.secondhandtrade.repository.MajorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorService {
    @Autowired
    private MajorRepository majorRepository;

    // 获取所有专业
    public List<Major> getAllMajors() {
        return majorRepository.findAll();
    }

    // 根据大学ID获取专业列表
    public List<Major> getMajorsByUniversityId(Integer universityId) {
        return majorRepository.findByUniversity_UniversityId(universityId);
    }

    // 添加或更新专业
    public Major saveOrUpdateMajor(Major major) {
        return majorRepository.save(major);
    }

    // 删除专业
    public void deleteMajor(Integer id) {
        majorRepository.deleteById(id);
    }
}
