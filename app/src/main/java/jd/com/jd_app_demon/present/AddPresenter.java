package jd.com.jd_app_demon.present;

import jd.com.jd_app_demon.bean.AddBean;
import jd.com.jd_app_demon.model.AddModel;
import jd.com.jd_app_demon.net.OnNetLisenter;
import jd.com.jd_app_demon.view.IDetailView;

/**
 * 作者： 崔冬涛
 * 时间： 2018/1/9.
 */

public class AddPresenter  {

    IDetailView iDetailView;
    AddModel  AddModel;

    public AddPresenter(IDetailView iDetailView) {
        this.iDetailView = iDetailView;
         AddModel = new AddModel();
    }

    public void getAddShop(){
        int pid = iDetailView.getPid();
        int uid = iDetailView.getUid();
        AddModel.getAdd(pid, uid, new OnNetLisenter<AddBean>() {
            @Override
            public void onSuccess(AddBean addBean) {
                iDetailView.isAddShop(addBean);
            }

            @Override
            public void onDefault(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }



}
