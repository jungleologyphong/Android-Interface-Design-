package nhatphong.ps11601.ps11601_phongdtn_asm.UserDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import nhatphong.ps11601.ps11601_phongdtn_asm.Database.SQLiteHelper;
import nhatphong.ps11601.ps11601_phongdtn_asm.Model.User;

public class UserDAO {
    private SQLiteDatabase db;

    public UserDAO(Context context){
        SQLiteHelper sqLiteHelper = new SQLiteHelper(context);
        db = sqLiteHelper.getWritableDatabase();
    }

    public void Register(User user) {
        ContentValues values = new ContentValues();
        values.put("username", user.username);
        values.put("email", user.email);
        values.put("password", user.password);
        long todo_id = db.insert("User", null, values);
    }

    public User Login(User user) {
        Cursor cursor = db.query("User",
                new String[]{"id", "username", "email", "password"},
                "email=?",
                new String[]{user.email},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }
        return null;
    }

    public boolean isEmailExists(String email) {
        //nhatphong@gmail.com.
        Cursor cursor = db.query("User",
                new String[]{"id", "username", "email", "password"},
                "email" + "=?",
                new String[]{email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            return true;
        }
        return false;
    }
}
