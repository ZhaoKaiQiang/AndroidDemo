package com.socks.androiddemo.ui;

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

import com.socks.androiddemo.R.id;
import com.socks.androiddemo.R.layout;
import com.socks.androiddemo.base.BaseActivity;

import java.util.ArrayList;

public class AllActivity extends BaseActivity {

    private ListView lv;
    private AllActivityAdapter adater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_all);
        lv = (ListView) findViewById(id.lv);
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
            activityModels = new ArrayList<ActivityModel>();
            activityModels.add(new ActivityModel("SpannbleString",
                    SpannableActivity.class));
            activityModels.add(new ActivityModel("Notification",
                    NotifycationActivity.class));
            activityModels.add(new ActivityModel("OtherActivity",
                    OtherActivity.class));
            activityModels.add(new ActivityModel("IntentActivity",
                    IntentActivity.class));
            activityModels.add(new ActivityModel("UtilsActivity",
                    UtilsActivity.class));
            activityModels.add(new ActivityModel("ReflectionActivity",
                    ReflectionActivity.class));
            activityModels.add(new ActivityModel("FocusActivity",
                    FocusActivity.class));
            activityModels.add(new ActivityModel("MenuActivity",
                    MenuActivity.class));
            activityModels.add(new ActivityModel("AnimatorActivity",
                    AnimatorActivity.class));
            activityModels.add(new ActivityModel("PointViewActivity",
                    PointViewActivity.class));
            activityModels.add(new ActivityModel("Camera3DActivity",
                    Camera3DActivity.class));
            activityModels.add(new ActivityModel("MatrixActivity",
                    MatrixActivity.class));
            activityModels.add(new ActivityModel("ApkFileInfoActivity",
                    ApkFileInfoActivity.class));
            activityModels.add(new ActivityModel("BitmapShaderActivity",
                    BitmapShaderActivity.class));
            activityModels.add(new ActivityModel("ColorMatrixActivity",
                    ColorMatrixActivity.class));
            activityModels.add(new ActivityModel("FllowerActivity",
                    FllowerActivity.class));
            activityModels.add(new ActivityModel("ConfirmActivity",
                    ConfirmActivity.class));
            activityModels.add(new ActivityModel("ShaderActivity",
                    ShaderActivity.class));
            activityModels.add(new ActivityModel("CanvasActivity",
                    CanvasActivity.class));
            activityModels.add(new ActivityModel("FloatingActionButtonActivity",
                    FloatingActionButtonActivity.class));
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
                        layout.item_activitys, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.tv_title = (TextView) convertView
                        .findViewById(id.tv_title);
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
