package cn.lwx000.xmut.activities;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

import cn.lwx000.xmut.R;

public class AndroidPieChartActivity extends HomeAsUpBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_chart);
        final String[] salaries = new String[]{"月薪8-15k","月薪15-20k","月薪20-30k",
        "月薪30k+"};
        int[] percentage = {50,25,15,10};
        TextView titleView = findViewById(R.id.tv_title);
        titleView.setText("Android统计");
        final PieChart chart = findViewById(R.id.chart);
        List<PieEntry> entries = new ArrayList<>();
        for (int i = 0;i < salaries.length;i++){
            entries.add(new PieEntry(percentage[i],salaries[i]));
        }
        PieDataSet dataSet = new PieDataSet(entries,"人数占比");
        int[] colors = {Color.rgb(106,155,253),
                Color.rgb(155,204,102),
                Color.rgb(88,203,151),
                Color.rgb(247,203,74)};
        dataSet.setColors(colors);
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueTextSize(12f);
        PieData pieData = new PieData(dataSet);
        pieData.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return value+"%";
            }
        });
        chart.setData(pieData);
        Description desc = new Description();
        desc.setText("Android工程师薪资占比情况");
        desc.setPosition(540,100);
        desc.setTextAlign(Paint.Align.CENTER);
        desc.setTextSize(16f);
        chart.setDescription(desc);
        Legend legend = chart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        chart.animateX(5000);
        chart.animateY(5000);
        chart.setCenterTextColor(Color.BLACK);
        chart.setCenterTextSize(24f);
        chart.setDrawCenterText(true);
        chart.setCenterText("点击圆环\n显示数据");
        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                PieEntry pieEntry = (PieEntry)e;
                chart.setCenterText(pieEntry.getLabel()+"\n"+pieEntry.getValue()+"%");
            }

            @Override
            public void onNothingSelected() {
                chart.setCenterText("点击圆环\n显示数据");
            }
        });
        chart.setExtraTopOffset(10f);
        chart.invalidate();
    }
}
