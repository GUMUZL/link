package com.hrx.core.test;

import com.hrx.core.ServerRegistry;
import com.hrx.core.impl.RedisServerRegistryImpl;

public class RegistryApp {

	public static void main(String[] args) {
		ServerRegistry server= new RedisServerRegistryImpl();
		server.serverRegistry("com.sxt.core.Test", "localhost:");
		System.out.println("服务注册完毕");
	}
}
