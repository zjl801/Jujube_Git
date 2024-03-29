package com.bgi.photopicker.util;

import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import mj.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by majian
 * on 2019/4/26
 * copyright:BGI
 * Describe:
 */
public class BGIBrowserPhotoViewAttacher extends PhotoViewAttacher {

    public BGIBrowserPhotoViewAttacher(ImageView imageView) {
        super(imageView);
    }

    private boolean isSetTopCrop = false;

    /**
     * 必须重写此方法，防止其他函数覆盖，导致setTopCrop不成功
     *
     * @param d - Drawable being displayed
     */
    @Override
    protected void updateBaseMatrix(Drawable d) {
        if (isSetTopCrop) {
            setTopCrop(d);
        } else {
            super.updateBaseMatrix(d);
        }
    }

    public void setIsSetTopCrop(boolean isSetTopCrop) {
        this.isSetTopCrop = isSetTopCrop;
    }

    public void setUpdateBaseMatrix() {
        ImageView view = getImageView();
        if (view == null) return;
        updateBaseMatrix(view.getDrawable());
    }

    private void setTopCrop(Drawable d) {
        ImageView imageView = getImageView();
        if (null == imageView || null == d) {
            return;
        }
        final float viewWidth = getImageViewWidth(imageView);
        final float viewHeight = getImageViewHeight(imageView);
        final int drawableWidth = d.getIntrinsicWidth();
        final int drawableHeight = d.getIntrinsicHeight();

        Matrix matrix = new Matrix();

        final float widthScale = viewWidth / drawableWidth;
        final float heightScale = viewHeight / drawableHeight;
        float scale = Math.max(widthScale, heightScale);
        matrix.postScale(scale, scale);
        matrix.postTranslate(0, 0);
        updateBaseMatrix(matrix);
    }

}