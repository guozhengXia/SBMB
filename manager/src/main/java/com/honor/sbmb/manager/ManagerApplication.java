package com.honor.sbmb.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by xiagz
 * Date:2018/9/17
 */
@SpringBootApplication(scanBasePackages = {"com.honor.sbmb.manager", "com.honor.sbmb.base"})
@MapperScan("com.honor.sbmb.base.dao")
public class ManagerApplication {
    public static void main(String[] args){
        SpringApplication.run(ManagerApplication.class,args);
    }
}
