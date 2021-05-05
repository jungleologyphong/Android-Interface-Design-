package nhatphong.ps11601.ps11601_phongdtn_asm.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import nhatphong.ps11601.ps11601_phongdtn_asm.Adapter.Adapter;
import nhatphong.ps11601.ps11601_phongdtn_asm.R;
import nhatphong.ps11601.ps11601_phongdtn_asm.TabFragment.Tab_KhoanChi;
import nhatphong.ps11601.ps11601_phongdtn_asm.TabFragment.Tab_LoaiChi;

public class Fragment_Chi extends Fragment {
    Context context;
    ViewPager viewPager;
    TabLayout tabLayout;
    Adapter adapter;
    ImageView btnAdd;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chi,container,false);
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);
        btnAdd = view.findViewById(R.id.btnAdd);
        adapter = new Adapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new Tab_KhoanChi(),"Khoản Chi ");
        adapter.addFragment(new Tab_LoaiChi(),"Loại Chi");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
