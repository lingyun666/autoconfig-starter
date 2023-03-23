package io.github.lingyun666.autoconfig.minio;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lingkong
 * @since 2023-01-09
 */
// proxyBeanMethods: 指定@Bean注解标注对象(方法)是否使用代理，默认true:使用代理,效率较低,从IOC容器中取.
@Configuration(proxyBeanMethods = false)
//@ConditionalOnClass(MinioTemplate.class)
// 将MinioProperties.java 以具体实例形式注入到容器中
@EnableConfigurationProperties(MinioProperties.class)
public class MinioAutoConfig {

    @Bean
    //@ConditionalOnMissingBean(name = "minioTemplate")
    public MinioTemplate minioTemplate(MinioProperties properties) {
        return new MinioTemplate(properties);
    }

}