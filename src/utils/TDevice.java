package utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

 
import base.AppContext;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * 作�?�：${weihaizhou} on 2016/5/26 10:11
 */
public class TDevice {
    /**
     * 判断是否有网�?
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    public static void hideSoftKeyboard(View view) {
        if (view == null) return;
        View mFocusView = view;

        Context context = view.getContext();
        if (context != null && context instanceof Activity) {
            Activity activity = ((Activity) context);
            mFocusView = activity.getCurrentFocus();
        }
        if (mFocusView == null) return;
        mFocusView.clearFocus();
        InputMethodManager manager = (InputMethodManager) mFocusView.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(mFocusView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void showSoftKeyboard(View view) {
        if (view == null) return;
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        if (!view.isFocused()) view.requestFocus();

        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(view, 0);
    }







    public static String getPhoneInfo() {
        String mtype = android.os.Build.MODEL; // 手机型号
        return mtype;
    }

    /**
     * <p>
     * Title: getAndroidSDKVersion
     * </p>
     * <p>
     * Description: 获取当前系统的AndroidSDK版本
     * </p>
     * <p>
     * </p>
     *
     * @return
     * @author zhangqy
     * @date 2015�?7�?10�? 下午5:12:30
     */
    private static int androidSdkVersion = -1;

    public static int getAndroidSDKVersion(Context context) {
        try {
            if (androidSdkVersion == -1) {
                androidSdkVersion = Integer.valueOf(android.os.Build.VERSION.SDK_INT);
            }
        } catch (Exception e) {
        }
        return androidSdkVersion;
    }

    /**
     * <p>
     * Title: getPackageName
     * </p>
     * <p>
     * Description: 取得apk的包�?
     * </p>
     * <p>
     * <p>
     * </p>
     *
     * @param context
     * @return
     * @author xwc1125
     * @date 2015-7-1上午10:53:00
     */
    private static int targetSdkVersion = -1;

    public static int getTargetSdkVersion(Context context) {
        if (context == null) {
            return targetSdkVersion;
        }
        if (targetSdkVersion == -1) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(),0);
                if (packageInfo != null) {
                    targetSdkVersion = packageInfo.applicationInfo.targetSdkVersion;
                }
            } catch (Exception e) {
            }
        }
        return targetSdkVersion;
    }


    /**
     * �?测是否安装微�?
     *
     * @param context
     * @return
     */
    public static boolean isSinaInstall(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取�?有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.sina.weibo")) {
                    return true;
                }
            }
        }

        return false;
    }

    public static float dp2px(float dp) {
        return dp * getDisplayMetrics().density;
    }

    public static float px2dp(float px) {
        return px / getDisplayMetrics().density;
    }

    public static DisplayMetrics getDisplayMetrics() {
        return AppContext.getContext().getResources().getDisplayMetrics();
    }




    // 判断市场是否存在的方�?
    public static boolean isAvilible(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取�?有已安装程序的包信息
        List<String> pName = new ArrayList<String>();// 用于存储�?有已安装程序的包�?
// 从pinfo中将包名字�?�一取出，压入pName list�?
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                pName.add(pn);
            }
        }
        return pName.contains(packageName);// 判断pName中是否有目标程序的包名，有TRUE，没有FALSE
    }

    /*
         * bitmap转base64
         */
    public static String bitmapToBase64(Bitmap bitmap) {
        String result = "";
        ByteArrayOutputStream bos = null;
        try {
            if (null != bitmap) {
                bos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);// 将bitmap放入字节数组流中

                bos.flush();// 将bos流缓存在内存中的数据全部输出，清空缓�?
                bos.close();

                byte[] bitmapByte = bos.toByteArray();
                result = Base64.encodeToString(bitmapByte, Base64.DEFAULT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static String FileToBase64(File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        InputStream inputStream = null;
        String result = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            result = Base64.encodeToString(bytes, Base64.DEFAULT);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static boolean hasInternet() {
        boolean flag;
        if (((ConnectivityManager) AppContext.getContext().getSystemService(
                "connectivity")).getActiveNetworkInfo() != null)
            flag = true;
        else
            flag = false;
        return flag;
    }
 
}