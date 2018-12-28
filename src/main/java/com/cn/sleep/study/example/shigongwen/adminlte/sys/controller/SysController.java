package com.cn.sleep.study.example.shigongwen.adminlte.sys.controller;

import com.cn.sleep.study.example.shigongwen.adminlte.sys.model.Museum;
import com.cn.sleep.study.example.shigongwen.adminlte.sys.service.MuseumService;
import com.cn.sleep.study.example.shigongwen.base.BaseController;
import com.cn.sleep.study.example.shigongwen.base.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("sys")
@RestController
public class SysController extends BaseController {
    @Autowired
    private MuseumService museumService;

    @RequestMapping("getMuseums")
    public Result getMuseums() {
        List<Museum> museumVoList = museumService.getMuseums();
        return Result.success().setData(museumVoList);
    }

}
