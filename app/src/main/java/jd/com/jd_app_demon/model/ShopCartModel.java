package jd.com.jd_app_demon.model;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.functions.Consumer;
import jd.com.jd_app_demon.bean.SelectBean;
import jd.com.jd_app_demon.net.HttpMethods;
import jd.com.jd_app_demon.net.OnNetLisenter;

/**
 * 作者： 崔冬涛
 * 时间： 2018/1/9.
 */

public class ShopCartModel implements IShopCartModel {
    @Override
    public void shopCart(int uid, final OnNetLisenter<SelectBean> onNetLisenter) {
        Map<String,String> map=new HashMap<>();
        map.put("uid",uid+"");
        map.put("source","android");
        HttpMethods.getInstnce().shopCart(map, new Consumer<SelectBean>() {
            @Override
            public void accept(SelectBean selectBean) throws Exception {
                onNetLisenter.onSuccess(selectBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                onNetLisenter.onDefault(throwable);
            }
        });
    }
}
