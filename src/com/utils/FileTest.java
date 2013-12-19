package com.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.backup.FileBackupHelper;
import android.test.AndroidTestCase;
import android.util.Log;

import com.utils.FileUtils;
import com.utils.L;
import com.utils.SDCardUtils;

public class FileTest extends AndroidTestCase {
	//defaultFilePath : /mnt/sdcard/AD/
    private String defaultFilePath = SDCardUtils.getSDCardPath()+"AD"+File.separator;
    
	public void sdcardTest() {
		if (SDCardUtils.isExistSDCard()) {
			String rootPath = SDCardUtils.getSDCardPath();
			String filePath = rootPath + "androidTest" + File.separator
					+ "Test.txt";
			System.out.println("exies!");
			System.out.println(rootPath);
			System.out.println("left:" + SDCardUtils.getSDFreeSize());
			if (FileUtils.makeDirs(filePath)) {
				if (FileUtils.writeFile(filePath, "test!!")) {
					System.out.println("write successfully");
					System.out.println("���ݣ�" + FileUtils.readFile(filePath));
				}
			}

		}
	}
	
	public void downloadPic(){
		String url = "http://www.umeng.com/images/pic/push/pic_push_1.png";
		if(FileUtils.writeFile(SDCardUtils.getSDCardPath()+"AD"+File.separator+"one.png", toDoownloadPic(url))){
			Log.i("debug", "ok!");
		}
		
	}
	
	
	protected byte[] toDoownloadPic(String url) {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		HttpResponse response = null;
		byte[] data = null;
		ByteArrayOutputStream bos = null;
		InputStream in = null;
		try {
			response = client.execute(get);
			if (response.getStatusLine().getStatusCode() == 200) {
				in = response.getEntity().getContent();
				bos = new ByteArrayOutputStream();
				int len = -1;
				byte[] buffer = new byte[1024];
				while ((len = in.read(buffer, 0, buffer.length)) != -1) {
					bos.write(buffer, 0, len);
				}
				return bos.toByteArray();
			} else {
				return null;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (client != null) {
				client.getConnectionManager().shutdown();
			}
		}

	}
	 

}
