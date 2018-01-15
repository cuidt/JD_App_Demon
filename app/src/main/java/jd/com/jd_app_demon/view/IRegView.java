package jd.com.jd_app_demon.view;

import jd.com.jd_app_demon.bean.RegistBean;

/**
 * Created by 崔 on 2017/12/28.
 */

public interface IRegView {
    String getMobile();

    String getPassword();

    void setReg(RegistBean bean);
}
