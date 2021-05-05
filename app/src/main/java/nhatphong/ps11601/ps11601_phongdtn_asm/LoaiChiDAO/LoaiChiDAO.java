package nhatphong.ps11601.ps11601_phongdtn_asm.LoaiChiDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import nhatphong.ps11601.ps11601_phongdtn_asm.Database.SQLiteHelper;
import nhatphong.ps11601.ps11601_phongdtn_asm.Model.KhoanThu;
import nhatphong.ps11601.ps11601_phongdtn_asm.Model.LoaiChi;
import nhatphong.ps11601.ps11601_phongdtn_asm.Model.LoaiThu;

public class LoaiChiDAO {
    private SQLiteDatabase DB;

    public LoaiChiDAO(Context context) {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(context);
        DB = sqLiteHelper.getWritableDatabase();
    }

    public long insert(LoaiChi item) {
        ContentValues values = new ContentValues();
        values.put("TenKhoanTC", item.getTieuDe());
        values.put("Ngay", item.getNgay());
        values.put("Tien", item.getTien());
        values.put("GhiChu", item.getMoTa());
        values.put("maLoai",item.getMaLoai());
        return DB.insert("TypeSpend", null, values);
    }
    public int update(LoaiChi item) {
        ContentValues values = new ContentValues();
        values.put("TenKhoanTC", item.getTieuDe());
        values.put("Ngay", item.getNgay());
        values.put("Tien",item.getTien());
        values.put("GhiChu",item.getMoTa());
        values.put("maLoai",item.getMaLoai());
        return DB.update("TypeSpend", values, "maTC=?", new String[]{String.valueOf(item.getMaGD())});
    }

    public int delete(String maTC) {
        return DB.delete("TypeSpend", "maTC=?", new String[]{maTC});
    }

    //Get tham so
    public List<LoaiChi> get(String sql, String...selectionArgs) {

        List<LoaiChi> listLoaiChi = new ArrayList<LoaiChi>();

        Cursor c = DB.rawQuery(sql, selectionArgs);

        while (c.moveToNext()){
            LoaiChi item = new LoaiChi();
            item.setMaGD(c.getInt(c.getColumnIndex("maTC")));
            item.setTieuDe(c.getString(c.getColumnIndex("TenKhoanTC")));
            item.setNgay(c.getString(c.getColumnIndex("Ngay")));
            item.setTien(c.getInt(c.getColumnIndex("Tien")));
            item.setMoTa(c.getString(c.getColumnIndex("GhiChu")));
            item.setMaLoai(c.getInt(c.getColumnIndex("maLoai")));
            listLoaiChi.add(item);
        }
        return listLoaiChi;
    }
    public List<LoaiChi> getAll() {
        String sql = "SELECT * FROM TypeSpend";
        return get(sql);
    }
    public double totalChi() {
        double total = 0.0;
        Cursor cursor = DB.rawQuery("SELECT SUM(Tien) FROM TypeSpend",null);
        if (cursor.moveToFirst()){
            do {
                total = cursor.getDouble(0);
            }while (cursor.moveToNext());
        }
        return total;
    }
    public double getTotalThuByDate(String date1, String date2){
        double total = 0.0;
        Cursor cursor = DB.rawQuery("SELECT SUM(Tien) FROM TypeSpend WHERE Ngay BETWEEN '"+date1+"' AND '"+date2+"'",null);
        if (cursor.moveToFirst()){
            do {
                total = cursor.getDouble(0);
            }while (cursor.moveToNext());
        }
        return total;
    }
    public ArrayList<LoaiChi> getThongKeChi(String date1, String date2){
        ArrayList<LoaiChi> list = new ArrayList<>();
        Cursor cursor = DB.rawQuery("SELECT * FROM TypeSpend WHERE ngay BETWEEN '"+date1+"' AND '"+date2+"'" ,null);
        if (cursor.moveToFirst()){
            do {
                int Magiaodich = cursor.getInt(0);
                String Tieude = cursor.getString(1);
                String Ngay = cursor.getString(2);
                float Tien = Float.parseFloat(cursor.getString(3));
                String Mota = cursor.getString(4);
                int Maloai = cursor.getInt(5);
                list.add(new LoaiChi(Magiaodich,Tieude,Ngay,Tien,Mota,Maloai));
            } while (cursor.moveToNext());
        }
        return list;
    }
}
