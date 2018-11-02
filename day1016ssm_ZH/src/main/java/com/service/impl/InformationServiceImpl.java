package com.service.impl;

import com.bean.Information;
import com.dao.InformationMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InformationServiceImpl implements InformationService {
    @Autowired
    private InformationMapper informationMapper;
    @Override
    public int deleteByPrimaryKey(Integer informationid) {
        return 0;
    }

    @Override
    public List findall() {
        return informationMapper.findall();
    }

    @Override
    public PageInfo findbytypeid(int pageindex, int size,int typeid) {
        Map map =new HashMap();
        map.put("typeid",typeid);
        PageHelper.startPage(pageindex,size);
        List list = informationMapper.getall(map);
        PageInfo pi =new PageInfo(list);
        return pi;

    }

    @Override
    public PageInfo getall(int pageindex, int size, String informationname) {
        Map map =new HashMap();
        map.put("ifname",informationname);
        PageHelper.startPage(pageindex,size);
        List list = informationMapper.getall(map);
        PageInfo pi =new PageInfo(list);
        return pi;

    }

    @Override
    public int insert(Information record) {
        return informationMapper.insert(record);
    }

    @Override
    public int insertSelective(Information record) {
        return 0;
    }

    @Override
    public Information selectByPrimaryKey(Integer informationid) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Information record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Information record) {
        return 0;
    }
}
