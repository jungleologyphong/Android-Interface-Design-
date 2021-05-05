package nhatphong.ps11601.ps11601_phongdtn_asm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import nhatphong.ps11601.ps11601_phongdtn_asm.Model.KhoanChi;
import nhatphong.ps11601.ps11601_phongdtn_asm.Model.KhoanThu;
import nhatphong.ps11601.ps11601_phongdtn_asm.R;
import nhatphong.ps11601.ps11601_phongdtn_asm.TabFragment.Tab_KhoanChi;
import nhatphong.ps11601.ps11601_phongdtn_asm.TabFragment.Tab_KhoanThu;

public class AdapterTabKhoanChi extends RecyclerView.Adapter<AdapterTabKhoanChi.ViewHolder> {
    public Context context;
    public Tab_KhoanChi tabKhoanChi;
    public ArrayList<KhoanChi> list;

    public AdapterTabKhoanChi(Context context, ArrayList<KhoanChi> list, Tab_KhoanChi tabkhoanChi) {
        this.context = context;
        this.list = list;
        this.tabKhoanChi = tabkhoanChi;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.custom_tabkhoanchirecycleview, parent, false);
        AdapterTabKhoanChi.ViewHolder viewHolder = new AdapterTabKhoanChi.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final KhoanChi khoanChi = list.get(position);
        if(list != null){
            holder.maLoai.setText("Mã Khoản Chi : "+String.valueOf(khoanChi.getMaLoai()));
            holder.tenLoai.setText("Tên : "+khoanChi.getTenLoai());
            holder.trangThai.setText("TT : "+khoanChi.getTrangThai());
        }

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabKhoanChi.openEditDialog(position);
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabKhoanChi.openDeleteDialog(khoanChi.getMaLoai(),position);
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
