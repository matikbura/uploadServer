package com.teng.uploadserver.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class aa implements EnvironmentPostProcessor{
    //指定配置文件的路径
    private static final String LOCATION = "/usr/local/images/config/application.properties";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment configurableEnvironment, SpringApplication springApplication) {
        File file = new File(LOCATION);
//        File file = new File(System.getProperty("user.home"), LOCATION);
//        System.out.println("user.home" + System.getProperty("user.home"));
        if (file.exists()) {
            MutablePropertySources propertySources = configurableEnvironment.getPropertySources();
//            System.out.println("Loading local settings from " + file.getAbsolutePath());
            Properties properties = loadProperties(file);
//            System.out.println(properties.toString());
            propertySources.addFirst(new PropertiesPropertySource("Config", properties));
        }
    }

    private Properties loadProperties(File f) {
        FileSystemResource resource = new FileSystemResource(f);
        try {
            return PropertiesLoaderUtils.loadProperties(resource);
        }
        catch (IOException ex) {
            throw new IllegalStateException("Failed to load local settings from " + f.getAbsolutePath(), ex);
        }
    }
}

