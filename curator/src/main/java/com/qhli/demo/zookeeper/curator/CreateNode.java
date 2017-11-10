package com.qhli.demo.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.retry.RetryUntilElapsed;
import org.apache.zookeeper.CreateMode;

public class CreateNode {

	public static void main(String[] args) throws Exception {
		
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
		
		
		String path = client.create()
				.creatingParentsIfNeeded() //如果父节点不存在，则创建父节点
				.withMode(CreateMode.EPHEMERAL)
				.forPath("/clients/curator","123".getBytes());
		
		System.out.println(path);
		
		Thread.sleep(Integer.MAX_VALUE);
		
		
	}
	
}
