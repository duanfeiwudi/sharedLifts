package base;

import android.app.Application;
import android.os.StrictMode;

/**
 * 作�?�：${weihaizhou} on 2017/6/26 16:15
 */
public class AppContext extends Application {

    private static AppContext mContext;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
 
    }


    public static AppContext getContext() {
        return mContext;
    }


}
