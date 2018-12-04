package com.hrx.core.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

import com.hrx.core.ClientApp;
import com.hrx.core.ServerDiscover;
import com.hrx.core.ServerLoadBalance;
import com.hrx.core.impl.ServerLoadBalanceImpl;
import com.hrx.core.impl.ZkServerDiscoverimpl;
import com.hrx.core.model.Request;
/**
 * proxy: 指代我们所代理的那个真实对象
 * method:指代的是我们所要调用真实对象的某个方法的Method对象 
 * args:指代的是调用真实对象某个方法时接受的参数
 */
public class ProxyAll implements InvocationHandler {
	private Class<?> clazz;
	//服务发现 redis
//	private ServerDiscover discoverImpl  = new RedisServerDiscoverImpl();
	//服务发现 zookeeper
	private ServerDiscover discoverImpl  = new ZkServerDiscoverimpl();
	//负载均衡
	private ServerLoadBalance balance = new ServerLoadBalanceImpl();

	
	public ProxyAll() {	}
	
	public ProxyAll(Class<?> clazz) {
		super();
		this.clazz = clazz;
	}





	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//远程调用即将开始
		System.out.println(clazz.getName());
		List<String> serverdiscover = discoverImpl.serverdiscover(clazz.getName());
		//负载均衡
		String loadBalance = balance.loadBalance(serverdiscover);
		Request request = new Request(clazz.getName(), method.getName(), args);
		//执行
		Object invoke = ClientApp.invoke(loadBalance.split(":")[0], Integer.valueOf(loadBalance.split(":")[1]), request);
		return invoke;
	}

}
