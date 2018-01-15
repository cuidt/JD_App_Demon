package jd.com.jd_app_demon.util;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import org.xutils.BuildConfig;
import org.xutils.x;

/**
 * Created by 崔 on 2017/12/27.
 */

public class RegistApplication extends Application {
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
        Fresco.initialize(this);

        MySharedPreferences.init(this);

    }
}
