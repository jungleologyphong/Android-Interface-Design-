package nhatphong.ps11601.ps11601_phongdtn_asm.KhoanChiDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import nhatphong.ps11601.ps11601_phongdtn_asm.Database.SQLiteHelper;
import nhatphong.ps11601.ps11601_phongdtn_asm.Model.KhoanChi;
import nhatphong.ps11601.ps11601_phongdtn_asm.Model.KhoanThu;

public class KhoanChiDAO {
    private SQLiteDatabase DB;

    public KhoanChiDAO(Context context) {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(context);
        DB = sqLiteHelper.getWritableDatabase();
    }

    public long insert(KhoanChi item) {
        ContentValues values = new ContentValues();
        values.put("tenLoai", item.getTenLoai());
        values.put("trangThai", item.getTrangThai());
        return DB.insert("Spend", null, values);
    }

    public int update(KhoanChi item) {
        ContentValues values = new ContentValues();
        values.put("tenLoai", item.getTenLoai());
        values.put("trangThai", item.getTrangThai());
        return DB.update("Spend", values, "maLoai=?", new String[]{String.valueOf(item.getMaLoai())});
    }

    public int delete(int maLoai) {
        return DB.delete("Spend", "maLoai=?", new String[]{String.valueOf(maLoai)});
    }

    public List<KhoanChi> get(String sql, String...selectionArgs) {

        List<KhoanChi> listKhoanChi = new ArrayList<>();

        Cursor c = DB.rawQuery(sql, selectionArgs);

        while (c.moveToNext()){
            KhoanChi item = new KhoanChi();
            item.setMaLoai(c.getInt(c.getColumnIndex("maLoai")));
            item.setTenLoai(c.getString(c.getColumnIndex("tenLoai"))) ;
            item.setTrangThai(c.getString(c.getColumnIndex("trangThai"))) ;
            listKhoanChi.add(item);
        }
        return listKhoanChi;
    }
    public List<KhoanChi> getAll() {
        String sqlGetAll = "SELECT * FROM Spend";
        return get(sqlGetAll);
    }
}
