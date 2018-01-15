package jd.com.jd_app_demon.model;

import jd.com.jd_app_demon.bean.RegistBean;
import jd.com.jd_app_demon.net.OnNetLisenter;

/**
 * Created by 崔 on 2017/12/28.
 */

public interface IRegModel {

    void reg(String mobile, String  password, OnNetLisenter<RegistBean> onNetLisenter);


}
