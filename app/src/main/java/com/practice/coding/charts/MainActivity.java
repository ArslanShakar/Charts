package com.practice.coding.charts;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView tvSelected;
    private PieChart pieChart;
    private float[] ySubjectsPercentage = {50f, 70.5f, 60f, 80f, 90f, 99.9f, 25f, 20f};
    private String[] xSubjects = {"English", "Math", "Physics", "Computer", "Urdu", "Chemistry", "Geograpgy", "Language"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSelected = findViewById(R.id.tvSelectedValue);
        pieChart = findViewById(R.id.pieChart);

        Description description = new Description();
        description.setText("Subject vise Attendance Report");
        pieChart.setDescription(description);
        pieChart.setRotationEnabled(true);
        pieChart.setDragDecelerationFrictionCoef(0.15f);
        pieChart.setHoleRadius(20f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setCenterText("Monthly");
        pieChart.setEntryLabelColor(Color.RED);
        pieChart.setExtraOffsets(5, 0, 5, 50);
        //pieChart.setUsePercentValues(true);
        addDataSet();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                PieEntry pieEntry = (PieEntry) e;
               // Toast.makeText(MainActivity.this, pieEntry.getLabel() + " : " + e.getY() + " %", Toast.LENGTH_SHORT).show();
                tvSelected.setText(pieEntry.getLabel() + " : " + e.getY() + " %");
            }

            @Override
            public void onNothingSelected() {
                Toast.makeText(MainActivity.this, "Nothing Selected.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void addDataSet() {
        ArrayList<PieEntry> yEntries = new ArrayList<>();

        //populate the data in arraylist
        for (int i = 0; i < ySubjectsPercentage.length; i++) {
            yEntries.add(new PieEntry(ySubjectsPercentage[i], xSubjects[i]));
        }


        pieChart.animateY(3000, Easing.EasingOption.EaseInOutCubic);

        //Create the dataSet
        PieDataSet pieDataSet = new PieDataSet(yEntries, "Subjects");
        pieDataSet.setSliceSpace(3);
        pieDataSet.setSelectionShift(10f);
        pieDataSet.setValueTextSize(12);
        pieDataSet.setValueTextColor(Color.BLACK);


        //add Colors
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.CYAN);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.YELLOW);
        colors.add(Color.GRAY);
        colors.add(Color.GREEN);
        colors.add(Color.YELLOW);
        colors.add(Color.RED);

        pieDataSet.setColors(colors);
        //pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        //set Legend
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setTextSize(10f);

        //legend.setExtra(new int[xSubjects.length], xSubjects);
        //legend.setPosition(Legend.LegendPosition.ABOVE_CHART_CENTER);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        //set Pie Data Object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}
