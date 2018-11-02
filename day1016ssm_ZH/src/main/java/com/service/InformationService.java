package com.service;

import com.bean.Information;
import com.github.pagehelper.PageInfo;

import java.util.List;

//资料
public interface InformationService {
    int deleteByPrimaryKey(Integer informationid);

    int insert(Information record);

    int insertSelective(Information record);

    Information selectByPrimaryKey(Integer informationid);

    int updateByPrimaryKeySelective(Information record);

    int updateByPrimaryKey(Information record);

    List findall();

    PageInfo getall(int pageindex, int size, String informationname);


    PageInfo findbytypeid(int pageindex, int size, int typeid);
}