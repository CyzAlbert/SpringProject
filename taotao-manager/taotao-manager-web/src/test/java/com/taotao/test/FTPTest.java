package com.taotao.test;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

public class FTPTest {
	
	@Test
	public void FTPClientTest() throws Exception{
		FTPClient client=new FTPClient();
		
		client.connect("192.168.1.34",21);
		
		boolean res=client.login("ftpuser","40994");
		
		if(res==true) {
			System.out.println("login success");
		}
		
		
		client.setFileTransferMode(FTP.BINARY_FILE_TYPE);
		
		client.enterLocalPassiveMode();

		
		res=client.changeWorkingDirectory("/home/ftpuser/images/2018/12/23");
		
		if(res==true) {
			System.out.println("changeWorkingDirectory success");
		}
		
		FileInputStream fileInputStream=new FileInputStream(new File("D:\\share\\timg.jpg"));
		
		client.setFileType(FTP.BINARY_FILE_TYPE);
		
		client.setControlEncoding("UTF-8");
		
		res=client.storeFile("it.jpg", fileInputStream);
		
		if(res==true) {
			System.out.println("file upload success");
		}
		else {
			System.out.println("file upload failed");
		}
		
		client.logout();
	}
}
