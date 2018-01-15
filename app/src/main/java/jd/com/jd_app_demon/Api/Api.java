package jd.com.jd_app_demon.Api;

/**
 * Created by 崔 on 2017/12/27.
 */

public class Api {
    //公共网址
    public static final String BASEURL = "https://www.zhaoapi.cn/";
    //首页
    public static final String HOME = "ad/getAd";
    //登录
    public static final String LOGIN = "user/login";
    //注册
    public static final String REG = "user/reg";
    //分类
    public static final String FENLEI = "http://120.27.23.105/product/getCatagory?source=android";
    //左边列表
    public static final String LEFT = "product/getCatagory";
    //右边列表
    public static final String RIGHT = "product/getProductCatagory";
    //当前子分类下的商品列表（分页）
    public static final String CHILD = "product/getProducts";
    //商品详情
    public static final String DETAIL = "product/getProductDetail";
    //添加购物车
    public static final String ADDSHOP = "product/addCart";
    //删除购物车
    public static final String DELETE = "product/deleteCart";
    //查询购物车
    public static final String SELECTSHOP = "product/getCarts";
    //获取用户信息
    public static final String USER = "user/getUserInfo";
    //上传头像
    public static final String HEAD = "file/upload";
    //更新购物车
    public static final String UPDATE = "product/updateCarts";
    //根据关键词搜索商品
    public static final String search = "product/searchProducts";



}
