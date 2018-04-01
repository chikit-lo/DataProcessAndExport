package com.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

public class FTPUtils {
	private FtpClient ftpClient = null;
	private InputStream inputStream = null;
	private OutputStream outputStream = null;
	
	// ����InputStream������
	public InputStream getFTPInputStream(String host, int port, String user, String password, String filename, String dir) {
		try {
			/*
			 * JDK 1.6 version
			 */
			// ftpClient = new FtpClient();
			// ftpClient.openServer(host, port);
			// ftpClient.login(user, password);
			// ftpClient.cd(dir);
			// telnetInputStream = ftpClient.get(filename);
			
			/*
			 * JDK 1.8 version
			 */
			ftpClient = FtpClient.create();
			ftpClient.connect(new InetSocketAddress(host, port));
			ftpClient.login(user, password.toCharArray());
			ftpClient.changeDirectory(dir);
			ftpClient.setBinaryType();
			inputStream = ftpClient.getFileStream(filename);
			
			System.out.println("����FTP�������ɹ�: " + host + ":" + port + "/" + dir);
			System.out.println("��Ҫ��ȡ���ļ�: " + filename);
		} catch (FileNotFoundException e) {
			System.out.println(filename + "�ļ�������"); 
		} catch (IOException | FtpProtocolException e) {
			e.printStackTrace();
		}
		
		return inputStream;
	}
	
	// ����OutputStream������
	public OutputStream getFTPOutputStream(String host, int port, String user, String password, String filename, String dir) {
		try {
			/*
			 * JDK 1.6 version
			 */
			// ftpClient = new FtpClient();
			// ftpClient.openServer(host, port);
			// ftpClient.login(user, password);
			// ftpClient.cd(dir);
			// telnetOutputStream = ftpClient.put(filename);
			
			/*
			 * JDK 1.8 version
			 */
			ftpClient = FtpClient.create();
			ftpClient.connect(new InetSocketAddress(host, port));
			ftpClient.login(user, password.toCharArray());
			ftpClient.changeDirectory(dir);
			outputStream = ftpClient.putFileStream(filename);
			System.out.println("����FTP�������ɹ�: " + host + ":" + port + "/" + dir);
			System.out.println("��Ҫд����ļ�: " + filename);
		} catch (IOException | FtpProtocolException e) {
			e.printStackTrace();
		}
		
		return outputStream;
	}
	
	// �ͷ���Դ
	public void releaseSource() {
		if(inputStream != null)
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		if(outputStream != null)
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		if (ftpClient != null)
			try {
				// ftpClient.closeServer();
				ftpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}