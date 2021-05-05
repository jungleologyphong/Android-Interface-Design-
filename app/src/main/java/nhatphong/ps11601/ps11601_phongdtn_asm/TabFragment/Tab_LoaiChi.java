package nhatphong.ps11601.ps11601_phongdtn_asm.TabFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;
import nhatphong.ps11601.ps11601_phongdtn_asm.Adapter.AdapterSninner;
import nhatphong.ps11601.ps11601_phongdtn_asm.Adapter.AdapterSninnerChi;
import nhatphong.ps11601.ps11601_phongdtn_asm.Adapter.AdapterTabLoaiChi;
import nhatphong.ps11601.ps11601_phongdtn_asm.KhoanChiDAO.KhoanChiDAO;
import nhatphong.ps11601.ps11601_phongdtn_asm.KhoanThuDAO.KhoanThuDAO;
import nhatphong.ps11601.ps11601_phongdtn_asm.LoaiChiDAO.LoaiChiDAO;
import nhatphong.ps11601.ps11601_phongdtn_asm.Model.KhoanChi;
import nhatphong.ps11601.ps11601_phongdtn_asm.Model.KhoanThu;
import nhatphong.ps11601.ps11601_phongdtn_asm.Model.LoaiChi;
import nhatphong.ps11601.ps11601_phongdtn_asm.Model.LoaiThu;
import nhatphong.ps11601.ps11601_phongdtn_asm.R;

