package com.honor.sbmb.base.service.token.impl;

import com.alibaba.fastjson.JSONObject;
import com.honor.sbmb.base.model.manager.ManagerModel;
import com.honor.sbmb.base.model.user.UserModel;
import com.honor.sbmb.base.redis.RedisDao;
import com.honor.sbmb.base.service.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Created by xiagz
 * Date:2018/7/17
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    RedisDao redisDao;

    /**
     * token有效期，默认2个小时
     */
    public static final long TOKEN_OUT_TIME_MANAGER = 1000L * 60 * 60 * 2;
    public static final long TOKEN_OUT_TIME_FRONT = 1000L * 60 * 60 * 24 * 30;//前端token有效期30天

    public static final String TOKEN_TYPE_MANAGER ="manager";//后端token的类型
    public static final String TOKEN_TYPE_FRONT ="front";//前端token的类型




    /**
     * 保存token，自定义有效期
     *
     * @param jsonModel
     * @param type 类型，包括manager，front。防止使用前端的token登录后端的系统
     * @return
     */
    public String generateToken(String jsonModel, String type) {
        String token = "Bearer " + type+"_"+UUID.randomUUID().toString() + "_" + DigestUtils.md5DigestAsHex((jsonModel).getBytes()) + "_" + System.currentTimeMillis();
        if(StringUtils.equals(TOKEN_TYPE_MANAGER,type)){//后端token
            redisDao.set(token, jsonModel, TOKEN_OUT_TIME_MANAGER);
        }else if(StringUtils.equals(TOKEN_TYPE_FRONT,type)){//前端token
            redisDao.set(token, jsonModel, TOKEN_OUT_TIME_FRONT);
        }else {
            //type只支持manager和front，否则报异常
            throw new RuntimeException("");
        }
        return token;
    }



    /**
     * 判断token是否存在，没有存则不存在，过期也是不存在.
     *
     * 如果存在则重置失效期
     *
     * @param token
     * @return
     */
    public boolean exists(String token) {
        return redisDao.exists(token);
    }

    public void resetExpireTime(String token,long expireTime){
        redisDao.resetExpireTime(token,expireTime);
    }

    /**
     * 根据token得到用户信息
     *
     * @param token
     * @return 返回json格式的用户对象
     */
    public String getUserInfoByToken(String token) {
        Object object = redisDao.get(token);
        if (object != null) {
            return (String) object;
        }
        return null;
    }

    /**
     * 从request中获取用户信息，userinfo为空表示没有token或token过期。
     *
     * @param request
     * @return
     */
    public String getUserInfoByRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null) {
            return null;
        }
        return getUserInfoByToken(token);
    }

    @Override
    public String getNameByRequest(HttpServletRequest request, Class clazz) {
        String userInfoByRequest = getUserInfoByRequest(request);
        if(userInfoByRequest == null){
            return null;
        }
        if(clazz == ManagerModel.class){//获取管理员姓名
            ManagerModel managerModel = JSONObject.parseObject(userInfoByRequest, ManagerModel.class);
            return managerModel.getName();
        }else if(clazz == UserModel.class){//用户用户姓名
            UserModel userModel = JSONObject.parseObject(userInfoByRequest, UserModel.class);
            return userModel.getName();
        }
        return null;
    }


    /**
     * 当用户退出登录时移除token
     *
     * @param token
     * @return
     */
    public void removeToken(String token) {
        if (exists(token)) {
            redisDao.remove(token);
        }
    }

}
