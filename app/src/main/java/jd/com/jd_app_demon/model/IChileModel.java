package jd.com.jd_app_demon.model;

import jd.com.jd_app_demon.bean.ChildBean;
import jd.com.jd_app_demon.net.OnNetLisenter;

/**
 * 作者： 崔冬涛
 * 时间： 2018/1/5.
 */

public interface IChileModel {

   // void getRight(int cid,OnNetLisenter<RightBean> onNetLisenter);
      void gerChild(int pscid, OnNetLisenter<ChildBean> onNetLisenter);


}
