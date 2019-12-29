package com.cjb.txtransactional.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cjb.txtransactional.bean.CjbTransaction;
import com.cjb.txtransactional.bean.TransactionType;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by chenjiabao on 2019/12/29.
 */
public class CjbTransactionManager {

    //key groupId
    private static final Map<String,CjbTransaction> transactionMap = new ConcurrentHashMap<>();

    private static ThreadLocal<CjbTransaction> threadLocal = new ThreadLocal<>();
    /**
     * 创建事务组
     * @return
     */
    public static String createCjbTransactionGroup(){
        String groupId = UUID.randomUUID().toString();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("groupId",groupId);
        jsonObject.put("commond","create");
        //发送jsonObject

        System.out.println("创建事务组成功！");
        return groupId;
    }

    /**
     * 创建事务
     * @param groupId
     * @return
     */
    public static CjbTransaction createCjbTransaction(String groupId){
        String transactionId = UUID.randomUUID().toString();
        CjbTransaction cjbTransaction = new CjbTransaction(groupId,transactionId);
        System.out.println("创建事务");
        transactionMap.put(groupId,cjbTransaction);
        threadLocal.set(cjbTransaction);
        return cjbTransaction;
    }

    public static CjbTransaction addCjbTransaction(CjbTransaction cjbTransaction, boolean isEnd, TransactionType transactionType){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("groupId",cjbTransaction.getGroupId());
        jsonObject.put("transactionId",cjbTransaction.getTransactionId());
        jsonObject.put("transactionType",transactionType);
        jsonObject.put("commond","add");
        jsonObject.put("isEnd",isEnd);
        jsonObject.put("transactionCount",isEnd);
        //发送
        return cjbTransaction;
    }

    public static CjbTransaction getCjbTransactionByGroupId(String groupId){
        return transactionMap.get(groupId);
    }

    public static CjbTransaction getCjbTransactionThreadLocal(){
        return threadLocal.get();
    }

}
