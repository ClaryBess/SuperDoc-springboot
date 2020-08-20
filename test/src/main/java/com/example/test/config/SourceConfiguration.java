package com.example.test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 文件读取配置
 */
@Configuration
public class SourceConfiguration extends WebMvcConfigurerAdapter {
    @Value("${UPLOADFILE_PATH}")
    String UPLOADFILE_PATH;
      @Override
      public void addResourceHandlers(ResourceHandlerRegistry registry) {
          String os = System.getProperty("os.name");
          if(os.toLowerCase().startsWith("win")){
              registry.addResourceHandler("/file/**").addResourceLocations("file:"+UPLOADFILE_PATH);
          }
          else{
              registry.addResourceHandler("/file/**").addResourceLocations("file:/home/ubuntu/images/");
          }
          System.out.println("文件上传目录："+UPLOADFILE_PATH);
          super.addResourceHandlers(registry);
      }
    }
