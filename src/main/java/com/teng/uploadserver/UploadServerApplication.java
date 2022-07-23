package com.teng.uploadserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class UploadServerApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(UploadServerApplication.class, args);
    }
}
