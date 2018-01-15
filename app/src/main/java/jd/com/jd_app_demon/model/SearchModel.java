package jd.com.jd_app_demon.model;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.functions.Consumer;
import jd.com.jd_app_demon.bean.SearchBean;
import jd.com.jd_app_demon.net.HttpMethods;
import jd.com.jd_app_demon.net.OnNetLisenter;

/**
 * 作者： 崔冬涛
 * 时间： 2018/1/12.
 */

public class SearchModel implements ISearchModel {

    @Override
    public void getSearch(String search, final OnNetLisenter<SearchBean> onNetLisenter) {
        Map<String,String> map =new HashMap<>();
        map.put("keywords",search);
        map.put("source","android");
        HttpMethods.getInstnce().search(map, new Consumer<SearchBean>() {
            @Override
            public void accept(SearchBean searchBean) throws Exception {
                onNetLisenter.onSuccess(searchBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                onNetLisenter.onDefault(throwable);
            }
        });
    }
}
