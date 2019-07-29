package com.bgi.photopicker.model;

import java.util.ArrayList;

/**
 * Created by majian
 * on 2019/4/26
 * copyright:BGI
 * Describe:
 */
public class BGIPhotoFolderModel {
    public String name;
    public String coverPath;
    private ArrayList<String> mPhotos = new ArrayList<>();
    private boolean mTakePhotoEnabled;

    public BGIPhotoFolderModel(boolean takePhotoEnabled) {
        mTakePhotoEnabled = takePhotoEnabled;
        if (takePhotoEnabled) {
            // 拍照
            mPhotos.add("");
        }
    }

    public BGIPhotoFolderModel(String name, String coverPath) {
        this.name = name;
        this.coverPath = coverPath;
    }

    public boolean isTakePhotoEnabled() {
        return mTakePhotoEnabled;
    }

    public void addLastPhoto(String photoPath) {
        mPhotos.add(photoPath);
    }

    public ArrayList<String> getPhotos() {
        return mPhotos;
    }

    public int getCount() {
        return mTakePhotoEnabled ? mPhotos.size() - 1 : mPhotos.size();
    }
}