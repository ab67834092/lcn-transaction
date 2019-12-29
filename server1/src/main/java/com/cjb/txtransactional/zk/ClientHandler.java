package com.cjb.txtransactional.zk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cjb.txtransactional.bean.CjbTransaction;
import com.cjb.txtransactional.bean.TransactionType;
import com.cjb.txtransactional.util.CjbTransactionManager;

/**
 * Created by chenjiabao on 2019/12/29.
 */
public class ClientHandler {

    //由zk调用
    public synchronized void read(Object msg){
        JSONObject jsonObject = JSON.parseObject((String) msg);
        String groupId = jsonObject.getString("groupId");
        String command = jsonObject.getString("command");
        CjbTransaction cjbTransactionByGroupId = CjbTransactionManager.getCjbTransactionByGroupId(groupId);
        if(command.equals(TransactionType.commit)){
            cjbTransactionByGroupId.setTransactionType(TransactionType.commit);
        }else{
            cjbTransactionByGroupId.setTransactionType(TransactionType.roleback);
        }
        cjbTransactionByGroupId.getTask().signalTask();
    }
}
