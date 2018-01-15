package jd.com.jd_app_demon.fragment.xqfragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jd.com.jd_app_demon.R;
import jd.com.jd_app_demon.bean.AddBean;
import jd.com.jd_app_demon.bean.DetailBean;
import jd.com.jd_app_demon.present.DetailPresenter;
import jd.com.jd_app_demon.util.GlideImageLoader;
import jd.com.jd_app_demon.util.MySharedPreferences;
import jd.com.jd_app_demon.view.IDetailView;


public class GoodsFragment extends Fragment implements IDetailView {

    @BindView(R.id.xq_banner)
    Banner mBanner;
    Unbinder unbinder;
    @BindView(R.id.xq_name)
    TextView xqName;
    @BindView(R.id.xq_subhead)
    TextView xqSubhead;
    @BindView(R.id.xq_price)
    TextView xqPrice;
    private int pid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods, container, false);

        Intent intent = getActivity().getIntent();
        pid = intent.getIntExtra("pid", 0);

        DetailPresenter detailPresenter = new DetailPresenter(this);
        detailPresenter.getDetail();

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public int getPid() {
        return pid;
    }

    @Override
    public void setDetail(DetailBean bean) {
        pid = bean.getData().getPid();
       // Toast.makeText(getActivity(), pid + "  ", Toast.LENGTH_SHORT).show();
        String images = bean.getData().getImages();
        String[] split = images.split("\\|");
        List<String> Imglist = new ArrayList<>();
        for (int i = 0; i < split.length - 1; i++) {
            String img = split[i];
            Imglist.add(img);
        }
        mBanner.setImageLoader(new GlideImageLoader());
        //自动轮播时间间隔
        // mBanner.setDelayTime(2000);
        mBanner.setImages(Imglist);
        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner停止自动轮播 可手动
        mBanner.isAutoPlay(false);
        mBanner.start();
        //设置商品的标题
        String title = bean.getData().getTitle();
        xqName.setText(" "+title);
        //设置商品的简介
        String subhead = bean.getData().getSubhead();
        xqSubhead.setText(subhead+subhead);
        //设置商品的价格
        double price = bean.getData().getPrice();
        xqPrice.setText("¥  "+price+"");

    }

    @Override
    public int getUid() {
        return MySharedPreferences.getInt("uid",0);
    }

    @Override
    public void isAddShop(AddBean addBean) {

    }

  /*  @Override
    public void isAddShop(boolean isAdd) {
        if(isAdd){
            Toast.makeText(getActivity(), "加成功", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getActivity(), "加入失败", Toast.LENGTH_SHORT).show();
        }
    }*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }
}
