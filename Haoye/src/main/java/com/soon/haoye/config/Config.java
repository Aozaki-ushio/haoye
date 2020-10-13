package com.soon.haoye.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;

/**
 * Created by Cici on 2017/8/30 0030.
 */
@Configuration
public class Config extends WebMvcConfigurationSupport {



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);

        if (!registry.hasMappingForPattern("/static/**")) {
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        }

        if (!registry.hasMappingForPattern("/favicon.ico")) {
            registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static/favicon.ico");
        }
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        //registry.addViewController("/template").setViewName("template");
        registry.addViewController("/status").setViewName("status");
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate template = new RestTemplate();
        template.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        return template;
    }
}
