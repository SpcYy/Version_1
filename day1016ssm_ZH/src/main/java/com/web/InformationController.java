package com.web;

import com.bean.Information;
import com.dao.InformationMapper;
import com.github.pagehelper.PageInfo;
import com.service.InformationService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class InformationController {

    @Autowired
    private InformationService informationService;

    //上传
    @RequestMapping("/book/Addbook")
    public String add(Information imf, HttpServletRequest request, HttpServletResponse response, MultipartFile myfile,ModelMap map) {

        String path = request.getRealPath("/image");
        try {
            myfile.transferTo(new File(path + "/" + myfile.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        imf.setFilename(myfile.getOriginalFilename());
        int k = informationService.insert(imf);

        return "redirect:/book/getlistziliao";
    }

    //下载
    @RequestMapping("/book/down")
    public ResponseEntity<byte[]> down(String filename, HttpServletRequest request){

        //下载文件
        String path = request.getRealPath("/image");
        File f = new File(path+"/"+filename);
        ResponseEntity<byte[]> rsp = null;

        //设置响应头信息
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            httpHeaders.setContentDispositionFormData("attachment", URLEncoder.encode(filename,"utf-8"));

            rsp  = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(f),httpHeaders, HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsp;
    }

    @RequestMapping("/book/getlistziliao")
    public String getall(ModelMap map,@RequestParam(value = "index",defaultValue =  "1") int pageindex,
                         @RequestParam(value = "size",defaultValue =  "2")int size, String informationname){
//        List list = informationService.findall();
//        map.put("ilist",list);
        //return "book/list-ziliao";
        PageInfo pageInfo = informationService.getall(pageindex,size,informationname);
        map.put("pi",pageInfo);
        map.put("informationname",informationname);
        map.put("size",size);
        return "/book/list-ziliao";
    }

    @RequestMapping("/book/getlistziliaodown")
    public String getall2(ModelMap map,@RequestParam(value = "index",defaultValue =  "1") int pageindex,
                         @RequestParam(value = "size",defaultValue =  "2")int size, String informationname){
//        List list = informationService.findall();
//        map.put("ilist",list);
        //return "book/list-ziliao";
        PageInfo pageInfo = informationService.getall(pageindex,size,informationname);
        map.put("pi",pageInfo);
        map.put("informationname",informationname);
        map.put("size",size);
        return "/study/StudentMaterial";
    }

    @RequestMapping("/book/getInfo")
    public String get(ModelMap map){
        List list = informationService.findall();
        map.put("ilist",list);
        return "book/add";

    }

    @RequestMapping("/book/getlistbytypeid")
    public String findbytypeid(ModelMap map,@RequestParam(value = "index",defaultValue =  "1") int pageindex,
                               @RequestParam(value = "size",defaultValue =  "2")int size,int typeid){
        PageInfo pageInfo = informationService.findbytypeid(pageindex,size,typeid);
        map.put("pi",pageInfo);
        map.put("typeid",typeid);
        return "/book/list-ziliao";

    }

    //分页查询
   /* @RequestMapping("/book/booklist")
    public String getall(@RequestParam(value = "index",defaultValue =  "1") int pageindex,
                         @RequestParam(value = "size",defaultValue =  "2")int size, String informationname,
                         ModelMap map){
        PageInfo pageInfo = informationService.getall(pageindex,size,informationname);
        map.put("pi",pageInfo);
        map.put("informationname",informationname);
        map.put("size",size);
        return "/book/list-ziliao";
    }*/
}
