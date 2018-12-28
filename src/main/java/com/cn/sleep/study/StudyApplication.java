package com.cn.sleep.study;

import com.cn.sleep.study.example.shigongwen.adminlte.sys.model.Museum;
import com.cn.sleep.study.example.shigongwen.adminlte.sys.repository.MuseumRepository;
import com.cn.sleep.study.test.MuseumTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.util.List;

@SpringBootApplication
@EnableCaching  // spring boot 内置缓存
public class StudyApplication {
    @Autowired
    public StudyApplication(MuseumRepository museumRepository) {
        MuseumTest museumTest = new MuseumTest();
        List<Museum> museumList = museumTest.create();
        museumRepository.saveAll(museumList);
    }

    public static void main(String[] args) {
        SpringApplication.run(StudyApplication.class, args);
    }



}
