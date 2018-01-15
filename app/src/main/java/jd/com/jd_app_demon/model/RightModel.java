package jd.com.jd_app_demon.model;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.functions.Consumer;
import jd.com.jd_app_demon.bean.RightBean;
import jd.com.jd_app_demon.net.HttpMethods;
import jd.com.jd_app_demon.net.OnNetLisenter;

/**
 * 作者： 崔冬涛
 * 时间： 2018/1/4.
 */

public class RightModel implements IRightModel {

    @Override
    public void getRight(int cid, final OnNetLisenter<RightBean> onNetLisenter) {
        Map<String, String> map = new HashMap<>();
        map.put("cid",cid+"");
        HttpMethods.getInstnce().right(map, new Consumer<RightBean>() {
            @Override
            public void accept(RightBean rightBean) throws Exception {
                onNetLisenter.onSuccess(rightBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                onNetLisenter.onDefault(throwable);
            }
        });

    }
}
