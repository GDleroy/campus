package com.shnu.campus.dao;

import com.shnu.campus.entity.Apply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by guodi on 2020-05-21 00:56
 */
public interface ApplyRepository extends JpaRepository<Apply,Long> {

    @Query("select b from Apply b where b.name = ?1 ")
    Page<Apply> findByName(String name, Pageable pageable);

    @Query("select b from Apply b where b.username = ?1 ")
    Page<Apply> findByUsername(String name, Pageable pageable);

    @Transactional
    void deleteApplyByActivityIdAndUsername(Long id,String name);


}
