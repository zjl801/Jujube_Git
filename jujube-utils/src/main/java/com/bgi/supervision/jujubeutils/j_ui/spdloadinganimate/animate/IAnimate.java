package com.bgi.supervision.jujubeutils.j_ui.spdloadinganimate.animate;

import android.graphics.Canvas;

import com.bgi.supervision.jujubeutils.j_ui.spdloadinganimate.PsdLoadingView;

/**
 * Created by majian
 * on 2019/4/25
 * copyright:BGI
 */
public interface IAnimate {
    void init(PsdLoadingView mPsdLoadingView);

    void startLoading();

    void stopLoading();

    void setDuration(int duration);

    void onDraw(Canvas canvas);

    void onVisibilityChanged(boolean isVisibiable);

    boolean isLoading();
}
