package dmzsmos.utils;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class Product {

    private static String APP_REGISTRY_API = "tenant/mdos.api/v1/product/register";
    private static String APP_LOGO_REGISTRY_API = "tenant/mdos.api/v1/product/logo/save";

    private static ConfigParam cfg;
    private static RestTemplate restTemplate;
    private static ResourceLoader resourceLoader;

    @PostConstruct
    public void init() {
        cfg = ConfigParam.getInstance();
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        Product.restTemplate = restTemplate;
    }

    @Autowired
    public void setResourceLoader(ResourceLoader resourceLoader) {
        Product.resourceLoader = resourceLoader;
    }

    public static void registry() {
        if (!"dev".equalsIgnoreCase(cfg.getEnv())) {
            if (registryApp()) {
                registryAppLogo();
            }
        }
    }

    private static boolean registryApp() {
        String lowerProductName = cfg.getAppEnName().toLowerCase();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("productNum", lowerProductName);
        params.put("productName", cfg.getAppEnName());
        params.put("description", cfg.getAppZhName());
        params.put("productUrl", String.join("", "/", lowerProductName));

        return post(String.join("", cfg.getBaseUrl(), APP_REGISTRY_API), params);
    }

    private static boolean registryAppLogo() {
        String logoBase64Str = getLogoBase64Str();

        if (logoBase64Str != null) {
            String lowerProductName = cfg.getAppEnName().toLowerCase();

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("product", lowerProductName);
            params.put("fileName", String.join(".",lowerProductName, "png"));
            params.put("file", cfg.getAppZhName());

            return post(String.join("", cfg.getBaseUrl(), APP_LOGO_REGISTRY_API), params);
        }

        return false;
    }

    private static boolean post(String url, Map<String, Object> params) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(params, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                String.class
        );

        return response.getStatusCode().value() == 200;
    }

    private static String getLogoBase64Str() {
        String logoPath = System.getProperty("work.dir") + "/logo";
        try {
            byte[] bytes = IOUtils.toByteArray(resourceLoader.getResource("file:" + logoPath).getInputStream());
            return new String(bytes);
        } catch (IOException e) {
            return null;
        }
    }

}
