package jd.com.jd_app_demon.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import jd.com.jd_app_demon.MainActivity;
import jd.com.jd_app_demon.R;
import jd.com.jd_app_demon.util.MySharedPreferences;

public class SetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.tx, R.id.exit,R.id.tc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tx:
                finish();
                break;
            case R.id.exit:
                MySharedPreferences.clear();
                Toast.makeText(this, "退出成功！", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(SetActivity.this, MainActivity.class);
                intent.putExtra("item", 5);
                startActivity(intent);
                break;
            case  R.id.tc:
                finish();
                break;
        }
    }
    //MySharedPreferences.clear();


}
