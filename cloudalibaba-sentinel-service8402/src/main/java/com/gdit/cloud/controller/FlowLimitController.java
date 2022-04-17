package com.gdit.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.gdit.cloud.handler.MyHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController

public class FlowLimitController {

    @GetMapping("/testA")
    @SentinelResource(value = "testA",blockHandler ="blockHandler" )
    public String testA(){
       /* try{
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }*/
        return "************testA";
    }
    @GetMapping("/testB")
    public String testB(){
        System.out.println(Thread.currentThread().getName()+"\t"+"testB......");
        return "************testB";
    }
    @GetMapping("/testC")
    @SentinelResource(value = "testC",blockHandler ="blockHandler" )
    public String testC(){

        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("testD,测试RT");
        return "************testC";
    }

    @GetMapping("/testD")
    @SentinelResource(value = "TestD",blockHandler = "blockHandlerEroor")
    public String TestD(){
        int i = 10/0;
        return "******testD";
    }

    @GetMapping("/testHot")
    @SentinelResource(value = "testHot",blockHandlerClass = MyHandler.class,blockHandler = "blockHandlerHot")
    public String testHot(@RequestParam(value = "p1",required = false) String p1,
                          @RequestParam(value = "p1",required = false)String p2){
        return "测试热点规则";

    }

    public String blockHandler(BlockException ex){
        System.out.println("blockHandler");
        return "访问太过频繁，请稍后再试。ex:"+ex;
    }

    public String blockHandlerEroor(BlockException ex){
        return "访问出错，请稍后再试。ex:"+ex;
    }
    /*public String blockHandlerHot(String p1,String p2,BlockException ex){
        return "该商品访问过于频繁，请稍后再试。ex:"+ex;
    }*/
}
