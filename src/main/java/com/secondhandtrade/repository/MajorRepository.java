package com.secondhandtrade.repository;

import com.secondhandtrade.model.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MajorRepository extends JpaRepository<Major, Integer> {
    // 根据大学ID查询专业列表
    List<Major> findByUniversity_UniversityId(Integer universityId);
}
