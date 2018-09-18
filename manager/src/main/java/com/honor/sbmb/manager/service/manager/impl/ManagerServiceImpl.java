package com.honor.sbmb.manager.service.manager.impl;

import com.alibaba.fastjson.JSONObject;
import com.honor.sbmb.base.model.manager.ManagerModel;
import com.honor.sbmb.base.model.user.UserModel;
import com.honor.sbmb.base.service.manager.ManagerBaseService;
import com.honor.sbmb.base.service.token.TokenService;
import com.honor.sbmb.base.service.token.impl.TokenServiceImpl;
import com.honor.sbmb.manager.service.manager.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by xiagz
 * Date:2018/9/17
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerBaseService managerBaseService;

    @Autowired
    TokenService tokenService;

    @Override
    public int add(ManagerModel managerModel) {
        ManagerModel managerModelTemp = new ManagerModel();
        managerModelTemp.setName(managerModel.getName());
        List<ManagerModel> userModelList = managerBaseService.queryList(managerModelTemp);
        if(userModelList != null && userModelList.size() > 0){
            return 1;//该用户已经存在
        }
        managerModelTemp.setPassword(managerModel.getPassword());
        managerBaseService.insert(managerModelTemp);
        return 2;//表示插入成功
    }

    @Override
    public ManagerModel login(HttpServletResponse response, ManagerModel managerModel) {
        //注意：现在的查询条件只有nickname和password，但不能保证managerModel中只有这两个字段，所以需要创建新的ManagerModel对象。
        ManagerModel managerModellTemp = new ManagerModel();
        managerModellTemp.setName(managerModel.getName());
        managerModellTemp.setPassword(managerModellTemp.getPassword());
        ManagerModel userModelNew = managerBaseService.queryOneByConditions(managerModellTemp);
        if(userModelNew == null){
            return null;
        }

        String jsonStr = JSONObject.toJSONString(userModelNew);
        String token = tokenService.generateToken(jsonStr, TokenServiceImpl.TOKEN_TYPE_MANAGER);

        //登录成功，向客户端通过header的形式返回token值
        response.addHeader("Authorization", token);

        return userModelNew;
    }
}
