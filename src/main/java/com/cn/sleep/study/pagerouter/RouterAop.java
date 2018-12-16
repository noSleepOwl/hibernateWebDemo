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
import java.util.stream.Stream;

@Component
@Aspect
public class RouterAop {
    @Autowired
    private HttpServletRequest request;

    @Pointcut("execution(public String com.cn.sleep.study.Controller.toPage())")
    public void indexPage() {
    }

    private void getAllPath(ProceedingJoinPoint joinPoint) {

        RequestMapping requestMapping = Controller.class
                .getAnnotation(RequestMapping.class);
        String[] urls = Stream.of(Controller.class.getDeclaredMethods())
                .filter(o -> {
                    System.out.println(o.getName());
                    return o.getAnnotation(RequestMapping.class) != null;
                })
                .filter(o -> !o.getName().equals(joinPoint.getSignature().getName()))
                .map(o -> o.getAnnotation(RequestMapping.class))
                .map(RequestMapping::value)
                .flatMap(Stream::of)
                .map(o -> {
                    if (requestMapping != null) {
                        return Stream.of(requestMapping.value()).map(curl -> curl + o).toArray();
                    } else {
                        return new String[]{o};
                    }
                })
                .flatMap(Stream::of)
                .toArray(String[]::new);
        request.setAttribute("examplePages", urls);
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
