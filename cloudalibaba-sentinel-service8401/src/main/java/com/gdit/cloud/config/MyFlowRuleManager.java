package com.gdit.cloud.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.CircuitBreakerStrategy;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowItem;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class MyFlowRuleManager {

    @Bean
    private static void initFlowQpsRule() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule1 = new FlowRule();
        rule1.setResource("testA");
        // Set max qps to 20
        rule1.setCount(1);
        rule1.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule1.setLimitApp("default");
        rules.add(rule1);
        FlowRuleManager.loadRules(rules);
    }
    @Bean
    private static void initDegradeRule() {
        System.out.println("ERROR_RATIO");
        List<DegradeRule> rules = new ArrayList<>();
       /* DegradeRule rule = new DegradeRule("testC");
        rule.setGrade(CircuitBreakerStrategy.SLOW_REQUEST_RATIO.getType());//熔断模式*/
        DegradeRule rule = new DegradeRule("TestD");
//        rule.setGrade(CircuitBreakerStrategy.ERROR_RATIO.getType());//熔断模式
        rule.setGrade(CircuitBreakerStrategy.ERROR_COUNT.getType());//熔断模式
        rule.setCount(5); // 慢调用临界 RT对应的阈值
        rule.setMinRequestAmount(10)//熔断触发的最小请求数，请求数小于该值时即使异常比率超出阈值也不会熔断（1.7.0 引入）
                .setStatIntervalMs(10000)//统计时长（单位为 ms），如 60*1000 代表分钟级（1.8.0 引入）
                .setTimeWindow(3);//熔断时长
                //.setSlowRatioThreshold(0.5);// RT模式慢请求率阈值
        rules.add(rule);
        DegradeRuleManager.loadRules(rules);
    }
//    热点参数规则
    @Bean
    private static void initParamFlowRules() {
        // QPS mode, threshold is 5 for every frequent "hot spot" parameter in index 0 (the first arg).
        ParamFlowRule rule = new ParamFlowRule("testHot")
                .setParamIdx(0)
                .setGrade(RuleConstant.FLOW_GRADE_QPS)
                .setDurationInSec(3L)
                //.setDurationInSec(3)
                .setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_RATE_LIMITER)
                .setMaxQueueingTimeMs(600)
                .setCount(1);

        // We can set threshold count for specific parameter value individually.
        // Here we add an exception item. That means: QPS threshold of entries with parameter `PARAM_B` (type: int)
        // in index 0 will be 10, rather than the global threshold (5).
        //当当前参数的值等于5是，它的阈值是100，应用场景，vip用户
        ParamFlowItem item = new ParamFlowItem("5",100,String.class.getName())/*.setObject(String.valueOf("p2"))
                .setClassType(int.class.getName())
                .setCount(1)*/;
        rule.setParamFlowItemList(Collections.singletonList(item));
        ParamFlowRuleManager.loadRules(Collections.singletonList(rule));
    }

}
