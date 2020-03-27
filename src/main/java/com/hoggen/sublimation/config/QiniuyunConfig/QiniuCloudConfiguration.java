package com.hoggen.sublimation.config.QiniuyunConfig;


import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class QiniuCloudConfiguration {

    @Value("${qiniu-cloud.access-key}")
    private String accessey;

    @Value("${qiniu-cloud.secret-key}")
    private String secretKey;

    @Bean(name = "uploadManager")
    public UploadManager uploadManager() {
        // 构造一个带指定Zone对象的配置类
        com.qiniu.storage.Configuration cfg = new com.qiniu.storage.Configuration();
        // ...其他参数参考类注释
        return new UploadManager(cfg);
    }

    @Bean
    public Auth auth() {
        return Auth.create(accessey, secretKey);
    }
}
