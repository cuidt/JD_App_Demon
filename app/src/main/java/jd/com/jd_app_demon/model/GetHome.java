package jd.com.jd_app_demon.model;

import io.reactivex.functions.Consumer;
import jd.com.jd_app_demon.bean.HomeBean;
import jd.com.jd_app_demon.net.HttpMethods;
import jd.com.jd_app_demon.net.OnNetLisenter;

/**
 * Created by 崔 on 2017/12/28.
 */

public class GetHome  implements HomeModel{


    @Override
    public void getHome(final OnNetLisenter<HomeBean> onNetLisenter) {
        HttpMethods.getInstnce().getHome(new Consumer<HomeBean>() {
            @Override
            public void accept(HomeBean homeBean) throws Exception {
                onNetLisenter.onSuccess(homeBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                onNetLisenter.onDefault(throwable);
            }
        });

    }




}
