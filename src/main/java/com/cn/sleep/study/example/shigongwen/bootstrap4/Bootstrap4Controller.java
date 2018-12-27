package com.cn.sleep.study.example.shigongwen.bootstrap4;

import com.cn.sleep.study.pagerouter.Router;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 只是提供bootstrap4测试用的控制器
 */
@Controller
@RequestMapping("bootstrap4")
public class Bootstrap4Controller {
    private static final String PAGE = "bootstrap_4_index";

    @GetMapping
    @Router
    public String page() {
        return PAGE;
    }
}
