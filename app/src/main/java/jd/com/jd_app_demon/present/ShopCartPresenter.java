package jd.com.jd_app_demon.present;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import jd.com.jd_app_demon.bean.DeleteBean;
import jd.com.jd_app_demon.bean.SelectBean;
import jd.com.jd_app_demon.bean.UpDataCardBean;
import jd.com.jd_app_demon.model.DeleteModel;
import jd.com.jd_app_demon.model.IShopCartModel;
import jd.com.jd_app_demon.model.ShopCartModel;
import jd.com.jd_app_demon.model.UpdateCartModel;
import jd.com.jd_app_demon.net.OnNetLisenter;
import jd.com.jd_app_demon.view.IShopCartView;

/**
 * 作者： 崔冬涛
 * 时间： 2018/1/9.
 */

public class ShopCartPresenter {
    private IShopCartView view;
    private final IShopCartModel model;
    private DeleteModel deleteModel;
    private UpdateCartModel iUpdateCartModel;


    public ShopCartPresenter(IShopCartView view) {
        model = new ShopCartModel();
        deleteModel=new DeleteModel();
        iUpdateCartModel=new UpdateCartModel();
        this.view = view;
    }

    public void shopCart() {
        int uid = view.getUid();
        model.shopCart(uid, new OnNetLisenter<SelectBean>() {
            @Override
            public void onSuccess(SelectBean selectBean) {
                view.setShopCartBean(selectBean);
            }

            @Override
            public void onDefault(Throwable throwable) {
                throwable.printStackTrace();
            }


        });
    }


    public void delete(){
        int pid = view.getPid();
        int uid = view.getUid();
        deleteModel.deleteShop(uid, pid, new OnNetLisenter<DeleteBean>() {
            @Override
            public void onSuccess(DeleteBean deleteBean) {
                Log.d("DeleteBean",deleteBean.getMsg());
            }

            @Override
            public void onDefault(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    public void update(String sellerid, int pid, int num, boolean selected){
        int uid = view.getUid();
        Map<String,String> map = new HashMap<>();
        map.put("uid",uid+"");
        map.put("sellerid",sellerid);
        map.put("pid",pid+"");
        map.put("num",num+"");
        if(selected){
            map.put("selected","1");
        }else {
            map.put("selected","0");
        }
        iUpdateCartModel.updateShopCart(map, new OnNetLisenter<UpDataCardBean>() {
            @Override
            public void onSuccess(UpDataCardBean upDataCardBean) {
                Log.d("upDataCardBean",upDataCardBean.getMsg());
            }

            @Override
            public void onDefault(Throwable throwable) {
                throwable.printStackTrace();

            }
        });

    }

}
