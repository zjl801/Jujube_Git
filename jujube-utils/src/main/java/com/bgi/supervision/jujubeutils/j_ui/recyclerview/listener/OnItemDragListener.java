package com.bgi.supervision.jujubeutils.j_ui.recyclerview.listener;

import android.support.v7.widget.RecyclerView;

/**
 * Created by majian
 * on 2019/4/25
 * copyright:BGI
 * Describe:
 */
public interface OnItemDragListener {
    void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos);

    void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to);

    void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos);
}
