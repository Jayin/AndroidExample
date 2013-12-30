package com.utils;

import org.apache.http.client.CookieStore;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
/**
 * Restful api 客户端<br>
 * 请求前要初始化,cookie设置
 * 
 * @author Jayin Ton
 *
 */
public class RestClient {
	private static final String BASE_URL = "http://api.siyuan.com/1/";
	private static final int HTTP_Timeout = 20*1000;
	public static CookieStore cookieStore;
    
	private static AsyncHttpClient client = new AsyncHttpClient();
    /**
     * get method
     * @param url
     * @param params
     * @param responseHandler 
     */
	public static void get(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		initClient(); 
		client.get(getAbsoluteUrl(url), params, responseHandler);
	}

	public static void post(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		initClient(); 
		client.post(getAbsoluteUrl(url), params, responseHandler);

	}
    /** 
     * 初始化
     */
	private static void initClient() {
		
		if (cookieStore != null)
			client.setCookieStore(cookieStore);
		client.setTimeout(HTTP_Timeout);
	}
    /**
     * set CookieStore
     * @param cookieStore
     */
	public static void setCookieStore(CookieStore cookieStore) {
		RestClient.cookieStore = cookieStore;
	}

	private static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL + relativeUrl;
	}
}
