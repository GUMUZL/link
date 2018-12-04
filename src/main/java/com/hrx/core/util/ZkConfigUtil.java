package com.hrx.core.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.I0Itec.zkclient.ZkClient;

import com.alibaba.fastjson.JSON;

public class ZkConfigUtil {
	//加载所有的配置配置信息 添加到env 容器中 
	private static Map<String , String > env = new HashMap<>();
	private static CountDownLatch lock = new CountDownLatch(1);
	static {
		String zkHost="192.168.230.129:2181";
		String projectName="link";
		
		ZkClient client = new ZkClient(zkHost);
		//获取项目节点下的所有子节点
		projectName="/"+projectName;
		List<String> children = client.getChildren(projectName);
		for (String node : children) {
			String configjson = client.readData(projectName+"/"+node);
			Map<String, String> configKV = (Map<String, String>)JSON.parseObject(configjson, HashMap.class);
			env.putAll(configKV);
		}
		lock.countDown();//执行一次，少一个锁
		
		
	}
	public static Map<String, String> getEnv(){
		
		return env;
	}
	
	
	public static void main(String[] args) {
		
		getEnv().forEach((k,v)->System.out.println(k+":"+v));
	}
	
	

}
