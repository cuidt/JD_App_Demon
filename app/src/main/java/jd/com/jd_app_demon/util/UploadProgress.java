package jd.com.jd_app_demon.util;

import android.util.Log;

public class UploadProgress implements UploadCallback {

    private String jindu;
    private UploadProgress(){}
    private static class UploadProgressHolder{
       public static final UploadProgress INSTANCE=new UploadProgress();
    }
    public static UploadProgress getInstance(){
        return UploadProgressHolder.INSTANCE;
    }
    @Override
    public void onProgress(final long progress, final long total) {
        jindu = "上传进度：" + progress * 100 / total + "%";
        Log.i("Upload", "上传进度：" + progress * 100 / total + "%");
    }

    public String getJidu() {
        return jindu;
    }



}
