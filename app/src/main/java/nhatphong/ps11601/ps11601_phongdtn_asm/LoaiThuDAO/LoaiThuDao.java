package nhatphong.ps11601.ps11601_phongdtn_asm.LoaiThuDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import nhatphong.ps11601.ps11601_phongdtn_asm.Database.SQLiteHelper;
import nhatphong.ps11601.ps11601_phongdtn_asm.Model.LoaiThu;

public class LoaiThuDao {
    private SQLiteDatabase DB;

    public LoaiThuDao(Context context) {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(context);
        DB = sqLiteHelper.getWritableDatabase();
    }
    public long insert(LoaiThu item) {
        ContentValues values = new ContentValues();
        values.put("TenKhoanTC", item.getTieuDe());
        values.put("Ngay", item.getNgay());
        values.put("Tien", item.getTien());
        values.put("GhiChu", item.getMoTa());
        values.put("maLoai",item.getMaLoai());
        return DB.insert("TypeIncome", null, values);
    }
    public int update(LoaiThu item) {
        ContentValues values = new ContentValues();
        values.put("TenKhoanTC", item.getTieuDe());
        values.put("Ngay", item.getNgay());
        values.put("Tien",item.getTien());
        values.put("GhiChu",item.getMoTa());
        values.put("maLoai",item.getMaLoai());
        return DB.update("TypeIncome", values, "maTC=?", new String[]{String.valueOf(item.getMaGD())});
    }

    public int delete(String maTC) {
        return DB.delete("TypeIncome", "maTC=?", new String[]{maTC});
    }
    //Get tham so
    public List<LoaiThu> get(String sql, String...selectionArgs) {

        List<LoaiThu> listLoaiThu = new ArrayList<LoaiThu>();

        Cursor c = DB.rawQuery(sql, selectionArgs);

        while (c.moveToNext()){
            LoaiThu item = new LoaiThu();
            item.setMaGD(c.getInt(c.getColumnIndex("maTC")));
            item.setTieuDe(c.getString(c.getColumnIndex("TenKhoanTC")));
            item.setNgay(c.getString(c.getColumnIndex("Ngay")));
            item.setTien(c.getInt(c.getColumnIndex("Tien")));
            item.setMoTa(c.getString(c.getColumnIndex("GhiChu")));
            item.setMaLoai(c.getInt(c.getColumnIndex("maLoai")));
            listLoaiThu.add(item);
        }
        return listLoaiThu;
    }
    public List<LoaiThu> getAll() {
        String sql = "SELECT * FROM TypeIncome";
        return get(sql);
    }

    public int totalThu() {
        int total = 0;
        Cursor cursor = DB.rawQuery("SELECT SUM(Tien) FROM TypeIncome",null);
        if (cursor.moveToFirst()){
            do {
                total = cursor.getInt(0);
            }while (cursor.moveToNext());
        }
        return total;
    }
    public int totalThuByMonth() {
        int total = 0;
        Cursor cursor = DB.rawQuery("SELECT Tien FROM TypeIncome",null);
        if (cursor.moveToFirst()){
            do {
                total = cursor.getInt(0);
            }while (cursor.moveToNext());
        }
        return total;
    }
    public int getTotalThuByDate(String date1, String date2){
        int total = 0;
        Cursor cursor = DB.rawQuery("SELECT SUM(Tien) FROM TypeIncome WHERE Ngay BETWEEN '"+date1+"' AND '"+date2+"'",null);
        if (cursor.moveToFirst()){
            do {
                total = cursor.getInt(0);
            }while (cursor.moveToNext());
        }
        return total;
    }

    public ArrayList<LoaiThu> getThongKeThu(String date1, String date2){
        ArrayList<LoaiThu> list = new ArrayList<>();
        Cursor cursor = DB.rawQuery("SELECT * FROM TypeIncome WHERE ngay BETWEEN '"+date1+"' AND '"+date2+"'" ,null);
        if (cursor.moveToFirst()){
            do {
                int Magiaodich = cursor.getInt(0);
                String Tieude = cursor.getString(1);
                String Ngay = cursor.getString(2);
                float Tien = Float.parseFloat(cursor.getString(3));
                String Mota = cursor.getString(4);
                int Maloai = cursor.getInt(5);
                list.add(new LoaiThu(Magiaodich,Tieude,Ngay,Tien,Mota,Maloai));
            } while (cursor.moveToNext());
        }
        return list;
    }
}