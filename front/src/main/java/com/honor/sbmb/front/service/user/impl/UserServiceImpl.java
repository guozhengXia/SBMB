package com.honor.sbmb.front.service.user.impl;

import com.alibaba.fastjson.JSONObject;
import com.honor.sbmb.base.model.user.UserModel;
import com.honor.sbmb.base.service.token.TokenService;
import com.honor.sbmb.base.service.token.impl.TokenServiceImpl;
import com.honor.sbmb.base.service.user.UserBaseService;
import com.honor.sbmb.front.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by xiagz
 * Date:2018/9/17
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserBaseService userBaseService;

    @Autowired
    TokenService tokenService;


    @Override
    public int register(UserModel userModel) {
        UserModel userModelTemp = new UserModel();
        userModelTemp.setName(userModel.getName());
        List<UserModel> userModelList = userBaseService.queryList(userModelTemp);
        if(userModelList != null && userModelList.size() > 0){
            return 1;//该用户已经存在
        }
        userModelTemp.setPassword(userModel.getPassword());
        userBaseService.insert(userModelTemp);
        return 2;//表示插入成功
    }

    @Override
    public UserModel login(HttpServletResponse response, UserModel userModel) {
        //注意：现在的查询条件只有nickname和password，但不能保证managerModel中只有这两个字段，所以需要创建新的ManagerModel对象。
        UserModel userModelTemp = new UserModel();
        userModelTemp.setName(userModel.getName());
        userModelTemp.setPassword(userModelTemp.getPassword());
        UserModel userModelNew = userBaseService.queryOneByConditions(userModelTemp);
        if(userModelNew == null){
            return null;
        }

        String jsonStr = JSONObject.toJSONString(userModelNew);
        String token = tokenService.generateToken(jsonStr, TokenServiceImpl.TOKEN_TYPE_FRONT);

        //登录成功，向客户端通过header的形式返回token值
        response.addHeader("Authorization", token);

        return userModelNew;
    }
}
