package com.bgi.supervision.jujubeutils.j_utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.util.Log;

import com.bgi.supervision.jujubeutils.j_log.L;

/**
 * Created by majian
 * on 2019/4/25
 * copyright:BGI
 * Describe:
 * 主要功能:App网络管理
 * --获取网络状态的权限--
 * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
 */
public class AppNetworkUtil {

    //未找到合适匹配网络类型
    public static final int TYPE_NO = 0;
    public static final String TYPE_NO_STRING = "未找到合适匹配网络类型";

    //中国移动CMNET网络(中国移动GPRS接入方式之一, 主要为PC、笔记本电脑、PDA设立)
    public static final int TYPE_MOBILE_CMNET = 1;
    public static final String TYPE_MOBILE_CMNET_STRING = "中国移动CMNET网络(中国移动GPRS接入方式之一, 主要为PC、笔记本电脑、PDA设立)";

    //中国移动CMWAP网络(中国移动GPRS接入方式之一,主要为手机WAP上网而设立)
    public static final int TYPE_MOBILE_CMWAP = 2;
    public static final String TYPE_MOBILE_CMWAP_STRING = "中国移动CMWAP网络(中国移动GPRS接入方式之一,主要为手机WAP上网而设立)";

    //中国联通UNIWAP网络(中国联通划分GPRS接入方式之一, 主要为手机WAP上网而设立)
    public static final int TYPE_MOBILE_UNIWAP = 3;
    public static final String TYPE_MOBILE_UNIWAP_STRING = "中国联通UNIWAP网络(中国联通划分GPRS接入方式之一, 主要为手机WAP上网而设立)";

    //中国联通3GWAP网络
    public static final int TYPE_MOBILE_3GWAP = 4;
    public static final String TYPE_MOBILE_3GWAP_STRING = "中国联通3GWAP网络";

    //中国联通3HNET网络
    public static final int TYPE_MOBLIE_3GNET = 5;
    public static final String TYPE_MOBLIE_3GNET_STRING = "中国联通3HNET网络";

    //中国联通UNINET网络(中国联通划分GPRS接入方式之一, 主要为PC、笔记本电脑、PDA设立)
    public static final int TYPE_MOBILE_UNINET = 6;
    public static final String TYPE_MOBILE_UNINET_STRING = "中国联通UNINET网络(中国联通划分GPRS接入方式之一, 主要为PC、笔记本电脑、PDA设立)";

    //中国电信CTWAP网络
    public static final int TYPE_MOBILE_CTWAP = 7;
    public static final String TYPE_MOBILE_CTWAP_STRING = "中国电信CTWAP网络";

    //中国电信CTNET网络
    public static final int TYPE_MOBILE_CTNET = 8;
    public static final String TYPE_MOBILE_CTNET_STRING = "中国电信CTNET网络";

    //WIFI网络
    public static final int TYPE_WIFI = 10;
    public static final String TYPE_WIFI_STRING = "WIFI网络";


