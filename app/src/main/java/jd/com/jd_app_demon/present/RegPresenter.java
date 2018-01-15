package jd.com.jd_app_demon.present;

import jd.com.jd_app_demon.bean.RegistBean;
import jd.com.jd_app_demon.model.GetRegModel;
import jd.com.jd_app_demon.model.IRegModel;
import jd.com.jd_app_demon.net.OnNetLisenter;
import jd.com.jd_app_demon.view.IRegView;

/**
 * Created by 崔 on 2017/12/28.
 */

public class RegPresenter  {

    private IRegView view;
    private final IRegModel model;

    public RegPresenter(IRegView view) {
        model = new GetRegModel();
        this.view = view;
    }

    public void reg(){
        String mobile = view.getMobile();
        String password = view.getPassword();
        model.reg(mobile, password, new OnNetLisenter<RegistBean>() {
            @Override
            public void onSuccess(RegistBean registBean) {
                if(registBean.getCode().equals("0")){
                    view.setReg(registBean);
                }
            }

            @Override
            public void onDefault(Throwable throwable) {
                throwable.printStackTrace();
            }
        });

    }


}
