package jd.com.jd_app_demon.model;

import java.util.Map;

import jd.com.jd_app_demon.bean.UpDataCardBean;
import jd.com.jd_app_demon.net.OnNetLisenter;

/**
 * 作者： 崔冬涛
 * 时间： 2018/1/12.
 */

public interface IUpdateModel {

    void updateShopCart(Map<String,String> map, OnNetLisenter<UpDataCardBean> onNetListener);


}
