package nhatphong.ps11601.ps11601_phongdtn_asm.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
        static  final String DBName = "PS11601_PhongDTN_ASM";
        static final int Version = 15;

    public SQLiteHelper(Context context) {
        super(context, DBName, null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Create User Command.
        String sqlUser = "CREATE TABLE User("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "email TEXT NOT NULL,"+
                "username TEXT NOT NULL,"+
                "password TEXT NOT NULL);";
        db.execSQL(sqlUser);
        //Create Khoan Thu Command.
        String sqlIncome = "CREATE TABLE Income(" +
                "maLoai INTEGER  PRIMARY KEY AUTOINCREMENT," +
                "tenLoai TEXT NOT NULL," +
                "id INTEGER  references User(id)," +
                "trangThai TEXT NOT NULL);";
        //Run Command SQL
        db.execSQL(sqlIncome);
        //Create Field
        String sql = "INSERT INTO Income(tenLoai,trangThai,id) VALUES ('Lãi ngân hàng','Thu',1)";
        db.execSQL(sql);
        sql = "INSERT INTO Income(tenLoai,trangThai,id) VALUES ('Lương','Thu',1)";
        db.execSQL(sql);
        sql = "INSERT INTO Income(tenLoai,trangThai,id) VALUES ('Bán hàng','Thu',1)";
        db.execSQL(sql);
        sql = "INSERT INTO Income(tenLoai,trangThai,id) VALUES ('Thu nợ','Thu',2)";
        db.execSQL(sql);
        sql = "INSERT INTO Income(tenLoai,trangThai,id) VALUES ('Bán sách','Thu',2)";
        db.execSQL(sql);

        //Create Thu Chi Command.
        String sqlTypeInCome = "CREATE TABLE TypeIncome(" +
                "maTC INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TenKhoanTC TEXT NOT NULL," +
                "Ngay Date NOT NULL," +
                "Tien Double NOT NULL," +
                "GhiChu TEXT NOT NULL," +
                "id INTEGER  references User(id)," +
                "maLoai INTEGER references TypeIncome(maLoai));";
        //Run Command SQL
        db.execSQL(sqlTypeInCome);
        //Create Field
        String TypeIncome = "INSERT INTO TypeIncome(TenKhoanTC,Ngay,Tien,GhiChu,maLoai) VALUES ('Lương tháng 3','2018-03-03',1000,'Lương',1)";
        db.execSQL(TypeIncome);
        TypeIncome = "INSERT INTO TypeIncome(TenKhoanTC,Ngay,Tien,GhiChu,maLoai) VALUES ('Lương tháng 4','2017-04-03',1000,'Lương',1)";
        db.execSQL(TypeIncome);
        TypeIncome = "INSERT INTO TypeIncome(TenKhoanTC,Ngay,Tien,GhiChu,maLoai) VALUES ('Lương tháng 5','2016-05-03',1000,'Lương',1)";
        db.execSQL(TypeIncome);
        TypeIncome = "INSERT INTO TypeIncome(TenKhoanTC,Ngay,Tien,GhiChu,maLoai) VALUES ('Lương tháng 6','2015-06-03',1000,'Lương',1)";
        db.execSQL(TypeIncome);
        TypeIncome = "INSERT INTO TypeIncome(TenKhoanTC,Ngay,Tien,GhiChu,maLoai) VALUES ('Lương tháng 7','2011-07-03',1000,'Lương',1)";
        db.execSQL(TypeIncome);
        TypeIncome = "INSERT INTO TypeIncome(TenKhoanTC,Ngay,Tien,GhiChu,maLoai) VALUES ('Lương tháng 8','2013-08-03',1000 ,'Lương',1)";
        db.execSQL(TypeIncome);
        TypeIncome = "INSERT INTO TypeIncome(TenKhoanTC,Ngay,Tien,GhiChu,maLoai) VALUES ('Lương tháng 9','2012-09-03',1000,'Lương',1)";
        db.execSQL(TypeIncome);


        String sqlSpend = "CREATE TABLE Spend(" +
                "maLoai INTEGER  PRIMARY KEY AUTOINCREMENT," +
                "tenLoai TEXT NOT NULL," +
                "id INTEGER  references User(id)," +
                "trangThai TEXT NOT NULL);";
        //Run Command SQL
        db.execSQL(sqlSpend);
        sql = "INSERT INTO Spend(tenLoai,trangThai) VALUES ('Mua sách','Chi')";
        db.execSQL(sql);
        sql = "INSERT INTO Spend(tenLoai,trangThai) VALUES ('Mua vở','Chi')";
        db.execSQL(sql);
        sql = "INSERT INTO Spend(tenLoai,trangThai) VALUES ('Mua đất','Chi')";
        db.execSQL(sql);
        String sqlTypeSpend = "CREATE TABLE TypeSpend(" +
                "maTC INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TenKhoanTC TEXT NOT NULL," +
                "Ngay Date NOT NULL," +
                "Tien Double NOT NULL," +
                "GhiChu TEXT NOT NULL," +
                "id INTEGER  references User(id)," +
                "maLoai INTEGER references TypeIncome(maLoai));";
        db.execSQL(sqlTypeSpend);
        //Create Field
        String TypeSpend = "INSERT INTO TypeSpend(TenKhoanTC,Ngay,Tien,GhiChu,maLoai) VALUES ('Mua sách','2019-05-03',100,'Mua sách giáo khoa',1)";
        db.execSQL(TypeSpend);
        TypeSpend = "INSERT INTO TypeSpend(TenKhoanTC,Ngay,Tien,GhiChu,maLoai) VALUES ('Mua dụng cụ học tập','2019-06-03',100,'Mua tập lớp 10',1)";
        db.execSQL(TypeSpend);
        TypeSpend = "INSERT INTO TypeSpend(TenKhoanTC,Ngay,Tien,GhiChu,maLoai) VALUES ('Mua nhà','2019-07-03',100,'Mua nhà lầu',1)";
        db.execSQL(TypeSpend);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlDropIncome = "DROP TABLE Income ;";
        db.execSQL(sqlDropIncome);
        String sqlDropTypeIncome = "DROP TABLE TypeIncome ;";
        db.execSQL(sqlDropTypeIncome);
        String sqlDropSpend = "DROP TABLE Spend ;";
        db.execSQL(sqlDropSpend);
        String sqlDropTypeSpend = "DROP TABLE TypeSpend ;";
        db.execSQL(sqlDropTypeSpend);
        String sqlDropUser = "DROP TABLE User;";
        db.execSQL(sqlDropUser);
        onCreate(db);
    }
}
