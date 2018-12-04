package com.hrx.core.test;

import java.util.List;

import com.hrx.core.ServerDiscover;
import com.hrx.core.ServerLoadBalance;
import com.hrx.core.impl.RedisServerDiscoverImpl;
import com.hrx.core.impl.ServerLoadBalanceImpl;

public class DiscoverApp {

	public static void main(String[] args) {
		ServerDiscover client = new RedisServerDiscoverImpl();
		List<String> serverdiscover = client.serverdiscover("com.sxt.core.Test");
		System.out.println("服务com.sxt.core.Test列表为");
		serverdiscover.forEach((e) -> System.out.println(e));
		ServerLoadBalance loadBalance = new ServerLoadBalanceImpl();
		String server = loadBalance.loadBalance(serverdiscover);
		System.out.println("该次要调用的服务器是" + server);

	}
}
