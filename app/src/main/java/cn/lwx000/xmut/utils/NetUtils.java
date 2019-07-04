package cn.lwx000.xmut.utils;

import android.os.Message;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import cn.lwx000.xmut.fragments.HomeFragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class NetUtils {
    public interface MyCallBack{
        void onFailure();
        void onResponse(String json);

    }

    public static void getDataAsyn(String url,final MyCallBack myCallBack) {
        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                myCallBack.onFailure();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                ResponseBody body=response.body();

                if (body!=null){
                    String json=body.string();
                    myCallBack.onResponse(json);

                }
            }
        });

    }
}
