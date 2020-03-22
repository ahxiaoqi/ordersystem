package com.orderSystem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ahxiaoqi
 * @date 2020/3/21 20:59
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${uploadUrl}")
    private String uploadUrl;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/AWyOfhUI/buyMore/uploadPath/**").addResourceLocations(uploadUrl);
        registry.addResourceHandler("/eryTuUd4/dev/uploadPath/**").addResourceLocations(uploadUrl);
        registry.addResourceHandler("/AWyOfhUI/uploadPath/**").addResourceLocations(uploadUrl);
        registry.addResourceHandler("/eryTuUd4/uploadPath/**").addResourceLocations(uploadUrl);
    }

}
