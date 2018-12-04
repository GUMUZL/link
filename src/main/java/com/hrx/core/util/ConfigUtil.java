/*package com.hrx.core.util;
*//**
 * 容器类相当于ioc
 * @author hrx
 *
 *//*

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;

public class ConfigUtil {
	//将所有的配置信息加载到ConfigUtil类里面，提供一个容器
	private final static Map<String, String> env  = new HashMap<>();
	//让系统能够运行起来的文件 env
	static {
		try {
			PropertyResourceBundle bundle = new PropertyResourceBundle(ConfigUtil.class.getClassLoader().getResourceAsStream("link.properties"));
			Enumeration<String> keys = bundle.getKeys();
			while(keys.hasMoreElements()) {
				String key = keys.nextElement();
				String value = bundle.getString(key);
				env.put(key, value);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Map<String, String> getEnv(){
		return env;
	}
	public static String getValue(String key) {
		if(env.containsKey(key)) {
			return env.get(key);
		}else {
			return null;
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println(getValue("port"));
		System.out.println(getValue("zk.host"));
	}
	
	

}
*/