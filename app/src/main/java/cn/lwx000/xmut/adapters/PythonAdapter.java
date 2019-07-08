package cn.lwx000.xmut.adapters;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import cn.lwx000.xmut.R;
import cn.lwx000.xmut.beans.PythonBean;

public class PythonAdapter extends BaseQuickAdapter<PythonBean, BaseViewHolder> {

    public PythonAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PythonBean item) {
        helper.setText(R.id.textView,item.getAddress());
        helper.setText(R.id.textView2,item.getContent());
    }
}
