package com.hrx.core.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * 注入服务的提供者
 * @author hrx
 *扫描包
 */
public class PackageScanUtil {
	
	public static List<Class<?>> scan(String packags) {
		//只要扫描就必须读取文件
		String projectDir=Thread.currentThread().getContextClassLoader().getResource("").getPath();
		//System.out.println(projectDir);///E:/STSWorkspace/link/target/classes/
		String replace = packags.replace(".", "\\");//com\sxt\service\impl
		String path = projectDir+replace;
		File dir = new File(path);
		List<Class<?>> classs = new ArrayList<>();
		if(dir.isDirectory()) {
			File[] listFiles = dir.listFiles();//获取文件夹里面的文件
			for (File file : listFiles) {
				String replace2 = dir.getPath().replace(replace,"");//E:\STSWorkspace\link\target\classes\
				//file.getPath //E:\STSWorkspace\link\target\classes\com\hrx\core\anno\ConsumerService.class
				String javaFileName = file.getPath().replace(replace2, "").replace("\\", ".");
				//System.out.println(javaFileName);
				String className = javaFileName.substring(0, javaFileName.lastIndexOf("."));
				Class<?> clazz = null;
				try {
					clazz = Class.forName(className);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				classs.add(clazz);
			}
		}
		return classs;
	}
	
	

}
