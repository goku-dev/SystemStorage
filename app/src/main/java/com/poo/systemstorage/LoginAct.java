package com.poo.systemstorage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginAct extends AppCompatActivity implements View.OnClickListener {
    private static final String KEY_NAME ="pref_data.xml" ;
    private EditText edtName, edtPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        initView();
    }

    private void initView() {
        edtName = findViewById(R.id.edt_mail);
        edtPass = findViewById(R.id.edt_pass);
        findViewById(R.id.bt_login).setOnClickListener(this);
        findViewById(R.id.bt_Register).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_Register) {
        saveUserInfo();
        }else if(v.getId()==R.id.bt_login){
            loginUserInfo();
        }
    }

    private void loginUserInfo() {
        SharedPreferences preferences=getSharedPreferences(KEY_NAME, Context.MODE_PRIVATE);
       String pass=preferences.getString(edtName.getText().toString(),null);
       if(pass ==null){
           Toast.makeText(this,"tài khoản không tồn tại",Toast.LENGTH_SHORT).show();
           return;
       } if(!pass.equals(edtPass.getText().toString())){
            Toast.makeText(this,"sai mật khẩu",Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this,"đăng nhập thành công",Toast.LENGTH_SHORT).show();
    }

    private void saveUserInfo() {
        SharedPreferences preferences=getSharedPreferences(KEY_NAME, Context.MODE_PRIVATE);

       if( preferences.contains(edtName.getText().toString())){
           Toast.makeText(this,"tài khoản đã tồn tại",Toast.LENGTH_SHORT).show();
           return;
       }else {


           SharedPreferences.Editor editor = preferences.edit();
           editor.putString(edtName.getText().toString(), edtPass.getText().toString());

           editor.apply();
           Toast.makeText(this, "Register success!!", Toast.LENGTH_SHORT).show();
       }
    }
}
