package com.bgi.supervision.jujubeutils.j_ui.recyclerview.entity;

import java.io.Serializable;

/**
 * Created by majian
 * on 2019/4/25
 * copyright:BGI
 * Describe:
 */
public abstract class SectionMultiEntity<T> implements Serializable, MultiItemEntity {

    public boolean isHeader;
    public T t;
    public String header;

    public SectionMultiEntity(boolean isHeader, String header) {
        this.isHeader = isHeader;
        this.header = header;
        this.t = null;
    }

    public SectionMultiEntity(T t) {
        this.isHeader = false;
        this.header = null;
        this.t = t;
    }
}
