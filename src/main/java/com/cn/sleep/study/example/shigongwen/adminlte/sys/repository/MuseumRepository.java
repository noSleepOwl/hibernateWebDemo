package com.cn.sleep.study.example.shigongwen.adminlte.sys.repository;

import com.cn.sleep.study.example.shigongwen.adminlte.sys.model.Museum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MuseumRepository extends JpaRepository<Museum, String> {
    @Query("select m from Museum  m where m.parent is null")
    List<Museum> findRoot();
}
