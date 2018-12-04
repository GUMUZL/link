package com.hrx.core.util;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;

import org.I0Itec.zkclient.ZkClient;

import com.alibaba.fastjson.JSON;

public class UploadUtil {



	
	public static void startUpload(String projectName,String propPath) {
		Map<String, String> zkConfig = new HashMap<>();
		PropertyResourceBundle res = null;
		try {
			res = new PropertyResourceBundle(UploadUtil.class.getClassLoader().getResourceAsStream(propPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Enumeration<String> keys = res.getKeys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			zkConfig.put(key, res.getString(key));
		}
		String zkHost = zkConfig.get("zk.host");
		ZkClient client = new ZkClient(zkHost);
		
		projectName ="/"+projectName;
		if(!client.exists(projectName)) {
			client.createPersistent(projectName,true);
		}
		String propNode =projectName+"/"+propPath;
		client.createPersistent(propNode, true);
		client.writeData(propNode, JSON.toJSONString(zkConfig));
		
		client.close();
		
		
		
		/*String readData = (String)client.readData(projectName+"/"+propPath);
		System.err.println(readData);*/
	}
	
	public static void main(String[] args) {
		startUpload("link", "link.properties");
	}
}
