package com.bgi.supervision.jujubeutils.j_ui.recyclerview.listener;

import android.view.View;

import com.bgi.supervision.jujubeutils.j_ui.recyclerview.BaseQuickAdapter;

/**
 * Created by majian
 * on 2019/4/25
 * copyright:BGI
 * Describe:
 */
public abstract class OnItemChildClickListener extends SimpleClickListener {
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        onSimpleItemChildClick(adapter, view, position);
    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    public abstract void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position);
}
