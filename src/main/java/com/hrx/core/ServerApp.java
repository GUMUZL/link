package com.hrx.core;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

import com.hrx.core.model.Request;
import com.hrx.core.util.RegistryUtil;

/**
 * 发布服务
 * 
 * @author hrx
 *
 */

public class ServerApp {

	@SuppressWarnings("resource")
	public void publishServer(int port) throws Exception {

		ServerSocket serverSocket = new ServerSocket(port);
		Socket accept = serverSocket.accept();// 阻塞
		// 获取远程的调用信息
		InputStream inputStream = accept.getInputStream();
		ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
		Request request = (Request) objectInputStream.readObject();

		String className = request.getClassName();
		String methodName = request.getMethodName();
		Object[] args = request.getArgs();

		// 反射构造调用
		//Class<?> clazz = Class.forName(className);
		Object object = RegistryUtil.iocss.get(className);
		Class<?>[] clazzs = new Class<?>[args.length];
		for (int i = 0; i < args.length; i++) {
			clazzs[i] = args[i].getClass();
		}
		// 获取方法
		Method method = object.getClass().getMethod(methodName, clazzs);
		Object result = method.invoke(object, args);

		// 把调用结果给客户端
		OutputStream outputStream = accept.getOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		objectOutputStream.writeObject(result);
		close(objectOutputStream,outputStream,objectInputStream,inputStream,accept,serverSocket);

	}

	private void close(Closeable ...closes) {
		for (Closeable closeable : closes) {
			if(null!=closeable) {
				try {
					closeable.close();
				} catch (IOException e) {
					e.printStackTrace();
				}finally {
					closeable=null;
				}
			}
		}
	}
	

}
