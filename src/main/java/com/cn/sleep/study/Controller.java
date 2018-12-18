package com.cn.sleep.study;

import com.cn.sleep.study.test.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.stereotype.Controller
public class Controller {

    private static final String EXAMPLE_1 = "pages/example_1 :: EXAMPLE_1";
    private static final String EXAMPLE_2 = "pages/example_2 :: EXAMPLE_2";
    private static final String HQL_QUERY = "pages/hql_query :: HQL_QUERY";
    private static final String HQL_PARAM_QUERY = "pages/hql_param_query :: HQL_PARAM_QUERY";

    @Autowired
    private Repository repository;

    @RequestMapping("test")
    public String toPage() {
        return "index";
    }

    @RequestMapping("example_1")
    @ResponseBody
    public ModelAndView testSubmit() {
        ModelAndView modelAndView = new ModelAndView(EXAMPLE_1);
        return modelAndView;
    }

    @RequestMapping("example_2")
    @ResponseBody
    public ModelAndView ex_2Page() {
        ModelAndView modelAndView = new ModelAndView(EXAMPLE_2);
        return modelAndView;
    }

    @RequestMapping("hql_query")
    @ResponseBody
    public ModelAndView hql_query() {
        ModelAndView modelAndView = new ModelAndView(HQL_QUERY);
        return modelAndView;
    }

    @RequestMapping("hql_param_query")
    @ResponseBody
    public ModelAndView hql_param_query() {
        ModelAndView modelAndView = new ModelAndView(HQL_PARAM_QUERY);
        return modelAndView;
    }
}
