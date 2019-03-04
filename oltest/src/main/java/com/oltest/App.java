package com.oltest;

//import com.horizon.storage.StorageProperties;
//import com.horizon.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 *
 */
//@SpringBootApplication
//注解的作用是@ConfigurationProperties注解生效。如果只配置@ConfigurationProperties注解，在IOC容器中是获取不到properties配置文件转化的bean的
//@EnableConfigurationProperties(StorageProperties.class)
//public class App
//{
//    public static void main( String[] args )
//    {
//        SpringApplication.run(App.class, args);
//    }
//
//    @Bean
//    CommandLineRunner init(StorageService storageService) {
//        return (args) -> {
//            storageService.deleteAll();
//            storageService.init();
//        };
//    }
//}
