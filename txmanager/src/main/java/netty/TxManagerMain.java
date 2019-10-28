package netty;

public class TxManagerMain {

    public static void main(String[] args) {
        NettyServer nettyServer = new NettyServer();
        nettyServer.start("localhost", 8088);

        System.out.println("netty 启动成功");
    }
}
