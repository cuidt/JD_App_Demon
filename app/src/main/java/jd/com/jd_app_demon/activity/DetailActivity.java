package jd.com.jd_app_demon.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jd.com.jd_app_demon.MainActivity;
import jd.com.jd_app_demon.R;
import jd.com.jd_app_demon.adapter.MyAdapter;
import jd.com.jd_app_demon.bean.AddBean;
import jd.com.jd_app_demon.bean.DetailBean;
import jd.com.jd_app_demon.fragment.xqfragment.BlankFragment;
import jd.com.jd_app_demon.fragment.xqfragment.EvaluateFragment;
import jd.com.jd_app_demon.fragment.xqfragment.GoodsFragment;
import jd.com.jd_app_demon.present.AddPresenter;
import jd.com.jd_app_demon.util.MySharedPreferences;
import jd.com.jd_app_demon.view.IDetailView;

public class DetailActivity extends AppCompatActivity implements IDetailView {

    private int pid;
    private List<Fragment> fragmentList = new ArrayList<>();

    @BindView(R.id.tab_shop)
    TabLayout tabShop;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.iv_pop_menu)
    ImageView ivPopMenu;
    @BindView(R.id.ll_shop_top)
    LinearLayout llShopTop;
    @BindView(R.id.vp_tab_shop)
    ViewPager vpTabShop;
    @BindView(R.id.ll_lingdang)
    LinearLayout llLingdang;
    @BindView(R.id.ll_dianpu)
    LinearLayout llDianpu;
    @BindView(R.id.iv_guanzhu)
    ImageView ivGuanzhu;
    @BindView(R.id.ll_guanzhu)
    LinearLayout llGuanzhu;
    @BindView(R.id.ll_shopcar)
    LinearLayout llShopcar;
    @BindView(R.id.ll_shop_bottom)
    LinearLayout llShopBottom;

    private String msg;
    private AddPresenter addPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        pid = intent.getIntExtra("pid", 0);
        initDataa();
        Toast.makeText(this, pid + " ", Toast.LENGTH_SHORT).show();
        addPresenter = new AddPresenter(this);

        vpTabShop.setAdapter(new MyAdapter(getSupportFragmentManager(), fragmentList));
        tabShop.setupWithViewPager(vpTabShop);
    }

    // 初始化数据
    private void initDataa() {
        fragmentList.add(new GoodsFragment());
        fragmentList.add(new BlankFragment());
        fragmentList.add(new EvaluateFragment());

    }

    @Override
    public int getPid() {
        return pid;
    }

    @Override
    public void setDetail(DetailBean bean) {
        pid = bean.getData().getPid();

    }

    @Override
    public int getUid() {
        return MySharedPreferences.getInt("uid", 0);
    }

    @Override
    public void isAddShop(AddBean addBean) {
        msg = addBean.getMsg();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    @OnClick({R.id.ll_shopcar, R.id.tv_addshop, R.id.iv_back_shop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_shopcar:
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                intent.putExtra("item", 4);
                startActivity(intent);
                break;
            case R.id.tv_addshop:
                addPresenter.getAddShop();
                break;
            case R.id.iv_back_shop:
                finish();
                break;


        }
    }

}
