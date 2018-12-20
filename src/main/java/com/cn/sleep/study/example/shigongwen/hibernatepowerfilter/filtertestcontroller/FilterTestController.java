package com.cn.sleep.study.example.shigongwen.hibernatepowerfilter.filtertestcontroller;

import com.cn.sleep.study.example.shigongwen.base.BaseController;
import com.cn.sleep.study.example.shigongwen.hibernatepowerfilter.UserFilter;
import com.cn.sleep.study.example.shigongwen.model.HomeWork;
import com.cn.sleep.study.example.shigongwen.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

import static com.cn.sleep.study.example.shigongwen.hibernatepowerfilter.Const.SESSION_USER;

@RestController
@RequestMapping("filterTest")
public class FilterTestController extends BaseController {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping("simpleLogin")
    public boolean simpleLogin(User user) {
        if (Objects.nonNull(user)) {
            request.getSession().setAttribute(SESSION_USER, user);
            return true;
        }
        return false;
    }

    @SuppressWarnings("JpaQlInspection")
    @RequestMapping("getAllHomeWork")
    @UserFilter
    public List<HomeWork> getAllHomeWork() {
        return entityManager.createQuery("select hm from HomeWork hm", HomeWork.class).getResultList();
    }
}
