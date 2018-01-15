package jd.com.jd_app_demon.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jd.com.jd_app_demon.R;
import jd.com.jd_app_demon.adapter.LeftAdapter;
import jd.com.jd_app_demon.adapter.MyParentRvAdapter;
import jd.com.jd_app_demon.bean.LeftBean;
import jd.com.jd_app_demon.bean.RightBean;
import jd.com.jd_app_demon.present.LeftPresenter;
import jd.com.jd_app_demon.present.RightPresenter;
import jd.com.jd_app_demon.view.LeftView;


public class ClassifyFragment extends Fragment implements LeftView {


    @BindView(R.id.lv_class)
    ListView lvClass;
    @BindView(R.id.rv_class)
    RecyclerView rvClass;
    Unbinder unbinder;
    private int cid=1;
    private LeftPresenter leftPresenter;
    private RightPresenter rightPresenter;
    private LeftAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classify, container, false);
        unbinder = ButterKnife.bind(this, view);
        rvClass.setLayoutManager(new LinearLayoutManager(getActivity()));

        leftPresenter = new LeftPresenter(this);
        leftPresenter.left();
        rightPresenter = new RightPresenter(this);
        rightPresenter.getRight();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void setLeftBean(final LeftBean leftBean) {
        //给左边的listview 添加适配器
        adapter = new LeftAdapter(getActivity(),leftBean.getData());
        adapter.setOnclickItem(new LeftAdapter.OnclickItem() {
            @Override
            public void ItemClick(LeftBean.DataBean dataBean1, int position) {
                adapter.setCheck(position);
                cid = leftBean.getData().get(position).getCid();
                adapter.notifyDataSetChanged();
                rightPresenter.getRight();
            }
        });
        lvClass.setAdapter(adapter);
        //取消listview的右侧滑动条
        lvClass.setVerticalScrollBarEnabled(false);
        lvClass.setFastScrollEnabled(false);

    }

    @Override
    public int getCid() {
        return cid;
    }

    @Override
    public void setRightBean(RightBean bean) {

        MyParentRvAdapter rvAdapter =new MyParentRvAdapter(getActivity(),bean.getData());
        rvClass.setAdapter(rvAdapter);

    }
}
