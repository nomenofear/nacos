package com.example.nacosboot;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.Properties;

@SpringBootApplication
@NacosPropertySource(dataId = "datasource",  autoRefreshed = true, type = ConfigType.YAML)
public class NacosBootApplication {


    public static void main(String[] args) {
        SpringApplication.run(NacosBootApplication.class, args);

    }

}
