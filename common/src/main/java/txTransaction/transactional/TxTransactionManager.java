package txTransaction.transactional;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import txTransaction.netty.NettyClient;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class  TxTransactionManager {

    private static NettyClient nettyClient;

    private static Map<String, TxTransaction> map = new HashMap<>();

    private static ThreadLocal<TxTransaction> current = new ThreadLocal<>();

    private static ThreadLocal<String> currentGroupId = new ThreadLocal<>();
    private static ThreadLocal<Integer> transactionCount = new ThreadLocal<>();

    @Autowired
    public void setNettyClient(NettyClient nettyClient) {
        TxTransactionManager.nettyClient = nettyClient;
    }

    public static String createTxTransactionGroup() {
        String groupId = UUID.randomUUID().toString();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("groupId", groupId);
        jsonObject.put("command", "create");
        nettyClient.send(jsonObject);
        System.out.println("创建事务组");
        return groupId;
    }

    public static TxTransaction createTxTransaction(String groupId) {
        String transactionId = UUID.randomUUID().toString();

        TxTransaction txTransaction = new TxTransaction(groupId, transactionId);
        map.put(groupId, txTransaction);
        current.set(txTransaction);
        System.out.println("创建事务");
        return txTransaction;

    }

    public static void addTxTransaction(String groupId, TxTransaction txTransaction, Boolean isEnd) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("group", txTransaction.getGroupId());
        jsonObject.put("transactionId", txTransaction.getTransactionId());
        jsonObject.put("transactionType", txTransaction.getTransactionType());
        jsonObject.put("command", "add");
        jsonObject.put("isEnd", isEnd);

        nettyClient.send(jsonObject);
        System.out.println("添加事务");
        System.out.println("添加事务");
    }

    public static TxTransaction getTxTransaction(String groupId) {

        return map.get(groupId);

    }

    public static TxTransaction getCurrent() {

        return current.get();

    }
    public static String getCurrentGroupId() {
        return currentGroupId.get();
    }

    public static void setCurrentGroupId(String groupId) {
        currentGroupId.set(groupId);
    }

    public static Integer  getTransactionCount() {
        return transactionCount.get();
    }

    public static void setTransactionCount(int i) {
        transactionCount.set(i);
    }
}
