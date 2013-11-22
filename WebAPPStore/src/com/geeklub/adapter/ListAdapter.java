package com.geeklub.adapter;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.geeklub.model.App;
import com.geeklub.ui.R;



/*
 * 自定义的数据适配器
 */

public class ListAdapter extends BaseAdapter {
	
	private LayoutInflater mInflater;
	private List<App> apps = new ArrayList<App>();
	
	
	
	
	public ListAdapter(Context context,List<App> apps){
		this.mInflater = LayoutInflater.from(context);
		this.apps = apps;
		
	}
	
	
	@Override
	public int getCount() {

		return apps.size();
	}


	@Override
	public Object getItem(int position) {

		return apps.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		convertView = mInflater.inflate(R.layout.app_list_item, null);
		
		ImageView iv_icon = (ImageView) convertView.findViewById(R.id.app_icon);
		
		TextView tv_name = (TextView) convertView.findViewById(R.id.app_name);
		Button down_button = (Button) convertView.findViewById(R.id.download);
		
		iv_icon.setImageBitmap(apps.get(position).getApp_icon());
		
		tv_name.setText(apps.get(position).getApp_name());
		down_button.setId(position);
		Log.i("ID",String.valueOf(position));
		
		return convertView;
	}

}
