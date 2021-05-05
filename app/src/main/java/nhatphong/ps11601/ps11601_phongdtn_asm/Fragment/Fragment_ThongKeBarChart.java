package nhatphong.ps11601.ps11601_phongdtn_asm.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import nhatphong.ps11601.ps11601_phongdtn_asm.LoaiThuDAO.LoaiThuDao;
import nhatphong.ps11601.ps11601_phongdtn_asm.Model.LoaiThu;
import nhatphong.ps11601.ps11601_phongdtn_asm.R;

public class Fragment_ThongKeBarChart extends Fragment {
    BarChart barChart;
    LoaiThuDao loaiThuDao;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_barchart, container, false);
        barChart = view.findViewById(R.id.barChart);
        BarDataSet barDataSet = new BarDataSet(dataValues(), "BarChart");
        BarData barData = new BarData();
        barData.addDataSet(barDataSet);
        barChart.setData(barData);
        barChart.invalidate();
        return view;
    }

    private ArrayList<BarEntry> dataValues() {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        loaiThuDao = new LoaiThuDao(getContext());
        barEntries.add(new BarEntry(3f,1f));
        barEntries.add(new BarEntry(4f,2f));
        barEntries.add(new BarEntry(5f,4f));
        barEntries.add(new BarEntry(2f,8f));
        barEntries.add(new BarEntry(1f,3f));
        return barEntries;
    }
}
