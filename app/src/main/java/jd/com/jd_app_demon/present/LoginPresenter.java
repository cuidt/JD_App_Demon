package jd.com.jd_app_demon.present;

import jd.com.jd_app_demon.bean.LoginBean;
import jd.com.jd_app_demon.model.ILoginModel;
import jd.com.jd_app_demon.model.LoginModel;
import jd.com.jd_app_demon.net.OnNetLisenter;
import jd.com.jd_app_demon.view.ILoginView;

/**
 * Created by 崔 on 2017/12/28.
 */

public class LoginPresenter {
    private ILoginView view;
    private final ILoginModel model;

    public LoginPresenter(ILoginView view) {
        model = new LoginModel();
        this.view = view;
    }
    public void login(){
        String mobile = view.getMobile();
        String password = view.getPassword();
        model.login(mobile, password, new OnNetLisenter<LoginBean>() {
            @Override
            public void onSuccess(LoginBean loginBean) {
                if ("0".equals(loginBean.getCode())){
                    view.setLoginBean(loginBean);
                }
            }

            @Override
            public void onDefault(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
