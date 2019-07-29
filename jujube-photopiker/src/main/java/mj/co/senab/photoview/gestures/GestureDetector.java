package mj.co.senab.photoview.gestures;

import android.view.MotionEvent;
/**
 * Created by majian
 * on 2019/4/26
 * copyright:BGI
 * Describe:
 */
public interface GestureDetector {

    boolean onTouchEvent(MotionEvent ev);

    boolean isScaling();

    boolean isDragging();

    void setOnGestureListener(OnGestureListener listener);

}
