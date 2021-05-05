package nhatphong.ps11601.ps11601_phongdtn_asm.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import nhatphong.ps11601.ps11601_phongdtn_asm.Model.KhoanChi;
import nhatphong.ps11601.ps11601_phongdtn_asm.Model.KhoanThu;
import nhatphong.ps11601.ps11601_phongdtn_asm.R;

public class AdapterSninnerChi extends BaseAdapter {

    ArrayList<KhoanChi> list;
    Context context;

    public AdapterSninnerChi(ArrayList<KhoanChi> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = ((Activity)context).getLayoutInflater().inflate(R.layout.item_sp_thu,viewGroup,false);
        TextView txtSpinnerThu = view.findViewById(R.id.txtSpinnerThu);
        txtSpinnerThu.setText(list.get(i).getTenLoai());
        return view;
    }
}
