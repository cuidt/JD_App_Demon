package jd.com.jd_app_demon.model;

import jd.com.jd_app_demon.bean.HomeBean;
import jd.com.jd_app_demon.net.OnNetLisenter;

/**
 * Created by 崔 on 2017/12/28.
 */

public interface HomeModel {

    void getHome(OnNetLisenter<HomeBean> onNetLisenter);

}
