package jd.com.jd_app_demon.model;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.functions.Consumer;
import jd.com.jd_app_demon.bean.DetailBean;
import jd.com.jd_app_demon.net.HttpMethods;
import jd.com.jd_app_demon.net.OnNetLisenter;

/**
 * 作者： 崔冬涛
 * 时间： 2018/1/7.
 */

public class DetailModel implements IDetaileModel {

    @Override
    public void getDetail(int pid, final OnNetLisenter<DetailBean> onNetLisenter) {
        Map<String, String> map = new HashMap<>();
        map.put("pid",pid+"");
        map.put("source","android");
        HttpMethods.getInstnce().detail(map, new Consumer<DetailBean>() {
            @Override
            public void accept(DetailBean detailBean) throws Exception {
                onNetLisenter.onSuccess(detailBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                onNetLisenter.onDefault(throwable);
            }
        });
    }
}