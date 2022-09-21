package com.tuling.mall.sentinelrulepush.common;

import com.tuling.common.utils.R;

public class CommonFallback {

    // 注意： 必须为 static 函数
    public static R fallback(Integer id,Throwable e){
        return R.error(-2,"===CommonFallback被异常降级啦==="+e.getMessage());
    }
}
