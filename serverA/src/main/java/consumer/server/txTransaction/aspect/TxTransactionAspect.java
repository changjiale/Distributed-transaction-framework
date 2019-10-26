package consumer.server.txTransaction.aspect;

import consumer.server.txTransaction.annotation.TxTransactional;
import consumer.server.txTransaction.connection.TxConnection;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.sql.Connection;

@Aspect
public class TxTransactionAspect {

    @Around("@annotation(consumer.server.txTransaction.annotation.TxTransactional)")
    public void invoke(ProceedingJoinPoint point) {
        //第一个事务，创建事务组

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        TxTransactional txAnnotation = method.getAnnotation(TxTransactional.class);
    }
}
