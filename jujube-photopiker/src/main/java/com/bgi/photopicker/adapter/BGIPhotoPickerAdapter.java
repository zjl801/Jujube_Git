package com.bgi.photopicker.adapter;

import android.support.v7.widget.RecyclerView;

import com.bgi.photopicker.R;
import com.bgi.photopicker.model.BGIPhotoFolderModel;
import java.util.ArrayList;
import cn.bingoogolapple.baseadapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;
import com.bgi.photopicker.imageloader.BGIImage;
import com.bgi.photopicker.util.BGIPhotoPickerUtil;

/**
 * Created by majian
 * on 2019/4/26
 * copyright:BGI
 * Describe:
 */
public class BGIPhotoPickerAdapter extends BGARecyclerViewAdapter<String> {
    private ArrayList<String> mSelectedPhotos = new ArrayList<>();
    private int mPhotoSize;
    private boolean mTakePhotoEnabled;

    public BGIPhotoPickerAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.bga_pp_item_photo_picker);
        mPhotoSize = BGIPhotoPickerUtil.getScreenWidth() / 6;
    }

    @Override
    public int getItemViewType(int position) {
        if (mTakePhotoEnabled && position == 0) {
            return R.layout.bga_pp_item_photo_camera;
        } else {
            return R.layout.bga_pp_item_photo_picker;
        }
    }

    @Override
    public void setItemChildListener(BGAViewHolderHelper helper, int viewType) {
        if (viewType == R.layout.bga_pp_item_photo_camera) {
            helper.setItemChildClickListener(R.id.iv_item_photo_camera_camera);
        } else {
            helper.setItemChildClickListener(R.id.iv_item_photo_picker_flag);
            helper.setItemChildClickListener(R.id.iv_item_photo_picker_photo);
        }
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, String model) {
        if (getItemViewType(position) == R.layout.bga_pp_item_photo_picker) {
            BGIImage.display(helper.getImageView(R.id.iv_item_photo_picker_photo), R.mipmap.bga_pp_ic_holder_dark, model, mPhotoSize);

            if (mSelectedPhotos.contains(model)) {
                helper.setImageResource(R.id.iv_item_photo_picker_flag, R.mipmap.bga_pp_ic_cb_checked);
                helper.getImageView(R.id.iv_item_photo_picker_photo).setColorFilter(helper.getConvertView().getResources().getColor(R.color.bga_pp_photo_selected_mask));
            } else {
                helper.setImageResource(R.id.iv_item_photo_picker_flag, R.mipmap.bga_pp_ic_cb_normal);
                helper.getImageView(R.id.iv_item_photo_picker_photo).setColorFilter(null);
            }
        }
    }

    public void setSelectedPhotos(ArrayList<String> selectedPhotos) {
        if (selectedPhotos != null) {
            mSelectedPhotos = selectedPhotos;
        }
        notifyDataSetChanged();
    }

    public ArrayList<String> getSelectedPhotos() {
        return mSelectedPhotos;
    }

    public int getSelectedCount() {
        return mSelectedPhotos.size();
    }

    public void setPhotoFolderModel(BGIPhotoFolderModel photoFolderModel) {
        mTakePhotoEnabled = photoFolderModel.isTakePhotoEnabled();
        setData(photoFolderModel.getPhotos());
    }
}
