package com.web;

import cn.com.webxml.MobileCodeWSSoap;
import com.bean.UserTb;
import com.service.UserTbService;
import com.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Controller
@SessionAttributes({"u1","logintime"})
public class UserTbController {
    @Autowired
    private UserTbService service;

    @Autowired
    private MobileCodeWSSoap soap;

    @RequestMapping("/login")

    public void login(HttpServletResponse resp, UserTb userTb,
                      String DropExpiration, ModelMap map,
                      String yanzheng){
        System.out.println(userTb.getUserName()+"..."+userTb.getUserPs());
        String yz= ImageUtil.getCode();
        resp.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out=resp.getWriter();
           if(yz.equals(yanzheng)){
                UserTb u=service.login(userTb);


                if(u==null){
                    out.print("<script>alert('用户名或密码不正确');location.href='login.jsp'</script>");
                }else{
                    map.put("u1",u);
                    Cookie c=new Cookie("uname",userTb.getUserName());
                    if (DropExpiration.equals("Day")){
                        c.setMaxAge(24*60*60);
                    }else if(DropExpiration.equals("Month")){
                        c.setMaxAge(24*60*60*31);
                    }else if(DropExpiration.equals("Year")){
                        c.setMaxAge(24*60*60*365);
                    }
                    resp.addCookie(c);
                    //登录时间
                    Date date=new Date();
                    map.put("logintime",date);
                    out.print("<script>alert('登录成功');location.href='index.jsp'</script>");
                }

            }else{
                out.print("<script>alert('验证码错误');location.href='login.jsp'</script>");
           }
        } catch (IOException e) {
           e.printStackTrace();
        }
  }

    @RequestMapping("/user/updateuser")
    public void update(HttpServletResponse response,UserTb userTb,ModelMap map){
        System.out.println(userTb.getUserId());
        int k = service.updateByPrimaryKeySelective(userTb);
        response.setContentType("text/html;charset=utf-8");

        try {
            PrintWriter out = response.getWriter();
            if (k>0){
                UserTb u = service.selectByPrimaryKey(userTb.getUserId());
                map.put("u1",u);
                out.print("<script>alert('修改成功');top.location.href='/index.jsp'</script>");
            }else {
                out.print("<script>alert('修改失败');top.location.href='MyUser.jsp'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/user/checkpass")

    //使用validate验证表单
    public void checkpass(HttpServletResponse response,ModelMap map,String yuanpass){
        UserTb userTb = (UserTb)map.get("u1");
        response.setContentType("text/html;charset=utf-8");

        try {
            PrintWriter out = response.getWriter();

            if (userTb.getUserPs().equals(yuanpass)){
                out.print(true);
            }else {
                out.print(false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/user/updatepass")
    public void updatepass(HttpServletResponse response, UserTb userTb, SessionStatus status){
       int k =  service.updateByPrimaryKeySelective(userTb);
       response.setContentType("text/html;charset=utf-8");

        try {
            PrintWriter out = response.getWriter();
            if (k>0){
                status.setComplete();
                out.print("<script>alert('修改成功');top.location.href='/login.jsp'</script>");
            }else{
                out.print("<script>alert('修改失败');location.href='password.jsp'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //归属地查询
    @RequestMapping("/getaddress")
    public void phone(String phone,HttpServletResponse response){
            String str = soap.getMobileCodeInfo(phone,null);

        try {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(str);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
