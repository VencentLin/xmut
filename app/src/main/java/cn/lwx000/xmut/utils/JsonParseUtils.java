package cn.lwx000.xmut.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

import cn.lwx000.xmut.beans.NewsBean;

public class JsonParseUtils {
    private static Gson gson=new Gson();
    public  static List<NewsBean> getNewsList(String json){
        Type listType=new TypeToken<List<NewsBean>>(){}.getType();
        return gson.fromJson(json,listType);
    }
    public  static List<NewsBean> getADList(String json){
        Type listType=new TypeToken<List<NewsBean>>(){}.getType();
        return gson.fromJson(json,listType);
    }
}
