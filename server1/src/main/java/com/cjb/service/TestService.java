package com.cjb.service;

import com.cjb.dao.TestDao;
import com.cjb.txtransactional.annotation.CjbTransactional;
import com.cjb.txtransactional.util.CjbTransactionManager;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by chenjiabao on 2019/12/28.
 */
@Service
public class TestService {

    @Autowired
    TestDao testDao;

    @Transactional
    @CjbTransactional(isStart=true)
    public void test() throws IOException {
        testDao.test("server1:"+UUID.randomUUID().toString());
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("http://localhost:8600/server2/test");
//        httpGet.addHeader("groupId", CjbTransactionManager.getCjbTransactionByGroupId());
        httpClient.execute(httpGet);
        int i = 1/0;
    }
}
