package jd.com.jd_app_demon.present;

import jd.com.jd_app_demon.bean.RightBean;
import jd.com.jd_app_demon.model.RightModel;
import jd.com.jd_app_demon.net.OnNetLisenter;
import jd.com.jd_app_demon.view.LeftView;

/**
 * 作者： 崔冬涛
 * 时间： 2018/1/4.
 */

public class RightPresenter {

    private LeftView leftView;
    private RightModel rightModel;

    public RightPresenter(LeftView leftView) {
        this.leftView = leftView;
         rightModel = new RightModel();
    }

    public void getRight(){
        int cid = leftView.getCid();
        rightModel.getRight(cid, new OnNetLisenter<RightBean>() {
            @Override
            public void onSuccess(RightBean rightBean) {
                //rightView.getCid();
                leftView.setRightBean(rightBean);

            }

            @Override
            public void onDefault(Throwable throwable) {
                throwable.printStackTrace();
            }
        });

    }



}
