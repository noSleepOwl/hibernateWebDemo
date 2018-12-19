package com.cn.sleep.study.example.shigongwen.savealldemo.controller;

import com.cn.sleep.study.example.shigongwen.base.BaseController;
import com.cn.sleep.study.example.shigongwen.savealldemo.model.Student;
import com.cn.sleep.study.example.shigongwen.savealldemo.service.SaveAllModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("sgw")
public class SaveAllAndDeleteAllModelController extends BaseController {
    @Autowired
    private SaveAllModelService saveAllModelService;

    @RequestMapping("saveStudent")
    public Map<String, Object> saveStudent(@RequestParam Map<String, Object> param) {
        return saveAllModelService.saveStudent(param);
    }

    @RequestMapping("deleteStudent")
    public Map<String, Object> deleteStudent(String studentId) {
        return saveAllModelService.deleteStudent(studentId);
    }

    @RequestMapping("getAllStudent")
    public List<Student> getAllStudent() {
        return saveAllModelService.getAllStudent();
    }
}
