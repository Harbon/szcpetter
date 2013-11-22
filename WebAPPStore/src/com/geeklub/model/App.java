package com.geeklub.model;

import android.graphics.Bitmap;



/**
 * App的业务类
 * 
 *
 */

public class App {
	
	
	private String app_id;
	private String app_name;
	private String app_info;
	private String app_author;
	private String app_url;
	private String app_icon_url;
	private Bitmap app_icon;
	
	
	
	
	
	
	/**
	 * 默认的构造函数
	 */
	public App() {
		
	}






	public App(String app_id, String app_name, String app_info,String app_author, String app_url, String app_icon_url,Bitmap app_icon) {
		this.app_id = app_id;
		this.app_name = app_name;
		this.app_info = app_info;
		this.app_author = app_author;
		this.app_url = app_url;
		this.app_icon_url = app_icon_url;
		this.app_icon = app_icon;
	}
	
	
	






	public App(String app_id, String app_name, String app_info, String app_url,
			Bitmap app_icon) {
		
		this.app_id = app_id;
		this.app_name = app_name;
		this.app_info = app_info;
		this.app_url = app_url;
		this.app_icon = app_icon;
	}






	public String getApp_id() {
		return app_id;
	}






	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}






	public String getApp_name() {
		return app_name;
	}






	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}






	public String getApp_info() {
		return app_info;
	}






	public void setApp_info(String app_info) {
		this.app_info = app_info;
	}






	public String getApp_author() {
		return app_author;
	}






	public void setApp_author(String app_author) {
		this.app_author = app_author;
	}






	public String getApp_url() {
		return app_url;
	}






	public void setApp_url(String app_url) {
		this.app_url = app_url;
	}






	public String getApp_icon_url() {
		return app_icon_url;
	}






	public void setApp_icon_url(String app_icon_url) {
		this.app_icon_url = app_icon_url;
	}






	public Bitmap getApp_icon() {
		return app_icon;
	}






	public void setApp_icon(Bitmap app_icon) {
		this.app_icon = app_icon;
	}
	
	
	
	


    




}	