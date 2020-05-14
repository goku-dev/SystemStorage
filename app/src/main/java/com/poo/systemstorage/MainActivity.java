package com.poo.systemstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        editText= findViewById(R.id.edt_text);
        findViewById(R.id.bt_save).setOnClickListener(this);
        findViewById(R.id.bt_Read).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.bt_save){
            saveDataToSystemStorage();
        }else if(v.getId()==R.id.bt_Read){
            readDataToSystemStorage();
        }
    }

    private void readDataToSystemStorage() {
      String path = Environment.getDataDirectory()+"/data/"+getPackageName();
        String fileName = path+"/content.txt";
        File file = new File(fileName);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte []buff = new byte[1024];
            int length = fileInputStream.read(buff);
                String text="";
            while(length>-1){
                text += new String(buff,0,length);
                length=fileInputStream.read(buff);

            }
            fileInputStream.close();
            editText.setText(text);

        }catch (Exception e){

        }
    }

    private void saveDataToSystemStorage()  {
      String path =  Environment.getDataDirectory()+"/data/"+ getPackageName();
      String fileName = path+"/content.txt";
        File file = new File(fileName);
        file.getParentFile().mkdirs();
      try {
          if(!editText.getText().toString().isEmpty()) {
              FileOutputStream fileOutputStream = new FileOutputStream(fileName, true);

              byte[] buff = editText.getText().toString().getBytes();
              fileOutputStream.write(buff);
              Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
              fileOutputStream.close();

          }else{
              Toast.makeText(this,"text null",Toast.LENGTH_SHORT).show();
          }

      }catch (Exception e){

      }


    }
}
