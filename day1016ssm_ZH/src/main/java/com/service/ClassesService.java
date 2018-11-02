package com.service;

import com.bean.Classes;
import com.github.pagehelper.PageInfo;

public interface ClassesService {
    //模糊查询
    public PageInfo getall(int pageindex,int size,String name,String classnum,int[] ids,String state);

    int deleteByPrimaryKey(Integer classid);

    int insert(Classes record);

    int insertSelective(Classes record);

    Classes selectByPrimaryKey(Integer classid);

    int updateByPrimaryKeySelective(Classes record);

    int updateByPrimaryKey(Classes record);
}
