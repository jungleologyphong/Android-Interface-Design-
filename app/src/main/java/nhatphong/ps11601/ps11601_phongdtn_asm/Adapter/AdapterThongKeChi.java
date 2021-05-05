package nhatphong.ps11601.ps11601_phongdtn_asm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import nhatphong.ps11601.ps11601_phongdtn_asm.Model.LoaiChi;
import nhatphong.ps11601.ps11601_phongdtn_asm.R;

public class AdapterThongKeChi extends RecyclerView.Adapter<AdapterThongKeChi.ViewHolder> {
    ArrayList<LoaiChi> list;
    Context context;

    public AdapterThongKeChi(ArrayList<LoaiChi> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customitemthongkechi,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtKhoanChi.setText(list.get(position).getTieuDe());
        holder.txtmoney.setText(list.get(position).getTien()+" USD");
        holder.txtdate.setText(list.get(position).getNgay()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtKhoanChi,txtmoney,txtdate;
        public ViewHolder(View itemview) {
            super(itemview);
            txtKhoanChi = itemview.findViewById(R.id.txtKhoanChi);
            txtmoney = itemview.findViewById(R.id.txtmoney);
            txtdate = itemview.findViewById(R.id.txtdate);
        }
    }
}
