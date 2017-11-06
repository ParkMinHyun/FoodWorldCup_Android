package com.example.parkminhyun.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MenuWorldCupActivity extends AppCompatActivity {

    ImageView topImage;
    ImageView downImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_world_cup);

        topImage = (ImageView)findViewById(R.id.topImage);
//        topImage.setAlpha(0.3f);
    }
}
