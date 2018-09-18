package com.honor.sbmb.front.service.user;

import com.honor.sbmb.base.model.user.UserModel;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by xiagz
 * Date:2018/9/17
 */
public interface UserService {

    /**
     * 注册
     *
     * @param userModel
     * @return
     */
    int register(UserModel userModel);

    /**
     * 登录
     *
     * @param response
     * @param userModel
     * @return
     */
    UserModel login(HttpServletResponse response, UserModel userModel);
}
