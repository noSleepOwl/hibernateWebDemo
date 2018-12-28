package com.cn.sleep.study;

import com.cn.sleep.study.example.shigongwen.adminlte.sys.model.Museum;
import com.cn.sleep.study.example.shigongwen.adminlte.sys.repository.MuseumRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudyApplicationTests {
    @Autowired
    private MuseumRepository museumRepository;


    @Test
    public void contextLoads() {
        MuseumTest museumTest = new MuseumTest();
        List<Museum> museumList = museumTest.create();
        museumRepository.saveAll(museumList);
    }

}
