package com.bgi.supervision.jujubeutils.j_ui.spdloadinganimate;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import com.bgi.supervision.jujubeutils.j_ui.spdloadinganimate.animate.BaseAnimate;
import com.bgi.supervision.jujubeutils.j_ui.spdloadinganimate.animate.IAnimate;
import com.bgi.supervision.jujubeutils.j_ui.spdloadinganimate.animate.TranslationX2Animate;

/**
 * Created by majian
 * on 2019/4/25
 * copyright:BGI
 */
public class PsdLoadingView extends EditText {

    private IAnimate mIAnimate = new TranslationX2Animate();


    public PsdLoadingView(Context context) {
        super(context);
        init(mIAnimate);
    }


    public PsdLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(mIAnimate);
    }


    public PsdLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(mIAnimate);
    }


    public void init(IAnimate mIAnimate) {
        if (this.mIAnimate.isLoading()) {
            return;
        }
        this.mIAnimate = mIAnimate;
        this.mIAnimate.init(this);
    }


    public void startLoading() {
        if (this.mIAnimate.isLoading()) {
            return;
        }
        mIAnimate.startLoading();
    }


    public void stopLoading() {
        mIAnimate.stopLoading();
    }


    public void setDuration(int duration) {
        mIAnimate.setDuration(duration);
    }


    public Editable getTextDuringLoading() {
        BaseAnimate baseAnimate = (BaseAnimate) mIAnimate;
        if (baseAnimate.isLoading) {
            return baseAnimate.text;
        } else {
            return super.getText();
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mIAnimate.onDraw(canvas);
    }


    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);

        if (visibility == GONE || visibility == INVISIBLE) {
            mIAnimate.onVisibilityChanged(false);
        } else {
            mIAnimate.onVisibilityChanged(true);
        }
    }
}
