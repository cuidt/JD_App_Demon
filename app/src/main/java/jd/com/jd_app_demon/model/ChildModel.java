package jd.com.jd_app_demon.model;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.functions.Consumer;
import jd.com.jd_app_demon.bean.ChildBean;
import jd.com.jd_app_demon.net.HttpMethods;
import jd.com.jd_app_demon.net.OnNetLisenter;

/**
 * 作者： 崔冬涛
 * 时间： 2018/1/5.
 */

public class ChildModel implements IChileModel {

    @Override
    public void gerChild(int pscid, final OnNetLisenter<ChildBean> onNetLisenter) {
        Map<String, String> map = new HashMap<>();
        map.put("pscid", pscid + "");
        HttpMethods.getInstnce().child(map, new Consumer<ChildBean>() {
            @Override
            public void accept(ChildBean childBean) throws Exception {
                onNetLisenter.onSuccess(childBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                onNetLisenter.onDefault(throwable);
            }
        });
    }
}
