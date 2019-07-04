package cn.lwx000.xmut.adapters;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import java.util.List;

import cn.lwx000.xmut.R;
import cn.lwx000.xmut.beans.NewsBean;
import cn.lwx000.xmut.utils.ConstantUtils;
import cn.lwx000.xmut.utils.ImageUtils;
import cn.lwx000.xmut.viewHolders.NewsViewHolder;

public class HomeAdapter extends BaseMultiItemQuickAdapter<NewsBean, NewsViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HomeAdapter(List<NewsBean> data) {
        super(data);
        addItemType(1, R.layout.item_news1);
        addItemType(2, R.layout.item_news2);
    }

    @Override
    protected void convert(NewsViewHolder helper, NewsBean item) {
        switch (helper.getItemViewType()){
            case 1:
                helper.setText(R.id.textView,item.getNewsName());
                helper.setText(R.id.textView2,item.getNewsTypeName());
                ImageView imageView=helper.getView(R.id.imageView);
//              Glide.with(helper.itemView.getContext()).
//              load(ConstantUtils.WEB_SITE+item.getImg1()).into(imageView);
                ImageUtils.setImage(helper.itemView.getContext(),
                        ConstantUtils.WEB_SITE+item.getImg1(),
                        imageView);

                break;
            case 2:
                helper.setText(R.id.textView,item.getNewsName());
                helper.setText(R.id.textView2,item.getNewsTypeName());
                ImageView imageView1=helper.getView(R.id.imageView);
                ImageView imageView2=helper.getView(R.id.imageView2);
                ImageView imageView3=helper.getView(R.id.imageView3);
                ImageUtils.setImage(helper.itemView.getContext(),
                        ConstantUtils.WEB_SITE+item.getImg1(),
                        imageView1);
                ImageUtils.setImage(helper.itemView.getContext(),
                        ConstantUtils.WEB_SITE+item.getImg2(),
                        imageView2);
                ImageUtils.setImage(helper.itemView.getContext(),
                        ConstantUtils.WEB_SITE+item.getImg3(),
                        imageView3);
                break;
        }
    }
}
