package com.geeklub.db;



import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import com.geeklub.model.App;
import com.geeklub.utils.Data_Change;



public class AppDao {
	
	private AppSQLiteHelper helper;
	
	

	//构造函数
	public AppDao(Context context){
		helper = new AppSQLiteHelper(context);
		
	}
	
	/**
	 * 增加一条记录到数据库
	 * @param app  要插入的app数据
	 * @return   如果返回值是-1，表示添加失败；返回的是大于0的值，表示新添加的这一行的id
	 */
	public long insertApp(App app){
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put("app_name", app.getApp_name());
		values.put("app_info", app.getApp_info());
		values.put("app_url", app.getApp_url());
		values.put("app_id", app.getApp_id());
		
		byte[] icon = Data_Change.bitmap_toBytes(app.getApp_icon());//将bitmap转换成byte[]，并保存到数据库中
		values.put("app_icon", icon);

		long id =  db.insert("app", null, values);
		
		db.close();

		return id;		
		
	}
	
	
	/**
	 * 删除数据库里的一条记录
	 * @param id   传入要删除的app的id
	 * @return     如果是真，表示删除成功；如果为假，表示删除失败
	 */
	
	
	public boolean  deleteApp(String app_id){
		SQLiteDatabase db = helper.getWritableDatabase();

		boolean flag = (db.delete("app", "app_id=?", new String[]{app_id})>0);

		db.close();

		return flag;

	}
	
	
	/**
	 * 更新数据库里的一条记录
	 * @param app   传入更新的app数据
	 * @return    如果是真，表示更新成功；如果为假，表示更新失败
	 */
	
	public boolean updateApp(App app){
		SQLiteDatabase db = helper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		
		values.put("app_id", app.getApp_id());
		values.put("app_name", app.getApp_name());
		values.put("app_info", app.getApp_info());
		values.put("app_url", app.getApp_url());
		byte[] icon = Data_Change.bitmap_toBytes(app.getApp_icon());  //将bitmap转换成byte[]，并保存到数据库中
		values.put("app_icon", icon);
		
		
		boolean flag = (db.update("app", values, "app_id=?", new String[]{app.getApp_id()})>0);
		db.close();
		
		return flag;
		
	}
	
	
	/**
	 * 返回数据库的全部信息
	 * @return     apps的集合,包含多个应用程序的信息
	 */
	public List<App> getAllApp(){
		SQLiteDatabase db = helper.getReadableDatabase();
		
		
		List<App> apps = new ArrayList<App>();
		
		Cursor cursor = db.query("app", null, null, null, null, null, null);
	    while(cursor.moveToNext()){
	    	String app_id = cursor.getString(cursor.getColumnIndex("app_id"));
	    	String app_name = cursor.getString(cursor.getColumnIndex("app_name"));
	    	String app_info = cursor.getString(cursor.getColumnIndex("app_info"));
	    	String app_url = cursor.getString(cursor.getColumnIndex("app_url"));
	    	
	    	//从数据库中得到app图片的byte[]数据，调用bytes_toBitmap方法将byte[]转换成bitmap
	    	byte[] byteData = cursor.getBlob(cursor.getColumnIndex("app_icon"));
	    	Bitmap app_icon = Data_Change.byte_toBitmap(byteData);
	    	
	    	App app = new App(app_id,app_name,  app_info, app_url,  app_icon);
	    	apps.add(app);
	    }
	    
	    db.close();
	    
		
		return apps;
		
	}
	
	
	


	
}
	
	