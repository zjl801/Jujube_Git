package com.bgi.supervision.jujubeutils.j_ui.recyclerview.animation;

import android.animation.Animator;
import android.view.View;

/**
 * Created by majian
 * on 2019/4/25
 * copyright:BGI
 * Describe:
 */
public interface BaseAnimation {
    Animator[] getAnimators(View view);
}
