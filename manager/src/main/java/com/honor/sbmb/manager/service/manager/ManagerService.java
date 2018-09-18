package com.honor.sbmb.manager.service.manager;

import com.honor.sbmb.base.model.manager.ManagerModel;
import com.honor.sbmb.base.model.user.UserModel;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by xiagz
 * Date:2018/9/17
 * <p>
 * 添加用户
 */
public interface ManagerService {
    /**
     * 添加管理员用户
     *
     * @param managerModel
     * @return
     */
    int add(ManagerModel managerModel);

    /**
     * 登录
     * @param response
     * @param managerModel
     * @return
     */
    ManagerModel login(HttpServletResponse response, ManagerModel managerModel);
}
