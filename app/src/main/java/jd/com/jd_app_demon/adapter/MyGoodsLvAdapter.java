package jd.com.jd_app_demon.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import jd.com.jd_app_demon.R;
import jd.com.jd_app_demon.bean.ChildBean;

/**
 * 作者： 崔冬涛
 * 时间： 2018/1/6.
 */

public class MyGoodsLvAdapter extends BaseAdapter {
    private Context context;
    private List<ChildBean.DataBean> list;
    private int type;

    public MyGoodsLvAdapter(Context context, List<ChildBean.DataBean> list) {
        this.context = context;
        this.list = list;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.lv_goodslist_item, null);
            holder.sdv_goods = convertView.findViewById(R.id.sdv_goods);
            holder.tv_goods_name = convertView.findViewById(R.id.tv_goods_name);
            holder.tv_goods_price = convertView.findViewById(R.id.tv_goods_price);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ChildBean.DataBean dataBean = list.get(position);
        String images = dataBean.getImages();
        String[] split = images.split("\\|");
        String image = split[0];
        double price = dataBean.getPrice();
        holder.sdv_goods.setImageURI(image);
        holder.tv_goods_name.setText(dataBean.getTitle());
        holder.tv_goods_price.setText(price+"");
        return convertView;
    }

    //表格的
    class ViewHolder {
        SimpleDraweeView sdv_goods;
        TextView tv_goods_name;
        TextView tv_goods_price;
    }
    //列表的
    class ViewHoler2{

    }

    public void setType(int type){
        this.type = type;


    }

    @Override
    public int getItemViewType(int position) {
        return type;
    }


}
