package nhatphong.ps11601.ps11601_phongdtn_asm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import nhatphong.ps11601.ps11601_phongdtn_asm.Model.User;
import nhatphong.ps11601.ps11601_phongdtn_asm.UserDAO.UserDAO;

public class MainLogin extends AppCompatActivity {
    Button btnLogin,btnRe,btnSignIn;
    EditText edtUser,edtPassword;
    UserDAO userDAO;
    CheckBox checkBox;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLogin);
        btnRe = findViewById(R.id.btnRe);
        btnSignIn = findViewById(R.id.btnSignIn);
        edtUser = findViewById(R.id.edtUser);
        edtPassword = findViewById(R.id.edtPassword);
        checkBox = findViewById(R.id.checkbox);
        userDAO = new UserDAO(this);
        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        editor = pref.edit();
        edtUser.setText(pref.getString("user",""));
        edtPassword.setText(pref.getString("pass",""));

        edtPassword.setText(pref.getString("user",""));
        edtUser.setText(pref.getString("pass",""));

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Validate()) {
                        String Username = edtUser.getText().toString();
                        String Password = edtPassword.getText().toString();
                        User currentUser = userDAO.Login(new User(null, null, Username, Password));
                        if (currentUser != null) {
                            Toast.makeText(MainLogin.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainLogin.this, MainActivity.class);
                            String id_user = currentUser.id;
                            intent.putExtra("id_user", id_user);
                            startActivity(intent);
                            finish();
                            if(checkBox.isChecked()) {
                                editor.putString("user",edtUser.getText().toString());
                                editor.putString("pass",edtPassword.getText().toString());
                                editor.apply();
                            }else {
                                editor.clear();
                                editor.apply();
                            }
                        } else {
                            Toast.makeText(MainLogin.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
                        }
                }
            }
        });
        btnRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainLogin.this, Main_Register.class);
                startActivity(i);
            }
        });
        Intent i = getIntent();
    }

    public boolean Validate() {
        boolean Validate = false;
        String Username = edtUser.getText().toString();
        String Password = edtPassword.getText().toString();
        if (Username.isEmpty()) {
            Validate = false;
            edtUser.setError("Tài khoản hoặc mật khẩu không đúng !");
        } else {
            if (Username.length() > 5) {
                Validate = true;
                edtUser.setError(null);
            } else {
                Validate = false;
                edtUser.setError("Tài khoản hoặc mật khẩu không đúng !");
            }
        }

        if (Password.isEmpty()) {
            Validate = false;
            edtPassword.setError("Tài khoản hoặc mật khẩu không đúng !");
        } else {
            if (Password.length() > 5) {
                Validate = true;
                edtPassword.setError(null);
            } else {
                Validate = false;
                edtPassword.setError("Tài khoản hoặc mật khẩu không đúng !");
            }
        }
        return Validate;
    }
}
