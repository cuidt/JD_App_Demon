package jd.com.jd_app_demon;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jd.com.jd_app_demon.fragment.ClassifyFragment;
import jd.com.jd_app_demon.fragment.FindFragment;
import jd.com.jd_app_demon.fragment.HomeFragment;
import jd.com.jd_app_demon.fragment.MyFragment;
import jd.com.jd_app_demon.fragment.ShopCarFragment;
//19241311
public class MainActivity extends AutoLayoutActivity {
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.iv_kind)
    ImageView ivKind;
    @BindView(R.id.iv_find)
    ImageView ivFind;
    @BindView(R.id.iv_shopping)
    ImageView ivShopping;
    @BindView(R.id.iv_my)
    ImageView ivMy;
    LinearLayout llBottom;

    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
    private ImmersionBar immersionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* immersionBar = ImmersionBar.with(this);
        immersionBar.init();*/
        ButterKnife.bind(this);
        ti(new HomeFragment());
        int item = getIntent().getIntExtra("item", 0);
        switch (item) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                ti(new ShopCarFragment());
                ivHead.setImageResource(R.mipmap.ac0);
                ivKind.setImageResource(R.mipmap.abw);
                ivFind.setImageResource(R.mipmap.aby);
                ivShopping.setImageResource(R.mipmap.abv);
                ivMy.setImageResource(R.mipmap.ac2);
                break;
            case 5:
                ti(new MyFragment());
                ivHead.setImageResource(R.mipmap.ac0);
                ivKind.setImageResource(R.mipmap.abw);
                ivFind.setImageResource(R.mipmap.aby);
                ivShopping.setImageResource(R.mipmap.abu);
                ivMy.setImageResource(R.mipmap.ac3);
                break;
            default:
                break;
        }
    }

    //二次退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }


    public void ti(Fragment fragment) {

        getSupportFragmentManager().beginTransaction().replace(R.id.fl_all, fragment).commit();

    }


    @OnClick({R.id.iv_head, R.id.iv_kind, R.id.iv_find, R.id.iv_shopping, R.id.iv_my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
                HomeFragment fragment_head = new HomeFragment();
                ti(fragment_head);
                ivHead.setImageResource(R.mipmap.ac1);
                ivKind.setImageResource(R.mipmap.abw);
                ivFind.setImageResource(R.mipmap.aby);
                ivShopping.setImageResource(R.mipmap.abu);
                ivMy.setImageResource(R.mipmap.ac2);
                break;
            case R.id.iv_kind:
                ti(new ClassifyFragment());
                ivHead.setImageResource(R.mipmap.ac0);
                ivKind.setImageResource(R.mipmap.abx);
                ivFind.setImageResource(R.mipmap.aby);
                ivShopping.setImageResource(R.mipmap.abu);
                ivMy.setImageResource(R.mipmap.ac2);
                break;
            case R.id.iv_find:
                ti(new FindFragment());
                ivHead.setImageResource(R.mipmap.ac0);
                ivKind.setImageResource(R.mipmap.abw);
                ivFind.setImageResource(R.mipmap.abz);
                ivShopping.setImageResource(R.mipmap.abu);
                ivMy.setImageResource(R.mipmap.ac2);
                break;
            case R.id.iv_shopping:
                ti(new ShopCarFragment());
                ivHead.setImageResource(R.mipmap.ac0);
                ivKind.setImageResource(R.mipmap.abw);
                ivFind.setImageResource(R.mipmap.aby);
                ivShopping.setImageResource(R.mipmap.abv);
                ivMy.setImageResource(R.mipmap.ac2);
                break;
            case R.id.iv_my:
                ti(new MyFragment());
                ivHead.setImageResource(R.mipmap.ac0);
                ivKind.setImageResource(R.mipmap.abw);
                ivFind.setImageResource(R.mipmap.aby);
                ivShopping.setImageResource(R.mipmap.abu);
                ivMy.setImageResource(R.mipmap.ac3);
                break;
        }
    }

   /* @Override
    protected void onDestroy() {
        super.onDestroy();
        immersionBar.destroy(); //必须调用该方法，防止内存泄漏
    }*/
}
