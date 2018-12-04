package com.hrx.core.util;

import org.I0Itec.zkclient.ZkClient;

public class ZookeeperUtil {
	public static ZkClient client = null;
	static {
		String host= ZkConfigUtil.getEnv().get("zk.host");
		 client = new ZkClient(host);
	}

}
