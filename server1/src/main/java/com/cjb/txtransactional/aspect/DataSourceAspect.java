package com.cjb.txtransactional.aspect;

import com.cjb.txtransactional.bean.CjbTransaction;
import com.cjb.txtransactional.connection.TxCollection;
import com.cjb.txtransactional.util.CjbTransactionManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.sql.Connection;

/**
 * Created by chenjiabao on 2019/12/29.
 */
@Aspect
@Component
public class DataSourceAspect {

    @Around("execution(* javax.sql.DataSource.getConnection(..))")
    public Connection around(ProceedingJoinPoint joinPoint){
        try {
            Connection connection = (Connection)joinPoint.proceed();
            CjbTransaction cjbTransactionThreadLocal = CjbTransactionManager.getCjbTransactionThreadLocal();
            return new TxCollection(connection,cjbTransactionThreadLocal);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
