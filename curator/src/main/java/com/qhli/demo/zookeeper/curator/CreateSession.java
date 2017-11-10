package com.qhli.demo.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.retry.RetryUntilElapsed;

public class CreateSession {

	public static void main(String[] args) throws InterruptedException {

		// RetryPolicy重试策略
		// allowRetry(int 重试次数, long 重试间隔, RetrySleeper var4)

		// ExponentialBackoffRetry(基本时间，最大重试次数),每次重试时间会增加
		//RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

		// RetryNTimes(最大重试次数，两次重试间隔)
		//RetryPolicy retryPolicy = new RetryNTimes(5, 1000);

		// RetryUntilElapsed（重试时长，每次重试间隔)
		RetryPolicy retryPolicy = new RetryUntilElapsed(5000, 1000);

//		CuratorFramework client = CuratorFrameworkFactory
//				.newClient("192.168.1.105:2181",5000,5000, retryPolicy);

		// fluen风格的构造
		CuratorFramework client = CuratorFrameworkFactory
				.builder()
				.connectString("192.168.1.9:2181")
				.sessionTimeoutMs(5000)
				.connectionTimeoutMs(5000)
				.retryPolicy(retryPolicy)
				.build();
		
		client.start();
		
		Thread.sleep(Integer.MAX_VALUE);
		
		
	}
	
}
