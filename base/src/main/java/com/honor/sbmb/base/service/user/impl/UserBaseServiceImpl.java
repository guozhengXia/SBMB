package com.honor.sbmb.base.service.user.impl;

import com.honor.sbmb.base.dao.base.BaseDao;
import com.honor.sbmb.base.dao.user.UserDao;
import com.honor.sbmb.base.model.user.UserModel;
import com.honor.sbmb.base.service.base.impl.BaseServiceImpl;
import com.honor.sbmb.base.service.user.UserBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiagz
 * Date:2018/9/17
 */
@Service
public class UserBaseServiceImpl extends BaseServiceImpl<UserModel> implements UserBaseService {

    @Autowired
    UserDao userDao;
    @Override
    protected BaseDao<UserModel> getBaseDao() {
        return userDao;
    }
}
