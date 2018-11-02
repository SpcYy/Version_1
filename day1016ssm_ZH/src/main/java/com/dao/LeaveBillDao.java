package com.dao;

import com.bean.LeaveBill;

import java.util.List;
import java.util.Map;

public interface LeaveBillDao {
    List<LeaveBill> findleavelist(int userid);

    int add(LeaveBill leaveBill);

    //修改请假状态
    int updateState(Map map);

    //通过id查询请假单对象
    LeaveBill findbyleaveid(int id);
}
