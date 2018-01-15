package jd.com.jd_app_demon.util;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;


public class ToastUtils {

    private ToastUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /* 是否弹toast总开关 */
    public static boolean isShow = true;

    public static void showStaticToast(final Activity act, final String msg) {
        //获取当前线程
        String nowThreadName = Thread.currentThread().getName();
        //如果为主线程
        if ("main".equals(nowThreadName)) {
            if (isShow)
                showToast(act, msg);

            //如果为子线程
        } else {
            act.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (isShow)
                        showToast(act, msg);
                }
            });
        }
    }


    private static Toast toast;

    //如果在主线程弹静态吐司可以使用这个，传入上下文即可
    public static void showToast(Context context, final String msg) {

        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        if (isShow)
            toast.show();
    }

    /**
     * 短时间显示Toast
     */
    public static void showShortToast(Context context, String message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     */
    public static void showLongToast(Context context, String message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

}
