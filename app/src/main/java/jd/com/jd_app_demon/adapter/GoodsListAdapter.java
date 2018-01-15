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

import butterknife.BindView;
import butterknife.ButterKnife;
import jd.com.jd_app_demon.R;
import jd.com.jd_app_demon.bean.ChildBean;

/**
 * 详情列表的适配器
 * 崔冬涛
 * 2018/1/7
 */

public class GoodsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private int type;
    private Context context;
    private List<ChildBean.DataBean> list;
    private OnRecyclerViewItemClickLisenter onItemClickListener;

    public interface OnRecyclerViewItemClickLisenter {
        void setItemId(int itemId);
    }

    public GoodsListAdapter(Context context, List<ChildBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getItemViewType(int position) {
        return type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View baseView;
        if (viewType == 0) {
            baseView = LayoutInflater.from(parent.getContext()).inflate(R.layout.goodslist_linear, parent, false);
            ListViewHolder viewHolder = new ListViewHolder(baseView);
            return viewHolder;
        } else {
            baseView = LayoutInflater.from(parent.getContext()).inflate(R.layout.goodslist_grid, parent, false);
            GridViewHolder viewHolder = new GridViewHolder(baseView);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ChildBean.DataBean bean = list.get(position);
        String[] imageUrls = bean.getImages().split("\\|");
        if (type == 0) {
            ListViewHolder listViewHolder = (ListViewHolder) holder;
            listViewHolder.productsListIv.setImageURI(imageUrls[0]);
            listViewHolder.productsListName.setText(bean.getTitle());
            listViewHolder.productsListPrice.setText("¥  " + bean.getPrice() + "");
            listViewHolder.productsListLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.setItemId(bean.getPid());
                }
            });
        } else {
            GridViewHolder gridViewHolder = (GridViewHolder) holder;
            gridViewHolder.productsGridIv.setImageURI(imageUrls[0]);
            gridViewHolder.productsGridName.setText(bean.getTitle());
            gridViewHolder.productsGridPrice.setText("¥ "+bean.getPrice() + "");
            gridViewHolder.productsGridLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.setItemId(bean.getPid());
                }
            });
        }

    }

    public void setOnItemClickListener(OnRecyclerViewItemClickLisenter onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.goodslist_linear_iv)
        SimpleDraweeView productsListIv;
        @BindView(R.id.goodslist_linear_name)
        TextView productsListName;
        @BindView(R.id.goodslist_linear_price)
        TextView productsListPrice;
        @BindView(R.id.goodslist_linear_ll)
        LinearLayout productsListLl;

        public ListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class GridViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.goodslist_grid_iv)
        SimpleDraweeView productsGridIv;
        @BindView(R.id.goodslist_grid_name)
        TextView productsGridName;
        @BindView(R.id.goodslist_grid_price)
        TextView productsGridPrice;
        @BindView(R.id.goodslist_grid_ll)
        LinearLayout productsGridLl;

        public GridViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
