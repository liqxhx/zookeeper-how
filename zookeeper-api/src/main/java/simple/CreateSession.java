package simple;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;


public class CreateSession implements Watcher {

    private static ZooKeeper zookeeper;

    public static void main(String[] args) throws IOException, InterruptedException {
        zookeeper = new ZooKeeper("192.168.1.9:2181", 5000, new CreateSession());
        System.out.println(zookeeper.getState());

        Thread.sleep(Integer.MAX_VALUE);
    }

    private void doSomething() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " connected.");
    }

    @Override
    public void process(WatchedEvent event) {
        // 连接刚建立时只有zk的状态为KeeperState.SyncConnected
        // 事件类型及事件对象都为null
        System.out.println("收到事件" + event);
        if (event.getState() == KeeperState.SyncConnected) {

            if (event.getType() == EventType.None && null == event.getPath()) {
                doSomething();
            }
        }
    }

}
