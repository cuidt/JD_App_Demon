package jd.com.jd_app_demon.present;

import jd.com.jd_app_demon.bean.LeftBean;
import jd.com.jd_app_demon.model.LeftModel;
import jd.com.jd_app_demon.net.OnNetLisenter;
import jd.com.jd_app_demon.view.LeftView;

/**
 * Created by 崔 on 2018/1/4.
 */

public class LeftPresenter {

    private LeftView view;
    private LeftModel leftModel;

    public LeftPresenter(LeftView view) {
         this.view = view;
         leftModel = new LeftModel();
    }

    public void left(){
        leftModel.getLeft(new OnNetLisenter<LeftBean>() {
            @Override
            public void onSuccess(LeftBean leftBean) {
                view.setLeftBean(leftBean);
            }

            @Override
            public void onDefault(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }


}
