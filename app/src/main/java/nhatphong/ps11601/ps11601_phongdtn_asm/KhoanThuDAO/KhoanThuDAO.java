package nhatphong.ps11601.ps11601_phongdtn_asm.KhoanThuDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

import nhatphong.ps11601.ps11601_phongdtn_asm.Database.SQLiteHelper;
import nhatphong.ps11601.ps11601_phongdtn_asm.Model.KhoanThu;

public class KhoanThuDAO {
    private SQLiteDatabase DB;

    public KhoanThuDAO(Context context) {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(context);
        DB = sqLiteHelper.getWritableDatabase();
    }

    public long insert(KhoanThu item) {
        ContentValues values = new ContentValues();
        values.put("tenLoai", item.getTenLoai());
        values.put("trangThai", item.getTrangThai());
        return DB.insert("Income", null, values);
    }

    public int update(KhoanThu item) {
        ContentValues values = new ContentValues();
        values.put("tenLoai", item.getTenLoai());
        values.put("trangThai", item.getTrangThai());
        return DB.update("Income", values, "maLoai=?", new String[]{String.valueOf(item.getMaLoai())});
    }

    public int delete(int maLoai) {
        return DB.delete("Income", "maLoai=?", new String[]{String.valueOf(maLoai)});
    }

    public List<KhoanThu> get(String sql, String...selectionArgs) {

        List<KhoanThu> listKhoanThu = new ArrayList<KhoanThu>();

        Cursor c = DB.rawQuery(sql, selectionArgs);

        while (c.moveToNext()){
            KhoanThu item = new KhoanThu();
            item.setMaLoai(c.getInt(c.getColumnIndex("maLoai")));
            item.setTenLoai(c.getString(c.getColumnIndex("tenLoai"))) ;
            item.setTrangThai(c.getString(c.getColumnIndex("trangThai"))) ;
            listKhoanThu.add(item);
        }
        return listKhoanThu;
    }
    public List<KhoanThu> getAll() {
        String sqlGetAll = "SELECT * FROM Income";
        return get(sqlGetAll);
    }

}
