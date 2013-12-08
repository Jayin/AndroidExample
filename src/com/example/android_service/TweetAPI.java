package com.example.android_service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * 校园资讯模块接口
 * 
 * 
 * @author Jayin Ton
 * 
 */
public class TweetAPI {
	private String account, psw;
	private String RootURL = "http://etipsweb.duapp.com/ETipsproject/";

	public TweetAPI() {

	}

	public TweetAPI(String account, String psw) {
		this.account = account;
		this.psw = psw;
	}

	/*
	 * 构造一个httpclient
	 */
	private DefaultHttpClient getDefaultHttpClient() {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpClientParams.setRedirecting(client.getParams(), false); // 不重定向
		HttpConnectionParams
				.setConnectionTimeout(client.getParams(), 15 * 1000); // 连接超时时间为15s
		return client;
	}

	/**
	 * For Post Method map -> NameValuePair
	 * 
	 * @param data
	 * @return
	 */
	private List<BasicNameValuePair> getValuePair(Map<String, String> data) {
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		for (Map.Entry<String, String> m : data.entrySet()) {
			params.add(new BasicNameValuePair(m.getKey(), m.getValue()));
		}
		return params;
	}

	/**
	 * post method String 可以null说明 有错误
	 * 
	 * @param params
	 * @param url
	 * @return
	 */
	private String post(Map<String, String> params, String url) {
		String res = null;
		DefaultHttpClient client = getDefaultHttpClient();
		HttpPost _post = new HttpPost(url);
		HttpResponse response = null;
		try {
			_post.setEntity(new UrlEncodedFormEntity(getValuePair(params),
					HTTP.UTF_8));
			response = client.execute(_post);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				res = EntityUtils.toString(response.getEntity(),"utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			return res;
		}
	}

	/**
	 * 公用的get方法
	 * 
	 * @param params
	 * @param baseURL
	 * @return
	 */
	private String get(Map<String, String> params, String baseURL) {
		String url = null;
		String res = null;
		DefaultHttpClient client = getDefaultHttpClient();
		HttpGet _get = null;
		HttpResponse response = null;
		StringBuilder sb = new StringBuilder(baseURL);

		if (params != null) {
			sb.append("?");
			for (Map.Entry<String, String> m : params.entrySet()) {
				sb.append(m.getKey()).append("=").append(m.getValue())
						.append("&");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		 
		try {
			url = sb.toString(); //不用url encode?
//			url = URLEncoder.encode(sb.toString(), "utf-8");
//			System.out.println(url);
			_get = new HttpGet(url);
			response = client.execute(_get);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				res = EntityUtils.toString(response.getEntity(),"utf-8");
			}
		} catch (UnsupportedEncodingException e1) {

			e1.printStackTrace();
		} catch (ClientProtocolException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			return res;
		}
	}

	/**
	 * 请求，而不是直接使用get，post方法。。
	 * 
	 * @param method
	 * @param params
	 * @param baseURL
	 * @return
	 */
	private String request(String method, Map<String, String> params,
			String baseURL) {
		 
		if (method.equals("post")) {
			return post(params, baseURL);
		} else {
			return get(params, baseURL);
		}
	}

	/**
	 * 注册：
	 * 
	 * @param nickname
	 *            用户名称（最好是真名）
	 * @param account
	 *            登录账户 （邮箱）
	 * @param id
	 *            学号
	 * @param psw
	 *            登录密码
	 * @return json 可以为空 状态： 200：正常 201 :该账号(邮箱)已存在
	 */
	public String regist(String nickname, String account, String id, String psw) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("nickname", nickname);
		params.put("account", account);
		params.put("id", id);
		params.put("psw", psw);
		return request("post", params, RootURL + "regist.php");
	}

	/**
	 * 登录
	 * 
	 * @param account
	 *            登录账号（邮箱）
	 * @param psw
	 *            该用户系统的密码 8位，英文+数字 8位-16位，包含8位和16位（客户端验证输入合法性）
	 * @return json 可以为空 状态： 200：正常 201 :该账号(邮箱)已存在 204 : 密码错误 205: 邮箱不存在
	 */
	public String login(String account, String psw) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("account", account);
		params.put("psw", psw);
		return request("post", params, RootURL + "login.php");
	}

