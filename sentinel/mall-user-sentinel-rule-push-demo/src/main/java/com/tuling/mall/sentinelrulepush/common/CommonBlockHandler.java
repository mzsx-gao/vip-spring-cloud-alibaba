package com.tuling.mall.sentinelrulepush.common;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.tuling.common.utils.R;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class CommonBlockHandler {

    // 注意： 必须为 static 函数   多个方法之间方法名不能一样
    public static R handleException(Map<String, Object> params, BlockException exception){
        return R.error(-1,"===CommonBlockHandler被限流啦==="+exception);
    }

    public static R handleException2(Integer id, BlockException e){
        log.info("CommonBlockHandler handleException2================" + e.getRule());
        R r = null;
        if (e instanceof FlowException) {
            r = R.error(100, "接口限流了");

        } else if (e instanceof DegradeException) {
            r = R.error(101, "服务降级了");

        } else if (e instanceof ParamFlowException) {
            r = R.error(102, "热点参数限流了");

        } else if (e instanceof SystemBlockException) {
            r = R.error(103, "触发系统保护规则了");

        } else if (e instanceof AuthorityException) {
            r = R.error(104, "授权规则不通过");
        }
        return r;
    }

    public static String handleException3(BlockException exception){
        return "===被限流啦==="+exception;
    }
}
