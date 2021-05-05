package nhatphong.ps11601.ps11601_phongdtn_asm.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import nhatphong.ps11601.ps11601_phongdtn_asm.R;

public class Fragment_ThongKePieChart extends Fragment {
    PieChart pieChart;
    PieData pieData;
    PieDataSet pieDataSet;
    ArrayList pieEntries;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_piechart, container, false);
        pieChart = view.findViewById(R.id.pieChart);
        getEntries();
        pieDataSet = new PieDataSet(pieEntries, "PieChart");
        pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        pieDataSet.setSliceSpace(2f);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(10f);
        pieDataSet.setSliceSpace(5f);
        return view;
    }
    private void getEntries() {
        pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(100, 100));
        pieEntries.add(new PieEntry(100, 100));
        pieEntries.add(new PieEntry(100, 100));
        pieEntries.add(new PieEntry(2000, 2000));
    }
}
