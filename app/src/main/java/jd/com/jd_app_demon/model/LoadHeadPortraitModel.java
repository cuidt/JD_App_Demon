package jd.com.jd_app_demon.model;

import java.io.File;

import io.reactivex.functions.Consumer;
import jd.com.jd_app_demon.bean.HandPortraitBean;
import jd.com.jd_app_demon.net.HttpMethods;
import jd.com.jd_app_demon.net.OnNetLisenter;
import jd.com.jd_app_demon.util.PhotoRequestBody;
import jd.com.jd_app_demon.util.UploadProgress;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 崔冬涛
 * 2018/1/10
 */

public class LoadHeadPortraitModel implements ILoadHeadPortraitModel {
    @Override
    public void uploadPhoto(int uid, String filePath, final OnNetLisenter<HandPortraitBean> onNetListener) {
        File file = new File(filePath);
        if (file == null || !file.exists()) {
            return;
        }
        final UploadProgress progress = UploadProgress.getInstance();
        RequestBody body = RequestBody.create(MediaType.parse("application/otcet-stream"), file);
        PhotoRequestBody photoRequestBody = new PhotoRequestBody(body, progress);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), photoRequestBody);
        HttpMethods.getInstnce().loadHeadPortrait(uid, part, new Consumer<HandPortraitBean>() {
            @Override
            public void accept(HandPortraitBean handPortraitBean) throws Exception {
                onNetListener.onSuccess(handPortraitBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                onNetListener.onDefault(throwable);
            }
        });
    }





}
