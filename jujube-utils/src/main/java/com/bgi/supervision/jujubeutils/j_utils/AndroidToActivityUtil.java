package com.bgi.supervision.jujubeutils.j_utils;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;


/**
 * Created by majian
 * on 2019/4/25
 * copyright:BGI
 * Describe:手机组件调用工具类
 */
public class AndroidToActivityUtil {

    private AndroidToActivityUtil() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * 调用系统发短信界面
     *
     * @param activity    Activity
     * @param phoneNumber 手机号码
     * @param smsContent  短信内容
     */
    public static void sendMessage(Context activity, String phoneNumber, String smsContent) {
        if (smsContent == null || phoneNumber.length() < 4) {
            return;
        }
        Uri uri = Uri.parse("smsto:" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", smsContent);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    /**
     * 拍照打开照相机！
     *
     * @param requestcode 返回值
     * @param activity    上下文
     * @param fileName    生成的图片文件的路径
     */
    public static void toTakePhoto(int requestcode, Activity activity, String fileName) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra("camerasensortype", 2);// 调用前置摄像头
        intent.putExtra("autofocus", true);// 自动对焦
        intent.putExtra("fullScreen", false);// 全屏
        intent.putExtra("showActionIcons", false);
        try {
            //创建一个当前任务id的文件，然后里面存放任务的照片和路径！这主文件的名字是用uuid到时候再用任务id去查路径！
            File file = new File(fileName);
            //如果这个文件不存在就创建一个文件夹！
            if (!file.exists()) {
                file.mkdirs();
            }
            Uri uri = Uri.fromFile(file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            activity.startActivityForResult(intent, requestcode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打开相册
     *
     * @param requestcode 响应码
     * @param activity    上下文
     */
    public static void toTakePicture(int requestcode, Activity activity) {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        activity.startActivityForResult(intent, requestcode);

    }

    /**
     * 跳转到拨号界面
     *
     * @param activity
     */
    public static void toDial(Activity activity, String phoneNumber) {
        //跳转到拨号界面，同时传递电话号码
        Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        activity.startActivity(dialIntent);
    }

    /**
     * 跳转到浏览器界面
     *
     * @param activity
     */
    public static void toWebView(Activity activity, String URL) {
        Intent intent = new Intent();
        intent.setData(Uri.parse(URL));//Url 就是你要打开的网址
        intent.setAction(Intent.ACTION_VIEW);
        activity.startActivity(intent); //启动浏览器
    }

    /**
     * 分享文字内容
     */
    private void shareText(Activity activity, String title, String content) {
        Intent intent1 = new Intent(Intent.ACTION_SEND);
        intent1.putExtra(Intent.EXTRA_TEXT, content);
        intent1.setType("text/plain");
        activity.startActivity(Intent.createChooser(intent1, title));
    }

    /**
     * 分享单张图片
     */
    private void shareImage(Activity activity, String title, String filePath) {
        Intent intent2 = new Intent(Intent.ACTION_SEND);
        Uri uri = Uri.fromFile(new File(filePath));
        intent2.putExtra(Intent.EXTRA_STREAM, uri);
        intent2.setType("image/*");
        activity.startActivity(Intent.createChooser(intent2, title));
    }

    /**
     * 分享多张图片
     */
    private void shareMultiImage(Activity activity, String title, String[] filePaths) {
        ArrayList<Uri> imageUris = new ArrayList<Uri>();
        for (String path : filePaths) {
            Uri imageUri1 = Uri.fromFile(new File(path));
            imageUris.add(imageUri1);
        }
        Intent intent3 = new Intent();
        intent3.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent3.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
        intent3.setType("image/*");
        activity.startActivity(Intent.createChooser(intent3, "share"));
    }
}
