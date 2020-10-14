package com.soon.haoye.dao;

import com.soon.haoye.entity.ProjectMahoushyojyoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BackstageJpa extends JpaRepository<ProjectMahoushyojyoEntity,Integer> {
    @Query(value ="select * from projectmahoushyojyo where number like :code " , nativeQuery = true)
    List<ProjectMahoushyojyoEntity> FindNumberByCode(@Param("code")String code);

    @Query(value ="select * from projectmahoushyojyo  where is_selled = :is_selled", nativeQuery = true)
    Page<ProjectMahoushyojyoEntity> selectAllByIsSelled(@Param("is_selled") String isSelled, Pageable pageable);

}
