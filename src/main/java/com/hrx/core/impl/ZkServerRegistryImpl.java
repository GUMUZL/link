package com.hrx.core.impl;

import org.I0Itec.zkclient.ZkClient;

import com.hrx.core.ServerRegistry;
import com.hrx.core.util.ZookeeperUtil;

public class ZkServerRegistryImpl implements ServerRegistry {

	@Override
	public void serverRegistry(String serverName, String uri) {
		ZkClient client = ZookeeperUtil.client;
		String serverNode = "/server-provider/provides/"+serverName;
		if(!client.exists(serverNode)) {
			//持久的
			client.createPersistent(serverNode, true);
		}
		//临时的 只要客户端断开就会删除
		client.createEphemeral(serverNode+"/"+uri, true);
	}

}
