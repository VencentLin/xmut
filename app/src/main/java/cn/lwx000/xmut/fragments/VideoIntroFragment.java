package cn.lwx000.xmut.fragments;

import android.view.View;
import android.widget.TextView;


import cn.lwx000.xmut.R;

public class VideoIntroFragment extends BaseFragment {

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_video_intro;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        TextView textView = view.findViewById(R.id.textView);
        String videoIntro = activity.getIntent().getStringExtra("videoIntro");
        textView.setText(videoIntro);
    }
}
