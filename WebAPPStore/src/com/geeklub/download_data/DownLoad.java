package com.geeklub.download_data;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.geeklub.model.App;
import com.geeklub.utils.Data_Change;

public class DownLoad {
	
	
	/**
	 * 将String解析的方法
	 * @param content   服务器上返回的字符串
	 * @return   将字符串解析为apps
	 */
	
	private  static List<App> getServerContent(String content){
		List<App> mlists = new ArrayList<App>();

		try{
			JSONObject response = new JSONObject(content);
			int status = response.getInt("status");
			if(status==0){
				JSONArray app_lists = response.getJSONArray("apps");

				for(int i = 0;i <app_lists.length();i++){
					JSONObject app = app_lists.getJSONObject(i);
					String app_id = app.getString("id");
					String app_name = app.getString("name");
					String app_info = app.getString("description");
					String app_author = app.getString("author");	
					String app_url = app.getString("url");	
					String app_icon_url = app.getString("icon_src");
					Bitmap app_icon = getPicture(app_icon_url);
					
					mlists.add(new App(app_id,app_name,app_info,app_author,app_url,app_icon_url,app_icon));
					
				}
			}

		}catch(Exception e){
			Log.e("json", "json解析失败");

		}
		return mlists;
	}
	
	
	/**
	 * 
	 * @param path   网址
	 * @return    apps
	 */
	public static List<App> Server_result(String path){
		List<App> apps = new ArrayList<App>();
		
		HttpClient client = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(path);
		try {
			HttpResponse response =  client.execute(httpget);
			InputStream is = response.getEntity().getContent();
			String result = Data_Change.readInputStream(is);
		    apps = getServerContent(result);

		}catch(Exception e){
			Log.e("http", "获取网络数据失败");

		}
		return apps;
	}


	/**
	 * 下载应用对应图标的方法
	 * @param app_icon_url    应用图标的下载网址
	 * @return                应用图标的bitmap对象
	 */
	private static Bitmap getPicture(String app_icon_url){
		Bitmap bm=null;
		try {
			URL url=new URL(app_icon_url);	//创建URL对象
			URLConnection conn=url.openConnection();	//获取URL对象对应的连接
			conn.connect();	//打开连接
			InputStream is=conn.getInputStream();	//获取输入流对象
			bm=BitmapFactory.decodeStream(is);	//根据输入流对象创建Bitmap对象

		} catch (MalformedURLException e1) {
			e1.printStackTrace();	//输出异常信息
		} catch (IOException e) {
			e.printStackTrace();	//输出异常信息
		}
		return bm;
	}



}
