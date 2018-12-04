package com.hrx.core.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;

import com.hrx.core.anno.ConsumerService;
import com.hrx.core.anno.Ref;
import com.hrx.core.proxy.ProxyFactory;

/**
 * 消费者要被执行的方法 ioc 创建对象
 * @author hrx
 *
 */
public class ConsumerUtil {
	
	public Map<String, Object> consumer(String pkg) throws Exception{
		List<Class<?>> clazzs = PackageScanUtil.scan(pkg);
		Map<String,Object> iocs = new HashMap<String, Object>();
		for (Class<?> clazz : clazzs) {
			//如果是要被注解执行的类和属性 就把他添加到ioc中 
			if(clazz.getAnnotation(ConsumerService.class)!=null) {
				Object instance = clazz.newInstance();
				Field[] fields = clazz.getDeclaredFields();
				for (Field field : fields) {
					Ref ref = field.getAnnotation(Ref.class);
					if(ref!=null) {
						field.setAccessible(true);
						//给属性赋值
						field.set(instance, ProxyFactory.getProxyObject(field.getType()));
						iocs.put(clazz.getName(),instance);
					}
				}
			}
		}
		return iocs;
		
	}
	
	

}
