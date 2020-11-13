package com.galaxy.galaxyblog.config;

import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.Configuration;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2020/11/12
 */
@Configuration
public class MyBeanPostProcessorConfig implements MergedBeanDefinitionPostProcessor {
    @Override
    public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {

    }
}
