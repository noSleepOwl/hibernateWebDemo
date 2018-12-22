package com.cn.sleep.study.example.shigongwen.immutable;

import com.cn.sleep.study.example.shigongwen.model.immutable.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImmutableRepository extends JpaRepository<Config, String> {

}
