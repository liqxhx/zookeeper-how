package com.qhli.demo.zookeeper.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.BytesPushThroughSerializer;

// 定阅节点数据内容改变事件
public class SubscribeDataChanges {

    private static class ZkDataListener implements IZkDataListener {

        public void handleDataChange(String dataPath, Object data)
                throws Exception {
            System.out.println(dataPath + ":" + data.toString());
        }

        public void handleDataDeleted(String dataPath) throws Exception {
            System.out.println(dataPath);

        }


    }

    // BytesPushThroughSerializer 原始字节码数组
    public static void main(String[] args) throws InterruptedException {
        ZkClient zc = new ZkClient("192.168.1.105:2181", 10000, 10000, new BytesPushThroughSerializer());
        System.out.println("conneted ok!");

        zc.subscribeDataChanges("/zkclient", new ZkDataListener());
        Thread.sleep(Integer.MAX_VALUE);


    }

}
