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

import nhatphong.ps11601.ps11601_phongdtn_asm.Model.LoaiChi;
import nhatphong.ps11601.ps11601_phongdtn_asm.Model.LoaiThu;
import nhatphong.ps11601.ps11601_phongdtn_asm.R;
import nhatphong.ps11601.ps11601_phongdtn_asm.TabFragment.Tab_LoaiChi;
import nhatphong.ps11601.ps11601_phongdtn_asm.TabFragment.Tab_LoaiThu;

public class AdapterTabLoaiChi extends RecyclerView.Adapter<AdapterTabLoaiChi.ViewHolder> {
    private ArrayList<LoaiChi> list;
    private Context context;
    private Tab_LoaiChi tabLoaiChi;

    public AdapterTabLoaiChi(ArrayList<LoaiChi> list, Context context, Tab_LoaiChi tabLoaiChi) {
        this.list = list;
        this.context = context;
        this.tabLoaiChi = tabLoaiChi;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.custom_tabloaithuview, parent, false);
        AdapterTabLoaiChi.ViewHolder viewHolder = new AdapterTabLoaiChi.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTabLoaiChi.ViewHolder holder, final int position) {
        final LoaiChi loaiChi = list.get(position);
        if(list != null) {
            holder.maGD.setText("Mã GD : "+String.valueOf(loaiChi.getMaGD()));
            holder.tenKhoanThu.setText(loaiChi.getTieuDe());
            holder.Ngay.setText(loaiChi.getNgay());
            holder.Tien.setText(String.valueOf(loaiChi.getTien()+" USD"));
        }
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabLoaiChi.openDeleteDialog(String.valueOf(loaiChi.getMaGD()),position);
            }
        });

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabLoaiChi.openEditDialog(position);
            }
        });
    }

    public Context getContext() {
        return context;
    }

    public ArrayList<LoaiChi> getList() {
        return list;
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
        public ImageView imgEdit,imgDelete;
        public TextView maGD,tenKhoanThu,Ngay,Tien;
        public LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.maGD = itemView.findViewById(R.id.maGiaoDich);
            this.tenKhoanThu = itemView.findViewById(R.id.tenKhoanThu);
            this.Ngay = itemView.findViewById(R.id.Ngay);
            this.Tien = itemView.findViewById(R.id.Tien);
            this.imgDelete = itemView.findViewById(R.id.imgDelete);
            this.imgEdit = itemView.findViewById(R.id.imgEdit);
            linearLayout =  itemView.findViewById(R.id.linearLayout);
        }
    }
}
