package com.socks.androiddemo.ui;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.socks.androiddemo.R;
import com.socks.androiddemo.R.id;
import com.socks.androiddemo.R.layout;

/**
 * 所有测试Activity的集合
 * 
 * @ClassName: AllActivity
 * @author ZhaoKaiQiang
 * @date 2015-3-27 上午11:05:00
 */
public class AllActivity extends Activity {

	private ListView lv;
	private AllActivityAdapter adater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all);
		lv = (ListView) findViewById(R.id.lv);
		adater = new AllActivityAdapter();
		lv.setAdapter(adater);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				startActivity(new Intent(AllActivity.this, adater.getItem(
						position).getActivityClass()));
			}
		});
	}

	private class AllActivityAdapter extends BaseAdapter {

		private ArrayList<ActivityModel> activityModels;

		public AllActivityAdapter() {
			activityModels = new ArrayList<AllActivity.ActivityModel>();
			activityModels.add(new ActivityModel("Spannble 的用法",
					new SpannableActivity().getClass()));
			activityModels.add(new ActivityModel("Notification 的用法",
					new NotifycationActivity().getClass()));
		}

		@Override
		public int getCount() {
			return activityModels.size();
		}

		@Override
		public ActivityModel getItem(int position) {
			return activityModels.get(position);
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder viewHolder;
			ActivityModel model = activityModels.get(position);

			if (convertView == null) {

				convertView = getLayoutInflater().inflate(
						R.layout.item_activitys, parent, false);
				viewHolder = new ViewHolder();
				viewHolder.tv_title = (TextView) convertView
						.findViewById(R.id.tv_title);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}

			viewHolder.tv_title.setText(model.getTitle());

			return convertView;
		}

	}

	private static class ViewHolder {
		TextView tv_title;
	}

	private class ActivityModel {

		private String title;
		private Class<? extends Activity> activityClass;

		public ActivityModel(String title,
				Class<? extends Activity> activityClass) {
			this.title = title;
			this.activityClass = activityClass;
		}

		public String getTitle() {
			return title;
		}

		public Class<? extends Activity> getActivityClass() {
			return activityClass;
		}

	}

}
