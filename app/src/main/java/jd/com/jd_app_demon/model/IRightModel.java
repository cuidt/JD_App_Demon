package jd.com.jd_app_demon.model;

import jd.com.jd_app_demon.bean.RightBean;
import jd.com.jd_app_demon.net.OnNetLisenter;

/**
 * 作者： 崔冬涛
 * 时间： 2018/1/4.
 */

public interface IRightModel {



    void getRight(int cid,OnNetLisenter<RightBean> onNetLisenter);


}



