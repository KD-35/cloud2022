package com.gdit.cloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class MyHandler {

//    触发热点规则的处理函数，必须是static
    public static String blockHandlerHot(String p1, String p2, BlockException ex){
        return "该商品访问过于频繁，请稍后再试。ex:"+ex;
    }

}
