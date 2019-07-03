package cn.lwx000.xmut.fragments;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import cn.lwx000.xmut.R;
import cn.lwx000.xmut.adapters.HomeAdapter;
import cn.lwx000.xmut.beans.NewsBean;
import cn.lwx000.xmut.utils.ConstantUtils;
import cn.lwx000.xmut.utils.JsonParseUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class HomeFragment extends BaseFragment {
    private HomeAdapter homeAdapter;
    private MyHandle myHandle;
    class MyHandle extends Handler {
        public static final int NEWS_OK=1;

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case NEWS_OK:
                    List<NewsBean> newslist= JsonParseUtils.getNewsList((String)msg.obj);
                    homeAdapter.setNewData(newslist);
                    break;
            }
        }
    }
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
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
        RecyclerView recyclerView=view.findViewById(R.id.rv_home);
        homeAdapter=new HomeAdapter(null);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        myHandle=new MyHandle();
    }

    @Override
    protected void initData() {
        super.initData();

        getADData();
        getNewsData();
    }

    private void getNewsData() {

        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url(ConstantUtils.REQUEST_NEW_URL)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e(TAG, "onFailure: ",e );
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                ResponseBody body=response.body();

                if (body!=null){
                    String json=body.string();
                    System.out.println(json);
                    Log.i(TAG,json);
                    Message message=Message.obtain();
                    message.what=MyHandle.NEWS_OK;
                    message.obj=json;
                    myHandle.sendMessage(message);
                }
            }
        });




    }

    private void getADData() {
    }

}
