package com.bgi.photopicker.imageloader;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import cn.bingoogolapple.baseadapter.BGABaseAdapterUtil;

/**
 * Created by majian
 * on 2019/4/26
 * copyright:BGI
 * Describe:
 */
public class BGIGlideImageLoader extends BGIImageLoader {

    @Override
    public void display(final ImageView imageView, String path, @DrawableRes int loadingResId, @DrawableRes int failResId, int width, int height, final DisplayDelegate delegate) {
        final String finalPath = getPath(path);
        Activity activity = getActivity(imageView);
        Glide.with(activity).load(finalPath).apply(new RequestOptions().placeholder(loadingResId).error(failResId).override(width, height).dontAnimate()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                if (delegate != null) {
                    delegate.onSuccess(imageView, finalPath);
                }
                return false;
            }
        }).into(imageView);
    }

    @Override
    public void download(String path, final DownloadDelegate delegate) {
        final String finalPath = getPath(path);
        Glide.with(BGABaseAdapterUtil.getApp()).asBitmap().load(finalPath).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                if (delegate != null) {
                    delegate.onSuccess(finalPath, resource);
                }
            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                if (delegate != null) {
                    delegate.onFailed(finalPath);
                }
            }
        });
    }

    @Override
    public void pause(Activity activity) {
        Glide.with(activity).pauseRequests();
    }

    @Override
    public void resume(Activity activity) {
        Glide.with(activity).resumeRequestsRecursive();
    }
}