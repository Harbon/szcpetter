package com.geeklub.db;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

public class AppSQLiteHelper extends SQLiteOpenHelper {

	
   /**
    * 数据库的构造方法 用来定义数据库的名称 数据库的查询的结果集 数据库的版本
    * @param context
    */
 
	public AppSQLiteHelper(Context context) {
		super(context, "app.db", null, 1);
		
	}
	
	/**
	 * 数据库第一次被创建的时候调用的方法
	 * @param db 被创建的数据库
	 * 包含一张名为app的表。这个表有5列:id、name、info、address、icon.
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		//初始化数据库的表结构
		
		db.execSQL("create table app(app_id text  primary key ,app_name varchar(20),app_info text ,app_url text ,app_icon blob)");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		

	}

}
