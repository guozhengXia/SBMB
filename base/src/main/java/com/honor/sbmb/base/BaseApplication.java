package com.honor.sbmb.base;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by xiagz
 * Date:2018/9/17
 * <p>
 * base项目作为操作dao和前后端共用的业务逻辑，不单独使用，此时写项目启动类是为了对dao的单元测试。
 */
@SpringBootApplication
@MapperScan("com.honor.sbmb.base.dao")
public class BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }
}
