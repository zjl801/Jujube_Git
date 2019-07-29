package mj.co.senab.photoview.scrollerproxy;

import android.annotation.TargetApi;
import android.content.Context;
/**
 * Created by majian
 * on 2019/4/26
 * copyright:BGI
 * Describe:
 */
@TargetApi(14)
public class IcsScroller extends GingerScroller {

    public IcsScroller(Context context) {
        super(context);
    }

    @Override
    public boolean computeScrollOffset() {
        return mScroller.computeScrollOffset();
    }

}
