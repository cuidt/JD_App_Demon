package jd.com.jd_app_demon.present;

import jd.com.jd_app_demon.bean.DetailBean;
import jd.com.jd_app_demon.model.DetailModel;
import jd.com.jd_app_demon.net.OnNetLisenter;
import jd.com.jd_app_demon.view.IDetailView;

/**
 * 作者： 崔冬涛
 * 时间： 2018/1/7.
 */

public class DetailPresenter {

    private IDetailView iDetailView;
    private DetailModel detailModel;

    public DetailPresenter(IDetailView iDetailView) {
        this.iDetailView = iDetailView;
        detailModel = new DetailModel();
    }

    public void getDetail() {
        int pid = iDetailView.getPid();
        detailModel.getDetail(pid, new OnNetLisenter<DetailBean>() {
            @Override
            public void onSuccess(DetailBean detailBean) {
                iDetailView.setDetail(detailBean);
            }

            @Override
            public void onDefault(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

}
