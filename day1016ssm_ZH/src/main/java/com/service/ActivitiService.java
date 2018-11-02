package com.service;

import com.bean.LeaveBill;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;

import java.io.InputStream;
import java.util.List;

public interface ActivitiService {
    //新增流程部署
    public int add(InputStream in,String name);

    //查询部署信息
    public List<Deployment> getdeplist();

    //查询流程定义
    public List<ProcessDefinition> getprolist();

    //查看部署流程图
    InputStream findimage(String depid,String imagename);

    //删除部署
    int  deletedeploy(String depid);

    //提交请假申请
    int qingjia(int leaveid,String username);

    //查询个人任务
    List<Task> gettasks(String username);

    //获得流程图中的form_key
    String getformkey(String taskid);

    //通过任务id，得到请假清单
    LeaveBill findleavebillbytaskid(String taskid);

    //获得连线信息
    List<String> getnames(String taskid);

    //处理任务
    void chuli(String taskid,String pizhu,String result,int leaveid,String username);

    //获得批注信息
    List<Comment> getcomments(String taskid);
}
