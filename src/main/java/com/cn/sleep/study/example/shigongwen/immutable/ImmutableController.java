package com.cn.sleep.study.example.shigongwen.immutable;

import com.cn.sleep.study.example.shigongwen.model.immutable.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("immutable")
public class ImmutableController {
    @Autowired
    private ImmutableRepository immutableRepository;

    @RequestMapping("findAll")
    public Object findAll() {
        return immutableRepository.findAll();
    }

    @RequestMapping("updateOrSave")
    public Object updateOrSave(Config config) {
        return immutableRepository.save(config);
    }
}
