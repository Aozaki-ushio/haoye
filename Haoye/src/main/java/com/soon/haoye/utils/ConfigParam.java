package dmzsmos.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
public class ConfigParam {

    private static ConfigParam instance = new ConfigParam();

    @Bean
    public static ConfigParam getInstance() {
        return instance;
    }

    @Value("${uyun.baseurl:}")
    private String baseUrl;

    @Value("${app.en-name:}")
    private String appEnName;

    @Value("${app.zh-name:}")
    private String appZhName;

    @Value("${app.test-addr:}")
    private String testAddr;

    @Value("${spring.profiles.active}")
    private String env;

    public String getBaseUrl() {
        return baseUrl == null || baseUrl.equals("") ? getTestAddr() : baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getAppEnName() {
        return appEnName;
    }

    public void setAppEnName(String appEnName) {
        this.appEnName = appEnName;
    }

    public String getAppZhName() {
        return appZhName;
    }

    public void setAppZhName(String appZhName) {
        this.appZhName = appZhName;
    }

    public String getTestAddr() {
        return testAddr;
    }

    public void setTestAddr(String testAddr) {
        this.testAddr = testAddr;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

}
