package com.bgi.supervision.jujube;
import android.app.Application;
import com.bgi.supervision.jujubeutils.jUtils;


/**
 * Created by majian
 * on 2019/4/26
 * copyright:BGI
 * Describe:
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        jUtils.initialize(this);

    }
}
