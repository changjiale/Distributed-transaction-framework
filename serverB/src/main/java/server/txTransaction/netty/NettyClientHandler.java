package server.txTransaction.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import server.txTransaction.transactional.TransactionType;
import server.txTransaction.transactional.TxTransaction;
import server.txTransaction.transactional.TxTransactionManager;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    private ChannelHandlerContext context;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        context = ctx;
    }

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("接受数据:" + msg.toString());
        JSONObject jsonObject = JSON.parseObject((String) msg);

        String groupId = jsonObject.getString("groupId");
        String command = jsonObject.getString("command");

        System.out.println("接收command:" + command);
        // 对事务进行操作

         //通知子事务去执行
        TxTransaction txTransaction = TxTransactionManager.getTxTransaction(groupId);

        if(command.equals("commit")) {
            txTransaction.setTransactionType(TransactionType.COMMIT);
            new TxTransaction().getTaskLock().singalTask();
        }else {
            txTransaction.setTransactionType(TransactionType.ROLLBAK);
        }

        txTransaction.getTaskLock().singalTask();

    }

    public synchronized Object call(JSONObject data) throws Exception {
        context.writeAndFlush(data.toJSONString());
        return null;
    }
}
