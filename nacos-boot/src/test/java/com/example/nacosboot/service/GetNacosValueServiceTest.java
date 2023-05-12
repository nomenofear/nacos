package com.example.nacosboot.service;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.example.nacosboot.NacosBootApplication;
import com.example.nacosboot.config.NacosDatasourceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <P><B>Description: TODO 添加描述 </B></P>
 * Revision Trail: (Date/Author/Description)
 * 2023/5/12 Roger Li CREATE
 *
 * @author Roger Li
 * @version 1.0
 */

@SpringBootTest(classes = NacosBootApplication.class)
@RunWith(SpringRunner.class)
public class GetNacosValueServiceTest {

    @Autowired
    private NacosDatasourceConfig nacosDatasourceConfig;

    @Value("${spring.datasource.url:error}")
    private String url2;

    @Test
    public void te() {
        System.out.println(nacosDatasourceConfig);
        System.out.println(url2);
    }

}