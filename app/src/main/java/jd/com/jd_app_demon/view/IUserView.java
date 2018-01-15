package jd.com.jd_app_demon.view;

import jd.com.jd_app_demon.bean.GainBean;
import jd.com.jd_app_demon.bean.HandPortraitBean;

/**
 * 作者： 崔冬涛
 * 时间： 2018/1/9.
 */

public interface IUserView {

    int getUid();
    void isAddShop(GainBean gainBean);

    String getImagePath();
    void setLoadHandPortrait(HandPortraitBean bean);


}
