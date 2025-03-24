package com.secondhandtrade.repository;

import com.secondhandtrade.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
    // 可以根据需要添加自定义查询方法
    University findByUniversityName(String universityName);
}
