package jd.com.jd_app_demon.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import jd.com.jd_app_demon.R;
import jd.com.jd_app_demon.bean.HomeBean;

/**
 * Created by 崔 on 2018/1/3.
 */

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.ViewHolder> implements View.OnClickListener {
    private OnItemClickListener mOnItemClickListener = null;
    public List<HomeBean.MiaoshaBean.ListBeanX> datas;
    private Context context;

    public HorizontalAdapter(List<HomeBean.MiaoshaBean.ListBeanX> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public HorizontalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recler_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        view.setOnClickListener(this);

        return vh;
    }

    @Override
    public void onBindViewHolder(HorizontalAdapter.ViewHolder holder, int position) {
        holder.ms_price.setText("¥ "+datas.get(position).getPrice()+"");
        String[] split = datas.get(position).getImages().split("\\|");
        Glide.with(context).load(split[0]).into(holder.ms_img);
        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView ms_price;
        public ImageView ms_img;
        public ViewHolder(View view){
            super(view);
            ms_price = (TextView) view.findViewById(R.id.ms_price);
            ms_img = (ImageView) view.findViewById(R.id.ms_img);
        }
    }

    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}
