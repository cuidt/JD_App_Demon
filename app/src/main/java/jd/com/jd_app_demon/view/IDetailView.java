package jd.com.jd_app_demon.view;

import jd.com.jd_app_demon.bean.AddBean;
import jd.com.jd_app_demon.bean.DetailBean;

/**
 * 作者： 崔冬涛
 * 时间： 2018/1/7.
 */

public interface IDetailView {


    int getPid();
    void setDetail(DetailBean bean);

    int getUid();
    void isAddShop(AddBean addBean);


}
