package jd.com.jd_app_demon.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jd.com.jd_app_demon.R;
import jd.com.jd_app_demon.bean.RegistBean;
import jd.com.jd_app_demon.present.RegPresenter;
import jd.com.jd_app_demon.view.IRegView;

public class RegActivity extends AppCompatActivity implements IRegView {

    @BindView(R.id.reg_num)
    EditText regNum;
    @BindView(R.id.reg_pwd)
    EditText regPwd;
    @BindView(R.id.reg)
    Button reg;
    private RegPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);
        presenter = new RegPresenter(this);

    }


    private boolean isPhoneNumber(String phoneStr) {
        //定义电话格式的正则表达式
        String regex = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        //设定查看模式
        Pattern p = Pattern.compile(regex);
        //判断Str是否匹配，返回匹配结果
        Matcher m = p.matcher(phoneStr);
        return m.find();
    }

//    private void register(String mobile,String password) {
//        RequestParams params = new RequestParams("http://120.27.23.105/user/reg");
//        params.addQueryStringParameter("mobile", mobile);
//        params.addQueryStringParameter("password", password);
//        x.http().get(params, new Callback.CacheCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                //成功
//                Gson gson = new Gson();
//                RegistBean registBean = gson.fromJson(result, RegistBean.class);
//                //如果注册成功就返回登录页面
//                Toast.makeText(RegActivity.this, registBean.getMsg(), Toast.LENGTH_SHORT).show();
//                if (registBean.getCode().equals("0")) {
//                    finish();
//                }
//            }
//
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//
//            @Override
//            public boolean onCache(String result) {
//                return false;
//            }
//        });
//
//    }

    @Override
    public String getMobile() {
        return regNum.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return regPwd.getText().toString().trim();
    }

    @Override
    public void setReg(RegistBean bean) {
        Toast.makeText(this, bean.getMsg(), Toast.LENGTH_SHORT).show();
        finish();
    }

    @OnClick({R.id.exit, R.id.reg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.exit:
                finish();
                break;
            case R.id.reg:
                String mobile = regNum.getText().toString().trim();
                String password = regPwd.getText().toString().trim();
                reg.setBackgroundColor(Color.RED);
                //判断输入的内容是否为phone
                boolean b = isPhoneNumber(mobile);
                if (mobile.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegActivity.this, "用户名/密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (!b) {
                    Toast.makeText(RegActivity.this, "手机号不合法", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    Toast.makeText(RegActivity.this, "密码不能少于六位数", Toast.LENGTH_SHORT).show();
                } else {
                    presenter.reg();
                }
                break;
        }
    }
}
