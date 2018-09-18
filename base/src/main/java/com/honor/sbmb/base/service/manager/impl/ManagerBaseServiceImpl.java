package com.honor.sbmb.base.service.manager.impl;

import com.honor.sbmb.base.dao.base.BaseDao;
import com.honor.sbmb.base.dao.manager.ManagerDao;
import com.honor.sbmb.base.model.manager.ManagerModel;
import com.honor.sbmb.base.service.base.impl.BaseServiceImpl;
import com.honor.sbmb.base.service.manager.ManagerBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiagz
 * Date:2018/9/17
 */
@Service
public class ManagerBaseServiceImpl extends BaseServiceImpl<ManagerModel> implements ManagerBaseService {

    @Autowired
    ManagerDao managerDao;

    @Override
    protected BaseDao<ManagerModel> getBaseDao() {
        return managerDao;
    }
}
