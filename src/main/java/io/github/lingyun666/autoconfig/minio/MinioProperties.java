package io.github.lingyun666.autoconfig.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lingkong
 * @since 2023-01-03
 */
@Data
@ConfigurationProperties(prefix = "lingkong.db.minio")
public class MinioProperties {
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;
    /**
     * 域名或ip
     */
    private String url;
}