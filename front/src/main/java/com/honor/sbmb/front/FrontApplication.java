package com.honor.sbmb.front;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by xiagz
 * Date:2018/9/13
 */
@SpringBootApplication(scanBasePackages = {"com.honor.sbmb.front", "com.honor.sbmb.base"})
@MapperScan("com.honor.sbmb.base.dao")
public class FrontApplication {
    public static void main(String[] args){
        SpringApplication.run(FrontApplication.class,args);
    }
}
