/*
 * Copyright (c) 2005, 2023, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.nacosboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <P><B>Description: TODO 添加描述 </B></P>
 * Revision Trail: (Date/Author/Description)
 * 2023/5/12 Roger Li CREATE
 *
 * @author Roger Li
 * @version 1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "datasource")
public class NacosDatasourceConfig {

    private String url;

    private String username;

    private String password;

}
