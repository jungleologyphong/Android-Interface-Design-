package nhatphong.ps11601.ps11601_phongdtn_asm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import nhatphong.ps11601.ps11601_phongdtn_asm.Model.User;
import nhatphong.ps11601.ps11601_phongdtn_asm.UserDAO.UserDAO;

public class Main_Register extends AppCompatActivity {
    Button btnLogin,btnRe,DangKi;
    EditText txtUsernameRe,txtPassWordRe,txtPassWordComfirm,txtEmail;
    UserDAO userDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userDAO = new UserDAO(this);
        initView();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main_Register.this,MainLogin.class);
                startActivity(i);
            }
        });
        DangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Validate()){
                String Username = txtUsernameRe.getText().toString();
                String Email = txtEmail.getText().toString();
                String Password = txtPassWordRe.getText().toString();
                if(!userDAO.isEmailExists(Email)){
                    userDAO.Register(new User(null,Username,Email,Password));
                    Toast.makeText(Main_Register.this,"Đăng kí thành công !",Toast.LENGTH_LONG).show();
                    txtUsernameRe.setText("");
                    txtPassWordRe.setText("");
                    txtPassWordComfirm.setText("");
                    txtEmail.setText("");
                }else{
                    Toast.makeText(Main_Register.this, "Đăng kí thất bại, kiểm tra lại tài khoản và mật khẩu", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    public void initView(){
        txtEmail = findViewById(R.id.txtEmail);
        txtUsernameRe = findViewById(R.id.txtUsernameRe);
        txtPassWordRe = findViewById(R.id.txtPassWordRe);
        txtPassWordComfirm = findViewById(R.id.txtPassWordComfirm);
        btnLogin = findViewById(R.id.btnLogin);
        btnRe = findViewById(R.id.btnRe);
        DangKi = findViewById(R.id.DangKi);
    }
    public boolean Validate() {
        boolean valid = false;
        String UserName = txtUsernameRe.getText().toString();
        String Email = txtEmail.getText().toString();
        String Password = txtPassWordRe.getText().toString();
        String ConfirmPass = txtPassWordComfirm.getText().toString();
    if (UserName.isEmpty()) {
        valid = false;
        txtUsernameRe.setError("Vui lòng nhập tên tài khoản !");
    } else {
        if (UserName.length() > 5) {
            valid = true;
            txtUsernameRe.setError(null);
        } else {
            valid = false;
            txtUsernameRe.setError("Tên tài khoản quá ngắn ! Tên tài khoản phải lớn hơn 5 kí tự ");
        }
    }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
        valid = false;
        txtEmail.setError("Vui lòng nhập Email !");
    } else {
        valid = true;
        txtEmail.setError(null);
    }

        if (Password.isEmpty()) {
        valid = false;
        txtPassWordRe.setError("Vui lòng nhập mật khẩu !");
    } else {
        if (Password.length() > 5) {
            valid = true;
            txtPassWordRe.setError(null);
        } else {
            valid = false;
            txtPassWordRe.setError("Mật khẩu quá ngắn ! Mật khẩu phải lớn hơn 5 kí tự ! ");
        }
    }

        if (ConfirmPass.isEmpty()) {
        valid = false;
        txtPassWordComfirm.setError("Vui lòng nhập mật khẩu !");
    } else {
        if (Password.length() > 5) {
            valid = true;
            txtPassWordComfirm.setError(null);
        } else {
            valid = false;
            txtPassWordComfirm.setError("Xác nhận mật khẩu quá ngắn ! Mật khẩu phải lớn hơn 5 kí tự");
        }
    }
        if(!ConfirmPass.equals(Password)){
        valid = false;
        txtPassWordComfirm.setError("Mật khẩu không trùng khớp !");
    } else {
        valid = true;
        txtPassWordComfirm.setError(null);
    }
        return valid;
    }
}
