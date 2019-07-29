package com.bgi.supervision.jujubeutils.j_ui.recyclerview.loadmore;


import com.bgi.supervision.jujubeutils.R;

/**
 * Created by majian
 * on 2019/4/25
 * copyright:BGI
 * Describe:
 */
public final class SimpleLoadMoreView extends LoadMoreView {

    @Override
    public int getLayoutId() {
        return R.layout.brvah_quick_view_load_more;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
