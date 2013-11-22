package com.geeklub.utils;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import android.graphics.Bitmap;

import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * 转换数据的类
 * 
 *
 */
public class Data_Change {

	
	/**
	 * 用来将byte[ ] 转换成bitmap的静态方法
	 * @param b  传入的byte数组
	 * @return    Bitmap数据
	 */
	public static  Bitmap byte_toBitmap(byte[] array){
	    if(array.length!=0)
	    	return BitmapFactory.decodeByteArray(array, 0, array.length);
	    
	    else 
	    	return null;
	    
  }
	
	
	
	
	
	/**
	 * 将服务器返回的InputStream转换为String型数据
	 * @param is  服务器返回的InputStream型数据
	 * @return    String型的数据
	 */
	
	public static String readInputStream(InputStream is){
		try{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int len = 0;
			byte[] buffer = new byte[1024];
			while((len = is.read(buffer))!=-1){
				baos.write(buffer, 0, len);
			}
			is.close();
			baos.close();

			byte[] result = baos.toByteArray();
			return new String(result);
		}catch (Exception e){
			Log.e("Data_Change", "inputstream数据转换失败");
			return "inputstream数据转换失败";
		}
	}
	
	
	
	
	/**
	 * 将bitmap转换成byte[]的方法
	 * @param bitmap      bitmap数据
	 * @return            bitmap转换成的byte[]数据
	 */
	
	public static byte[] bitmap_toBytes(Bitmap bitmap){  
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();    
	    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);    
	    return baos.toByteArray();  
	   }  


	
	
	
	
}
