package jd.com.jd_app_demon.net;

/**
 * Created by 崔 on 2017/12/28.
 */

public interface OnNetLisenter<T> {

    void onSuccess(T t);
    void onDefault(Throwable throwable);

}
