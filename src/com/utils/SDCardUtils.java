package com.utils;

import java.io.File;

import android.os.Environment;
import android.os.StatFs;

/***
 * SD��������
 * 
 * @author Lenovo
 * 
 */
public class SDCardUtils {
	/** SDCard�ĸ�·�� **/
	private static String SDCARD_PATH = null;

	/**
	 * �ж�sdcard�Ƿ����
	 * 
	 * @return
	 */
	public static boolean isExistSDCard() {
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			return true;
		} else
			return false;
	}

	/**
	 * 
	 * ȡ��SD��·������/(����File.separator)��β
	 * 
	 * @return
	 */
	public static String getSDCardPath() {
		if (!isExistSDCard())
			return null;
		if (null != SDCARD_PATH)
			return SDCARD_PATH;
		File path = Environment.getExternalStorageDirectory();
		String SDCardPath = path.getAbsolutePath();
		SDCardPath += SDCardPath.endsWith(File.separator) ? "" : File.separator;
		SDCARD_PATH = SDCardPath;
		return SDCardPath;
	}
    /**
     * ��ȡSD��ʣ��ռ�(MB)
     * @return 0 if SDCard is not exist
     */
	public static long getSDFreeSize() {
		if(!isExistSDCard())return 0;
		// ȡ��SD���ļ�·��
		File path = Environment.getExternalStorageDirectory();
		StatFs sf = new StatFs(path.getPath());
		// ��ȡ�������ݿ�Ĵ�С(Byte)
		long blockSize = sf.getBlockSize();
		// ���е����ݿ������
		long freeBlocks = sf.getAvailableBlocks();
		// ����SD�����д�С
		// return freeBlocks * blockSize; //��λByte
		// return (freeBlocks * blockSize)/1024; //��λKB
		return (freeBlocks * blockSize) / 1024 / 1024; // ��λMB
	}
	
	 
}
