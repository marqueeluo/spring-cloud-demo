package com.luo.demo.seata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 启动类
 *
 * @author luohq
 * @date 2021-11-03
 */
@EnableDiscoveryClient
@EnableFeignClients
//@SpringBootApplication(scanBasePackages = {"com.work"}, exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

}
