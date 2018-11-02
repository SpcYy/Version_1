package com.service.impl;

import com.bean.LeaveBill;
import com.service.ActivitiService;
import com.service.LeaveBillService;
import org.activiti.engine.*;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

@Service
public class ActivitiServiceImpl implements ActivitiService {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private LeaveBillService leaveBillService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private FormService formService;

    @Autowired
    private HistoryService historyService;
    //添加部署
    @Override
    public int add(InputStream in, String name) {
        ZipInputStream zip = new ZipInputStream(in);
        repositoryService.createDeployment()
                .addZipInputStream(zip)
                .name(name)
                .deploy();
        return 1;
    }

    //查看部署
    @Override
    public List<Deployment> getdeplist() {
        List list =
                repositoryService.createDeploymentQuery().list();

        return list;
    }


    //查看流程定义
    @Override
    public List<ProcessDefinition> getprolist() {
        List list =repositoryService.createProcessDefinitionQuery().list();
        return list;
    }

    //删除部署
    @Override
    public int deletedeploy(String depid) {

        repositoryService.deleteDeployment(depid);
        return 1;
    }

    //查看部署图
    @Override
    public InputStream findimage(String depid, String imagename) {
        return repositoryService.getResourceAsStream(depid,imagename);
    }

    //提交请假申请

    @Override
    @Transactional //如何将业务过程和流程定义进行关联
    public int qingjia(int leaveid, String username) {
        //1.修改请假状态
        leaveBillService.updateState(leaveid,1);
        //2.启动流程实例时候，需要建立流程和业务之间的关系
        //使用business_key来保存业务的信息
        //bussiness_key的组成方式：流程定义的key+业务的id
        String business="LeaveBill."+leaveid;
        Map map =new HashMap();
        map.put("busid",business);
        map.put("username",username);
        ProcessInstance instance =
                runtimeService.startProcessInstanceByKey("LeaveBill",business,map);
        //查询流程实例的id查询任务对象
        Task task =
                taskService.createTaskQuery()
                .processInstanceId(instance.getId()).singleResult();

        //完成个人任务
        taskService.complete(task.getId());

        return 1;
    }

    //查询个人任务
    @Override
    public List<Task> gettasks(String username) {
        List<Task> list =taskService.createTaskQuery().taskAssignee(username).list();
        return list;
    }

    //得到form_key
    @Override
    public String getformkey(String taskid) {
        TaskFormData data = formService.getTaskFormData(taskid);
        return data.getFormKey();
    }

    //通过任务Id，查询请假清单
    @Override
    public LeaveBill findleavebillbytaskid(String taskid) {
        //1.通过任务id查询任务对象
        Task task = taskService.createTaskQuery().taskId(taskid).singleResult();
        //2.通过task得到流程实例的id
        String instanceid = task.getProcessInstanceId();
        //3.通过流程实例id得到流程实例对象
        ProcessInstance processInstance =runtimeService.createProcessInstanceQuery()
                .processInstanceId(instanceid)
                .singleResult();
        //4.通过流程实例的对象得到businesskey    LeaveBill.5
        String str = processInstance.getBusinessKey();

        //截取得到id
        String id = str.substring(str.indexOf(".")+1);

        //6.通过id查询请假清单对象
        LeaveBill lb = leaveBillService.findbyleaveid(Integer.parseInt(id));
        return lb;
    }

    @Override
    public List<String> getnames(String taskid) {

        //存储连线名字
        List names =new ArrayList();
        //通过任务id查询任务对象
        Task task = taskService.createTaskQuery().taskId(taskid).singleResult();

        //得到流程定义id
        String processdefinitionid = task.getProcessDefinitionId();

        //3.通过流程定义id得到流程图对象
        ProcessDefinitionEntity processDefinitionEntity =
                (ProcessDefinitionEntity)repositoryService.getProcessDefinition(processdefinitionid);

        //4.得到当前任务正在活动的activiti_id
        String instanceid = task.getProcessInstanceId();//得到流程实例id

        ProcessInstance processInstance =  //通过流程定义id查询流程实例
                runtimeService.createProcessInstanceQuery().processInstanceId(instanceid).singleResult();

        String act_id = processInstance.getActivityId();//得到正在活动任务的id

        //5.根据正在活动的activiti_id获得activiti对象
        ActivityImpl activity =
                processDefinitionEntity.findActivity(act_id);

       List<PvmTransition> list = activity.getOutgoingTransitions();
       if (list.size()>0){
           for (PvmTransition pvmTransition : list) {
              String name =(String) pvmTransition.getProperty("name");
              names.add(name);
           }
       }
        return names;
    }

    //处理任务
    @Override
    public void chuli(String taskid, String pizhu, String result, int leaveid, String username) {
        //1.添加批注信息表
        Task task =taskService.createTaskQuery().taskId(taskid).singleResult();
        //批注人的名称  一定要写，不然查看的时候不知道人物信息
        Authentication.setAuthenticatedUserId(username);
        taskService.addComment(taskid,task.getProcessInstanceId(),pizhu);

        //2.设置流程变量值
        Map map =new HashMap();
        map.put("rs",result);

        //完成个人任务
        taskService.complete(taskid,map);

        //指定下一个任务的办理人--已完成
        if(result.equals("驳回")){
            //修改请假状态
            leaveBillService.updateState(leaveid,3);
        }
        //3.判断任务是否执行完毕
        //查询流程实例对象
        ProcessInstance pro =
                runtimeService.createProcessInstanceQuery()
                .processInstanceId(task.getProcessInstanceId())
                .singleResult();

        if(pro==null){ //任务完成
            //修改请假状态
            leaveBillService.updateState(leaveid,2);

        }
    }

    //获得批注信息
    @Override
    public List<Comment> getcomments(String taskid) {
        //存储批注集合
        List alllist =new ArrayList();
        //得到任务流程的实例id
        Task task =taskService.createTaskQuery().taskId(taskid).singleResult();
        String instanceid = task.getProcessInstanceId();
        //查询历史信息，通过流程实例id查询
        //方法一
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(instanceid).list();
        //判断是否有历史信息
        if (list.size()>0){
            for (HistoricTaskInstance taskInstance : list) {
               String id =  taskInstance.getId();//得到历史任务的ID
                List list2 = taskService.getTaskComments(id); //得到任务的批注信息

                alllist.addAll(list2);

            }
        }
        return alllist;
    }
}
