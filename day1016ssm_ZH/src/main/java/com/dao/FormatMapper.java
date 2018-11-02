package com.dao;

import com.bean.Format;

public interface FormatMapper {
    int insert(Format record);

    int insertSelective(Format record);
}