package jd.com.jd_app_demon.model;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.functions.Consumer;
import jd.com.jd_app_demon.bean.LoginBean;
import jd.com.jd_app_demon.net.HttpMethods;
import jd.com.jd_app_demon.net.OnNetLisenter;

/**
 * Created by 崔 on 2017/12/28.
 */

public class LoginModel implements ILoginModel {
    @Override
    public void login(String mobile, String password, final OnNetLisenter<LoginBean> onNetLisenter) {
        Map<String, String> map = new HashMap<>();
        map.put("mobile", mobile);
        map.put("password", password);
        HttpMethods.getInstnce().login(map, new Consumer<LoginBean>() {
            @Override
            public void accept(LoginBean loginBean) throws Exception {
                onNetLisenter.onSuccess(loginBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                onNetLisenter.onDefault(throwable);
            }
        });
    }
}
