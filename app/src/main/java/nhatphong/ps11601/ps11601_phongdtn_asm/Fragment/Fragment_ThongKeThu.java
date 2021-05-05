package nhatphong.ps11601.ps11601_phongdtn_asm.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import nhatphong.ps11601.ps11601_phongdtn_asm.Adapter.AdapterThongKeThu;
import nhatphong.ps11601.ps11601_phongdtn_asm.LoaiThuDAO.LoaiThuDao;
import nhatphong.ps11601.ps11601_phongdtn_asm.Model.LoaiThu;
import nhatphong.ps11601.ps11601_phongdtn_asm.R;

public class Fragment_ThongKeThu extends Fragment {
    TextView totalThu;
    Button btnDateStart,btnDateEnd;
    Integer total;
    ArrayList<LoaiThu> list;
    LoaiThuDao loaiThuDao;
    AdapterThongKeThu adapterThongKeThu;
    RecyclerView recyclerView;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongkethu, container, false);
        totalThu = view.findViewById(R.id.totalThu);
        btnDateStart = view.findViewById(R.id.btnDateStart);
        btnDateEnd = view.findViewById(R.id.btnDateEnd);
        recyclerView = view.findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        totalThu();
        btnDateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date1();
                btnDateEnd.setEnabled(true);
            }
        });
        btnDateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date2();
                btnDateEnd.setEnabled(false);
            }
        });
        list = new ArrayList<>();
        loaiThuDao = new LoaiThuDao(getContext());

        list = (ArrayList<LoaiThu>) loaiThuDao.getAll();
        adapterThongKeThu = new AdapterThongKeThu(list,getContext());
        recyclerView.setAdapter(adapterThongKeThu);
        return view;

    }
    public void totalThu(){
        LoaiThuDao loaiThuDao = new LoaiThuDao(getContext());
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        total = loaiThuDao.totalThu();
        String s = decimalFormat.format(total);
        totalThu.setText("TỔNG THU : "+s+" USD");
    }
    public void Date1(){
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        final int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        final int months = cal.get(Calendar.MONTH);
        final int years = cal.get(Calendar.YEAR);
        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int ii, int iii) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                calendar.set(i,ii,iii);
                btnDateStart.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },years,months,dayOfWeek);
        datePickerDialog.show();
    }
    public void Date2(){
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);

        final int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        final int months = cal.get(Calendar.MONTH);
        final int years = cal.get(Calendar.YEAR);
        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int ii, int iii) {
                SimpleDateFormat date2 = new SimpleDateFormat("yyyy-MM-dd");
                calendar.set(i,ii,iii);
                btnDateEnd.setText(date2.format(calendar.getTime()));
                list = loaiThuDao.getThongKeThu(btnDateStart.getText().toString(), btnDateEnd.getText().toString());
                adapterThongKeThu = new AdapterThongKeThu(list, getContext());
                recyclerView.setAdapter(adapterThongKeThu);
                LoaiThuDao loaiThuDao = new LoaiThuDao(getContext());
                String date1 = btnDateStart.getText().toString();
                DecimalFormat decimalFormat = new DecimalFormat("#,###");
                total = loaiThuDao.getTotalThuByDate(date1, date2.format(calendar.getTime()));
                String s = decimalFormat.format(total);
                totalThu.setText("TỔNG THU : "+s+" USD");
            }
        },years,months,dayOfWeek);
        datePickerDialog.show();
    }
}