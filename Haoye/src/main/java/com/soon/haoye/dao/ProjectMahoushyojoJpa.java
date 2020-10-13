package com.soon.haoye.dao;

import com.soon.haoye.entity.ProjectMahoushyojyoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectMahoushyojoJpa extends JpaRepository<ProjectMahoushyojyoEntity,String> {
    @Query(value ="select * from project_mahoushyojyo where list3x like :code or list4x like :code" , nativeQuery = true)
    List<ProjectMahoushyojyoEntity> FindNumberByCode(@Param("code")String code);
}
