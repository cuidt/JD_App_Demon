package jd.com.jd_app_demon.model;

import jd.com.jd_app_demon.bean.SearchBean;
import jd.com.jd_app_demon.net.OnNetLisenter;

/**
 * 作者： 崔冬涛
 * 时间： 2018/1/12.
 */

public interface ISearchModel {

    void getSearch(String search, OnNetLisenter<SearchBean> onNetLisenter);

}
