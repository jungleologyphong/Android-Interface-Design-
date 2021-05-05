package nhatphong.ps11601.ps11601_phongdtn_asm.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import nhatphong.ps11601.ps11601_phongdtn_asm.R;

public class Fragment_ThongKeChart extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongkewithchart,container,false);
        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_navigation);
        if(savedInstanceState == null){
            loadFragment(new Fragment_ThongKePieChart());
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.pieChart:
                        fragment = new Fragment_ThongKePieChart();
                        loadFragment(fragment);
                        break;
                    case R.id.barChart:
                        fragment = new Fragment_ThongKeBarChart();
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
