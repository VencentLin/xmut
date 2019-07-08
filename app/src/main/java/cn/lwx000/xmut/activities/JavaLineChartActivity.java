package cn.lwx000.xmut.activities;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

import cn.lwx000.xmut.R;

public class JavaLineChartActivity extends HomeAsUpBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_chart);
        final String[] years = new String[]{"应届生","1-2年","2-3年","3-5年",
        "5-8年","8-10年","10年"};
        int[] salaries = {6000,13000,20000,26000,35000,50000,100000};
        TextView titleView = findViewById(R.id.tv_title);
        titleView.setText("Java统计");
        LineChart chart = findViewById(R.id.chart);
        List<Entry> entries = new ArrayList<>();
        for (int i = 0;i < salaries.length;i++){
            entries.add(new Entry(i,salaries[i]));
        }
        LineDataSet dataSet = new LineDataSet(entries,"工资");
        dataSet.setColor(Color.GRAY);
        dataSet.setLineWidth(6f);
        dataSet.setValueTextColor(Color.RED);
        dataSet.setValueTextSize(12f);
        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisLineWidth(2f);
        xAxis.enableGridDashedLine(10f,10f,0f);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return years[(int)value];
            }
        });
        chart.getAxisRight().setEnabled(false);
        YAxis yAxis = chart.getAxisLeft();
        yAxis.setAxisLineWidth(2f);
        yAxis.enableGridDashedLine(10f,10f,0f);
        yAxis.setSpaceTop(20f);
        LimitLine limitLine = new LimitLine(15000f,"平均工资");
        limitLine.setLineColor(Color.MAGENTA);
        limitLine.setLineWidth(2f);
        yAxis.addLimitLine(limitLine);
        Description desc = new Description();
        desc.setText("Java工程师工作经验对应的薪资情况");
        desc.setPosition(530,100);
        desc.setTextAlign(Paint.Align.CENTER);
        desc.setTextSize(16f);
        chart.setDescription(desc);
        Legend legend = chart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        chart.animateX(5000);
        chart.invalidate();
    }
}
