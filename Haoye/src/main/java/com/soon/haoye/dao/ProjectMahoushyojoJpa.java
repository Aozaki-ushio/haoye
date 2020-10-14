package com.soon.haoye.dao;

import com.soon.haoye.entity.ProjectMahoushyojyoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectMahoushyojoJpa extends JpaRepository<ProjectMahoushyojyoEntity,String> {
    @Query(value ="select * from projectmahoushyojyo where list_of_4X like :code or list_of_memory_crystalls like :code" , nativeQuery = true)
    List<ProjectMahoushyojyoEntity> FindNumberByCode(@Param("code")String code);
}
