package consumer.server.txTransaction.aspect;

import consumer.server.txTransaction.annotation.TxTransactional;
import consumer.server.txTransaction.transactional.TransactionType;
import consumer.server.txTransaction.transactional.TxTransaction;
import consumer.server.txTransaction.transactional.TxTransactionManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class TxTransactionAspect implements Ordered {

    @Around("@annotation(consumer.server.txTransaction.annotation.TxTransactional)")
    public void invoke(ProceedingJoinPoint point) {
        //第一个事务，创建事务组

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        TxTransactional txAnnotation = method.getAnnotation(TxTransactional.class);

        String groupId = "";
        if (txAnnotation.isStart()) {
            //创建事务组
            groupId = TxTransactionManager.createTxTransactionGroup();
        } else {
            groupId = TxTransactionManager.getCurrentGroupId();
        }


        TxTransaction txTransaction = TxTransactionManager.createTxTransaction(groupId);

        try {
            point.proceed();  //spring原有逻辑

            txTransaction.setTransactionType(TransactionType.COMMIT);
        } catch (Exception e) {
            txTransaction.setTransactionType(TransactionType.ROLLBAK);
            e.printStackTrace();
        } catch (Throwable throwable) {
            txTransaction.setTransactionType(TransactionType.ROLLBAK);
            throwable.printStackTrace();
        }
        TxTransactionManager.addTxTransaction(groupId, txTransaction, txAnnotation.isEnd());
    }

    @Override
    public int getOrder() {
        return 10000;
    }
}
