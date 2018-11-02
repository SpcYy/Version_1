package com.web;


import com.bean.Classes;
import com.dao.DepartmentMapper;
import com.github.pagehelper.PageInfo;
import com.service.ClassesService;
import com.service.DepartmentService;
import com.service.MajorService;
import com.service.UserTbService;
import com.sun.deploy.net.HttpResponse;
import com.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ClassesController {
    @Autowired
    private ClassesService classesService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private MajorService majorService;

    @Autowired
    private UserTbService userTbService;
    //模糊查询
    @RequestMapping("/Educational/class/getclasslist")
    public String getall(@RequestParam(value = "index",defaultValue =  "1") int pageindex,
                         @RequestParam(value = "size",defaultValue =  "2")int size, String classname, String classnum,
                            ModelMap map){
       PageInfo pageInfo = classesService.getall(pageindex,size,classname,classnum,null,null);
        map.put("pi",pageInfo);
        map.put("cname",classname);
        map.put("cnum",classnum);
        map.put("size",size);

        return "/Educational/class/list";
    }

    //审核查询班级
    @RequestMapping("/Educational/getclasses")
    public String getclass(@RequestParam(value = "index",defaultValue =  "1") int pageindex,
                         @RequestParam(value = "size",defaultValue =  "2")int size, String classname, String classnum,
                         ModelMap map){
        PageInfo pageInfo = classesService.getall(pageindex,size,classname,classnum,null,"审核中");
        map.put("pi",pageInfo);
        map.put("cname",classname);
        map.put("cnum",classnum);
        map.put("size",size);

        return "/Educational/Auditing";
    }
    //查询所有学院
    @RequestMapping("/Educational/class/getDepts")
    public String getdepts(ModelMap map){
        List list = departmentService.findall();
        map.put("dlist",list);
        return "/Educational/class/add";
    }

    //根据学院编号查询学院对应的专业
    @RequestMapping("/Educational/class/getmajorbyid")
    @ResponseBody  //转json,返回值和方法返回值相同
    public  List getmajor(int did){
        List list =  majorService.findbydepartid(did);
        return list;
    }

    //根据学院id，班级id和角色名查询老师
    @RequestMapping("/Educational/class/getteacher")
    @ResponseBody
    public List getteacher(int did,int mid){
        List list = userTbService.findall(did,mid,"班主任");
        return list;
    }

    //新增班级
    @RequestMapping("/Educational/class/addclass")
    public String add(Classes classes){
        classes.setClassstate("未审核");
        classesService.insert(classes);
        return "redirect:/Educational/class/getclasslist";
    }
    //提交审核
    @RequestMapping("/Educational/class/updateclassstate")
    public String update(Classes classes){
        classes.setClassstate("审核中");
        classes.setAuditcount(classes.getAuditcount()+1);
        classesService.updateByPrimaryKeySelective(classes);

        return "redirect:/Educational/class/getclasslist";
}

    @RequestMapping("/Educational/updatestate")
    public String update2(Classes classes){
        classesService.updateByPrimaryKeySelective(classes);
        return "redirect:/Educational/getclasses";//审核界面
    }
    //导出班级信息
   @RequestMapping("/Educational/class/daochu")
    public void daochu(int[] single, HttpServletResponse response){
        PageInfo pageInfo = classesService.getall(0,0,null,null,single,null);
        List<Classes> list = pageInfo.getList();

       ExcelUtil.createhead();
       ExcelUtil.createother(list);
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
       String date = simpleDateFormat.format(new Date());
       FileOutputStream out = null;

       try {
           out=new FileOutputStream("d:\\class"+date+".xls");
           ExcelUtil.export(out);
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       }finally {
           try {
               out.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }

        response.setContentType("text/html;charset=utf-8");

       try {
           PrintWriter out2 = response.getWriter();
           out2.print("<script>alert('导出成功');location.href='/Educational/class/getclasslist'</script>");
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
}
