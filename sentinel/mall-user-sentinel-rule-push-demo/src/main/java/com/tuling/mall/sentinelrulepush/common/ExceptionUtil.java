package com.tuling.mall.sentinelrulepush.common;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.tuling.common.utils.R;

public class ExceptionUtil {

    // 用于在抛出异常的时候提供 fallback 处理逻辑。
    // fallback 函数可以针对所有类型的异常（除了 exceptionsToIgnore 里面排除掉的异常类型）进行处理
    public static R fallback(Integer id,Throwable e){
        return R.error(-2,"=========被异常降级啦===");
    }

    public static R handleException(Integer id, BlockException e){
        return R.error(-1,"==========被限流啦===");
    }
}
