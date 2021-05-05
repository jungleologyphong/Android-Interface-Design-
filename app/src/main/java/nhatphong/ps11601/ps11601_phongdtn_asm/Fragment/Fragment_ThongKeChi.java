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
import nhatphong.ps11601.ps11601_phongdtn_asm.Adapter.AdapterThongKeChi;
import nhatphong.ps11601.ps11601_phongdtn_asm.LoaiChiDAO.LoaiChiDAO;
import nhatphong.ps11601.ps11601_phongdtn_asm.Model.LoaiChi;
import nhatphong.ps11601.ps11601_phongdtn_asm.R;

public class Fragment_ThongKeChi extends Fragment {
    Button btnDateStart,btnDateEnd;
    Double total;
    TextView totalChi;
    LoaiChiDAO loaiChiDAO;
    RecyclerView recyclerView;
    ArrayList<LoaiChi> list;
    AdapterThongKeChi adapterThongKeChi;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongkechi, container, false);
        totalChi = view.findViewById(R.id.totalChi);
        btnDateStart = view.findViewById(R.id.btnDateStart);
        btnDateEnd = view.findViewById(R.id.btnDateEnd);
        recyclerView = view.findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        totalChi();

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
        loaiChiDAO = new LoaiChiDAO(getContext());

        list = (ArrayList<LoaiChi>) loaiChiDAO.getAll();
        adapterThongKeChi = new AdapterThongKeChi(list,getContext());
        recyclerView.setAdapter(adapterThongKeChi);
        return view;
    }
    public void totalChi(){
        LoaiChiDAO loaiChiDAO = new LoaiChiDAO(getContext());
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        total = loaiChiDAO.totalChi();
        String s =decimalFormat.format(total);
        totalChi.setText("TỔNG CHI : "+s+" USD");
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
                list = loaiChiDAO.getThongKeChi(btnDateStart.getText().toString(), btnDateEnd.getText().toString());
                adapterThongKeChi = new AdapterThongKeChi(list, getContext());
                recyclerView.setAdapter(adapterThongKeChi);
                loaiChiDAO = new LoaiChiDAO(getContext());
                String date1 = btnDateStart.getText().toString();
                DecimalFormat decimalFormat = new DecimalFormat("#,###");
                total = loaiChiDAO.getTotalThuByDate(date1, date2.format(calendar.getTime()));
                String s = decimalFormat.format(total);
                totalChi.setText("TỔNG THU : "+s+" USD");
            }
        },years,months,dayOfWeek);
        datePickerDialog.show();
    }
}
