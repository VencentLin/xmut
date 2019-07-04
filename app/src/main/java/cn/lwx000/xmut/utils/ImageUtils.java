package cn.lwx000.xmut.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageUtils {
    public static void setImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).
                into(imageView);
    }
}
