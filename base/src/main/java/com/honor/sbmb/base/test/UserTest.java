package com.honor.sbmb.base.test;

import com.alibaba.fastjson.JSONObject;
import com.honor.sbmb.base.BaseApplication;
import com.honor.sbmb.base.model.book.BookModel;
import com.honor.sbmb.base.model.user.UserModel;
import com.honor.sbmb.base.service.book.BookBaseService;
import com.honor.sbmb.base.service.user.UserBaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by xiagz
 * Date:2018/9/17
 * <p>
 * 单元测试类，测试User的dao
 * <p>
 * 注意：单元测试同样会启动项目，加载所有配置文件。优点是：只需要点击一次，自动启动项目，自动关闭项目，自动执行方法中的代码。不需要使用浏览器或postman请求接口
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseApplication.class)//必须指定一个项目启动类
public class UserTest {

    @Autowired
    UserBaseService userBaseService;
    @Autowired
    BookBaseService bookBaseService;

    //测试，右击该方法即可执行
    @Test
    public void queryList() throws Exception {
        List<BookModel> userModels = bookBaseService.queryList(new BookModel());
        int count = userModels.size();
        System.out.println(JSONObject.toJSONString(userModels));
    }
}
