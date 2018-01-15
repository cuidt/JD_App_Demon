package jd.com.jd_app_demon.fragment;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jd.com.jd_app_demon.R;
import jd.com.jd_app_demon.activity.SaoActivity;
import jd.com.jd_app_demon.activity.SeekActivity;
import jd.com.jd_app_demon.adapter.HorizontalAdapter;
import jd.com.jd_app_demon.adapter.ShowAdapter;
import jd.com.jd_app_demon.adapter.Vpadapter;
import jd.com.jd_app_demon.bean.HomeBean;
import jd.com.jd_app_demon.fragment.home_fragment.Fragment_1s;
import jd.com.jd_app_demon.fragment.home_fragment.Fragment_2s;
import jd.com.jd_app_demon.present.HomePresenter;
import jd.com.jd_app_demon.util.GlideImageLoader;
import jd.com.jd_app_demon.view.HomeView;

public class HomeFragment extends Fragment implements HomeView {

    Unbinder unbinder;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.ms_title)
    TextView msTitle;
    @BindView(R.id.tv_hour)
    TextView tvHour;
    @BindView(R.id.tv_minute)
    TextView tvMinute;
    @BindView(R.id.tv_second)
    TextView tvSecond;
    @BindView(R.id.h_rc)
    RecyclerView h_rc;
    @BindView(R.id.tj_view)
    RecyclerView tj_view;
    private GridLayoutManager mLayoutManager;
    private GridLayoutManager tjLayoutManager;
    private ArrayList<String> imgs;
    private ArrayList<Fragment> listf;
    private Vpadapter adapter;
    private HorizontalAdapter adapter1;
    private ImmersionBar mImmersionBar;
    int REQUEST_CODE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        HomePresenter presenter = new HomePresenter(this);
        presenter.home();
        listf = new ArrayList<>();
        listf.add(new Fragment_1s());
        listf.add(new Fragment_2s());
        adapter = new Vpadapter(getFragmentManager(), getActivity(), listf);
        vp.setAdapter(adapter);
        vp.setCurrentItem(0);
        getCameraPermission();
        //ZXingLibrary初始化
        ZXingLibrary.initDisplayOpinion(getActivity());
        return view;
    }
    @Override
    public void setHomeBean(HomeBean bean) {
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        h_rc.setLayoutManager(mLayoutManager);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        h_rc.setHasFixedSize(true);
        adapter1 = new HorizontalAdapter(bean.getMiaosha().getList(), getActivity());
        h_rc.setAdapter(adapter1);
        tjLayoutManager = new GridLayoutManager(getActivity(), 2);
        tj_view.setLayoutManager(tjLayoutManager);
        tj_view.setHasFixedSize(true);
        ShowAdapter adapter2 = new ShowAdapter(getActivity(), bean.getTuijian().getList());
        tj_view.setAdapter(adapter2);

        imgs = new ArrayList<>();
        List<HomeBean.DataBean> data = bean.getData();
        for (int i = 0; i < data.size(); i++) {
            imgs.add(data.get(i).getIcon());
        }
        banner.setImages(imgs);
        banner.setDelayTime(5000);
        //Banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        banner.setImageLoader(new GlideImageLoader());
        banner.setDelayTime(5000);

    }

    @OnClick({R.id.saoyisao, R.id.xiaoxi, R.id.seek_shop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //点击扫描二维码
            case R.id.saoyisao:
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
                break;
            case R.id.xiaoxi:
                break;

            case R.id.seek_shop:
                Intent intent2=new Intent(getActivity(),SeekActivity.class);
                startActivity(intent2);
                break;

        }
    }
    private void getCameraPermission() {
        if (Build.VERSION.SDK_INT>22){
            if (ContextCompat.checkSelfPermission(getActivity(),
                    android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                //先判断有没有权限 ，没有就在这里进行权限的申请
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{android.Manifest.permission.CAMERA},2);
            }else {
                //说明已经获取到摄像头权限了 想干嘛干嘛
            }
        }else {
            //这个说明系统版本在6.0之下，不需要动态获取权限。
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getActivity(),SaoActivity.class);
                    intent.putExtra("ewm",result);
                    startActivity(intent);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }



}
