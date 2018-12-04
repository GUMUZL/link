package com.hrx.core.impl;

import java.util.List;
import java.util.Random;

import com.hrx.core.ServerLoadBalance;

public class ServerLoadBalanceImpl implements ServerLoadBalance{
	
	private static Random random = new Random();
	@Override
	public String loadBalance(List<String> servers) {
		return servers.get(random.nextInt(servers.size()));
	}
	

}
