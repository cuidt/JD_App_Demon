package jd.com.jd_app_demon.present;

import jd.com.jd_app_demon.bean.GainBean;
import jd.com.jd_app_demon.bean.HandPortraitBean;
import jd.com.jd_app_demon.model.ILoadHeadPortraitModel;
import jd.com.jd_app_demon.model.LoadHeadPortraitModel;
import jd.com.jd_app_demon.model.USerModel;
import jd.com.jd_app_demon.net.OnNetLisenter;
import jd.com.jd_app_demon.view.IUserView;

/**
 * 作者： 崔冬涛
 * 时间： 2018/1/9.
 */

public class UserPresenter  {
    USerModel uSerModel;
    IUserView iUserView;
    ILoadHeadPortraitModel iLoadHeadPortraitModel;

    public UserPresenter(  IUserView iUserView) {
        uSerModel = new USerModel();
        this.iUserView = iUserView;
        iLoadHeadPortraitModel=new LoadHeadPortraitModel();

    }

    public void getUser(){

        int  uid = iUserView.getUid();
        uSerModel.getUid(uid, new OnNetLisenter<GainBean>() {
            @Override
            public void onSuccess(GainBean gainBean) {
                iUserView.isAddShop(gainBean);

            }

            @Override
            public void onDefault(Throwable throwable) {
                throwable.printStackTrace();

            }
        });
    }

    //上传头像
    public void loadHandPortrait() {
        int uid = iUserView.getUid();
        String imagePath = iUserView.getImagePath();
        iLoadHeadPortraitModel.uploadPhoto(uid, imagePath, new OnNetLisenter<HandPortraitBean>() {
            @Override
            public void onSuccess(HandPortraitBean bean) {
                iUserView.setLoadHandPortrait(bean);
            }

            @Override
            public void onDefault(Throwable t) {
                t.printStackTrace();
            }
        });
    }






}
