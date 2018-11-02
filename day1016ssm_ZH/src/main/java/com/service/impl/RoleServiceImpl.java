package com.service.impl;

import com.bean.Role;
import com.dao.RoleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Override
    public int deleteByPrimaryKey(Integer roleid) {
        return 0;
    }

    @Override
    public int insert(Role record) {
        return 0;
    }

    @Override
    public int insertSelective(Role record) {
        return 0;
    }

    @Override
    public Role selectByPrimaryKey(Integer roleid) {
        return roleMapper.selectByPrimaryKey(roleid);
    }

    @Override
    public int updateByPrimaryKeySelective(Role record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Role record) {
        return 0;
    }

    @Override
    //分页查询
    public PageInfo getallrole(int index, int size) {
        PageHelper.startPage(index,size);
        List list = roleMapper.getallrole();
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public int addrole(Role r, int[] menus) {
        //1.新增到角色
        roleMapper.insert(r);

        //2.新增中间表
        Map map = new HashMap();
        map.put("roleid",r.getRoleid());
        map.put("menuids",menus);
        int k2 = roleMapper.insertMiddle(map);
        return k2;
    }

    @Override
    @Transactional
    public int update(Role r, int[] ids) {
        int k=0;

        try {
            //修改角色表
            roleMapper.updateByPrimaryKey(r);
            //修改中间表（先删后增）
            roleMapper.deletemiddlebyrid(r.getRoleid());

            Map map =new HashMap();
            map.put("roleid",r.getRoleid());
            map.put("menuids",ids);
            roleMapper.insertMiddle(map);
            k=1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return k;
    }

    @Override
    public int deleterole(int roleid) {
        //先判断该角色下是否有用户
        int count = roleMapper.findusercountbyroleid(roleid);
        if (count>0){
            return 0;
        }
        //先删除中间表
        roleMapper.deletemiddlebyrid(roleid);

        //再删除角色
        roleMapper.deleteByPrimaryKey(roleid);
        return 1;
    }
}
