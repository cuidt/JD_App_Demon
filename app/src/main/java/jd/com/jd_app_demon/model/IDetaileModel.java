package jd.com.jd_app_demon.model;

import jd.com.jd_app_demon.bean.DetailBean;
import jd.com.jd_app_demon.net.OnNetLisenter;

/**
 * 作者： 崔冬涛
 * 时间： 2018/1/7.
 */

public interface IDetaileModel {

    void getDetail(int pid,OnNetLisenter<DetailBean> onNetLisenter);

}
