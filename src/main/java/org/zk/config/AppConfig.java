package org.zk.config;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangkang
 * @date 2023/7/13 14:37
 */
@Configuration
public class AppConfig {

    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }

//    @Bean
    public CommandLineRunner commandLineRunner() {
        // 初始化sentinel配置
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                List<FlowRule> rules = new ArrayList<>();
                FlowRule rule = new FlowRule();
                rule.setResource("hello");
                rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
                // Set limit QPS to 20.
                rule.setCount(2);
                rules.add(rule);
                FlowRuleManager.loadRules(rules);
            }
        };
    }
}
