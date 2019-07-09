package cn.lwx000.xmut.activities;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

import cn.lwx000.xmut.R;

public class PHPBarChartActivity extends HomeAsUpBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_php_chart);
        final String[] years = new String[]{"应届生","1-2年","2-3年","3-5年",
                "5-8年","8-10年","10年"};
        int[] salaries = {6000,13000,20000,26000,35000,50000,100000};
        TextView titleView = findViewById(R.id.tv_title);
        titleView.setText("PHP统计");
        BarChart chart = findViewById(R.id.chart);
        List<BarEntry> entries = new ArrayList<>();
        for (int i = 0;i < salaries.length;i++){
            entries.add(new BarEntry(i,salaries[i]));
        }
        BarDataSet dataSet = new BarDataSet(entries,"工资");
        BarData barData = new BarData(dataSet);
        int[] colors = {Color.rgb(106,155,253),
                Color.rgb(155,204,102),
                Color.rgb(88,203,151),
                Color.rgb(247,203,74),
                Color.rgb(248,141,72),
                Color.rgb(243,83,82),
                Color.rgb(	186,85,211)};
        dataSet.setColors(colors);
        List<Integer> colorList = new ArrayList<>();
        for (int color:colors){
            colorList.add(color);
        }
        dataSet.setValueTextColors(colorList);
        dataSet.setValueTextSize(12f);
        chart.setData(barData);
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
        yAxis.setSpaceTop(38.2f);
        yAxis.setAxisMinimum(0);
        LimitLine limitLine = new LimitLine(15000f,"平均工资");
        limitLine.setLineColor(Color.rgb(192,192,192));
        limitLine.setLineWidth(2f);
        yAxis.addLimitLine(limitLine);
        Description desc = new Description();
        desc.setText("PHP工程师工作经验对应的薪资情况");
        desc.setPosition(540,100);
        desc.setTextAlign(Paint.Align.CENTER);
        desc.setTextSize(16f);
        chart.setDescription(desc);
        Legend legend = chart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        chart.animateY(5000);
        chart.invalidate();
    }
}
