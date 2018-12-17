package com.cn.sleep.study.example_1;

import com.cn.sleep.study.test.Model;
import com.cn.sleep.study.test.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("exp_1")
public class ExampleOneController {
    @Autowired
    private Repository repository;

    @RequestMapping("save")
    public Map<String, Object> saveStudent(Model model) {
        repository.save(model);
        return new HashMap<String, Object>() {
            {
                put("isSuccess", true);
            }
        };
    }

    @RequestMapping("findAll")
    public List<Model> findAll() {
        return repository.findAll();
    }
}
