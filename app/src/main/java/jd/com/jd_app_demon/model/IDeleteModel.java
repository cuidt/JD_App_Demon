package jd.com.jd_app_demon.model;

import jd.com.jd_app_demon.bean.DeleteBean;
import jd.com.jd_app_demon.net.OnNetLisenter;

/**
 * 作者： 崔冬涛
 * 时间： 2018/1/10.
 */

public  interface IDeleteModel {

    //void getDetail(int pid,OnNetLisenter<DetailBean> onNetLisenter);
    void deleteShop(int uid, int pid, OnNetLisenter<DeleteBean> onNetLisenter);


}
