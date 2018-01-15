package jd.com.jd_app_demon.present;

import jd.com.jd_app_demon.bean.HomeBean;
import jd.com.jd_app_demon.model.GetHome;
import jd.com.jd_app_demon.net.OnNetLisenter;
import jd.com.jd_app_demon.view.HomeView;

/**
 * Created by 崔 on 2017/12/29.
 */

public class HomePresenter   {
    private HomeView view;
    private GetHome model;


    public HomePresenter(HomeView view) {
        this.view = view;
        model = new GetHome();
    }

    public void home(){
        model.getHome(new OnNetLisenter<HomeBean>() {
            @Override
            public void onSuccess(HomeBean bean) {
              //  view.onSuccess(bean);
                view.setHomeBean(bean);
            }

            @Override
            public void onDefault(Throwable throwable) {
                //view.onDefault(throwable);
            throwable.printStackTrace();

            }
        });

    }







}
