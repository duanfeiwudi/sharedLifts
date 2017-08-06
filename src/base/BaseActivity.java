package base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
 
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

 
 
public abstract class BaseActivity extends Activity {

 

    @Override
    protected void onCreate( Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        setContentView(LayoutInflater.from(this).inflate(getContentView(), null));
     
        initData();
        initWidget();
      
    }

 

    /**
     * 返回界面的layoutId
     *
     * @return
     */
    protected abstract int getContentView();
 

    /**
     * 初始化数据
     */
    protected abstract void initWidget();

    /**
     * 初始化界面
     */
    protected abstract void initData();

 
}
