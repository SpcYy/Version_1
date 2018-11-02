package com.service;

import com.bean.Menu;
import com.bean.Middle;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface MenuService {
    int deleteByPrimaryKey(Integer menuid);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer menuid);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    //根据角色id查询菜单集合
    public List<Menu> findbyroleid(int roleid);

    //查询全部菜单
    public List<Menu> getall();

    //分页查询上级菜单
    PageInfo getall2(int index,int size,int upmenuid);

    //
    List getall3();

    //删除目录
    int deletebyid(int menuid);

    //批量删除需要用到的方法
    int deletebyids(String[] ids);

}