package com.example.android_service;

import org.json.JSONException;
import org.json.JSONObject;



/**
 * JSON解析器
 * 
 * @author Jayin Ton
 * 
 */
public class JSONParse {
	public static String get() {
		String t = "{\"response\": [null]}";
	    try {
			JSONObject jsonObject = new JSONObject(t);
			jsonObject.getJSONArray("response");
			System.out.println(jsonObject.toString());
			return jsonObject.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return "false";
	}
}
