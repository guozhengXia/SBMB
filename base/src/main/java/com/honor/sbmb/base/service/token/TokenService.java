package com.honor.sbmb.base.service.token;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xiagz
 * Date:2018/7/17
 */
public interface TokenService {


    /**
     * 保存token，自定义有效期
     *
     * @param jsonModel
     * @param type : 类型，包括manager，front
     * @return
     */
    String generateToken(String jsonModel,String type);

    /**
     * 判断token是否存在，没有存则不存在，过期也是不存在
     *
     * @param token
     * @return
     */
    boolean exists(String token);

    /**
     * 重置有效期
     * @param key
     * @param expireTime
     */
    void resetExpireTime(String key, long expireTime);



        /**
         * 根据token得到用户信息
         *
         * @param token
         * @return 返回json格式的用户对象
         */
    String getUserInfoByToken(String token);

    /**
     * 从request中获取用户信息，userinfo为空表示没有token或token过期。
     *
     * @param request
     * @return 返回所有用户信息
     */
    String getUserInfoByRequest(HttpServletRequest request);

    /**
     * 从request中获取用户信息，然后解析成用户名
     *
     * @param request
     * @param clazz   类型，支持ManagerModel和UserModel
     * @return 返回管理员的name
     */
    String getNameByRequest(HttpServletRequest request, Class clazz);


    /**
     * 当用户退出登录时移除token
     *
     * @param token
     * @return
     */
    void removeToken(String token);
}
