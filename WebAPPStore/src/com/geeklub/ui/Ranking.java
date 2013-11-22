package com.geeklub.ui;






import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import android.widget.Toast;

import com.geeklub.adapter.ListAdapter;
import com.geeklub.db.AppDao;
import com.geeklub.download_data.DownLoad;
import com.geeklub.model.App;




public class Ranking extends  ListActivity {
    
    private List<App> items = new ArrayList<App>();
    private String address="http://lydiaapp.duapp.com/apps/rank";
	private ProgressDialog progressdialog = null;
	private ListView mListView;
	private ListAdapter mAdapter;
	private AppDao db ;
	
	public void StartLink(View v){		
		int id = v.getId();
		Log.i("startlink", ""+id);
		App insert_app = items.get(id);
		Log.i("----size----",items.size()+"");
		db = new AppDao(this);
		db.insertApp(insert_app);
		Toast.makeText(this, insert_app.getApp_name()+"已经添加到我的", Toast.LENGTH_SHORT).show();
	}
	
	
	private Handler mHandler = new Handler() {               

		@SuppressWarnings("unchecked")
		public void handleMessage(Message msg) {
			items = (List<App>) msg.obj;
			switch (msg.what) {
			case 1:
				progressdialog.dismiss();
				
				mAdapter = new ListAdapter(Ranking.this,items);
				mListView.setAdapter(mAdapter);
				
				break;
				
				
			case 2:
				break;

			}
		}
	};

	

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ranking);
		
		progressdialog = ProgressDialog.show(this, "正在获取数据中。。。", "请等待。。。",true);
		
		
		mListView = (ListView) this.findViewById(android.R.id.list);
		
		
		new Thread(new Runnable(){			
			@Override
			public void run() {
				List<App> temp = DownLoad.Server_result(address);			
				Message msg = mHandler.obtainMessage(1,temp);
				mHandler.sendMessage(msg);
			
			}
		}).start();
		
		
		
		
		
	}
}
		
	
		
		


	

	