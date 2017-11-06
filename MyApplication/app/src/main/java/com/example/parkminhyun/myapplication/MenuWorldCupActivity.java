package com.example.parkminhyun.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MenuWorldCupActivity extends AppCompatActivity {

    View roots;
    public static FrameLayout menuLayout;
    ImageView topImage, downImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_world_cup);

        roots = findViewById(R.id.root);
        menuLayout = (FrameLayout)findViewById(R.id.menuLayout);
        topImage = (ImageView)findViewById(R.id.topImage);
        downImage = (ImageView)findViewById(R.id.downImage);


//        topImage.setAlpha(0.3f);
    }

    public void topImageClick(View v){
        downImage.setAlpha(0.3f);
//        for(int i = (int) menuLayout.getX(); i>-1000; i--){
//            menuLayout.setX(i);
//        }

        menuLayout.startAnimation(new Animate());
    }
    public void downImageClick(View v){
        topImage.setAlpha(0.3f);
    }
}
