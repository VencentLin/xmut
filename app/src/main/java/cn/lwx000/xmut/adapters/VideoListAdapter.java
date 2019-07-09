package cn.lwx000.xmut.adapters;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.lwx000.xmut.R;
import cn.lwx000.xmut.beans.VideoBean;

public class VideoListAdapter extends BaseQuickAdapter<VideoBean.VideoDetailListBean, BaseViewHolder> {

    public VideoListAdapter(int layoutResId){
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoBean.VideoDetailListBean item) {
        helper.setText(R.id.textView,item.getVideo_name());
    }
}
