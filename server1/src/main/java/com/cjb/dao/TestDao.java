package com.cjb.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by chenjiabao on 2019/12/28.
 */
@Mapper
public interface TestDao {

    public void test(@Param("userName") String userName);
}