public class Tab_LoaiChi extends Fragment {
    Context context;
    EditText enterMaGD,enterTieuDe,enterTien,enterMota;
    Button btnAddKT,btnCan,btnDelete,btnCancel,enterDate;
    ArrayList<KhoanChi> listKhoanChi;
    ArrayList<LoaiChi> list;
    LoaiChiDAO loaiChiDAO;
    RecyclerView recyclerView;
    ImageView btnAdd;
    Dialog dialog;
    nhatphong.ps11601.ps11601_phongdtn_asm.Adapter.AdapterTabLoaiChi AdapterTabLoaiChi;
    LoaiChi loaiChi;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;
    AdapterSninnerChi adapterSninnerChi;
    KhoanChiDAO khoanChiDAO;
    Spinner spinner;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_loaichi,container,false);
        list = new ArrayList<>();
        recyclerView = view.findViewById(R.id.my_recycler_view);
        btnAdd = view.findViewById(R.id.btnAdd);
        loaiChiDAO = new LoaiChiDAO(getContext());
        ArrayList<LoaiChi> list = new ArrayList<>();
        list = (ArrayList<LoaiChi>) loaiChiDAO.getAll();
        UpdateRecyclerView();
        RecyclerView recyclerView = view.findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterTabLoaiChi = new AdapterTabLoaiChi(list,getContext(),this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(AdapterTabLoaiChi);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        return view;
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            final int position = viewHolder.getAdapterPosition();
            final LoaiChi deletedMovie = list.get(position);
            switch (direction){
                case ItemTouchHelper.LEFT :
                    loaiChiDAO = new LoaiChiDAO(getContext());
                    loaiChi = new LoaiChi();
                    loaiChiDAO.delete(String.valueOf(deletedMovie.getMaGD()));
                    UpdateRecyclerView();
                    Toast.makeText(getContext(), "Delete", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(getContext(),c,recyclerView,viewHolder,dX,dY,actionState,isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(getContext(),R.color.white))
                    .addSwipeLeftActionIcon(R.drawable.delete)
                    .create().decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

    public void openDialog(){
        final BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(getContext());
        mBottomSheetDialog.setContentView(R.layout.add_loaichi);
        list = (ArrayList<LoaiChi>) loaiChiDAO.getAll();
        enterTieuDe = mBottomSheetDialog.findViewById(R.id.enterTieuDe);
        enterDate = mBottomSheetDialog.findViewById(R.id.enterDate);
        enterTien = mBottomSheetDialog.findViewById(R.id.enterTien);
        enterMota = mBottomSheetDialog.findViewById(R.id.enterMoTa);
        btnAddKT = mBottomSheetDialog.findViewById(R.id.btnAddKT);
        btnCan = mBottomSheetDialog.findViewById(R.id.btnCan);

        spinner = mBottomSheetDialog.findViewById(R.id.spinner);

        listKhoanChi = (ArrayList<KhoanChi>) khoanChiDAO.getAll();
        adapterSninnerChi = new AdapterSninnerChi(listKhoanChi,getContext());
        spinner.setAdapter(adapterSninnerChi);

        enterDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        calendar.set(year,month,dayOfMonth);
                        enterDate.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },day,month,year);
                datePickerDialog.show();
            }
        });

        btnAddKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaiChi = new LoaiChi();
                loaiChi.setTieuDe(enterTieuDe.getText().toString());
                loaiChi.setNgay(enterDate.getText().toString());
                loaiChi.setTien(Float.parseFloat(enterTien.getText().toString()));
                loaiChi.setMoTa(enterMota.getText().toString());
                loaiChi.setMaLoai(spinner.getSelectedItemPosition());
                loaiChiDAO.insert(loaiChi);
                UpdateRecyclerView();
                Toast.makeText(getContext(), "Save", Toast.LENGTH_SHORT).show();
                mBottomSheetDialog.dismiss();
            }
        });

        btnCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
            }
        });
        mBottomSheetDialog.show();
    }

    public void openDeleteDialog(final String maTC,int position){
        final BottomSheetDialog BottomSheetDialog = new BottomSheetDialog(getContext());
        BottomSheetDialog.setContentView(R.layout.delete_loaithu);

        list = (ArrayList<LoaiChi>) loaiChiDAO.getAll();

        btnDelete = BottomSheetDialog.findViewById(R.id.btnDelete);
        btnCancel = BottomSheetDialog.findViewById(R.id.btnCancel);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaiChiDAO = new LoaiChiDAO(getContext());
                loaiChi = new LoaiChi();
                loaiChiDAO.delete(maTC);
                UpdateRecyclerView();
                BottomSheetDialog.dismiss();
                Toast.makeText(getContext(), "Deleting", Toast.LENGTH_SHORT).show();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog.dismiss();
            }
        });
        BottomSheetDialog.show();
    }
    public void openEditDialog(final int position){
        final BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(getContext());
        mBottomSheetDialog.setContentView(R.layout.add_loaichi);
        loaiChi = list.get(position);
        enterTieuDe = mBottomSheetDialog.findViewById(R.id.enterTieuDe);
        enterDate = mBottomSheetDialog.findViewById(R.id.enterDate);
        enterTien = mBottomSheetDialog.findViewById(R.id.enterTien);
        enterMota = mBottomSheetDialog.findViewById(R.id.enterMoTa);

        btnAddKT = mBottomSheetDialog.findViewById(R.id.btnAddKT);
        btnCan = mBottomSheetDialog.findViewById(R.id.btnCan);

        spinner = mBottomSheetDialog.findViewById(R.id.spinner);

        khoanChiDAO = new KhoanChiDAO(getContext());
        listKhoanChi = (ArrayList<KhoanChi>)khoanChiDAO.getAll();
        adapterSninnerChi = new AdapterSninnerChi(listKhoanChi,getContext());
        spinner.setAdapter(adapterSninnerChi);

        enterTieuDe.setText(loaiChi.getTieuDe());
        enterDate.setText(loaiChi.getNgay());
        enterTien.setText(loaiChi.getTien()+"");
        enterMota.setText(loaiChi.getMoTa());
        spinner.setSelection(loaiChi.getMaLoai());

        enterDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        calendar.set(year,month,dayOfMonth);
                        enterDate.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },day,month,year);
                datePickerDialog.show();
            }
        });

        btnAddKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaiChiDAO = new LoaiChiDAO(getContext());
                loaiChi.setTieuDe(enterTieuDe.getText().toString());
                loaiChi.setNgay(enterDate.getText().toString());
                loaiChi.setTien(Float.parseFloat(enterTien.getText().toString()));
                loaiChi.setMoTa(enterMota.getText().toString());
                loaiChi.setMaLoai(spinner.getSelectedItemPosition());
                loaiChiDAO.update(loaiChi);
                UpdateRecyclerView();
                Toast.makeText(getContext(), "Save", Toast.LENGTH_SHORT).show();
                mBottomSheetDialog.dismiss();
            }
        });
        btnCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
            }
        });
        mBottomSheetDialog.show();
    }

    public void UpdateRecyclerView(){
        list = (ArrayList<LoaiChi>) loaiChiDAO.getAll();
        AdapterTabLoaiChi = new AdapterTabLoaiChi(list,getContext(),this);
        recyclerView.setAdapter(AdapterTabLoaiChi);
    }
}
