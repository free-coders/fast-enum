package com.fastenum.fenum.start;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 服务启动类
 */
@SpringBootApplication(scanBasePackages={"com.fastenum.fenum"})
@MapperScan("com.fastenum.fenum.mybatis")
public class FenumApplication {

    public static void main(String[] args) {
        SpringApplication.run( FenumApplication.class, args );
    }

}