    /**
     * 获取当前手机连接的网络类型
     *
     * @param context 上下文
     * @return int 网络类型
     */
    public static int getNetworkState(Context context) {
        //获取ConnectivityManager对象
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //获得当前网络信息
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()) {
            //获取网络类型
            int currentNetWork = networkInfo.getType();
            //手机网络类型
            if (currentNetWork == ConnectivityManager.TYPE_MOBILE) {
                if (networkInfo.getExtraInfo() != null) {
                    if (networkInfo.getExtraInfo().equals("cmnet")) {
                        Log.i("AppNetworkMgr", "当前网络为中国移动CMNET网络");
                        return TYPE_MOBILE_CMNET;
                    }
                    if (networkInfo.getExtraInfo().equals("cmwap")) {
                        Log.i("AppNetworkMgr", "当前网络为中国移动CMWAP网络");
                        return TYPE_MOBILE_CMWAP;
                    }
                    if (networkInfo.getExtraInfo().equals("uniwap")) {
                        Log.i("AppNetworkMgr", "当前网络为中国联通UNIWAP网络");
                        return TYPE_MOBILE_UNIWAP;
                    }
                    if (networkInfo.getExtraInfo().equals("3gwap")) {
                        Log.i("AppNetworkMgr", "当前网络为中国联通3GWAP网络");
                        return TYPE_MOBILE_3GWAP;
                    }
                    if (networkInfo.getExtraInfo().equals("3gnet")) {
                        Log.i("AppNetworkMgr", "当前网络为中国联通3GNET网络");
                        return TYPE_MOBLIE_3GNET;
                    }
                    if (networkInfo.getExtraInfo().equals("uninet")) {
                        Log.i("AppNetworkMgr", "当前网络为中国联通UNINET网络");
                        return TYPE_MOBILE_UNINET;
                    }
                    if (networkInfo.getExtraInfo().equals("ctwap")) {
                        Log.i("AppNetworkMgr", "当前网络为中国电信CTWAP网络");
                        return TYPE_MOBILE_CTWAP;
                    }
                    if (networkInfo.getExtraInfo().equals("ctnet")) {
                        Log.i("AppNetworkMgr", "当前网络为中国电信CTNET网络");
                        return TYPE_MOBILE_CTNET;
                    }
                }
                //WIFI网络类型
            } else if (currentNetWork == ConnectivityManager.TYPE_WIFI) {
                Log.i("AppNetworkMgr", "当前网络为WIFI网络");
                return TYPE_WIFI;
            }
        }
        L.i("AppNetworkMgr-->>NetworkUtils", "当前网络为不是我们考虑的网络");
        return TYPE_NO;
    }

    /**
     * 获取当前手机连接的网络类型
     *
     * @param context 上下文
     * @return String 网络类型的名称
     */
    public static String getNetworkStateString(Context context) {
        //获取ConnectivityManager对象
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //获得当前网络信息
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()) {
            //获取网络类型
            int currentNetWork = networkInfo.getType();
            //手机网络类型
            if (currentNetWork == ConnectivityManager.TYPE_MOBILE) {
                if (networkInfo.getExtraInfo() != null) {
                    if (networkInfo.getExtraInfo().equals("cmnet")) {
                        Log.i("AppNetworkMgr", "当前网络为中国移动CMNET网络");
                        return TYPE_MOBILE_CMNET_STRING;
                    }
                    if (networkInfo.getExtraInfo().equals("cmwap")) {
                        Log.i("AppNetworkMgr", "当前网络为中国移动CMWAP网络");
                        return TYPE_MOBILE_CMWAP_STRING;
                    }
                    if (networkInfo.getExtraInfo().equals("uniwap")) {
                        Log.i("AppNetworkMgr", "当前网络为中国联通UNIWAP网络");
                        return TYPE_MOBILE_UNIWAP_STRING;
                    }
                    if (networkInfo.getExtraInfo().equals("3gwap")) {
                        Log.i("AppNetworkMgr", "当前网络为中国联通3GWAP网络");
                        return TYPE_MOBILE_3GWAP_STRING;
                    }
                    if (networkInfo.getExtraInfo().equals("3gnet")) {
                        Log.i("AppNetworkMgr", "当前网络为中国联通3GNET网络");
                        return TYPE_MOBLIE_3GNET_STRING;
                    }
                    if (networkInfo.getExtraInfo().equals("uninet")) {
                        Log.i("AppNetworkMgr", "当前网络为中国联通UNINET网络");
                        return TYPE_MOBILE_UNINET_STRING;
                    }
                    if (networkInfo.getExtraInfo().equals("ctwap")) {
                        Log.i("AppNetworkMgr", "当前网络为中国电信CTWAP网络");
                        return TYPE_MOBILE_CTWAP_STRING;
                    }
                    if (networkInfo.getExtraInfo().equals("ctnet")) {
                        Log.i("AppNetworkMgr", "当前网络为中国电信CTNET网络");
                        return TYPE_MOBILE_CTNET_STRING;
                    }
                }
                //WIFI网络类型
            } else if (currentNetWork == ConnectivityManager.TYPE_WIFI) {
                Log.i("AppNetworkMgr", "当前网络为WIFI网络");
                return TYPE_WIFI_STRING;
            }
        }
        L.i("AppNetworkMgr-->>NetworkUtils", "当前网络为不是我们考虑的网络");
        return TYPE_NO_STRING;
    }

    /**
     * 判断网络是否连接
     *
     * @param context 上下文
     * @return boolean 网络连接状态
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            //获取连接对象
            if (mNetworkInfo != null) {
                //判断是TYPE_MOBILE网络
                if (ConnectivityManager.TYPE_MOBILE == mNetworkInfo.getType()) {
                    Log.i("AppNetworkMgr", "网络连接类型为：TYPE_MOBILE");
                    //判断移动网络连接状态
                    State STATE_MOBILE = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
                    if (STATE_MOBILE == State.CONNECTED) {
                        Log.i("AppNetworkMgrd", "网络连接类型为：TYPE_MOBILE, 网络连接状态CONNECTED成功！");
                        return mNetworkInfo.isAvailable();
                    }
                }
                //判断是TYPE_WIFI网络
                if (ConnectivityManager.TYPE_WIFI == mNetworkInfo.getType()) {
                    Log.i("AppNetworkMgr", "网络连接类型为：TYPE_WIFI");
                    //判断WIFI网络状态
                    State STATE_WIFI = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
                    if (STATE_WIFI == State.CONNECTED) {
                        Log.i("AppNetworkMgr", "网络连接类型为：TYPE_WIFI, 网络连接状态CONNECTED成功！");
                        return mNetworkInfo.isAvailable();
                    }
                }
            }
        }
        return false;
    }

    /**
     * 判断网络是否连接
     *
     * @param activity Activity
     * @return boolean 网络连接状态
     */
    public static boolean isNetworkConnected(Activity activity) {
        // 创建并初始化连接对象
        ConnectivityManager connMan = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        // 判断初始化是否成功并作出相应处理
        if (connMan != null) {
            // 调用getActiveNetworkInfo方法创建对象,如果不为空则表明网络连通，否则没连通
            NetworkInfo info = connMan.getActiveNetworkInfo();
            if (info != null) {
                return info.isAvailable();
            }
        }
        return false;
    }

    /**
     * 打开网络设置界面
     *
     * @param context Context
     */
    public static void openNetSetting(Context context) {
        //android4.0以后舍弃了这种方式
//        Intent intent = new Intent("/");
//        ComponentName cm = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
//        intent.setComponent(cm);
//        intent.setAction("android.intent.action.VIEW");
//        activity.startActivityForResult(intent, 0);

//        具体修改如下：
        if (android.os.Build.VERSION.SDK_INT > 13) {
            context.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
        } else {
            context.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
        }
    }


}
