package jd.com.jd_app_demon.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import jd.com.jd_app_demon.R;
import jd.com.jd_app_demon.bean.LeftBean;

/**
 * 作者： 崔冬涛
 * 时间： 2018/1/4.
 */

public class LeftAdapter extends BaseAdapter {

    private Context context;
    private List<LeftBean.DataBean> list;
    private OnclickItem onclickItem;

    public void setOnclickItem(OnclickItem onclickItem) {
        this.onclickItem = onclickItem;
    }

    public interface OnclickItem {
        void ItemClick(LeftBean.DataBean dataBean1, int position);
    }

    int check;

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    private LayoutInflater inflater = null;

    public LeftAdapter(Context context, List<LeftBean.DataBean> list) {
        this.context = context;
        this.list = list;
        // 布局装载器对象
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.leftitem, null);
            viewHolder.left_tv = (TextView) convertView.findViewById(R.id.left_tv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();// 获取，通过ViewHolder找到相应的控件
        }

        LeftBean.DataBean dataBean = list.get(position);
        viewHolder.left_tv.setText(dataBean.getName());
        if (dataBean.isChecked()) {
            viewHolder.left_tv.setTextColor(Color.parseColor("#FF3306"));
            viewHolder.left_tv.setBackgroundColor(Color.parseColor("#cfcfcf"));
        } else {
            viewHolder.left_tv.setTextColor(Color.parseColor("#FF262426"));
            viewHolder.left_tv.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        if (getCheck() == position) {
            viewHolder.left_tv.setTextColor(Color.RED);
            viewHolder.left_tv.setBackgroundColor(Color.parseColor("#f3f5f7"));
        } else {
            viewHolder.left_tv.setTextColor(Color.BLACK);
            viewHolder.left_tv.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        viewHolder.left_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onclickItem != null) {
                    LeftBean.DataBean dataBean1 = list.get(position);
                    //接口回调监听点击
                    onclickItem.ItemClick(dataBean1, position);

                }
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView left_tv;
    }

}
