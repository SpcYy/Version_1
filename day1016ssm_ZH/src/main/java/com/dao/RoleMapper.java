package com.dao;

import com.bean.Role;

import java.util.List;
import java.util.Map;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    //查询所有角色
    public List<Role> getallrole();

    //添加中间表
    public int insertMiddle(Map map);

    //根据角色id删除中间表
    int deletemiddlebyrid(int rid);

    //根据角色id查询用户数
    int findusercountbyroleid(int rid);
}