package com.qhli.demo.zookeeper.zkclient;

import java.util.List;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class NodeDel {

	public static void main(String[] args) {
		ZkClient zc = new ZkClient("192.168.1.105:2181",10000,10000,new SerializableSerializer());
		System.out.println("conneted ok!");
		
//		boolean e = zc.exists("/zkclient");
//		zc.delete("/path");
		boolean flag = zc.deleteRecursive("/zkclient");
		System.out.println(flag);
		
	}
	
}
