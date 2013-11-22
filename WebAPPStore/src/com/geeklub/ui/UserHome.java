package com.geeklub.ui;




import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.geeklub.adapter.IconAdapter;
import com.geeklub.db.AppDao;
import com.geeklub.model.App;






public class UserHome extends Activity{
	private GridView gridview;
	private IconAdapter adapter;
	private List<App> apps;
	private AppDao db;
	private Intent Launcher;
	
	
	private final String ACTION_ADD_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.userhome);
		
		
		db = new AppDao(this);
		apps = db.getAllApp();
		
		gridview = (GridView) findViewById(R.id.gridView);
		
		adapter = new IconAdapter(apps,this);      //自定义的数据适配器
		gridview.setAdapter(adapter);
		
		registerForContextMenu(gridview);         //为上下文菜单注册
		
		gridview.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				OnClickGridViewItem(position);
				
			}
			
		});
		
		
		
		
			
		
		
	}

	@Override
	protected void onResume() {
		
		super.onResume();
		
		apps = db.getAllApp();
		adapter = new IconAdapter(apps,this);
		gridview.setAdapter(adapter);
		
		
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		MenuInflater mInflater = getMenuInflater();
		mInflater.inflate(R.menu.cmenu, menu);
		
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		
		switch(item.getItemId()){
		case R.id.delete:
			DeleteApp(apps.get(info.position).getApp_id());
			return true;
			
		case R.id.help:
			Toast.makeText(UserHome.this, "显示帮助", Toast.LENGTH_SHORT).show();
			return true;
			
		case R.id.launcher:
			App launcher_app = apps.get(info.position);
			Bitmap icon = launcher_app.getApp_icon();
			String name = launcher_app.getApp_name();
			String path = launcher_app.getApp_url();
			
			addShortcut( icon, name, path);
             
			return true;
		}
		return super.onContextItemSelected(item);
	}








	private void DeleteApp(String app_icon_url) {
		db.deleteApp(app_icon_url);
		apps = db.getAllApp();
		adapter = new IconAdapter(apps,this);
		gridview.setAdapter(adapter);
	}








	/**
	 * 功能 ： 点击app的图标时，打开用户手机中的浏览器，连接到应用的网址
	 * @param position    应用的位置
	 */

	protected void OnClickGridViewItem(int position) {
		String link = apps.get(position).getApp_url();
		Log.i("vass","Address是"+link);
		Launcher = new Intent(Intent.ACTION_VIEW,Uri.parse(link));
		
		UserHome.this.startActivity(Launcher);
		
	}
	
	
	
	
	
	public void addShortcut(Bitmap icon, String name, String path){  
		  
		Intent intentAddShortcut = new Intent(ACTION_ADD_SHORTCUT);  
		  
		//添加名称  
		  
		intentAddShortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);  
		  
		//添加图标  
		  
		intentAddShortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);  
		  
		//设置Launcher的Uri数据  
		  
		Intent intentLauncher = new Intent();  
		  
		intentLauncher.setData(Uri.parse(path));             
		  
		//添加快捷方式的启动方法  
		  
		intentAddShortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intentLauncher);  
		  
		sendBroadcast(intentAddShortcut);          
		  
		}  





	

}

	


	

			   
	     
	      
	      
	      
	    
	     
	      
	   
	      
	     
	      
	   
	      
	  
	   
	      
	      
	   
	     
	      
	      
	    
	      
	      
	      
	     
	     
	  

	  
	  
	      
	

	