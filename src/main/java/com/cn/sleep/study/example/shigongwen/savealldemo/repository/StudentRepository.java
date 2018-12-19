package com.cn.sleep.study.example.shigongwen.savealldemo.repository;

import com.cn.sleep.study.example.shigongwen.savealldemo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

}
