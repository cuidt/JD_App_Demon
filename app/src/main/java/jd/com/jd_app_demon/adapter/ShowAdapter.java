package jd.com.jd_app_demon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import jd.com.jd_app_demon.R;
import jd.com.jd_app_demon.activity.DetailActivity;
import jd.com.jd_app_demon.bean.HomeBean;

/**
 * Created by 崔 on 2018/1/3.
 */

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ViewHolder> implements View.OnClickListener {

    private HorizontalAdapter.OnItemClickListener mOnItemClickListener = null;
    public List<HomeBean.TuijianBean.ListBean> datas;
    private Context context;
    private OnItemClickListener listener;


    public ShowAdapter(Context context, List<HomeBean.TuijianBean.ListBean> datas) {
        this.datas = datas;
        this.context = context;
    }


    @Override
    public ShowAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.reclertj_item,viewGroup,false);
        ViewHolder vh = new ViewHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(ShowAdapter.ViewHolder viewHolder, final int position) {
        viewHolder.tj_price.setText("¥"+datas.get(position).getBargainPrice()+"");
        viewHolder.tj_name.setText(datas.get(position).getSubhead());
        String[] split = datas.get(position).getImages().split("\\|");
        Glide.with(context).load(split[0]).into(viewHolder.tj_img);
        //将position保存在itemView的Tag中，以便点击时进行获取
        viewHolder.itemView.setTag(position);
        viewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailActivity.class);
                intent.putExtra("pid",datas.get(position).getPid());
                context.startActivity(intent);

            }
        });
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
        public TextView tj_price;
        public TextView tj_name;
        public RelativeLayout ll;
        public ImageView tj_img;
        public ViewHolder(View view){
            super(view);
            tj_price = (TextView) view.findViewById(R.id.tj_price);
            tj_name = (TextView) view.findViewById(R.id.tj_name);
            tj_img = (ImageView) view.findViewById(R.id.tj_img);
            ll=  view.findViewById(R.id.tj_ll);

        }

    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
      //  this.mOnItemClickListener = listener;
        this.listener = listener;
    }

}
