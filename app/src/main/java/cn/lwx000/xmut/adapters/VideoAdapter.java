package cn.lwx000.xmut.adapters;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.lwx000.xmut.R;
import cn.lwx000.xmut.beans.VideoBean;
import cn.lwx000.xmut.utils.ConstantUtils;
import cn.lwx000.xmut.utils.ImageUtils;

public class VideoAdapter extends BaseQuickAdapter<VideoBean, BaseViewHolder> {
    public VideoAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoBean item) {
        ImageUtils.setImage(helper.itemView.getContext(), ConstantUtils.WEB_SITE + item.getImg(),
                (ImageView)helper.getView(R.id.imageView));
    }
}
