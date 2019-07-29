package mj.co.senab.photoview.gestures;
/**
 * Created by majian
 * on 2019/4/26
 * copyright:BGI
 * Describe:
 */
public interface OnGestureListener {

    void onDrag(float dx, float dy);

    void onFling(float startX, float startY, float velocityX,
                 float velocityY);

    void onScale(float scaleFactor, float focusX, float focusY);

}