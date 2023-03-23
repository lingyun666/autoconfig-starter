package io.github.lingyun666.autoconfig.minio;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author lingkong
 * @desc 操作minio的模板, 封装了上传方法
 * @exp 基础的MinioTemplate本应在minio的jar包中(如RedisTemplate),
 * 我们这里进行设置, 用于给各server依赖, 则可省去重复设置!
 * @since 2023-03-22
 */
public class MinioTemplate {

    private MinioProperties properties;

    public MinioTemplate(MinioProperties properties) {
        this.properties = properties;
    }

    /**
     * 构建客户端
     *
     * @return
     */
    private MinioClient getMinioClient() {
        return MinioClient.builder()
                .endpoint(properties.getEndpoint())
                .credentials(properties.getAccessKey(), properties.getSecretKey())
                .build();
    }

    public List<String> uploads(MultipartFile[] files) {
        ArrayList<String> urls = new ArrayList<>();
        for (MultipartFile file : files) {
            String url = upload(file);
            urls.add(url);
        }
        return urls;
    }

    public String upload(MultipartFile file) {
        if (file == null) {
            return null;
        }
        String originalFilename = file.getOriginalFilename();
        String fileNameAndPath = new SimpleDateFormat("yyyy/MM/dd").format(new Date())
                + "/" + UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        try {
            InputStream is = file.getInputStream();
            getMinioClient().putObject(
                    PutObjectArgs.builder()
                            .bucket(properties.getBucketName())
                            .object(fileNameAndPath) // 通过指定此处,来存放多级目录!
                            .stream(is, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return properties.getUrl() + "/" + fileNameAndPath;
    }

}