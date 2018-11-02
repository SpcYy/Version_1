package com.service;

import com.bean.UserTb;

import java.util.List;

public interface UserTbService {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserTb record);

    int insertSelective(UserTb record);

    UserTb selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserTb record);

    int updateByPrimaryKey(UserTb record);

    //登录
    UserTb login(UserTb userTb);

    //根据班级mid，学院did，角色名，查询老师
    List findall(int did,int mid,String rolename);
}
