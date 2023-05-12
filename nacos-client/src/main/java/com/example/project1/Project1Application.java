package com.example.project1;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.Event;
import com.alibaba.nacos.api.naming.listener.EventListener;
import com.alibaba.nacos.api.naming.listener.NamingEvent;
import com.baomidou.mybatisplus.autoconfigure.IdentifierGeneratorAutoConfiguration;
import org.apache.http.conn.util.InetAddressUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.Properties;
import java.util.concurrent.Executor;


// 如果不需要使用数据库则排除DataSource的自动配置，否则会去读取相关配置项，未配置则报错
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Project1Application {

    public static void main(String[] args) {
        SpringApplication.run(Project1Application.class, args);


        // 最原始  通过nacos-client来获取配置、注册/发现服务
        try {
            // 1 作为配置中心的相关操作
            // 1.1读取配置 此方式获取的配置不会自动刷新
            Properties properties = new Properties();
            properties.setProperty("serverAddr","127.0.0.1:8848");
            // 指定命名空间
            properties.setProperty("namespace","f73f4940-9b74-404e-944f-fc14137426ef");
            ConfigService configService = NacosFactory.createConfigService(properties);
            String content = configService.getConfig("test","dev", 20000);
            System.out.println(content);

            // 发布配置
            // configService.publishConfig()
            // 删除配置
            // configService.removeConfig();

            // 1.2添加监听器读取配置
            // 此时更新配置之后，监听器receiveConfigInfo会收到新的配置信息
            configService.addListener("test", "dev", new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }
                @Override
                public void receiveConfigInfo(String configInfo) {
                    System.out.println("recieve:" + configInfo);
                }
            });
            // 删除监听
            // configService.removeListener();

            // 测试让主线程不退出，因为订阅配置是守护线程，主线程退出守护线程就会退出。 正式代码中无需下面代码
            // while (true) {
            //     try {
            //         Thread.sleep(1000);
            //     } catch (InterruptedException e) {
            //         e.printStackTrace();
            //     }
            // }

            // 2.服务发现/服务注册
            // 2.1 注册
            NamingService naming = NamingFactory.createNamingService(properties);
            // 集群名称clusterName默认为DEFAULT
            naming.registerInstance("proj1", "127.0.0.1",8080);
            // 2.2 注销
            // naming.deregisterInstance("proj1", "127.0.0.1", 8080, "DEFAULT");

            // 2.2 获取
            System.out.println(naming.getAllInstances("proj1"));

            // 2.3 监听服务下的实例列表变化
            naming.subscribe("proj1", event -> {
                if (event instanceof NamingEvent) {
                    System.out.println("subscribe===========");
                    System.out.println(((NamingEvent) event).getServiceName());
                    System.out.println(((NamingEvent) event).getInstances());
                }
            });

            naming.registerInstance("proj1", "127.0.0.1",8080);
            naming.registerInstance("proj1", "127.0.0.2",8080);





        } catch (NacosException e) {
            e.printStackTrace();
        }

    }
    


}
