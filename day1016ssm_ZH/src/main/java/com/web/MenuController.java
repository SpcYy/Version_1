package com.web;

import com.bean.Menu;
import com.bean.Middle;
import com.github.pagehelper.PageInfo;
import com.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/power/menu/getmenus")
    public String getmenus(@RequestParam(defaultValue = "1")int index, @RequestParam(defaultValue = "5")int size,
                           ModelMap map){
        PageInfo pageInfo = menuService.getall2(index,size,-10);
        map.put("pi",pageInfo);
        return "/power/menu/list";
    }

    //查询一级菜单
    @RequestMapping("/power/menu/getupmenu")
    public String getup(ModelMap map){
        List list = menuService.getall3();
        map.put("uplist",list);

        return "/power/menu/add";
    }

    //添加菜单
    @RequestMapping("/power/menu/addmenu")
    public String add(Menu menu){
        menuService.insert(menu);
        return "redirect:/power/menu/getmenus";
    }

    //删除
    @RequestMapping("/power/menu/deletemenu")
    public void delete(int menuid, HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8");

        try {
            PrintWriter out = response.getWriter();
            int k = menuService.deletebyid(menuid);
            if(k>0){
                out.print("<script>alert('删除成功');location.href='/power/menu/getmenus';</script>");
            }else {
                out.print("<script>alert('删除失败，请先删除菜单下的目录');location.href='/power/menu/getmenus';</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //批量删除
    @RequestMapping("/power/menu/deleteall")
    public String deleteall(String[] menuids){
        menuService.deletebyids(menuids);

        return "redirect:/power/menu/getmenus";
    }

}
