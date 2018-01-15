package jd.com.jd_app_demon.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jd.com.jd_app_demon.R;
import jd.com.jd_app_demon.adapter.GoodsListAdapter;
import jd.com.jd_app_demon.adapter.SearchListAdapter;
import jd.com.jd_app_demon.bean.ChildBean;
import jd.com.jd_app_demon.bean.SearchBean;
import jd.com.jd_app_demon.present.ChildPresent;
import jd.com.jd_app_demon.view.IChildView;

public class GoodslistActivity extends AppCompatActivity implements IChildView {

    @BindView(R.id.lv_goodslist)
    RecyclerView lvGoodslist;
    @BindView(R.id.two)
    ImageView two;
    @BindView(R.id.list_shop)
    EditText list_shop;
    private int pscid;
    private int goodsType = 0;
    private GoodsListAdapter adapter;
    private ChildPresent childPresent;
    private String search;
    private SearchListAdapter adapter2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodslist);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        pscid = intent.getIntExtra("pscid", 0);
        search = intent.getStringExtra("trim");
        int type = intent.getIntExtra("type", 0);
        childPresent = new ChildPresent(this);
        lvGoodslist.setLayoutManager(new LinearLayoutManager(this));
        list_shop.setText(search);
        if (type == 1) {
            childPresent.getSearch();
            two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (goodsType == 0) {
                        two.setImageResource(R.mipmap.kind_grid);
                        adapter2.setType(1);
                        lvGoodslist.setLayoutManager(new GridLayoutManager(GoodslistActivity.this, 2));
                        adapter2.notifyDataSetChanged();
                        goodsType = 1;
                    } else {
                        two.setImageResource(R.mipmap.kind_liner);
                        adapter2.setType(0);
                        lvGoodslist.setLayoutManager(new LinearLayoutManager(GoodslistActivity.this));
                        adapter2.notifyDataSetChanged();
                        goodsType = 0;
                    }
                }
            });
        } else if (type == 2) {
            childPresent.getChild();
            two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (goodsType == 0) {
                        two.setImageResource(R.mipmap.kind_grid);
                        adapter.setType(1);
                        lvGoodslist.setLayoutManager(new GridLayoutManager(GoodslistActivity.this, 2));
                        adapter.notifyDataSetChanged();
                        two.clearFocus();
                        goodsType = 1;
                    } else {
                        two.setImageResource(R.mipmap.kind_liner);
                        adapter.setType(0);
                        lvGoodslist.setLayoutManager(new LinearLayoutManager(GoodslistActivity.this));
                        adapter.notifyDataSetChanged();
                        goodsType = 0;
                    }
                }
            });
        }
    }

    @Override
    public int getPscid() {
        return pscid;
    }

    @Override
    public void setChildBean(final ChildBean bean) {
        adapter = new GoodsListAdapter(GoodslistActivity.this, bean.getData());
        adapter.setType(goodsType);
        adapter.setOnItemClickListener(new GoodsListAdapter.OnRecyclerViewItemClickLisenter() {
            @Override
            public void setItemId(int itemId) {
                Intent intent = new Intent(GoodslistActivity.this, DetailActivity.class);
                intent.putExtra("pid", itemId);
                startActivity(intent);
            }
        });
        lvGoodslist.setAdapter(adapter);
    }

    @Override
    public String getSearch() {
        return search;
    }

    @Override
    public void setSearchBean(SearchBean searchBean) {
        adapter2 = new SearchListAdapter(GoodslistActivity.this, searchBean.getData());
        adapter2.setType(goodsType);
        adapter2.setOnItemClickListener(new GoodsListAdapter.OnRecyclerViewItemClickLisenter() {
            @Override
            public void setItemId(int itemId) {
                Intent intent = new Intent(GoodslistActivity.this, DetailActivity.class);
                intent.putExtra("pid", itemId);
                startActivity(intent);
            }
        });
        lvGoodslist.setAdapter(adapter2);
    }

    @OnClick(R.id.tui)
    public void onViewClicked() {
        finish();
    }
}
