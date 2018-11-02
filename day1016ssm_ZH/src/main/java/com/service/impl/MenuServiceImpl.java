package com.service.impl;

import com.bean.Menu;
import com.bean.Middle;
import com.dao.MenuMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    //手动控制事务
    @Autowired
    private DataSourceTransactionManager tx;
    @Override
    public int deleteByPrimaryKey(Integer menuid) {
        return 0;
    }

    @Override
    @Transactional
    public int insert(Menu record) {

        return menuMapper.insert(record);
    }

    @Override
    public int insertSelective(Menu record) {
        return 0;
    }

    @Override
    public Menu selectByPrimaryKey(Integer menuid) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Menu record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Menu record) {
        return 0;
    }

    @Override
    public List<Menu> getall() {
        //调取dao层的查询方法
        List<Menu> list =menuMapper.getall();
        //分级
        List newlist = new ArrayList();//用来存储分级之后的menu
        for (Menu menu : list) {
            if (menu.getUpmenuid()==-1){
                List menus = new ArrayList();
                for (Menu menu2 : list) {
                    if (menu2.getUpmenuid()==menu.getMenuid()){
                        menus.add(menu2);
                    }

                }
                menu.setSeconds(menus);
                newlist.add(menu);
            }
        }
        return newlist;
    }

    @Override
    public PageInfo getall2(int index, int size, int upmenuid) {
        PageHelper.startPage(index,size);
        List<Menu> list = menuMapper.getall();
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    //批量删除逻辑
    @Override
    public int deletebyids(String[] ids) {
        DefaultTransactionDefinition ddf=new DefaultTransactionDefinition();
        TransactionStatus status =tx.getTransaction(ddf);

        try {
            //1.先将被删除的菜单分类
            List<Integer> first=new ArrayList();//用来保存以及目录
            List second=new ArrayList();//用来保存二级目录
            for (String id : ids) {
                int index =id.indexOf("-");//得到-的位置
                int upid = Integer.parseInt(id.substring(index+1));
                int mid=Integer.parseInt(id.substring(0,index));
                if (upid==-1){
                    first.add(mid);
                }else {
                    second.add(mid);
                }
            }
            //2.先删除二级菜单，然后再判断一级菜单
            if (first.size()==ids.length){//全是一级菜单
                //判断对应的二级菜单全都不存在可以删除
                for (Integer id : first) {
                    List list=menuMapper.getall3(id);
                    if (list.size()>0){
                        //不能删，抛出异常，事务回滚
                        throw new Exception("存在二级目录，无法删除");
                    }else{
                        //可以删除
                        menuMapper.deleteByPrimaryKey(id);
                    }
                }

            }else {
                //包含了二级菜单
                if (second.size()==ids.length){//全是二级菜单,可以直接删除
                    menuMapper.deletebyids(second);
                }else {
                    //先删除二级菜单
                    menuMapper.deletebyids(second);
                    //再判断一级菜单是否还有子菜单
                    for (Integer id : first) {
                        List list=menuMapper.getall3(id);
                        if (list.size()>0){
                            //不能删，抛出异常，事务回滚
                            throw new Exception("存在二级目录，无法删除");
                        }else{
                            //可以删除
                            menuMapper.deleteByPrimaryKey(id);
                        }
                    }
                }
            }
            //全部执行，无异常，提交事务
            tx.commit(status);
        } catch (Exception e) {
            //e.printStackTrace();
            //捕捉异常
            tx.rollback(status);
        }

        return 0;
    }

    @Override
    public List getall3() {
        return menuMapper.getall3(-1);
    }

    @Override
    public int deletebyid(int menuid) {
        //判断删除的菜单有没有二级目录
        int k = menuMapper.findmenubyupmenuid(menuid);
        if(k>0) {
            return 0;
        }

        //先根据menuid删除中间表
        menuMapper.deletebymenuid(menuid);

        //在删除该目录
        menuMapper.deleteByPrimaryKey(menuid);
        return 1;
    }


    @Override
    public List<Menu> findbyroleid(int roleid) {
        return menuMapper.findbyroleid(roleid);
    }
}
