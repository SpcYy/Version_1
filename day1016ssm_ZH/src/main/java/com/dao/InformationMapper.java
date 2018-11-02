package com.dao;

import com.bean.Information;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface InformationMapper {
    int deleteByPrimaryKey(Integer informationid);

    int insert(Information record);

    int insertSelective(Information record);

    Information selectByPrimaryKey(Integer informationid);

    int updateByPrimaryKeySelective(Information record);

    int updateByPrimaryKey(Information record);

    List findall();

    List getall(Map map);

    List findbytypeid(int typeid);
}