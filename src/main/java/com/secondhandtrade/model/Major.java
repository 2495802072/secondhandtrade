package com.secondhandtrade.model;

import jakarta.persistence.*;

@Entity
@Table(name = "majors")
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "major_id")
    private Integer majorId; // 主键，SQL自动生成

    @Column(name = "major_name", nullable = false)
    private String majorName; // 专业名称

    @Column(name = "department")
    private String department; // 所属院系

    @ManyToOne
    @JoinColumn(name = "university_id", nullable = false)
    private University university; // 所属大学

    public Major() {
    }

    public Major(Integer majorId, String majorName, String department, University university) {
        this.majorId = majorId;
        this.majorName = majorName;
        this.department = department;
        this.university = university;
    }

    // Getter & Setter
    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}
