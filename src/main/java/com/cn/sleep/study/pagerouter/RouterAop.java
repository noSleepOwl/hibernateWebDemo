package com.cn.sleep.study.pagerouter;

import com.cn.sleep.study.Controller;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Aspect
public class RouterAop {
    @Autowired
    private HttpServletRequest request;

    @Pointcut("execution(public String com.cn.sleep.study.Controller.toPage())")
    public void indexPage() {
    }

    private MuseumModel createMuseum(RequestMapping mapping, Museum museum) {
        MuseumModel museumModel = new MuseumModel();

        String name = "";
        int order = 0;
        if (museum == null) {
            name = mapping.value()[0];
        } else {
            name = museum.value();
            order = museum.order();
        }

        museumModel.setName(name);
        museumModel.setOrder(order);
        museumModel.setPath(mapping.value()[0]);
        return museumModel;
    }

    private void getAllPath(ProceedingJoinPoint joinPoint) {
        RequestMapping requestMapping = Controller.class
                .getAnnotation(RequestMapping.class);
        List<MuseumModel> museumModels = Stream.of(Controller.class.getDeclaredMethods())
                .filter(o -> o.getAnnotation(RequestMapping.class) != null)
                .filter(o -> !o.getName().equals(joinPoint.getSignature().getName()))
                .filter(o -> o.getAnnotation(RequestMapping.class).value().length > 0)
                .map(o -> {
                    RequestMapping mapping = o.getAnnotation(RequestMapping.class);
                    Museum museum = o.getAnnotation(Museum.class);
                    return createMuseum(mapping, museum);
                })
                .map(o -> {
                    if (requestMapping != null) {
                        o.setPath(requestMapping.value() + o.getPath());
                    }
                    return o;
                }).collect(Collectors.toList());
        request.setAttribute("examplePages", museumModels);
    }

    @Around(value = "indexPage()")
    public String doBeforeMethod(ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        getAllPath(joinPoint);
        return (String) result;
    }
}
