package nhatphong.ps11601.ps11601_phongdtn_asm.Model;

import android.content.SharedPreferences;

public class User {
    public String id;
    public String username;
    public String password;
    public String email;

    public User() {
    }

    public User(String id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
