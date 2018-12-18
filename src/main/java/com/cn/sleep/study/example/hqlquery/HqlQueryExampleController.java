package com.cn.sleep.study.example.hqlquery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("hqlExample")
public class HqlQueryExampleController {
    @Autowired
    private HqlQueryService hqlQueryService;

    @RequestMapping("query")
    public Object runQuery(String hql) {
        return hqlQueryService.query(hql);
    }

    @RequestMapping("paramQuery")
    public Object runQuery(@RequestParam Map<String, Object> map) {
        return hqlQueryService.paramQuery(map);
    }

}
