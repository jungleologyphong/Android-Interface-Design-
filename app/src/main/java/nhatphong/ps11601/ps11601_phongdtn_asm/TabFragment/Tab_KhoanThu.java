package nhatphong.ps11601.ps11601_phongdtn_asm.TabFragment;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;
import nhatphong.ps11601.ps11601_phongdtn_asm.Adapter.AdapterTabKhoanThu;
import nhatphong.ps11601.ps11601_phongdtn_asm.KhoanChiDAO.KhoanChiDAO;
import nhatphong.ps11601.ps11601_phongdtn_asm.KhoanThuDAO.KhoanThuDAO;
import nhatphong.ps11601.ps11601_phongdtn_asm.Model.KhoanChi;
import nhatphong.ps11601.ps11601_phongdtn_asm.Model.KhoanThu;
import nhatphong.ps11601.ps11601_phongdtn_asm.R;

public class Tab_KhoanThu extends Fragment {
    KhoanThuDAO khoanThuDAO;
    EditText enterKhoanThu,enterTT;
    ArrayList<KhoanThu> list;
    AdapterTabKhoanThu AdapterTabKhoanThu;
    Context context;
    ImageView btnAdd,imgEdit;
    Button btnAddKT,btnCan,btnCancel,btnDelete;
    KhoanThu khoanThu;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_khoanthu, container, false);
        recyclerView = view.findViewById(R.id.my_recycler_view);

        btnAdd = view.findViewById(R.id.btnAdd);
        khoanThuDAO = new KhoanThuDAO(getContext());
        list = new ArrayList<>();

        UpdateRecycleViewTab();
        
        list = (ArrayList<KhoanThu>) khoanThuDAO.getAll();

        RecyclerView recyclerView = view.findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterTabKhoanThu = new AdapterTabKhoanThu(getContext(),list,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(AdapterTabKhoanThu);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getContext());
            }
        });

        return view;
    }

    public void openDialog(final Context context){
        final BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(getContext());
        mBottomSheetDialog.setContentView(R.layout.add_thu);
        list = (ArrayList<KhoanThu>)khoanThuDAO.getAll();
        enterKhoanThu = mBottomSheetDialog.findViewById(R.id.enterKhoanThu);
        enterTT = mBottomSheetDialog.findViewById(R.id.enterTT);
        btnAddKT = mBottomSheetDialog.findViewById(R.id.btnAddKT);
        btnCan = mBottomSheetDialog.findViewById(R.id.btnCan);
        btnAddKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                khoanThu = new KhoanThu();
                khoanThu.setTenLoai(enterKhoanThu.getText().toString());
                khoanThu.setTrangThai(enterTT.getText().toString());
                khoanThuDAO.insert(khoanThu);
                UpdateRecycleViewTab();
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

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            final int position = viewHolder.getAdapterPosition();
            final KhoanThu deletedMovie = list.get(position);
            switch (direction){
                case ItemTouchHelper.LEFT :
                    khoanThuDAO = new KhoanThuDAO(getContext());
                    khoanThu = new KhoanThu();
                    khoanThuDAO.delete(deletedMovie.getMaLoai());
                    UpdateRecycleViewTab();
                    Toast.makeText(getContext(), "Delete", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(getContext(),c,recyclerView,viewHolder,dX,dY,actionState,isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimaryDark))
                    .addSwipeLeftActionIcon(R.drawable.delete)
                    .create().decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

    public void openDeleteDialog(final int maLoai){
        final BottomSheetDialog BottomSheetDialog = new BottomSheetDialog(getContext());
        BottomSheetDialog.setContentView(R.layout.delete_khoanthu);

        list = (ArrayList<KhoanThu>)khoanThuDAO.getAll();

        btnDelete = BottomSheetDialog.findViewById(R.id.btnDelete);
        btnCancel = BottomSheetDialog.findViewById(R.id.btnCancel);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                khoanThuDAO = new KhoanThuDAO(getContext());
                khoanThu = new KhoanThu();
                khoanThuDAO.delete(maLoai);
                BottomSheetDialog.dismiss();
                Toast.makeText(getContext(), "Delete", Toast.LENGTH_SHORT).show();
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
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        bottomSheetDialog.setContentView(R.layout.add_thu);
        list = (ArrayList<KhoanThu>) khoanThuDAO.getAll();
        khoanThu = new KhoanThu();
        khoanThu = list.get(position);
        enterKhoanThu = bottomSheetDialog.findViewById(R.id.enterKhoanThu);
        enterTT = bottomSheetDialog.findViewById(R.id.enterTT);
        btnAddKT = bottomSheetDialog.findViewById(R.id.btnAddKT);
        btnCan = bottomSheetDialog.findViewById(R.id.btnCan);
        enterKhoanThu.setText(khoanThu.getTenLoai());
        enterTT.setText(khoanThu.getTrangThai());

        btnAddKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                khoanThu = new KhoanThu();
                khoanThuDAO = new KhoanThuDAO(getContext());
                khoanThu.setTenLoai(enterKhoanThu.getText().toString());
                khoanThu.setTrangThai(enterTT.getText().toString());
                khoanThuDAO.update(khoanThu);
                UpdateRecycleViewTab();
                bottomSheetDialog.dismiss();
                Toast.makeText(getContext(), "Updating", Toast.LENGTH_LONG).show();
            }
        });
        btnCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.show();
    }

    public void UpdateRecycleViewTab(){
        khoanThuDAO = new KhoanThuDAO(getContext());
        list = (ArrayList<KhoanThu>) khoanThuDAO.getAll();
        AdapterTabKhoanThu = new AdapterTabKhoanThu(getContext(),list,this);
        recyclerView.setAdapter(AdapterTabKhoanThu);
    }
}
