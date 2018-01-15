package jd.com.jd_app_demon.model;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.functions.Consumer;
import jd.com.jd_app_demon.bean.GainBean;
import jd.com.jd_app_demon.net.HttpMethods;
import jd.com.jd_app_demon.net.OnNetLisenter;

/**
 * 作者： 崔冬涛
 * 时间： 2018/1/9.
 */

public class USerModel implements IUserModel{


    @Override
    public void getUid(int uid, final OnNetLisenter<GainBean> onNetLisenter) {
        Map<String,String> map =new HashMap<>();
        map.put("uid",uid+"");
        HttpMethods.getInstnce().getuser(map, new Consumer<GainBean>() {
            @Override
            public void accept(GainBean gainBean) throws Exception {
                onNetLisenter.onSuccess(gainBean);

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                onNetLisenter.onDefault(throwable);
            }
        });
    }

}
