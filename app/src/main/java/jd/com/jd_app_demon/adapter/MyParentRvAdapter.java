package jd.com.jd_app_demon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import jd.com.jd_app_demon.R;
import jd.com.jd_app_demon.activity.GoodslistActivity;
import jd.com.jd_app_demon.bean.RightBean;

/**
 * 作者： 崔冬涛
 * 时间： 2018/1/4.
 */

public class MyParentRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<RightBean.DataBean> list;

    public MyParentRvAdapter(Context context, List<RightBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.parent_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        RightBean.DataBean dataBean = list.get(position);
        String name = dataBean.getName();
        myViewHolder.tv_parent_name.setText(name);
        myViewHolder.rv_child.setLayoutManager(new GridLayoutManager(context, 3));
        List<RightBean.DataBean.ListBean> list2 = dataBean.getList();
        MyChildRvAdapter myChildRvAdapter = new MyChildRvAdapter(context, list2);
        myViewHolder.rv_child.setAdapter(myChildRvAdapter);
        myChildRvAdapter.setOnChildIitemListener(new MyChildRvAdapter.OnChildIitemListener() {
            @Override
            public void OnChildItemClick(int pscid, int position) {

                Intent intent = new Intent(context, GoodslistActivity.class);
                intent.putExtra("pscid", pscid);
                intent.putExtra("type",2);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_parent_name;
        private final RecyclerView rv_child;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_parent_name = itemView.findViewById(R.id.tv_parent_name);
            rv_child = itemView.findViewById(R.id.rv_child);
        }
    }
}
