package com.hrx.core.impl;

import java.util.List;

import org.I0Itec.zkclient.ZkClient;

import com.hrx.core.ServerDiscover;
import com.hrx.core.util.ZookeeperUtil;

public class ZkServerDiscoverimpl implements ServerDiscover{

	@Override
	public List<String> serverdiscover(String serverName) {
		ZkClient client = ZookeeperUtil.client;
		String serverNode = "/server-provider/provides/"+serverName;
		List<String> children = client.getChildren(serverNode);
		if(!client.exists(serverNode)) {
			throw new RuntimeException("该服务未注册!");
		}
		if(children==null||children.size()==0) {
			throw new RuntimeException("该"+serverName+"没有可用的节点来执行");
		}
		
		return children;
	}

}
