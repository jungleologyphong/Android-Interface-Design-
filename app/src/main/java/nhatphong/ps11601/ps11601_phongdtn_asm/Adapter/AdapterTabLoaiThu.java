package nhatphong.ps11601.ps11601_phongdtn_asm.Adapter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Calendar;
import nhatphong.ps11601.ps11601_phongdtn_asm.Model.LoaiThu;
import nhatphong.ps11601.ps11601_phongdtn_asm.R;
import nhatphong.ps11601.ps11601_phongdtn_asm.TabFragment.Tab_LoaiThu;
import nhatphong.ps11601.ps11601_phongdtn_asm.LoaiThuDAO.LoaiThuDao;

public class AdapterTabLoaiThu extends RecyclerView.Adapter<AdapterTabLoaiThu.ViewHolder> {
    private ArrayList<LoaiThu> list;
    private Context context;
    private Tab_LoaiThu tabLoaiThu;

    public AdapterTabLoaiThu(ArrayList<LoaiThu> list, Context context, Tab_LoaiThu tabLoaiThu) {
        this.list = list;
        this.context = context;
        this.tabLoaiThu = tabLoaiThu;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.custom_tabloaithuview, parent, false);
        AdapterTabLoaiThu.ViewHolder viewHolder = new AdapterTabLoaiThu.ViewHolder(listItem);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final LoaiThu loaiThu = list.get(position);
        if(list != null) {
            holder.maGD.setText("Mã GD : "+String.valueOf(loaiThu.getMaGD()));
            holder.tenKhoanThu.setText(loaiThu.getTieuDe());
            holder.Ngay.setText(loaiThu.getNgay());
            holder.Tien.setText(String.valueOf(loaiThu.getTien()+" USD"));
        }
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabLoaiThu.openDeleteDialog(String.valueOf(loaiThu.getMaGD()),position);
            }
        });

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabLoaiThu.openEditDialog(position);
            }
        });
    }


    public Context getContext() {
        return context;
    }

    public ArrayList<LoaiThu> getList() {
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
