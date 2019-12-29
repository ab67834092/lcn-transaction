package com.cjb.service;

import com.cjb.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by chenjiabao on 2019/12/28.
 */
@Service
public class TestService {

    @Autowired
    TestDao testDao;

    public void test(){
        testDao.test("server2:"+UUID.randomUUID().toString());

    }
}
