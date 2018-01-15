package jd.com.jd_app_demon.net;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import jd.com.jd_app_demon.Api.ApiService;
import jd.com.jd_app_demon.bean.AddBean;
import jd.com.jd_app_demon.bean.ChildBean;
import jd.com.jd_app_demon.bean.DeleteBean;
import jd.com.jd_app_demon.bean.DetailBean;
import jd.com.jd_app_demon.bean.GainBean;
import jd.com.jd_app_demon.bean.HandPortraitBean;
import jd.com.jd_app_demon.bean.HomeBean;
import jd.com.jd_app_demon.bean.LeftBean;
import jd.com.jd_app_demon.bean.LoginBean;
import jd.com.jd_app_demon.bean.RegistBean;
import jd.com.jd_app_demon.bean.RightBean;
import jd.com.jd_app_demon.bean.SearchBean;
import jd.com.jd_app_demon.bean.SelectBean;
import jd.com.jd_app_demon.bean.UpDataCardBean;
import okhttp3.MultipartBody;

/**
 * Created by 崔 on 2017/12/28.
 */
//写网络请求的方法
public class HttpMethods {

    private final ApiService service;

    //单例模式
    private HttpMethods() {
        service = HttpResService.getInstnce().getService();
    }

    private static HttpMethods instnce = null;

    public static HttpMethods getInstnce() {
        if (instnce == null) {
            synchronized (HttpMethods.class) {
                if (instnce == null) {
                    instnce = new HttpMethods();
                }
            }

        }
        return instnce;
    }

    private <T> void toConsumer(Observable<T> observable, Consumer<T> consumer, Consumer<Throwable> throwable) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer, throwable);
    }


    public void getHome(Consumer<HomeBean> consumer, Consumer<Throwable> throwable) {

        Observable<HomeBean> observable = service.gethome();
        toConsumer(observable, consumer, throwable);

    }

    public void login(Map<String, String> map, Consumer<LoginBean> consumer, Consumer<Throwable> throwable) {

        Observable<LoginBean> observable = service.login(map);
        toConsumer(observable, consumer, throwable);

    }

    public void reg(Map<String, String> map, Consumer<RegistBean> consumer, Consumer<Throwable> throwable) {

        Observable<RegistBean> observable = service.reg(map);
        toConsumer(observable, consumer, throwable);

    }

    public void left(Consumer<LeftBean> consumer, Consumer<Throwable> throwable) {
        Observable<LeftBean> observable = service.left();
        toConsumer(observable, consumer, throwable);

    }

    //右边的网络请求
    public void right(Map<String, String> map, Consumer<RightBean> consumer, Consumer<Throwable> throwable) {
        Observable<RightBean> observable = service.right(map);
        toConsumer(observable, consumer, throwable);
    }

    //点击右边展示商品列表
    public void child(Map<String, String> map, Consumer<ChildBean> consumer, Consumer<Throwable> throwable) {
        Observable<ChildBean> observable = service.child(map);
        toConsumer(observable, consumer, throwable);
    }

    //商品详情页面的网络请求
    public void detail(Map<String, String> map, Consumer<DetailBean> consumer, Consumer<Throwable> throwable) {
        Observable<DetailBean> observable = service.datail(map);
        toConsumer(observable, consumer, throwable);
    }

    //添加购物车
    public void addshop(Map<String, String> map, Consumer<AddBean> consumer, Consumer<Throwable> throwable) {
        Observable<AddBean> observable = service.addshop(map);
        toConsumer(observable, consumer, throwable);
    }

    //获取购物车
    public void shopCart(Map<String, String> map, Consumer<SelectBean> consumer, Consumer<Throwable> throwable) {
        Observable<SelectBean> observable = service.select(map);
        toConsumer(observable, consumer, throwable);
    }

    //删除购物车
    public void delete(Map<String, String> map, Consumer<DeleteBean> consumer, Consumer<Throwable> throwable) {
        Observable<DeleteBean> observable = service.delete(map);
        toConsumer(observable, consumer, throwable);
    }

    //获取用户信息
    public void getuser(Map<String, String> map, Consumer<GainBean> consumer, Consumer<Throwable> throwable) {
        Observable<GainBean> observable = service.userinfo(map);
        toConsumer(observable, consumer, throwable);
    }

    //上传头像
    public void loadHeadPortrait(int uid, MultipartBody.Part file, Consumer<HandPortraitBean> consumer, Consumer<Throwable> throwable) {
        Observable<HandPortraitBean> observable = service.loadHandPortrait(uid, file);
        toConsumer(observable, consumer, throwable);
    }

    //修改购物车
    public void update(Map<String, String> map, Consumer<UpDataCardBean> consumer, Consumer<Throwable> throwable) {
        Observable<UpDataCardBean> observable = service.update(map);
        toConsumer(observable, consumer, throwable);
    }

    //关键词搜索商品
    public void search(Map<String,String> map , Consumer<SearchBean> consumer,Consumer<Throwable> throwable){
        Observable<SearchBean> observable =service.search(map);
        toConsumer(observable, consumer, throwable);
    }


}
