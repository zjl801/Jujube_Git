package com.bgi.photopicker.imageloader;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import cn.bingoogolapple.baseadapter.BGABaseAdapterUtil;

/**
 * Created by majian
 * on 2019/4/26
 * copyright:BGI
 * Describe:
 */
public class BGIUILImageLoader extends BGIImageLoader {

    private void initImageLoader() {
        if (!ImageLoader.getInstance().isInited()) {
            DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();
            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(BGABaseAdapterUtil.getApp()).threadPoolSize(3).defaultDisplayImageOptions(options).build();
            ImageLoader.getInstance().init(config);
        }
    }

    @Override
    public void display(ImageView imageView, String path, @DrawableRes int loadingResId, @DrawableRes int failResId, int width, int height, final DisplayDelegate delegate) {
        initImageLoader();

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(loadingResId)
                .showImageOnFail(failResId)
                .cacheInMemory(true)
                .build();
        ImageSize imageSize = new ImageSize(width, height);

        ImageLoader.getInstance().displayImage(getPath(path), new ImageViewAware(imageView), options, imageSize, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                if (delegate != null) {
                    delegate.onSuccess(view, imageUri);
                }
            }
        }, null);
    }

    @Override
    public void download(String path, final DownloadDelegate delegate) {
        initImageLoader();

        ImageLoader.getInstance().loadImage(getPath(path), new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, final Bitmap loadedImage) {
                if (delegate != null) {
                    delegate.onSuccess(imageUri, loadedImage);
                }
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                if (delegate != null) {
                    delegate.onFailed(imageUri);
                }
            }
        });
    }

    @Override
    public void pause(Activity activity) {
        initImageLoader();
        ImageLoader.getInstance().pause();
    }

    @Override
    public void resume(Activity activity) {
        initImageLoader();
        ImageLoader.getInstance().resume();
    }
}