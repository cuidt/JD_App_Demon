package jd.com.jd_app_demon.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jd.com.jd_app_demon.R;
import jd.com.jd_app_demon.adapter.ShopCarAdapter;
import jd.com.jd_app_demon.bean.MessageCounEvent;
import jd.com.jd_app_demon.bean.SelectBean;
import jd.com.jd_app_demon.present.ShopCartPresenter;
import jd.com.jd_app_demon.util.MySharedPreferences;
import jd.com.jd_app_demon.view.IShopCartView;


public class ShopCarFragment extends Fragment implements IShopCartView {

    @BindView(R.id.elv)
    ExpandableListView elv;
    @BindView(R.id.tvTotal)
    TextView tvTotal;
    @BindView(R.id.cbAll)
    CheckBox mCbAll;
    private boolean isEdit = false;
    Unbinder unbinder;
    @BindView(R.id.shop_edit_show)
    LinearLayout shopEditShow;
    @BindView(R.id.shop_edit)
    LinearLayout shopEdit;
    @BindView(R.id.shop_edit_but)
    Button shopeditbut;
    private ShopCarAdapter myErAdapter;
    private ShopCartPresenter presenter;
    private List<SelectBean.DataBean> group;
    private List<List<SelectBean.DataBean.ListBean>> child;
    private int pid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_car, container, false);
        presenter = new ShopCartPresenter(this);
        presenter.shopCart();
        EventBus.getDefault().register(this);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public int getUid() {
        return MySharedPreferences.getInt("uid", 0);
    }

    @Override
    public void setShopCartBean(SelectBean bean) {
        group = bean.getData();
        child = new ArrayList<>();
        //计算数量以及进入购物车默认全选并计算总价
        float price = 0;
        // int num = 0;
        for (int i = 0; i < group.size(); i++) {
            List<SelectBean.DataBean.ListBean> list = group.get(i).getList();
            child.add(list);
            for (int j = 0; j < group.get(i).getList().size(); j++) {
                SelectBean.DataBean.ListBean listBean = child.get(i).get(j);
                price += listBean.getNum() * listBean.getPrice();
                // num+=listBean.getNum();
            }
        }
        tvTotal.setText("合计: " + price + " 元");

        myErAdapter = new ShopCarAdapter(getActivity(), group, child);
        //隐藏下拉三角
        elv.setGroupIndicator(null);
        elv.setAdapter(myErAdapter);
        //全部展开
        for (int i = 0; i < group.size(); i++) {
            elv.expandGroup(i);
        }
        mCbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myErAdapter.allchecked(mCbAll.isChecked());
            }
        });
        myErAdapter.setOnNumChangeLisenter(new ShopCarAdapter.OnNumChangeLisenter() {
            @Override
            public void setShop(String sellerid, int pid, int num, boolean selected) {
                presenter.update(sellerid, pid, num, selected);
            }
        });
    }

    @Override
    public int getPid() {
        return pid;
    }

    //商品删除方法
    void delete() {
        //双重循环获取商品是否选中
        for (int i = 0; i < group.size(); i++) {
            for (int j = 0; j < group.get(i).getList().size(); j++) {
                if (child.get(i).get(j).isChecked()) {
                    pid = group.get(i).getList().get(j).getPid();
                    //进行网络删除
                    presenter.delete();
                    //同时删除集合当中的
                    child.get(i).remove(j);
                }
            }
            if (child.get(i).size() == 0) {
                group.remove(i);
                child.remove(i);
            }
        }
        myErAdapter.notifyDataSetChanged();
    }


    @Subscribe
    public void messageCountEvent(MessageCounEvent msg) {
        tvTotal.setText("合计: " + msg.getMoney() + " 元");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }


    @OnClick({R.id.shop_edit_but, R.id.shop_delete, R.id.exit1, R.id.bt_zf})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shop_edit_but:
                if (isEdit) {
                    shopeditbut.setText("编辑");
                    shopEditShow.setVisibility(View.VISIBLE);
                    shopEdit.setVisibility(View.GONE);
                    isEdit = false;
                } else {
                    shopeditbut.setText("完成");
                    shopEditShow.setVisibility(View.GONE);
                    shopEdit.setVisibility(View.VISIBLE);
                    isEdit = true;
                }

                break;
            case R.id.shop_delete:
                delete();
                break;
            //点击销毁当前页面
            case R.id.exit1:
                getActivity().finish();
                break;
            //去结算
            case R.id.bt_zf:

                break;
        }
    }
}
