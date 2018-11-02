package com.dao;

import com.bean.Menu;
import com.bean.Middle;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer menuid);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer menuid);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    //根据角色id查询菜单集合
    public List<Menu> findbyroleid(int roleid);

    //查询所有菜单
    public List<Menu> getall();

    List getall3(int mid);

    //根据menuid删除目录
    int deletebyid(int menuid);

    //根据menuid查询是否有二级目录
    int findmenubyupmenuid(int menuid);

    //根据menuid删除中间表
    int deletebymenuid(int menuid);

    //
    int deletebyids(List list);

}