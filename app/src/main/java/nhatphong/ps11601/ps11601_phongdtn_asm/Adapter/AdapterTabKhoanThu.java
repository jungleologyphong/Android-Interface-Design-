package nhatphong.ps11601.ps11601_phongdtn_asm.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.util.ArrayList;
import nhatphong.ps11601.ps11601_phongdtn_asm.Model.KhoanThu;
import nhatphong.ps11601.ps11601_phongdtn_asm.R;
import nhatphong.ps11601.ps11601_phongdtn_asm.TabFragment.Tab_KhoanThu;


public class AdapterTabKhoanThu extends RecyclerView.Adapter<AdapterTabKhoanThu.ViewHolder>{
    public Context context;
    public Tab_KhoanThu tabKhoanThu;
    public ArrayList<KhoanThu> list;

    public AdapterTabKhoanThu() {
    }

    public AdapterTabKhoanThu(Context context, ArrayList<KhoanThu> list, Tab_KhoanThu tabKhoanThu) {
        this.context = context;
        this.list = list;
        this.tabKhoanThu = tabKhoanThu;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.custom_tabkhoanthurecycleview, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final KhoanThu khoanThu = list.get(position);
        if(list != null){
            holder.maLoai.setText("Mã Khoản Thu : "+(khoanThu.getMaLoai()));
            holder.tenLoai.setText("Tên : "+khoanThu.getTenLoai());
            holder.trangThai.setText("TT : "+khoanThu.getTrangThai());
        }

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabKhoanThu.openEditDialog(position);
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 tabKhoanThu.openDeleteDialog(khoanThu.getMaLoai());
                }
            });
        }


    public Context getContext() {
        return context;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public ArrayList<KhoanThu> getList() {
        return list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView maLoai,tenLoai,trangThai;
        public LinearLayout linearLayout;
        public ImageView imgEdit,imgDelete;
        public ViewHolder(View itemView) {
            super(itemView);
            this.maLoai = itemView.findViewById(R.id.maLoai);
            this.tenLoai = itemView.findViewById(R.id.tenLoai);
            this.trangThai = itemView.findViewById(R.id.trangThai);
            this.imgDelete = itemView.findViewById(R.id.imgDelete);
            this.imgEdit = itemView.findViewById(R.id.imgEdit);
            this.linearLayout =  itemView.findViewById(R.id.linearLayout);
        }
    }
}