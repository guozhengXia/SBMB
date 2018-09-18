package com.honor.sbmb.base.model.manager;

import com.fasterxml.jackson.annotation.JsonView;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * Created by xiagz
 * Date:2018/9/17
 */
public class ManagerModel {

    //指定返回给前端的字段
    public interface ManagerModelView{}

    //用户id
    @JsonView(ManagerModelView.class)
    private int id;
    //用户姓名
    @JsonView(ManagerModelView.class)
    private String name;
    //密码
    private String password;
    //用户注册时间
    @JsonView(ManagerModelView.class)
    private Date createdTime;
    //用户信息更新时间
    @JsonView(ManagerModelView.class)
    private Date updatedTime;

    //校验参数
    public String checkParam(){
        //校验username参数
        if (StringUtils.isEmpty(name)) {
            return "name";
        }
        //校验password参数
        if (StringUtils.isEmpty(password)) {
            return "password";
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

}
