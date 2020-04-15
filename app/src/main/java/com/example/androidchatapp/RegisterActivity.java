package com.example.androidchatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    MaterialEditText userName, email, password;
    Button btnRegister;
    FirebaseAuth firebaseAuth;
    DatabaseReference dbReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //setActionBar(toolbar);
        getSupportActionBar().setTitle("Đăng ký");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        userName = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnRegister = findViewById(R.id.btnRegister);

        firebaseAuth = FirebaseAuth.getInstance();
        //Toast.makeText(RegisterActivity.this, firebaseAuth.toString(), Toast.LENGTH_SHORT).show();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtUserName = userName.getText().toString();
                String txtEmail = email.getText().toString();
                String txtPassword = password.getText().toString();
                boolean isCheckOk = true;

                if(TextUtils.isEmpty(txtUserName)) {
                    Toast.makeText(RegisterActivity.this, "Tên đăng nhập bắt buộc nhập", Toast.LENGTH_SHORT).show();
                    isCheckOk = false;
                }else if(TextUtils.isEmpty(txtEmail)) {
                    Toast.makeText(RegisterActivity.this, "Email bắt buộc nhập", Toast.LENGTH_SHORT).show();
                    isCheckOk = false;
                }else if(TextUtils.isEmpty(txtPassword)) {
                    Toast.makeText(RegisterActivity.this, "Password bắt buộc nhập", Toast.LENGTH_SHORT).show();
                    isCheckOk = false;
                } else {
                    if(txtPassword.length() < 6) {
                        Toast.makeText(RegisterActivity.this, "Password quá yếu, tối thiểu 6 ký tự", Toast.LENGTH_SHORT).show();
                        isCheckOk = false;
                    }
                }
                if(isCheckOk == true) {
                    register(txtUserName, txtEmail, txtPassword);
                }
            }
        });
    }

    private void register(final String userName, String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    assert firebaseUser != null;
                    String userId = firebaseUser.getUid();

                    dbReference = FirebaseDatabase.getInstance().getReference("Users").child(userId);

                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("id", userId);
                    hashMap.put("username", userName);
                    hashMap.put("imageURL", "default");

                    dbReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                } else {
                    Toast.makeText(RegisterActivity.this, "Đăng ký fail rồi !!! ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        //updateUI(currentUser);
    }
}
