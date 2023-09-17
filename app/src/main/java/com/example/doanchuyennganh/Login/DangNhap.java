package com.example.doanchuyennganh.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doanchuyennganh.HomeActivity;
import com.example.doanchuyennganh.Model.CurrentUser;
import com.example.doanchuyennganh.Model.User;
import com.example.doanchuyennganh.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DangNhap extends AppCompatActivity {
    EditText edtSdt, edtPassword;
    Button btnSignIn;
    TextView txtSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        edtSdt = findViewById(R.id.edtPhone);
        edtPassword = findViewById(R.id.edtPassword);
        txtSignUp = findViewById(R.id.txtSignup);
        btnSignIn = findViewById(R.id.btnLogin);

        //init firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.child(edtSdt.getText().toString()).exists()){
                            User user = snapshot.child(edtSdt.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(edtPassword.getText().toString()) && user.getPhone().equals(edtSdt.getText().toString())){
                                Toast.makeText(DangNhap.this,"Đăng nhập thành công!",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(DangNhap.this, HomeActivity.class);
                                CurrentUser.currentUser=user;
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(DangNhap.this,"Đăng nhập thất bại!!!",Toast.LENGTH_SHORT).show();

                            }
                            if (edtSdt.getText().toString().isEmpty()){
                                Toast.makeText(DangNhap.this,"Số điện thoại không được để trống",Toast.LENGTH_SHORT).show();
                            }else if (edtPassword.getText().toString().isEmpty()){
                                Toast.makeText(DangNhap.this,"Mật khẩu không được để trống",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(DangNhap.this,"Tài khoản không tồn tại",Toast.LENGTH_SHORT).show();

                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhap.this, DangKy.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
