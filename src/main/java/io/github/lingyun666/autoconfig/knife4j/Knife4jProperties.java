package io.github.lingyun666.autoconfig.knife4j;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lingkong
 * @since 2023-03-22
 */
@Data
@ConfigurationProperties(prefix = "lingkong.knife4j")
public class Knife4jProperties {
    private String basePackage = "com.lingkong";
    private String name = "lingkong";
    private String url = "www.lingkong.top";
    private String email = "873983080@qq.com";
    private String description;
}