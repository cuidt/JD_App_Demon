package jd.com.jd_app_demon.model;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.functions.Consumer;
import jd.com.jd_app_demon.bean.DeleteBean;
import jd.com.jd_app_demon.net.HttpMethods;
import jd.com.jd_app_demon.net.OnNetLisenter;

/**
 * 作者： 崔冬涛
 * 时间： 2018/1/10.
 */

public class DeleteModel implements IDeleteModel {

    @Override
    public void deleteShop(int uid, int pid, final OnNetLisenter<DeleteBean> onNetLisenter) {
        Map<String, String> map = new HashMap<>();
        map.put("uid",uid+"");
        map.put("pid",pid+"");
        map.put("source","android");
        HttpMethods.getInstnce().delete(map, new Consumer<DeleteBean>() {
            @Override
            public void accept(DeleteBean deleteBean) throws Exception {
                onNetLisenter.onSuccess(deleteBean);

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                onNetLisenter.onDefault(throwable);
            }
        });

    }
}
