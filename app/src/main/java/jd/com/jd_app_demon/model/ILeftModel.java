package jd.com.jd_app_demon.model;

import jd.com.jd_app_demon.bean.LeftBean;
import jd.com.jd_app_demon.net.OnNetLisenter;

/**
 * Created by 崔 on 2018/1/4.
 */

public interface ILeftModel {


    void getLeft(OnNetLisenter<LeftBean> onNetLisenter);


}
