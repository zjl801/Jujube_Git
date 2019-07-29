package com.bgi.photopicker.adapters;

import android.databinding.BindingAdapter;

import com.bgi.photopicker.widget.BGINinePhotoLayout;

import java.util.ArrayList;

/**
 * Created by majian
 * on 2019/4/26
 * copyright:BGI
 * Describe:
 */
public class BGINinePhotoLayoutAdapter {

    @BindingAdapter({"bga_npl_delegate"})
    public static void setDelegate(BGINinePhotoLayout ninePhotoLayout, BGINinePhotoLayout.Delegate delegate) {
        ninePhotoLayout.setDelegate(delegate);
    }

    @BindingAdapter({"bga_npl_data"})
    public static void setData(BGINinePhotoLayout ninePhotoLayout, ArrayList<String> data) {
        ninePhotoLayout.setData(data);
    }
}
