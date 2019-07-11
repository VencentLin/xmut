package cn.lwx000.xmut.fragments;

import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;

import com.google.android.material.appbar.CollapsingToolbarLayout;


import cn.lwx000.xmut.R;
import cn.lwx000.xmut.activities.LoginActivity;
import cn.lwx000.xmut.activities.RegisterActivity;
import cn.lwx000.xmut.activities.UserInfoActivity;
import de.hdodenhof.circleimageview.CircleImageView;


public class MineFragment extends BaseFragment {
    private static final int REQUEST_CODE1=0x1001;
    private boolean isLogin;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        collapsingToolbarLayout=view.findViewById(R.id.collapsingToolbarLayout1);
        CircleImageView circleImageView=view.findViewById(R.id.circleImageView);
        circleImageView.setOnClickListener(v -> {
            if (isLogin){
                //跳转到个人资料界面
                Intent userInfo = new Intent(getActivity(), UserInfoActivity.class);
                startActivity(userInfo);
            }else {
                Intent login=new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(login,REQUEST_CODE1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE1){
            if (data!=null){
                Boolean isLogin=data.getBooleanExtra("isLogin",false);
                MineFragment.this.isLogin=isLogin;
                if (isLogin) {
                    String userName=data.getStringExtra("userName");
                    collapsingToolbarLayout.setTitle(userName);
                }
            }
        }
    }
}