package com.qhli.demo.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryUntilElapsed;

public class DelNode {

    public static void main(String[] args) throws Exception {

        RetryPolicy retryPolicy = new RetryUntilElapsed(5000, 1000);

        CuratorFramework client = CuratorFrameworkFactory
                .builder()
                .connectString("192.168.1.9:2181")
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();

        client.start();

//		删除单节点
//		client.delete().forPath("/curator");

        // .withVersion(),版本校验
        //.deletingChildrenIfNeeded()如果有子节点，则删除子节点,先删除子节点再删除当前节点
        // .guaranteed()通过一种保障机制，来保证在一次不能删除节点的情况下，curator在后台能够持续删除，直到节点删除成功
        client.delete().guaranteed().deletingChildrenIfNeeded().withVersion(-1).forPath("/curator");


        Thread.sleep(Integer.MAX_VALUE);


    }

}
