package cn.lwx000.xmut.fragments;


import android.content.Intent;
import android.view.View;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import cn.lwx000.xmut.R;
import cn.lwx000.xmut.activities.JavaLineChartActivity;

public class ChartFragment extends BaseFragment {

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_chart;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        int[] images_id = {R.drawable.bat,R.drawable.bear,R.drawable.bee,
                R.drawable.butterfly,R.drawable.cat,R.drawable.dolphin,R.drawable.eagle,
                R.drawable.horse,R.drawable.elephant};

        String[] texts = {"Android","Java","PHP","黑马程序员.Python","黑马程序员.C/C++","黑马程序员.iOS",
                "黑马程序员.前端与移动开发","黑马程序员.UI设计","黑马程序员.网络营销"};
        BoomMenuButton bmb = view.findViewById(R.id.bmb);
        for (int i = 0;i < bmb.getPiecePlaceEnum().pieceNumber();i++){
            TextInsideCircleButton.Builder builder = new TextInsideCircleButton.Builder()
                    .normalImageRes(images_id[i])
                    .normalText(texts[i])
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            switch (index){
                                case 0:
                                    //Intent intent0 = new Intent(activity,AndroidPieChartActivity.class);
                                    //startActivity(intent0);
                                    break;
                                case 1:
                                    Intent intent1 = new Intent(activity, JavaLineChartActivity.class);
                                    startActivity(intent1);
                                    break;
                                case 2:
                                    //Intent intent2 = new Intent(activity,PHPBarChartActivity.class);
                                    //startActivity(intent2);
                                    break;
                            }
                        }
                    });
            bmb.addBuilder(builder);
        }
    }
}
