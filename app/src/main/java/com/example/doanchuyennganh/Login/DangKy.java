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

import com.example.doanchuyennganh.Fragment.InfoFragment;
import com.example.doanchuyennganh.Model.User;
import com.example.doanchuyennganh.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DangKy extends AppCompatActivity {
    EditText edtSdt, edtPassword, edtName;
    Button btnSignUp;
    TextView txtSignIn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        edtName = findViewById(R.id.edtName);
        edtPassword = findViewById(R.id.edtPassword);
        edtSdt = findViewById(R.id.edtPhone);
        txtSignIn = findViewById(R.id.txtSignIn);
        btnSignUp = findViewById(R.id.btnRegister);

        //init firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (!snapshot.child(edtSdt.getText().toString()).exists()){
                            User user = new User(edtName.getText().toString(),edtPassword.getText().toString(),edtSdt.getText().toString());
                            table_user.child(edtSdt.getText().toString()).setValue(user);
                            Toast.makeText(DangKy.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DangKy.this, DangNhap.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(DangKy.this, "Số điện thoại đã tồn tại!!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangKy.this, DangNhap.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
