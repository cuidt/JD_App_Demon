package jd.com.jd_app_demon.view;

import jd.com.jd_app_demon.bean.LoginBean;

/**
 * Created by 崔 on 2017/12/28.
 */

public interface ILoginView {
    String getMobile();
    String getPassword();
    void setLoginBean(LoginBean bean);
}
