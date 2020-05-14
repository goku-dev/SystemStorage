package com.poo.systemstorage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.io.InputStream;

public class StoryActivity extends AppCompatActivity {
    private static final String[] nameStory = new String[]{"Con Gái", "Công sở", "con nít", "con trai", "Cực Hài", "cừoi 18", "Dân Gian", "gia đình", "Giao Thông"};
    private static final String[] IMG_STORY = new String[]{"congai.png", "congso.png", "connit.png", "contrai.png", "cuchai.png", "cuoi18.png", "dangian.png", "giadinh.png", "giaothong.png"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_story);
    initView();
    }

    private void initView() {
        LinearLayout lnMain = findViewById(R.id.ln_main);
        lnMain.removeAllViews();

        for (int i=0; i<nameStory.length;i++){
            View itemView = LayoutInflater.from(this).inflate(R.layout.item_story,null);
            findData(itemView,i);

            lnMain.addView(itemView);
        }
    }

    private void findData(View itemView, int i) {
        ImageView imageView= itemView.findViewById(R.id.iv_story);
        TextView textView= itemView.findViewById(R.id.tv_story);
        textView.setText(nameStory[i]);
        try {
           InputStream inputStream= getAssets().open("icon/vn/"+IMG_STORY[i]);
            Bitmap img = BitmapFactory.decodeStream(inputStream);
            imageView.setImageBitmap(img);
        }catch (Exception e){

        }


    }
}
