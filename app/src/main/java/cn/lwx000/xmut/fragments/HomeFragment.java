package cn.lwx000.xmut.fragments;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import java.util.ArrayList;
import java.util.List;

import cn.lwx000.xmut.R;
import cn.lwx000.xmut.activities.NewsDetailActivity;
import cn.lwx000.xmut.adapters.HomeAdapter;
import cn.lwx000.xmut.beans.NewsBean;
import cn.lwx000.xmut.utils.ConstantUtils;
import cn.lwx000.xmut.utils.ImageUtils;
import cn.lwx000.xmut.utils.JsonParseUtils;
import cn.lwx000.xmut.utils.NetUtils;



public class HomeFragment extends BaseFragment {
    private HomeAdapter homeAdapter;
    private Banner banner;
//    private MyHandle myHandle;
    private List<NewsBean> newsList_news;
    private List<NewsBean> newsList_AD;

//    class MyHandle extends Handler {
//        public static final int NEWS_OK=1;
//
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what){
//                case NEWS_OK:
////                    List<NewsBean> newslist= JsonParseUtils.getNewsList((String)msg.obj);
//                    List<NewsBean> newslist= JsonParseUtils.getList(NewsBean.class,(String)msg.obj);
//                    homeAdapter.setNewData(newslist);
//                    break;
//            }
//        }
//    }
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        TextView title=view.findViewById(R.id.tv_title);
        title.setText("首页");
        RefreshLayout refreshLayout = view.findViewById(R.id.srl_home);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                getADData();
                getNewsData();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                getNewsData();
            }
        });
        RecyclerView recyclerView=view.findViewById(R.id.rv_home);
        homeAdapter=new HomeAdapter(null);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        View headView = LayoutInflater.from(activity).inflate(R.layout.head_home,null);
        homeAdapter.addHeaderView(headView);
        banner = headView.findViewById(R.id.banner);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                if (path instanceof String){
                    ImageUtils.setImage(context,(String)path,imageView);
                }
                if (path instanceof Integer){
                    imageView.setImageResource((Integer)path);
                }
            }
        });
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        ArrayList<Integer> images = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<>();
        for(int i = 0;i < 3;i++){
            images.add(R.drawable.pic_item_list_default);
            titles.add("");
        }
        banner.setBannerTitles(titles);
        banner.setImages(images);
        banner.start();
        View emptyView = LayoutInflater.from(activity).inflate(R.layout.empty_home,null);
        homeAdapter.setEmptyView(emptyView);
        homeAdapter.setHeaderAndEmpty(true);
        homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(activity, NewsDetailActivity.class);
                intent.putExtra("url",newsList_news.get(position).getNewsUrl());
                startActivity(intent);
            }
        });
        LinearLayout linearLayout = headView.findViewById(R.id.linearLayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(activity,PythonActivity.class);
                //startActivity(intent);
            }
        });


//        myHandle=new MyHandle();
    }


    @Override
    protected void initData() {
        super.initData();

//        getADData();
//        getNewsData();
    }

    private void getNewsData() {
        NetUtils.getDataAsyn(ConstantUtils.REQUEST_NEWS_URL, new NetUtils.MyCallBack() {
            @Override
            public void onFailure() {

            }

            @Override
            public void onResponse(final String json) {
//                    Log.i(TAG,json);
//                    Message message=Message.obtain();
//                    message.what= HomeFragment.MyHandle.NEWS_OK;
//                    message.obj=json;
//                    myHandle.sendMessage(message);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        newsList_news = JsonParseUtils.getList(NewsBean.class,json);
                        homeAdapter.setNewData(newsList_news);
                    }
                });




            }
        });




    }

    private void getADData() {
        NetUtils.getDataAsyn(ConstantUtils.REQUEST_AD_URL, new NetUtils.MyCallBack() {
            @Override
            public void onFailure() {

            }

            @Override
            public void onResponse(final String json) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        newsList_AD = JsonParseUtils.getList(NewsBean.class,json);
                        List<String> images = new ArrayList<>();
                        List<String> titles = new ArrayList<>();
                        for (NewsBean newsBean : newsList_AD){
                            images.add(ConstantUtils.WEB_SITE + newsBean.getImg1());
                            titles.add(newsBean.getNewsName());
                        }
                        banner.setBannerTitles(titles);
                        banner.setImages(images);
                        banner.setOnBannerListener(new OnBannerListener() {
                            @Override
                            public void OnBannerClick(int position) {
                                Intent intent = new Intent(activity,NewsDetailActivity.class);
                                intent.putExtra("url",newsList_AD.get(position).getNewsUrl());
                                startActivity(intent);
                            }
                        });
                        banner.start();
                    }
                });
            }
        });
    }
}

