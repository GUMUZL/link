package com.hrx.core;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.hrx.core.model.Request;

public class ClientApp {

	@SuppressWarnings("resource")
	public static Object invoke(String ip, int port, Request request) throws Exception {

		Socket socket = new Socket(ip, port);

		// 发送请求
		OutputStream outputStream = socket.getOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		objectOutputStream.writeObject(request);

		// 接受响应
		InputStream inputStream = socket.getInputStream();
		ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
		Object readObject = objectInputStream.readObject();

		// 关闭资源
		close(objectInputStream, inputStream, objectOutputStream, outputStream, socket);
		return readObject;

	}

	/**
	 * 关闭资源的操作
	 * 
	 * @param closes
	 */
	private static void close(Closeable... closes) {
		for (Closeable closeable : closes) {
			if (null != closeable) {
				try {
					closeable.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					closeable = null;
				}
			}
		}
	}

}
