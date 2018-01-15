package jd.com.jd_app_demon.model;

import jd.com.jd_app_demon.bean.AddBean;
import jd.com.jd_app_demon.net.OnNetLisenter;

/**
 * 作者： 崔冬涛
 * 时间： 2018/1/9.
 */

public interface IAddModel {

    void getAdd(int pid,int uid,OnNetLisenter<AddBean> onNetLisenter);


}