	/**
	 * 修改密码
	 * 
	 * @param account
	 *            账号（邮箱）
	 * @param resetpsw
	 *            重置的密码 （客户端验证输入合法性）
	 * @param validtime
	 *            有效时间（客户端验证输入合法性）
	 * @return json::状态： 200：正常 201 : 该账号已存在 202 ：修改没有在有效时间内
	 */
	public String reSetPsw(String account, String resetpsw, long validtime) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("account", account);
		params.put("resetpsw", resetpsw);
		params.put("validtime", validtime + "");
		return request("post", params, RootURL + "resetpsw.php");
	}

	/**
	 * 获取话题列表
	 * 
	 * @return a list of topic(topic_id+topic_name)
	 */
	public String getTopicList() {
		return request("get", null, RootURL + "topiclist.php");
	}

	/**
	 * 获取帖子(tweet list 推文列表)
	 * 
	 * @param topic_id
	 *            话题的唯一标识id
	 * @param page该话题下第几页的帖子
	 *            ；注意： 缺省 or page<=0 or page >总页数 时默认为第一页
	 * @return json 多条帖子
	 */
	public String getTopic(String topic_id, String page) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("topic_id", topic_id);
		params.put("page", page);
		return request("get", params, RootURL + "topic.php");
	}

	/**
	 * 发布一帖子（tweet）
	 * 
	 * @param topic_id
	 *            话题的唯一标识id
	 * @param content
	 *            帖子内容
	 * @param author
	 *            发布人
	 * @param sendTime
	 *            发 布 时间
	 * @param incognito
	 *            是否匿名:是为1，其他为否，但否一般约定为0
	 * @return json 状态： 200：正常 201 : 该账号已存在 202 ：修改没有在有效时间内 203 ：发布失败（各种原因）
	 */
	public String compose(String topic_id, String content, String author,
			String sendTime, String incognito) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("topic_id", topic_id);
		params.put("content", content);
		params.put("author", author);
		params.put("sendTime", sendTime);
		params.put("incognito", incognito);
		return request("get", params, RootURL + "post.php");
	}

	/**
	 * 评论一帖子 (tweet)
	 * 
	 * @param topic_id
	 *            话题的唯一标识id
	 * @param article_id
	 *            帖子的唯一标识id
	 * @param sendTime
	 *            发 布 时间
	 * @param author
	 *            评论者
	 * @param incognito
	 *            是否匿名:是为1，其他为否，但否一般约定为0
	 * @param content
	 *            评论内容
	 * @return json 状态： 200：正常 201 : 该账号已存在 202 ：修改没有在有效时间内 203 ：发布失败（各种原因）
	 */
	public String comment(String topic_id, String article_id, String content,
			String sendTime, String author, String incognito) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("topic_id", topic_id);
		params.put("article_id", article_id);
		params.put("sendTime", sendTime);
		params.put("author", author);
		params.put("incognito", incognito);
		params.put("content", content);
		return request("get", params, RootURL + "comment.php");
	}

	/**
	 * 获取某一帖子的评论
	 * 
	 * @param topic_id
	 *            话题的唯一标识id
	 * @param article_id
	 *            帖子的唯一标识id
	 * @return json 多条评论
	 */
	public String getComment(String topic_id, String article_id) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("topic_id", topic_id);
		params.put("article_id", article_id);
		return request("get", params, RootURL + "getcomment.php");
	}
    /**
     * 获取当前最新(每个话题最新的帖子)
     * 
     * @return 多条帖子（包含评论）
     */
	public String getCurrent() {
        return request("get", null, RootURL + "current.php");
	}
}
