package cn.lwx000.xmut.fragments;

import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import cn.lwx000.xmut.R;
import cn.lwx000.xmut.activities.VideoDetailActivity;
import cn.lwx000.xmut.adapters.VideoListAdapter;
import cn.lwx000.xmut.beans.VideoBean;

public class VideoListFragment extends BaseFragment {

    private String url0 = "http://video.7k.cn/app_video/20171202/6c8cf3ea/v.m3u8.mp4";
    private String url1 = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_video_list;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        VideoListAdapter videoListAdapter = new VideoListAdapter(R.layout.item_video_list);
        recyclerView.setAdapter(videoListAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));
        ArrayList<VideoBean.VideoDetailListBean> videoDetailList = activity.getIntent()
                .getParcelableArrayListExtra("videoDetailList");
        videoListAdapter.setNewData(videoDetailList);
        videoListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position==0){
                    ((VideoDetailActivity)activity).playNewUrl(url0);
                }else {
                    ((VideoDetailActivity)activity).playNewUrl(url1);
                }
            }
        });
    }
}
