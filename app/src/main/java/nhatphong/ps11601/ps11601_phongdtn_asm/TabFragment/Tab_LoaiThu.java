package nhatphong.ps11601.ps11601_phongdtn_asm.TabFragment;

import android.app.DatePickerDialog;
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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;
import nhatphong.ps11601.ps11601_phongdtn_asm.Adapter.AdapterTabLoaiThu;
import nhatphong.ps11601.ps11601_phongdtn_asm.Adapter.AdapterSninner;
import nhatphong.ps11601.ps11601_phongdtn_asm.KhoanThuDAO.KhoanThuDAO;
import nhatphong.ps11601.ps11601_phongdtn_asm.Model.KhoanThu;
import nhatphong.ps11601.ps11601_phongdtn_asm.Model.LoaiThu;
import nhatphong.ps11601.ps11601_phongdtn_asm.R;
import nhatphong.ps11601.ps11601_phongdtn_asm.LoaiThuDAO.LoaiThuDao;

public class Tab_LoaiThu extends Fragment {
    Context context;
    KhoanThu khoanThu;
    EditText enterTieuDe,enterTien,enterMota,edtSearch;
    Button btnAddKT,btnCan,btnDelete,btnCancel,enterDate;
    ArrayList<LoaiThu> list;
    ArrayList<KhoanThu> listKhoanThu;
    LoaiThuDao loaiThuDao;
    RecyclerView recyclerView;
    ImageView btnAdd;
    AdapterTabLoaiThu AdapterTabLoaiThu;
    LoaiThu loaiThu;
    BottomSheetDialog mbottomSheetDialog;
    Spinner spinner;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;
    KhoanThuDAO khoanThuDAO;
    AdapterSninner adapterSpThu;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_loaithu,container,false);
        list = new ArrayList<>();
        recyclerView = view.findViewById(R.id.my_recycler_view);
        btnAdd = view.findViewById(R.id.btnAdd);
        loaiThuDao = new LoaiThuDao(getContext());
        ArrayList<LoaiThu> list = new ArrayList<>();
        list = (ArrayList<LoaiThu>) loaiThuDao.getAll();
        UpdateRecyclerView();
        RecyclerView recyclerView = view.findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterTabLoaiThu = new AdapterTabLoaiThu(list,getContext(),this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(AdapterTabLoaiThu);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
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
            final LoaiThu deletedMovie = list.get(position);
            switch (direction){
                case ItemTouchHelper.LEFT :
                    loaiThuDao = new LoaiThuDao(getContext());
                    loaiThu = new LoaiThu();
                    loaiThuDao.delete(String.valueOf(deletedMovie.getMaGD()));
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
        mBottomSheetDialog.setContentView(R.layout.add_loaithu);
        khoanThuDAO = new KhoanThuDAO(getContext());

        list = (ArrayList<LoaiThu>) loaiThuDao.getAll();

        enterTieuDe = mBottomSheetDialog.findViewById(R.id.enterTieuDe);
        enterDate = mBottomSheetDialog.findViewById(R.id.enterDate);
        enterTien = mBottomSheetDialog.findViewById(R.id.enterTien);
        enterMota = mBottomSheetDialog.findViewById(R.id.enterMoTa);
        btnAddKT = mBottomSheetDialog.findViewById(R.id.btnAddKT);
        btnCan = mBottomSheetDialog.findViewById(R.id.btnCan);
        spinner = mBottomSheetDialog.findViewById(R.id.spinner);

        listKhoanThu = (ArrayList<KhoanThu>)khoanThuDAO.getAll();
        adapterSpThu = new AdapterSninner(listKhoanThu,getContext());
        spinner.setAdapter(adapterSpThu);

        enterDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                final int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                final int months = calendar.get(Calendar.MONTH);
                final int years = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayofWeek) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        calendar.set(year,month,dayofWeek);
                        enterDate.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },dayOfWeek,months,years);
                datePickerDialog.show();
            }
        });

        btnAddKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaiThu = new LoaiThu();
                loaiThu.setTieuDe(enterTieuDe.getText().toString());
                loaiThu.setNgay(enterDate.getText().toString());
                loaiThu.setTien(Float.parseFloat(enterTien.getText().toString()));
                loaiThu.setMoTa(enterMota.getText().toString());
                loaiThu.setMaLoai(spinner.getSelectedItemPosition());
                loaiThuDao.insert(loaiThu);
                UpdateRecyclerView();
                Toast.makeText(getContext(), "Save Successfully", Toast.LENGTH_SHORT).show();
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

        list = (ArrayList<LoaiThu>) loaiThuDao.getAll();

        btnDelete = BottomSheetDialog.findViewById(R.id.btnDelete);
        btnCancel = BottomSheetDialog.findViewById(R.id.btnCancel);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaiThuDao = new LoaiThuDao(getContext());
                loaiThu = new LoaiThu();
                loaiThuDao.delete(maTC);
                UpdateRecyclerView();
                BottomSheetDialog.dismiss();
                Toast.makeText(getContext(), "Delete Successfully", Toast.LENGTH_SHORT).show();
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
        mbottomSheetDialog = new BottomSheetDialog(getContext());
        mbottomSheetDialog.setContentView(R.layout.add_loaithu);
        enterTieuDe = mbottomSheetDialog.findViewById(R.id.enterTieuDe);
        enterDate = mbottomSheetDialog.findViewById(R.id.enterDate);
        enterTien = mbottomSheetDialog.findViewById(R.id.enterTien);
        enterMota = mbottomSheetDialog.findViewById(R.id.enterMoTa);
        btnAddKT = mbottomSheetDialog.findViewById(R.id.btnAddKT);
        btnCan = mbottomSheetDialog.findViewById(R.id.btnCan);
        spinner = mbottomSheetDialog.findViewById(R.id.spinner);

        khoanThuDAO = new KhoanThuDAO(getContext());
        listKhoanThu = (ArrayList<KhoanThu>)khoanThuDAO.getAll();
        adapterSpThu = new AdapterSninner(listKhoanThu,getContext());
        spinner.setAdapter(adapterSpThu);

        loaiThu = list.get(position);

        enterTieuDe.setText(loaiThu.getTieuDe());
        enterDate.setText(loaiThu.getNgay());
        enterTien.setText(loaiThu.getTien()+"");
        enterMota.setText(loaiThu.getMoTa());
        spinner.setSelection(loaiThu.getMaLoai());

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
                loaiThuDao = new LoaiThuDao(getContext());
                loaiThu.setTieuDe(enterTieuDe.getText().toString());
                loaiThu.setNgay(enterDate.getText().toString());
                loaiThu.setTien(Float.parseFloat(enterTien.getText().toString()));
                loaiThu.setMoTa(enterMota.getText().toString());
                loaiThu.setMaLoai(spinner.getSelectedItemPosition());
                loaiThuDao.update(loaiThu);
                UpdateRecyclerView();
                Toast.makeText(getContext(), "Update Successfully", Toast.LENGTH_SHORT).show();
                mbottomSheetDialog.dismiss();
            }
        });
        btnCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbottomSheetDialog.dismiss();
            }
        });
        mbottomSheetDialog.show();
    }

    public void UpdateRecyclerView(){
        list = (ArrayList<LoaiThu>) loaiThuDao.getAll();
        AdapterTabLoaiThu = new AdapterTabLoaiThu(list,getContext(),this);
        recyclerView.setAdapter(AdapterTabLoaiThu);
    }
}
