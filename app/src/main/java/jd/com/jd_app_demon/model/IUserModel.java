package jd.com.jd_app_demon.model;

import jd.com.jd_app_demon.bean.GainBean;
import jd.com.jd_app_demon.net.OnNetLisenter;

/**
 * 作者： 崔冬涛
 * 时间： 2018/1/9.
 */

public interface IUserModel {

    void getUid(int uid, OnNetLisenter<GainBean> onNetLisenter);


}
