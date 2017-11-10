package com.qhli.demo.zookeeper.zkclient;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class GetData {

	public static void main(String[] args) {
		ZkClient zc = new ZkClient("192.168.1.9:2181",10000,10000,new SerializableSerializer());
		System.out.println("conneted ok!");
		
		Stat stat = new Stat();
		User u = zc.readData("/zkclient",stat);
		System.out.println(u.toString());
		System.out.println(stat);
		
	}
	
}
