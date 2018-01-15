package jd.com.jd_app_demon.fragment.home_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import jd.com.jd_app_demon.Api.Api;
import jd.com.jd_app_demon.R;
import jd.com.jd_app_demon.adapter.Gvadapter;
import jd.com.jd_app_demon.bean.Fenlei_bean;
import jd.com.jd_app_demon.util.ToastUtils;


public class Fragment_2s extends Fragment{
    private List<String> list;
    private List<String> listimg;
    private GridView gv1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(), R.layout.item2,null);
        gv1= (GridView) view.findViewById(R.id.gv1);
        RequestParams params=new RequestParams(Api.FENLEI);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                Fenlei_bean bean = gson.fromJson(result, Fenlei_bean.class);
                list=new ArrayList<String>();
                listimg=new ArrayList<String>();
                for(int i=0;i<10;i++){
                    list.add(bean.getData().get(i+9).getName());
                    listimg.add(bean.getData().get(i+9).getIcon());
                }
                Gvadapter myadapter=new Gvadapter(listimg,list,getActivity());
                gv1.setAdapter(myadapter);
                gv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ToastUtils.showShortToast(getActivity(), position+"条目");
                    }
                });
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


        return view;
    }
}

