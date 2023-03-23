package io.github.lingyun666.autoconfig.knife4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * 纯配置信息
 */
//@Configuration
@EnableSwagger2WebMvc
@EnableConfigurationProperties(Knife4jProperties.class)
public class Knife4jAutoConfig {
    @Autowired
    private Knife4jProperties properties;

    @Bean
    @ConditionalOnMissingBean(name = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(new ApiInfoBuilder()
                        .description(properties.getDescription())
                        .contact(new Contact(properties.getName(), properties.getUrl(), properties.getEmail()))
                        .version("0.1")
                        .build())
                //.groupName("2.X版本") // 分组名称
                .select()
                .apis(RequestHandlerSelectors.basePackage(properties.getBasePackage()))
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}