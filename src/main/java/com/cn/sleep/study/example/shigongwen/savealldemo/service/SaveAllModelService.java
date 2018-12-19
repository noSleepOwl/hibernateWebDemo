package com.cn.sleep.study.example.shigongwen.savealldemo.service;

import com.cn.sleep.study.example.shigongwen.savealldemo.model.HomeWork;
import com.cn.sleep.study.example.shigongwen.savealldemo.model.Student;
import com.cn.sleep.study.example.shigongwen.savealldemo.model.UserInfo;
import com.cn.sleep.study.example.shigongwen.savealldemo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class SaveAllModelService {
    @Autowired
    private StudentRepository studentRepository;

    public Map<String, Object> saveStudent(Map<String, Object> param) {
        Student student = new Student();
        UserInfo userInfo = new UserInfo();
        student.setUserInfo(userInfo);
        try {
            userInfo.setAge(Integer.valueOf((String) param.get("age")));
        } catch (NumberFormatException e) {
            userInfo.setAge(10);
        }

        userInfo.setCode((String) param.get("code"));
        userInfo.setName((String) param.get("name"));
        String contents = (String) param.get("content");

        if (!StringUtils.isEmpty(contents)) {
            String[] contentArr = contents.split(",");
            Stream.of(contentArr).map(o -> {
                HomeWork homeWork = new HomeWork();
                homeWork.setContent(o);
                return homeWork;
            }).forEach(o -> student.getHomeWorkList().add(o));
        }

        studentRepository.save(student);
        return new HashMap<String, Object>() {
            {
                put("success", true);
            }
        };
    }

    public Map<String, Object> deleteStudent(String studentId) {
        studentRepository.deleteById(studentId);
        return new HashMap<String, Object>() {
            {
                put("success", true);
            }
        };
    }

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }
}
