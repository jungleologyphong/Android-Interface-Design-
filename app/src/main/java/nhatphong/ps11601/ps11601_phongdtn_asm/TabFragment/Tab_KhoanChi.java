package nhatphong.ps11601.ps11601_phongdtn_asm.TabFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;
import nhatphong.ps11601.ps11601_phongdtn_asm.Adapter.AdapterTabKhoanChi;
import nhatphong.ps11601.ps11601_phongdtn_asm.KhoanChiDAO.KhoanChiDAO;
import nhatphong.ps11601.ps11601_phongdtn_asm.Model.KhoanChi;
import nhatphong.ps11601.ps11601_phongdtn_asm.R;

public class Tab_KhoanChi extends Fragment {
    RecyclerView recyclerView;
    KhoanChiDAO khoanChiDAO;
    EditText enterKhoanThu,enterTT;
    ArrayList<KhoanChi> list;
    AdapterTabKhoanChi AdapterTabKhoanChi;
    Context context;
    ImageView btnAdd,imgEdit;
    Button btnAddKT,btnCan,btnCancel,btnDelete;
    KhoanChi khoanChi;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_khoanchi, container, false);
        recyclerView = view.findViewById(R.id.my_recycler_view);
        btnAdd = view.findViewById(R.id.btnAdd);
        khoanChiDAO = new KhoanChiDAO(getContext());
        list = new ArrayList<>();
        imgEdit = view.findViewById(R.id.imgEdit);
        UpdateRecycleViewTab();
        list = (ArrayList<KhoanChi>) khoanChiDAO.getAll();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        RecyclerView recyclerView = view.findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterTabKhoanChi = new AdapterTabKhoanChi(getContext(),list,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(AdapterTabKhoanChi);
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
            final KhoanChi deletedMovie = list.get(position);
            switch (direction){
                case ItemTouchHelper.LEFT :
                    khoanChiDAO = new KhoanChiDAO(getContext());
                    khoanChi = new KhoanChi();
                    khoanChiDAO.delete(deletedMovie.getMaLoai());
                    UpdateRecycleViewTab();
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
        mBottomSheetDialog.setContentView(R.layout.add_chi);
        list = (ArrayList<KhoanChi>)khoanChiDAO.getAll();
        enterKhoanThu = mBottomSheetDialog.findViewById(R.id.enterKhoanThu);
        enterTT = mBottomSheetDialog.findViewById(R.id.enterTT);
        btnAddKT = mBottomSheetDialog.findViewById(R.id.btnAddKT);
        btnCan = mBottomSheetDialog.findViewById(R.id.btnCan);

        btnAddKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                khoanChi = new KhoanChi();
                khoanChi.setTenLoai(enterKhoanThu.getText().toString());
                khoanChi.setTrangThai(enterTT.getText().toString());
                khoanChiDAO.insert(khoanChi);
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

    public void openDeleteDialog(final int maLoai,int position){
        final BottomSheetDialog BottomSheetDialog = new BottomSheetDialog(getContext());
        BottomSheetDialog.setContentView(R.layout.delete_khoanthu);

        list = (ArrayList<KhoanChi>)khoanChiDAO.getAll();

        btnDelete = BottomSheetDialog.findViewById(R.id.btnDelete);
        btnCancel = BottomSheetDialog.findViewById(R.id.btnCancel);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                khoanChiDAO= new KhoanChiDAO(getContext());
                khoanChi = new KhoanChi();
                khoanChiDAO.delete(maLoai);
                UpdateRecycleViewTab();
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
        bottomSheetDialog.setContentView(R.layout.add_chi);
        khoanChi = list.get(position);
        list = (ArrayList<KhoanChi>) khoanChiDAO.getAll();
        enterKhoanThu = bottomSheetDialog.findViewById(R.id.enterKhoanThu);
        enterTT = bottomSheetDialog.findViewById(R.id.enterTT);
        btnAddKT = bottomSheetDialog.findViewById(R.id.btnAddKT);
        btnCan = bottomSheetDialog.findViewById(R.id.btnCan);
        enterKhoanThu.setText(khoanChi.getTenLoai());
        enterTT.setText(khoanChi.getTrangThai());

        btnAddKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                khoanChi = new KhoanChi();
                khoanChiDAO = new KhoanChiDAO(getContext());
                khoanChi.setTenLoai(enterKhoanThu.getText().toString());
                khoanChi.setTrangThai(enterTT.getText().toString());
                khoanChiDAO.update(khoanChi);
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
        khoanChiDAO = new KhoanChiDAO(getContext());
        list = (ArrayList<KhoanChi>) khoanChiDAO.getAll();
        AdapterTabKhoanChi = new AdapterTabKhoanChi(context,list,this);
        recyclerView.setAdapter(AdapterTabKhoanChi);
    }
}
