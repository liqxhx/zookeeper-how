package com.qhli.demo.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.RetryUntilElapsed;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CheckExists {

    public static void main(String[] args) throws Exception {

        ExecutorService es = Executors.newFixedThreadPool(5);

        //RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        //RetryPolicy retryPolicy = new RetryNTimes(5, 1000);
        RetryPolicy retryPolicy = new RetryUntilElapsed(5000, 1000);
//		CuratorFramework client = CuratorFrameworkFactory
//				.newClient("192.168.1.9:2181",5000,5000, retryPolicy);

        CuratorFramework client = CuratorFrameworkFactory
                .builder()
                .connectString("192.168.1.9:2181")
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();

        client.start();

        // 同步调用
//        Stat stat = client.checkExists().forPath("/curator");
//        System.out.println(stat);

        // 下面是异步调用，要在forpath()前调用inBackground(回调接口, 回调上下文, 线程池)
        // inBackground会在一个单独的线程中去执行，所以第三个参数提供一个线程池，可以避免创建线程的开销
        // 线程池可以提高性能用后要关闭
        client.checkExists().inBackground(new BackgroundCallback() {

            // arg0 客户端对象
            // arg1 curator事件对象，从中可以获取很多数据、状态信息
            public void processResult(CuratorFramework arg0, CuratorEvent arg1)
                    throws Exception {
                System.out.println(arg1.getPath());
                System.out.println(arg1.getName());
                Stat stat = arg1.getStat();
                System.out.println(stat);
                System.out.println(arg1.getContext()); //inBackground方法的第二个参数就是上下文


            }
        }, "123", es).forPath("/curator");

        Thread.sleep(Integer.MAX_VALUE);


    }

}
