/*
 * Copyright (c) 2005, 2023, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.nacosboot.service;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.example.nacosboot.config.NacosDatasourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * <P><B>Description: TODO 添加描述 </B></P>
 * Revision Trail: (Date/Author/Description)
 * 2023/5/12 Roger Li CREATE
 *
 * @author Roger Li
 * @version 1.0
 */
@Service
public class GetNacosValueService {

    @Autowired
    private NacosDatasourceConfig nacosDatasourceConfig;

    @NacosInjected
    private NamingService namingService;

    // 除了使用@NacosValue等注解获取配置，依旧可以注入ConfigService，编码获取配置文件
    @NacosInjected
    private ConfigService configService;

    @Value("${spring.application.name:}")
    private String appName;

    @PostConstruct
    public void discovery() throws NacosException {

        // 如果不是集成spring-cloud的组件，则不会去自动注册，需要手动调api注册
        namingService.registerInstance(appName, "127.0.0.2",8080);

        String content = configService.getConfig("datasource", null,20000);
        System.out.println(content);
    }

    public void printValue() {
        System.out.println(nacosDatasourceConfig);
    }
}
