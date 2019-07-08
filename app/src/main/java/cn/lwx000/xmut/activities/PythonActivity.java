package cn.lwx000.xmut.activities;

import android.os.Bundle;

import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.lwx000.xmut.R;
import cn.lwx000.xmut.adapters.PythonAdapter;
import cn.lwx000.xmut.beans.PythonBean;
import cn.lwx000.xmut.utils.ConstantUtils;
import cn.lwx000.xmut.utils.JsonParseUtils;
import cn.lwx000.xmut.utils.NetUtils;

public class PythonActivity extends HomeAsUpBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_python);
        TextView tittleView = findViewById(R.id.title);
        tittleView.setText("Python学科");
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        final PythonAdapter pythonAdapter = new PythonAdapter(R.layout.item_python);
        recyclerView.setAdapter(pythonAdapter);
        NetUtils.getDataAsyn(ConstantUtils.REQUEST_PYTHON_URL, new NetUtils.MyCallBack() {
            @Override
            public void onFailure() {

            }

            @Override
            public void onResponse(final String json) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        List<PythonBean> pythonBeanList = JsonParseUtils.getList(PythonBean.class,json);
                        pythonAdapter.setNewData(pythonBeanList);
                    }
                });
            }
        });
    }
}
