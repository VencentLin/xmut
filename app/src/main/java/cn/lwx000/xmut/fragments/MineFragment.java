package cn.lwx000.xmut.fragments;

import android.content.Intent;
import android.view.View;


import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.annotation.Nullable;
import cn.lwx000.xmut.R;
import cn.lwx000.xmut.activities.LoginActivity;
import de.hdodenhof.circleimageview.CircleImageView;

public class MineFragment extends BaseFragment {
    private boolean isLogin;
    private int REQUEST_CODE1;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        collapsingToolbarLayout = view.findViewById(R.id.collapsingToolbarLayout);
        CircleImageView circleImageView = view.findViewById(R.id.circleImageView);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLogin){

                }else{
                    Intent intent =  new Intent(activity, LoginActivity.class);
                    startActivityForResult(intent,REQUEST_CODE1);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE1)
            if(data!=null){
                boolean isLogin = data.getBooleanExtra("isLogin",false);
                MineFragment.this.isLogin=isLogin;
                String userName = data.getStringExtra("userName");
                collapsingToolbarLayout.setTitle(userName);
            }
    }
}
