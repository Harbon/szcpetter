package com.geeklub.ui;



 







import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;


@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity implements OnTouchListener,OnGestureListener {


	private static final int FLING_MIN_DISTANCE = 100;
	private static final int FLING_MIN_VELOCITY = 0;
	private TabHost tabHost;
	private String[] titles;
	private GestureDetector mGestureDetector;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);





		mGestureDetector = new GestureDetector(this);
		LinearLayout ll = (LinearLayout) findViewById(R.id.linew);
		ll.setOnTouchListener(this);
		ll.setLongClickable(true);
		titles = getResources().getStringArray(R.array.title);
		initTab();

	}

	private void initTab() {

		tabHost = getTabHost();

		LinearLayout view = createView(0, R.drawable.main_navigation_news);
		tabHost.addTab(tabHost.newTabSpec("first").setIndicator(view)
				.setContent(new Intent(this, UserHome.class)));

		LinearLayout view1 = createView(1, R.drawable.main_navigation_answers);
		tabHost.addTab(tabHost.newTabSpec("second").setIndicator(view1)
				.setContent(new Intent(this, Ranking.class)));

		LinearLayout view2 = createView(2, R.drawable.main_navigation_action);
		tabHost.addTab(tabHost.newTabSpec("third").setIndicator(view2)
				.setContent(new Intent(this, Boutique.class)));

		tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
			public void onTabChanged(String arg0) {

			}
		});
		
		
		
		
		
		
	}

	/**
	 * 
	 * @param id        选项卡的名字的id
	 * @param draeble_id  选项卡对应的图片的id
	 * @return   将xml填充好的view对象
	 */

	private LinearLayout createView(int id, int draeble_id) {
		LinearLayout view = (LinearLayout) getLayoutInflater().inflate(
				R.layout.tab_wighet, null);
		ImageView tab_imageView = (ImageView) view
				.findViewById(R.id.tab_imageView);
		tab_imageView.setImageDrawable(getResources().getDrawable(draeble_id));
		TextView tab_TextView = (TextView) view.findViewById(R.id.tab_textView);
		tab_TextView.setText(titles[id]);
		return view;
	}

	public boolean onDown(MotionEvent arg0) {

		return false;
	}

	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {

		int total = tabHost.getTabWidget().getChildCount();
		int current = tabHost.getCurrentTab();
		int index = 0;

		if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE
				&& Math.abs(velocityX) > FLING_MIN_VELOCITY) {

			//向左手势


			index = current + 1 > total ? total - 1 : current + 1;
			tabHost.setCurrentTab(index);
		} else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
				&& Math.abs(velocityX) > FLING_MIN_VELOCITY) {
			// Fling right
			index = current - 1 < 0 ? 0 : current - 1;
			//向右手势
			tabHost.setCurrentTab(index);
		}

		return false;
	}

	public void onLongPress(MotionEvent arg0) {



	}

	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {

		return false;
	}

	public void onShowPress(MotionEvent arg0) {


	}

	public boolean onSingleTapUp(MotionEvent arg0) {

		return false;
	}

	public boolean onTouch(View arg0, MotionEvent event) {

		return mGestureDetector.onTouchEvent(event);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {




		return super.dispatchKeyEvent(event);
	}

	/**
	 * listview 和水平滑动事件冲突
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		if (mGestureDetector.onTouchEvent(event)) {
			event.setAction(MotionEvent.ACTION_CANCEL);
		}



		return super.dispatchTouchEvent(event);
	}



	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {


		return super.onKeyDown(keyCode, event);
	}

}






