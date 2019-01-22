package com.cn.sleep.study;

import com.cn.sleep.study.example.shigongwen.adminlte.sys.model.Museum;
import com.cn.sleep.study.example.shigongwen.adminlte.sys.repository.MuseumRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Stream;

public class StudyApplicationTests {
    @Autowired
    private MuseumRepository museumRepository;


    @Test
    public void contextLoads() {
        MuseumTest museumTest = new MuseumTest();
        List<Museum> museumList = museumTest.create();
        museumRepository.saveAll(museumList);
    }

    @Test
    public void createMethod() {
        String target = "participate teamName teamLeader evaluation  members personNums groupRemark groupGrade  groupScore personRemark  fileName  filePath memberScoringList whichGroup";
        Stream.of(target.split("\\s")).forEach(str -> {

            str = str.trim();
            if(str.length() > 0 ){
                str = "String  get"+str.substring(0, 1).toUpperCase() + str.substring(1)+"();";
                System.out.println(str);
            }
        });
    }
}
