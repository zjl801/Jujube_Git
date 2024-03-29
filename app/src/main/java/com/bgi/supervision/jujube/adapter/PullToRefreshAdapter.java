package com.bgi.supervision.jujube.adapter;

import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import com.bgi.supervision.jujube.R;
import com.bgi.supervision.jujube.bean.Status;
import com.bgi.supervision.jujube.utils.SpannableStringUtils;
import com.bgi.supervision.jujubeutils.jUtils;
import com.bgi.supervision.jujubeutils.j_log.L;
import com.bgi.supervision.jujubeutils.j_ui.recyclerview.BaseQuickAdapter;
import com.bgi.supervision.jujubeutils.j_ui.recyclerview.BaseViewHolder;

/**
 * 文 件 名: PullToRefreshAdapter
 * 创 建 人: Allen
 * 创建日期: 16/12/24 19:55
 * 邮   箱: AllenCoder@126.com
 * 修改时间：
 * 修改备注：
 */
public class PullToRefreshAdapter extends BaseQuickAdapter<Status, BaseViewHolder> {
    public PullToRefreshAdapter() {
        super(R.layout.layout_animation, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, Status item) {
        switch (helper.getLayoutPosition() % 3) {
            case 0:
                helper.setImageResource(R.id.img, R.mipmap.animation_img1);
                break;
            case 1:
                helper.setImageResource(R.id.img, R.mipmap.animation_img2);
                break;
            case 2:
                helper.setImageResource(R.id.img, R.mipmap.animation_img3);
                break;
            default:
                break;
        }
        helper.setText(R.id.tweetName, "Hoteis in Rio de Janeiro");
        String msg = "\"He was one of Australia's most of distinguished artistes, renowned for his portraits\"";

        ((TextView) helper.getView(R.id.tweetText)).
                setText(SpannableStringUtils.getBuilder(msg)
                        .append("landscapes and nedes")
                        .setClickSpan(clickableSpan)
                        .create());

        ((TextView) helper.getView(R.id.tweetText))
                .setMovementMethod(LinkMovementMethod.getInstance());
    }

    ClickableSpan clickableSpan = new ClickableSpan() {
        @Override
        public void onClick(View widget) {
//            ToastUtils.showShortToast("事件触发了 landscapes and nedes");
            L.e("事件触发了 landscapes and nedes");
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(jUtils.getApp().getResources().getColor(R.color.clickspan_color));
            ds.setUnderlineText(true);
        }
    };


}
