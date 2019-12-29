package com.cjb.txtransactional.aspect;

import com.cjb.txtransactional.annotation.CjbTransactional;
import com.cjb.txtransactional.bean.CjbTransaction;
import com.cjb.txtransactional.bean.TransactionType;
import com.cjb.txtransactional.util.CjbTransactionManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by chenjiabao on 2019/12/29.
 */
@Aspect
@Component
public class CjbTransactionalAspect implements Ordered{

    @Around("@annotation(com.cjb.txtransactional.annotation.CjbTransactional)")
    public void invoke(ProceedingJoinPoint joinPoint){
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        CjbTransactional cjb = method.getAnnotation(CjbTransactional.class);
        System.out.println(cjb.isStart());

        String groupId="";
        if(cjb.isStart()){
            //创建事务组
            groupId = CjbTransactionManager.createCjbTransactionGroup();
        }

        //创建事务
        CjbTransaction cjbTransaction = CjbTransactionManager.createCjbTransaction(groupId);

        try {
            //走spring逻辑
            joinPoint.proceed();
            CjbTransactionManager.addCjbTransaction(cjbTransaction,cjb.isEnd(), TransactionType.commit);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            CjbTransactionManager.addCjbTransaction(cjbTransaction,cjb.isEnd(), TransactionType.roleback);
        }
    }

    @Override
    public int getOrder() {
        return 10000;
    }
}
