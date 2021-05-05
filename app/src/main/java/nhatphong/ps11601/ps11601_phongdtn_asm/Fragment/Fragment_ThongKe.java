package nhatphong.ps11601.ps11601_phongdtn_asm.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import nhatphong.ps11601.ps11601_phongdtn_asm.Model.KhoanThu;
import nhatphong.ps11601.ps11601_phongdtn_asm.R;

public class Fragment_ThongKe extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongke,container,false);
        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_navigation);
        if (savedInstanceState == null){
            loadFragment(new Fragment_ThongKeThu());
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.bot_thu:
                        fragment = new Fragment_ThongKe();
                        loadFragment(fragment);
                        break;
                    case R.id.bot_chi:
                        fragment = new Fragment_ThongKeChi();
                        loadFragment(fragment);
                        break;
                }
                return false;
            }
        });
        return view;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frm_layout_bottom, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
