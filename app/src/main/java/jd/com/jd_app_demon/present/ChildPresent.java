package jd.com.jd_app_demon.present;

import jd.com.jd_app_demon.bean.ChildBean;
import jd.com.jd_app_demon.bean.SearchBean;
import jd.com.jd_app_demon.model.ChildModel;
import jd.com.jd_app_demon.model.SearchModel;
import jd.com.jd_app_demon.net.OnNetLisenter;
import jd.com.jd_app_demon.view.IChildView;

/**
 * 作者： 崔冬涛
 * 时间： 2018/1/5.
 */

public class ChildPresent {

    IChildView iChildView;
    ChildModel childModel;
    SearchModel searchModel;

    public ChildPresent(IChildView iChildView) {
        this.iChildView = iChildView;
        childModel = new ChildModel();
        searchModel = new SearchModel();
    }

    public void getChild() {

        int pscid = iChildView.getPscid();
        childModel.gerChild(pscid, new OnNetLisenter<ChildBean>() {
            @Override
            public void onSuccess(ChildBean childBean) {
                iChildView.setChildBean(childBean);
            }

            @Override
            public void onDefault(Throwable throwable) {
                throwable.printStackTrace();
            }
        });

    }


    public void getSearch() {
        String search = iChildView.getSearch();
        searchModel.getSearch(search, new OnNetLisenter<SearchBean>() {
            @Override
            public void onSuccess(SearchBean searchBean) {
                iChildView.setSearchBean(searchBean);

            }

            @Override
            public void onDefault(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }


}
