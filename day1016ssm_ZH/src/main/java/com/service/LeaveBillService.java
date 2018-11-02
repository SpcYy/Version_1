package com.service;

import com.bean.LeaveBill;

import java.util.List;

public interface LeaveBillService {
    //查询请假列表
    List<LeaveBill> findleavelist(int userid);

    //添加请假单
    int add(LeaveBill leaveBill);

    //更新请假状态
    int updateState(int leaveid,int state);

    //通过id查询请假单对象
    LeaveBill findbyleaveid(int id);
}
