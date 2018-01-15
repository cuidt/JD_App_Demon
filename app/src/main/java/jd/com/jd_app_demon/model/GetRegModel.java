package jd.com.jd_app_demon.model;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.functions.Consumer;
import jd.com.jd_app_demon.bean.RegistBean;
import jd.com.jd_app_demon.net.HttpMethods;
import jd.com.jd_app_demon.net.OnNetLisenter;

/**
 * Created by 崔 on 2017/12/28.
 */

public class GetRegModel implements IRegModel {
    @Override
    public void reg(String mobile, String password, final OnNetLisenter<RegistBean> onNetLisenter) {
        Map<String,String> map= new HashMap<>();
        map.put("mobile",mobile);
        map.put("password",password);
        HttpMethods.getInstnce().reg(map, new Consumer<RegistBean>() {
            @Override
            public void accept(RegistBean registBean) throws Exception {
                onNetLisenter.onSuccess(registBean);

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                onNetLisenter.onDefault(throwable);
            }
        });



    }


}
