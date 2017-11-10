package com.qhli.demo.zookeeper.zkclient;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.util.List;

// 定阅子节点列表变化（增加、删除）
// 同时可以收到/zkclient节点创建、删除的通知
public class SubscribeChildChanges {

    private static class ZkChildListener implements IZkChildListener {

        // parentPath节点路径
        // currentChilds子节点列表
        public void handleChildChange(String parentPath,
                                      List<String> currentChilds) throws Exception {


            System.out.println(parentPath);
            System.out.println(currentChilds.toString());

        }


    }

    public static void main(String[] args) throws InterruptedException {
        ZkClient zc = new ZkClient("192.168.1.9:2181", 10000, 10000, new SerializableSerializer());
        System.out.println("conneted ok!");

        zc.subscribeChildChanges("/zkclient", new ZkChildListener());
        Thread.sleep(Integer.MAX_VALUE);


    }

}
