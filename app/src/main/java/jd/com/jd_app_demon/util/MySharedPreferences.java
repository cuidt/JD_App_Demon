package jd.com.jd_app_demon.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * SharedPreferences轻量级存储，自定义重写
 * 王斌
 * 2017/12/6
 */

public class MySharedPreferences {
    private static SharedPreferences mSP = null;

    public synchronized static void init(Context context) {
        if (mSP == null) {
            mSP = context.getSharedPreferences("privates", Context.MODE_PRIVATE);
        }
    }

    /**
     * 清除存储
     * @return
     */
    public static boolean clear() {
        boolean bRet = false;
        if (mSP != null) {
            bRet = mSP.edit().clear().commit();
        }
        return bRet;
    }

    public static boolean putBoolean(String name, boolean value) {
        boolean bRet = value;
        if (mSP != null) {
            SharedPreferences.Editor editor = mSP.edit();
            editor.putBoolean(name, value);
            bRet = editor.commit();
        }
        return bRet;
    }

    public static boolean getBoolean(String name, boolean defaultValue) {
        boolean bRet = defaultValue;
        if (mSP != null) {
            bRet = mSP.getBoolean(name, defaultValue);
        }
        return bRet;
    }

    public static boolean putLong(String name, long value) {
        boolean bRet = false;
        if (mSP != null) {
            SharedPreferences.Editor editor = mSP.edit();
            editor.putLong(name, value);
            bRet = editor.commit();
        }
        return bRet;
    }

    public static long getLong(String name, long defaultValue) {
        long lRet = defaultValue;
        if (mSP != null) {
            lRet = mSP.getLong(name, defaultValue);
        }
        return lRet;
    }

    public static boolean putInt(String name, int value) {
        boolean bRet = false;
        if (mSP != null) {
            SharedPreferences.Editor editor = mSP.edit();
            editor.putInt(name, value);
            bRet = editor.commit();

        }
        return bRet;
    }

    public static int getInt(String name, int defaultValue) {
        int iRet = defaultValue;
        if (mSP != null) {
            iRet = mSP.getInt(name, defaultValue);
        }
        return iRet;
    }

    public static boolean putFloat(String name, float value) {
        boolean bRet = false;
        if (mSP != null) {
            SharedPreferences.Editor editor = mSP.edit();
            editor.putFloat(name, value);
            bRet = editor.commit();
        }
        return bRet;
    }

    public static float getFloat(String name, float defaultValue) {
        float fRet = defaultValue;
        if (mSP != null) {
            fRet = mSP.getFloat(name, defaultValue);
        }
        return fRet;
    }

    public static boolean putString(String name, String value) {
        boolean bRet = false;
        if (mSP != null) {
            SharedPreferences.Editor editor = mSP.edit();
            editor.putString(name, value);
            bRet = editor.commit();
        }
        return bRet;
    }

    public static String getString(String name, String defaultValue) {
        String sRet = defaultValue;
        if (mSP != null) {
            sRet = mSP.getString(name, defaultValue);
        }
        return sRet;
    }

    public static boolean putStringSet(String name, Set value) {
        boolean bRet = false;
        if (mSP != null) {
            SharedPreferences.Editor editor = mSP.edit();
            editor.putStringSet(name, value);
            bRet = editor.commit();
        }
        return bRet;
    }

    public static Set getStringSet(String name, Set defaultValue) {
        Set sRet = defaultValue;
        if (mSP != null) {
            sRet = mSP.getStringSet(name, defaultValue);
        }
        return sRet;
    }
}
