package cn.lwx000.xmut.utils;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import androidx.annotation.NonNull;


public class JsonParseUtils {

    public static <T> List<T>  getList(Class<T> tClass,String json){
        Gson gson=new Gson();
        Type listType=new MyParameterizedType(tClass);
        return gson.fromJson(json,listType);
    }

    private static class MyParameterizedType implements ParameterizedType{

        Class raw;

        MyParameterizedType(Class raw) {
            this.raw = raw;
        }

        @NonNull
        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{raw};
        }

        @NonNull
        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }
}
