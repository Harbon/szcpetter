package com.geeklub.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.geeklub.model.App;
import com.geeklub.ui.R;




/**
 * UsrHome中GridView中的适配器类
 * @author Vass
 *
 */

public class IconAdapter extends BaseAdapter {
	private List<App> apps = null;
	private Context context;
	
	public IconAdapter(List<App> apps,Context context){
		this.apps = apps;
		this.context = context;
		
	}

	@Override
	public int getCount() {
		if(apps!=null)
			return apps.size();
		else
			return 0;
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

		LayoutInflater inflater = LayoutInflater.from(context);
		ViewHolder viewHolder = null;

		if(convertView==null){
			convertView = inflater.inflate(R.layout.grid_item_info, null);
			viewHolder = new ViewHolder();
			viewHolder.image = (ImageView) convertView.findViewById(R.id.ItemImage);
			viewHolder.title = (TextView) convertView.findViewById(R.id.ItemText);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.title.setText(apps.get(position).getApp_name());
		
		Bitmap icon = apps.get(position).getApp_icon();
		viewHolder.image.setImageBitmap(icon);
		return convertView;
	}
}

/**
 * 容器类
 * @author Vass
 *@serialData image   应用的图标
 *@serialData title   应用的名字
 */
class ViewHolder{
	public ImageView image;
	public TextView title;
}
