package jd.com.jd_app_demon.Api;

import java.util.Map;

import io.reactivex.Observable;
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
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;

/**
 * Created by 崔 on 2017/12/27.
 */

public interface ApiService {
    //首页的接口
    @POST(Api.HOME)
    Observable<HomeBean> gethome();

    //登录接口
    @POST(Api.LOGIN)
    Observable<LoginBean> login(@QueryMap Map<String, String> map);

    //注册接口
    @POST(Api.REG)
    Observable<RegistBean> reg(@QueryMap Map<String, String> map);//QueryMap是请求的参数体

    //左侧列表
    @POST(Api.LEFT)
    Observable<LeftBean> left();

    //右侧列表
    @POST(Api.RIGHT)
    Observable<RightBean> right(@QueryMap Map<String, String> map);

    //子类分页
    @POST(Api.CHILD)
    Observable<ChildBean> child(@QueryMap Map<String, String> map);

    //商品详情
    @POST(Api.DETAIL)
    Observable<DetailBean> datail(@QueryMap Map<String, String> map);

    //添加购物车
    @POST(Api.ADDSHOP)
    Observable<AddBean> addshop(@QueryMap Map<String, String> map);

    //删除购物车
    @POST(Api.DELETE)
    Observable<DeleteBean> delete(@QueryMap Map<String, String> map);

    //修改购物车
    @POST(Api.UPDATE)
    Observable<UpDataCardBean> update(@QueryMap Map<String, String> map);

    //查询购物车
    @POST(Api.SELECTSHOP)
    Observable<SelectBean> select(@QueryMap Map<String, String> map);

    //获取用户信息
    @POST(Api.USER)
    Observable<GainBean> userinfo(@QueryMap Map<String, String> map);

    //上传头像
    @Multipart//上传文件需要的注解
    @POST(Api.HEAD)
    Observable<HandPortraitBean> loadHandPortrait(@Part("uid") int uid, @Part MultipartBody.Part file);

    //根据关键词搜索商品
    // 接口地址：https://www.zhaoapi.cn/product/searchProducts?keywords=笔记本&page=1

    @POST(Api.search)
    Observable<SearchBean> search(@QueryMap Map<String, String> map);


}
