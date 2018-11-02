package com.service.impl;

import com.bean.Menu;
import com.bean.Role;
import com.bean.UserTb;
import com.dao.MenuMapper;
import com.dao.UserTbMapper;
import com.service.MenuService;
import com.service.UserTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserTbServiceImpl implements UserTbService {

    @Autowired
    private UserTbMapper userTbMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private MenuService menuService;
    @Override
    public int deleteByPrimaryKey(Integer userId) {
        return 0;
    }

    @Override
    public int insert(UserTb record) {
        return 0;
    }

    @Override
    public int insertSelective(UserTb record) {
        return 0;
    }

    @Override
    public UserTb selectByPrimaryKey(Integer userId) {

        return userTbMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(UserTb record) {
        return userTbMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserTb record) {
        return 0;
    }

    @Override

    public UserTb login(UserTb userTb) {
       UserTb userTb1 =  userTbMapper.login(userTb.getUserName());
       if(userTb1!=null && userTb1.getUserPs().equals(userTb.getUserPs())){
           //判断用户的状态为1是启用（可以登录），0禁用（不能登录）
           if (userTb1.getRole().getRolestate()==1){
               //获取role的信息
               Role role = userTb1.getRole();
               //根据角色id查询所对应的菜单集合
               List<Menu> list = menuMapper.findbyroleid(role.getRoleid());
               //将所有菜单分类
               //遍历菜单查找上级目录
               List fenjimenu = new ArrayList();
               for (Menu menu : list) {
                   //如果upmenuid为-1就说明是上级目录
                   if (menu.getUpmenuid()==-1){
                       List<Menu> newlist = new ArrayList();//只保存二级菜单
                       for (Menu menu2 : list) {
                           //子菜单的上级菜单id等于上级菜单的id，保存
                           if (menu2.getUpmenuid()==menu.getMenuid()){
                               newlist.add(menu2);
                           }
                       }
                       //保存二级目录
                       menu.setSeconds(newlist);
                       //保存分级目录
                       fenjimenu.add(menu);
                   }
               }
            //将分级完成后的菜单赋值给角色
           role.setMenus(fenjimenu);
           //将角色赋给用户
           userTb1.setRole(role);
           //登录次数
           userTb1.setLogincount(userTb1.getLogincount()+1);
           userTbMapper.updateByPrimaryKeySelective(userTb1);

           //管理人
           UserTb manager =userTbMapper.selectByPrimaryKey(userTb1.getManagerid());
           userTb1.setManager(manager);
           return userTb1;
           }
       }
        return null;
    }

    @Override
    public List findall(int did, int mid, String rolename) {
        Map map = new HashMap();
        map.put("did",did);
        map.put("mid",mid);
        map.put("rolename",rolename);
        return userTbMapper.findall(map);
    }
}
