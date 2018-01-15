package jd.com.jd_app_demon.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jd.com.jd_app_demon.R;

public class SeekActivity extends AppCompatActivity {

    @BindView(R.id.seek_sousuo)
    EditText seekSousuo;
    private String trim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.tui, R.id.seek})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tui:
                finish();
                break;
            case R.id.seek:
                trim = seekSousuo.getText().toString().trim();
                if(trim.isEmpty()){
                    Toast.makeText(this, "请输入您需要搜索的商品名称！", Toast.LENGTH_SHORT).show();
                    break;
                }else {
                    Intent intent =new Intent(SeekActivity.this,GoodslistActivity.class);
                    intent.putExtra("trim",trim);
                    intent.putExtra("type",1);
                    startActivity(intent);
                    break;
                }
        }
    }
}
