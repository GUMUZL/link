package com.hrx.core.proxy;

import java.lang.reflect.Proxy;

/**
 * 代理工厂
 * 可以为所有的对象，创建一个对象
 * @author hrx
 *
 */
public class ProxyFactory {
	
	public static Object getProxyObject(Class<?> clazz) {
		ProxyAll proxyAll = new ProxyAll(clazz);
		//clazz 真实对象(要被代理的对象)
		return Proxy.newProxyInstance(ProxyFactory.class.getClassLoader(),new Class<?>[]{clazz},proxyAll );
	}
	

}
