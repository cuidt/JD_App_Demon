package jd.com.jd_app_demon.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jd.com.jd_app_demon.MainActivity;
import jd.com.jd_app_demon.R;
import jd.com.jd_app_demon.bean.LoginBean;
import jd.com.jd_app_demon.present.LoginPresenter;
import jd.com.jd_app_demon.util.MySharedPreferences;
import jd.com.jd_app_demon.view.ILoginView;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    @BindView(R.id.login_num)
    EditText loginNum;
    @BindView(R.id.phone_pwd)
    EditText phonePwd;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter = new LoginPresenter(this);



    }

    @OnClick({R.id.exit, R.id.login, R.id.phone_reg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.exit:
                finish();
                break;
            case R.id.login:
                String mobile = loginNum.getText().toString().trim();
                String password = phonePwd.getText().toString().trim();
                //判断输入的内容是否为phone
                boolean b = isPhoneNumber(mobile);
                if (mobile.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "用户名/密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (!b) {
                    Toast.makeText(LoginActivity.this, "手机号不合法", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    Toast.makeText(LoginActivity.this, "密码不能少于六位数", Toast.LENGTH_SHORT).show();
                } else {
                    presenter.login();
                }
                break;
            case R.id.phone_reg:
                Intent intent = new Intent(LoginActivity.this, RegActivity.class);
                startActivity(intent);
                break;
        }
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
//    private void login(String mobile,String password) {
//
//        RequestParams params = new RequestParams("http://120.27.23.105/user/login");
//        params.addQueryStringParameter("mobile", mobile);
//        params.addQueryStringParameter("password", password);
//        x.http().get(params, new Callback.CacheCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                //成功
//                Gson gson = new Gson();
//                LoginBean loginBean = gson.fromJson(result, LoginBean.class);
//                Toast.makeText(LoginActivity.this, loginBean.getMsg(), Toast.LENGTH_SHORT).show();
//                if (loginBean.getCode().equals("0")) {
//
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    intent.putExtra("item",5);
//
//                    startActivity(intent);
//
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
//    }


    @Override
    public String getMobile() {
        return loginNum.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return phonePwd.getText().toString().trim();
    }


    @Override
    public void setLoginBean(LoginBean bean) {
        int uid = bean.getData().getUid();
        MySharedPreferences.putInt("uid",uid);

        Toast.makeText(this, bean.getMsg(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, uid + "", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("item", 5);
        intent.putExtra("uid",uid);
        startActivity(intent);
    }
}
