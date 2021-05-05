package nhatphong.ps11601.ps11601_phongdtn_asm.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import nhatphong.ps11601.ps11601_phongdtn_asm.Adapter.Adapter;
import nhatphong.ps11601.ps11601_phongdtn_asm.R;
import nhatphong.ps11601.ps11601_phongdtn_asm.TabFragment.Tab_KhoanThu;
import nhatphong.ps11601.ps11601_phongdtn_asm.TabFragment.Tab_LoaiThu;

public class Fragment_Thu extends Fragment {
    private Adapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView btnAdd;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thu,container,false);
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);
        btnAdd = view.findViewById(R.id.btnAdd);
        adapter = new Adapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new Tab_KhoanThu(),"Khoản Thu");
        adapter.addFragment(new Tab_LoaiThu(),"Loại Thu");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}

