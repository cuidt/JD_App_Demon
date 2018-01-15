package jd.com.jd_app_demon;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.zhy.autolayout.AutoLayoutActivity;

public class WelcomeActivity extends AutoLayoutActivity {

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            //欢迎界面完毕后启动主界面
            Intent it = new Intent();
            it.setClass(WelcomeActivity.this, MainActivity.class);
            WelcomeActivity.this.startActivity(it);
            WelcomeActivity.this.finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {

                }
                handler.sendEmptyMessage(0);
            }
        }).start();

    }
}
