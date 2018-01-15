package jd.com.jd_app_demon.model;

import io.reactivex.functions.Consumer;
import jd.com.jd_app_demon.bean.LeftBean;
import jd.com.jd_app_demon.net.HttpMethods;
import jd.com.jd_app_demon.net.OnNetLisenter;

/**
 * Created by 崔 on 2018/1/4.
 */

public class LeftModel implements ILeftModel {


    @Override
    public void getLeft(final OnNetLisenter<LeftBean> onNetLisenter) {
        HttpMethods.getInstnce().left(new Consumer<LeftBean>() {
            @Override
            public void accept(LeftBean leftBean) throws Exception {
                    onNetLisenter.onSuccess(leftBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                onNetLisenter.onDefault(throwable);
            }
        });
    }


   /* @Override
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

    }*/




}
