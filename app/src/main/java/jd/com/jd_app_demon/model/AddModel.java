package jd.com.jd_app_demon.model;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.functions.Consumer;
import jd.com.jd_app_demon.bean.AddBean;
import jd.com.jd_app_demon.net.HttpMethods;
import jd.com.jd_app_demon.net.OnNetLisenter;

/**
 * 作者： 崔冬涛
 * 时间： 2018/1/9.
 */

public class AddModel implements IAddModel {


    @Override
    public void getAdd(int pid, int uid, final OnNetLisenter<AddBean> onNetLisenter) {
        Map<String,String> map =new HashMap<>();
        map.put("uid",uid+"");
        map.put("pid",pid+"");
        map.put("source","android");

        HttpMethods.getInstnce().addshop(map, new Consumer<AddBean>() {
            @Override
            public void accept(AddBean addBean) throws Exception {
                onNetLisenter.onSuccess(addBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                onNetLisenter.onDefault(throwable);
            }
        });
    }
}
