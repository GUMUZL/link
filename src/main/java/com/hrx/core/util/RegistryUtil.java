package com.hrx.core.util;
/**
 * 自动注册工具类
 * @author hrx
 *
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hrx.core.ServerRegistry;
import com.hrx.core.anno.ExposeService;
import com.hrx.core.impl.ZkServerRegistryImpl;

public class RegistryUtil {
	//存储注册了的服务
	public static Map<String, Object> iocss = new HashMap<String, Object>();
	//通过redis来注册
	//private  ServerRegistry registry =new  ServerRegistryImpl();
	//通过zookeeper注册
	private  ServerRegistry registry =new  ZkServerRegistryImpl();
	//注册包类所有服务
	public Map<String, Object> registryAll(String packages,int port) {
		Map<String, Object> iocs = new HashMap<String, Object>();
		//扫描包 获取该包中所有服务
		List<Class<?>> clazzs = PackageScanUtil.scan(packages);
		for (Class<?> clazz : clazzs) {
			ExposeService service = clazz.getAnnotation(ExposeService.class);
			if(service!=null) { //该服务要被暴露
				//获取服务名
				String serviceName = service.serviceName();
				//如果没有服务名 则 服务名为类名
				if(serviceName.equals("")) {
					serviceName = clazz.getName();
				}
				Object newInstance =null;
				try {
					newInstance = clazz.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
				iocs.put(newInstance.getClass().getInterfaces()[0].getName(), newInstance);
				//注册服务
				registry.serverRegistry(serviceName, "localhost:"+port);
			}
			
		}
		iocss.putAll(iocs);
		return iocs;
		
	}
	

}
