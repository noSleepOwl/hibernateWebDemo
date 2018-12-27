package com.cn.sleep.study;

import com.cn.sleep.study.pagerouter.Museum;
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
    private static final String SAVE_ALL_EXAMPLE = "pages/shigongwen/save_all :: SAVE_ALL_EXAMPLE";
    private static final String FILTER_TEST_DEMO = "pages/shigongwen/filter_test :: FILTER_TEST_DEMO";
    private static final String SUBSELECT_ENTITY_TEST = "pages/shigongwen/subselect_entity_test :: SUBSELECT_ENTITY_TEST";
    private static final String IMMUTABLE_MODEL = "pages/shigongwen/immutable :: IMMUTABLE_MODEL";

    @Autowired
    private Repository repository;

    @RequestMapping("test")
    public String toPage() {
        return "index";
    }

    @RequestMapping("start")
    public String adminLTE() {
        return "admin_lte_temp/layout/starter";
    }

 /*   @RequestMapping("example_1")
    @ResponseBody
    public ModelAndView testSubmit() {
        ModelAndView modelAndView = new ModelAndView(EXAMPLE_1);
        return modelAndView;
    }

    @RequestMapping("example_2")
    @Museum("测试例子_2")
    @ResponseBody
    public ModelAndView ex_2Page() {
        ModelAndView modelAndView = new ModelAndView(EXAMPLE_2);
        return modelAndView;
    }*/

    /*@RequestMapping("hql_query")
    @Museum("HQL查询例子")
    @ResponseBody
    public ModelAndView hql_query() {
        ModelAndView modelAndView = new ModelAndView(HQL_QUERY);
        return modelAndView;
    }*/

    @RequestMapping("hql_param_query")
    @Museum("HQL查询例子")
    @ResponseBody
    public ModelAndView hql_param_query() {
        ModelAndView modelAndView = new ModelAndView(HQL_PARAM_QUERY);
        return modelAndView;
    }

    @RequestMapping("save_all_example")
    @Museum("级联更新和保存")
    @ResponseBody
    public ModelAndView save_all_example() {
        ModelAndView modelAndView = new ModelAndView(SAVE_ALL_EXAMPLE);
        return modelAndView;
    }

    @RequestMapping("filter_test_demo")
    @Museum("模型过滤器")
    @ResponseBody
    public ModelAndView filter_test_demo() {
        ModelAndView modelAndView = new ModelAndView(FILTER_TEST_DEMO);
        return modelAndView;
    }

    @RequestMapping("subselect_entity_test")
    @Museum("子查询实体映射")
    @ResponseBody
    public ModelAndView subselect_entity_test() {
        ModelAndView modelAndView = new ModelAndView(SUBSELECT_ENTITY_TEST);
        return modelAndView;
    }

    @RequestMapping("immutable_model")
    @Museum("实体映射策略配置")
    @ResponseBody
    public ModelAndView immutable_model() {
        ModelAndView modelAndView = new ModelAndView(IMMUTABLE_MODEL);
        return modelAndView;
    }
}
