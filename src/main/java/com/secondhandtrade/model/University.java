package com.secondhandtrade.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "universities")
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "university_id")
    private Integer universityId; // 主键，SQL自动生成

    @Column(name = "university_name", nullable = false)
    private String universityName; // 大学名称

    @Column(name = "location")
    private String location; // 大学所在地

    @Column(name = "established_year")
    private Integer establishedYear; // 成立年份

    @Column(name = "website")
    private String website; // 大学官网

//    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Major> majors; // 大学下的专业列表

    public University() {
    }

    public University(Integer universityId, String universityName, String location, Integer establishedYear, String website) {
        this.universityId = universityId;
        this.universityName = universityName;
        this.location = location;
        this.establishedYear = establishedYear;
        this.website = website;
    }

    // Getter & Setter
    public Integer getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Integer universityId) {
        this.universityId = universityId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getEstablishedYear() {
        return establishedYear;
    }

    public void setEstablishedYear(Integer establishedYear) {
        this.establishedYear = establishedYear;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

//    public List<Major> getMajors() {
//        return majors;
//    }

//    public void setMajors(List<Major> majors) {
//        this.majors = majors;
//    }
}
