
/**
 * @ParamDef 中的 type 属性 需要注意 , 其为小写... 大写会报错
 * 过滤器之只能做简单的查询过滤,会跟随实体进行实时的sql 生成
 *
 * */
@FilterDefs({
        @FilterDef(name = Const.USER_FILTER, parameters = {
                @ParamDef(name = Const.USER_FILTER_PARAM_USER_ID, type = "string")
        })
})
package com.cn.sleep.study.example.shigongwen.model;

import com.cn.sleep.study.example.shigongwen.hibernatepowerfilter.Const;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;
