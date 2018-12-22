
/**
 * @ParamDef 中的 type 属性 需要注意 , 其为小写... 大写会报错
 * 过滤器之只能做简单的查询过滤,会跟随实体进行实时的sql 生成
 *包级别的注释  定义在 package-info.java 文件中
 * */
@FilterDefs({  // 定义过滤器列表
        @FilterDef(name = Const.USER_FILTER, parameters = { // 过滤器定义  name 过滤器名称 项目中唯一  ,parameters 参数列表
                //参数 name 参数名称  , type 参数类型
                @ParamDef(name = Const.USER_FILTER_PARAM_USER_ID, type = "string")
        })
})
package com.cn.sleep.study.example.shigongwen.model;

import com.cn.sleep.study.example.shigongwen.hibernatepowerfilter.Const;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;
