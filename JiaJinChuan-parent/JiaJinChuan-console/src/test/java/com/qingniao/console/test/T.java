package com.qingniao.console.test;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import com.qingniao.core.common.FastDFSClient;

public class T {
	// 手写图片上传
	@Test
	public void test1() {
		try {
			// 读取配置文件
			ClientGlobal.init(
					"");
			// 创建TrackerClient
			TrackerClient trackerClient = new TrackerClient();
			// 获得TrackerServer
			TrackerServer trackerServer = trackerClient.getConnection();
			// 创建StoragedServer
			StorageServer storageServer = null;
			// 创建StorageClient
			StorageClient1 storageClient = new StorageClient1(trackerServer, storageServer);
			// 上传文件
			String[] file = storageClient.upload_file("C:/Users/Wang.YN/Pictures/Saved Pictures/timg.jpg", "jpg", null);
			for (String string : file) {
				System.out.println(string);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Test
	public void te2() throws Exception {
	 FastDFSClient fastDFSClient= new FastDFSClient("D:/babasport/JiaJinChuan-parent/JiaJinChuan-console/src/main/resources/properties/fastDFS.properties");
	 String a =  fastDFSClient.uploadFile("C:/Users/Wang.YN/Pictures/Saved Pictures/u=3895479132,3815226723&fm=27&gp=0.jpg");
	System.out.println(a);
	}
	
	@Test
	public void ce(){
		
			int a= 1;
			int b=1;
			System.out.println(a==b);
		if(a==b){
			System.out.println(true);
		}else{
			System.out.println(false);
		}
	}
	@Test
	public void ce2(){
	
			String a= "1";
			String b=new String("1") ;
			
		if(a.equals(b)){
			System.out.println(true);
		}else{
			System.out.println(false);
		}
	}
	
}
