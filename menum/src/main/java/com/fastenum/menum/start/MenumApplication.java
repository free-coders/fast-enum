package com.fastenum.menum.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages={ "com.fastenum" })
@EnableDiscoveryClient
public class MenumApplication {

    public static void main(String[] args) {
        SpringApplication.run( MenumApplication.class, args );
    }

}
