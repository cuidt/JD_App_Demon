package jd.com.jd_app_demon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import jd.com.jd_app_demon.R;
import jd.com.jd_app_demon.bean.RightBean;

public class MyChildRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<RightBean.DataBean.ListBean> list;
    OnChildIitemListener onChildIitemListener;

    interface OnChildIitemListener {
        void OnChildItemClick(int pscid, int position);
    }
    public void setOnChildIitemListener(OnChildIitemListener onChildIitemListener){
        this.onChildIitemListener = onChildIitemListener;
    }

    public MyChildRvAdapter(Context context, List<RightBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.child_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        RightBean.DataBean.ListBean listBean = list.get(position);
        String icon = listBean.getIcon();
        String name = listBean.getName();
        final int pscid = listBean.getPscid();
        myViewHolder.sdv_child.setImageURI(icon);
        myViewHolder.tv_child_name.setText(name);
        myViewHolder.ll_child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChildIitemListener.OnChildItemClick(pscid,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        private final SimpleDraweeView sdv_child;
        private final TextView tv_child_name;
        private final LinearLayout ll_child;

        public MyViewHolder(View itemView) {
            super(itemView);
            sdv_child = itemView.findViewById(R.id.sdv_child);
            tv_child_name = itemView.findViewById(R.id.tv_child_name);
            ll_child = itemView.findViewById(R.id.ll_child);
        }
    }
}