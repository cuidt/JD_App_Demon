package jd.com.jd_app_demon.model;


import jd.com.jd_app_demon.bean.HandPortraitBean;
import jd.com.jd_app_demon.net.OnNetLisenter;

/**
 * 崔冬涛
 * 2018/1/10
 */

public interface ILoadHeadPortraitModel {
    void uploadPhoto(int uid, String filePath, OnNetLisenter<HandPortraitBean> onNetListener);

}
