package com.cn.sleep.study.example.shigongwen.hibernatepowerfilter;

import com.cn.sleep.study.example.shigongwen.model.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Objects;

import static com.cn.sleep.study.example.shigongwen.hibernatepowerfilter.Const.*;

@Component
@Aspect
public class UserFilterCut {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private HttpServletRequest request;

    private HttpSession getSession() {
        return request.getSession();
    }

    private User getUser() {
        return (User) getSession().getAttribute(SESSION_USER);
    }

    private Filter getQueryFilter() {
        return entityManager.unwrap(Session.class).enableFilter(USER_FILTER);
    }

    private void removeQueryFilter() {
        entityManager.unwrap(Session.class).disableFilter(USER_FILTER);
    }


    @Pointcut("@annotation(com.cn.sleep.study.example.shigongwen.hibernatepowerfilter.UserFilter)")
    public void userFilter() {
    }

    @Around("userFilter()")
    public Object doProcess(ProceedingJoinPoint joinPoint) {
        User user = getUser();

        if (Objects.nonNull(user)) {
            Filter filter = getQueryFilter();
            filter.setParameter(USER_FILTER_PARAM_USER_ID, user.getId());
        }

        Object object = null;
        try {
            object = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            removeQueryFilter();
        }
        return object;
    }

}
