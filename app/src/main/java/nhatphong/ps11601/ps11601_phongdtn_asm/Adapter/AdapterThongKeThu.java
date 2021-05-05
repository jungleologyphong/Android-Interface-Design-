package nhatphong.ps11601.ps11601_phongdtn_asm.Adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import nhatphong.ps11601.ps11601_phongdtn_asm.LoaiThuDAO.LoaiThuDao;
import nhatphong.ps11601.ps11601_phongdtn_asm.Model.LoaiThu;
import nhatphong.ps11601.ps11601_phongdtn_asm.R;

public class AdapterThongKeThu extends RecyclerView.Adapter<AdapterThongKeThu.ViewHolder> {
    ArrayList<LoaiThu> list;
    Context context;

    public AdapterThongKeThu(ArrayList<LoaiThu> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customitemthongke,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtKhoanThu.setText(list.get(position).getTieuDe());
        holder.txtmoney.setText(list.get(position).getTien()+" USD");
        holder.txtdate.setText(list.get(position).getNgay()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtKhoanThu,txtmoney,txtdate;
        public ViewHolder(View itemview) {
            super(itemview);
            txtKhoanThu = itemview.findViewById(R.id.txtKhoanThu);
            txtmoney = itemview.findViewById(R.id.txtmoney);
            txtdate = itemview.findViewById(R.id.txtdate);
        }
    }
}
