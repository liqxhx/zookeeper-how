package com.qhli.demo.zookeeper.curator;

import java.util.List;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.retry.RetryUntilElapsed;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class NodeChildrenListener {

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

		//客户端对象，子节点路径，ture当监听到子节点列表发生变化时同时去获取子节点的数据
		final PathChildrenCache cache = new PathChildrenCache(client,"/jike",true);
		cache.start();
		cache.getListenable().addListener(new PathChildrenCacheListener() {
			
			public void childEvent(CuratorFramework client, PathChildrenCacheEvent event)
					throws Exception {
				// TODO Auto-generated method stub
				switch (event.getType()) {
				case CHILD_ADDED:
					System.out.println("CHILD_ADDED:"+event.getData());
					break;
				case CHILD_UPDATED:
					System.out.println("CHILD_UPDATED:"+event.getData());
					break;
				case CHILD_REMOVED:
					System.out.println("CHILD_REMOVED:"+event.getData());
					break;
				default:
					break;
				}
			}
		});
		
		Thread.sleep(Integer.MAX_VALUE);
		
	}
	
}
