package com.bgi.photopicker.imageloader;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

/**
 * Created by majian
 * on 2019/4/26
 * copyright:BGI
 * Describe:
 */
public class BGIRVOnScrollListener extends RecyclerView.OnScrollListener {
    private Activity mActivity;

    public BGIRVOnScrollListener(Activity activity) {
        mActivity = activity;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            BGIImage.resume(mActivity);
        } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
            BGIImage.pause(mActivity);
        }
    }
}
