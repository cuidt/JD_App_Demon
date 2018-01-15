package jd.com.jd_app_demon.view;

import jd.com.jd_app_demon.bean.ChildBean;
import jd.com.jd_app_demon.bean.SearchBean;

/**
 * 作者： 崔冬涛
 * 时间： 2018/1/6.
 */

public interface IChildView {

    int getPscid();
    void setChildBean(ChildBean bean);

    String getSearch();
    void setSearchBean(SearchBean searchBean);
    //void getSearch(String search, SearchBean searchBean);
}
