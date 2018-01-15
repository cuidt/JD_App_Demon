package jd.com.jd_app_demon.model;

import java.util.Map;

import io.reactivex.functions.Consumer;
import jd.com.jd_app_demon.bean.UpDataCardBean;
import jd.com.jd_app_demon.net.HttpMethods;
import jd.com.jd_app_demon.net.OnNetLisenter;

/**
 * 作者： 崔冬涛
 * 时间： 2018/1/12.
 */

public class UpdateCartModel implements IUpdateModel {
    @Override
    public void updateShopCart(Map<String, String> map, final OnNetLisenter<UpDataCardBean> onNetListener) {
        HttpMethods.getInstnce().update(map, new Consumer<UpDataCardBean>() {
            @Override
            public void accept(UpDataCardBean upDataCardBean) throws Exception {
                onNetListener.onSuccess(upDataCardBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                onNetListener.onDefault(throwable);
            }
        });
    }
}
