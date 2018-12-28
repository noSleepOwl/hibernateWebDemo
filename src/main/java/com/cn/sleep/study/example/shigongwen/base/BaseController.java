package com.cn.sleep.study.example.shigongwen.base;

import com.cn.sleep.study.example.shigongwen.base.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Result getErrorMsg(BindingResult bindingResult) {
        List<String> error = bindingResult.getAllErrors().stream().map(o -> o.getDefaultMessage()).collect(Collectors.toList());
        return Result.error(error);
    }

    public Result error(String msg) {
        return Result.error(msg);
    }

    public Result success(String name, Object data) {
        return Result.success().addObj(name, data);
    }

    public Result success(Map<String, Object> data) {
        return Result.success().setData(data);
    }

    public Result success(Object object) {
        return Result.success().setData(object);
    }


    public Result returnStatusTrue() {
        return Result.success().addObj("returnStatus", true);
    }

    public Result returnStatusFalse() {
        return Result.success().addObj("returnStatus", false);
    }

    public Result success(Object... objects) {
        if (objects != null) {
            Result result = Result.success();
            for (Object object : objects) {
                result.addObj(object);
            }
            return result;
        }
        return Result.success();
    }
}
