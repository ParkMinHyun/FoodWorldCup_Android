package com.example.parkminhyun.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MenuWorldCupActivity extends AppCompatActivity {

    View view;
    FrameLayout roots, menuLayout, foodMenuLayout, menuFrameLayout;
    ImageView topImage, downImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_world_cup);

        view = findViewById(R.id.includeFrame);
        findViewInit();
    }

    public void topImageClick(View v) throws InterruptedException {
        downImage.setAlpha(0.3f);

        // 1초 뒤 Handler 실행
        Handler myHandler = new Handler();
        myHandler.postDelayed(mMyRunnable, 1000);
    }

    public void downImageClick(View v) {
        topImage.setAlpha(0.3f);
    }

    private Runnable mMyRunnable = new Runnable() {
        @Override
        public void run() {
            menuLayout.startAnimation(new Animate());

            // 1초 뒤 Handler 실행
            Handler Handler = new Handler();
            Handler.postDelayed(mMyRunnable2, 100);
        }
    };

    private Runnable mMyRunnable2 = new Runnable() {
        @Override
        public void run() {
            roots.removeView(view);

            // 1초 뒤 Handler 실행
            Handler Handler = new Handler();
            Handler.postDelayed(mMyRunnable3, 100);
        }
    };

    private Runnable mMyRunnable3 = new Runnable() {
        @Override
        public void run() {
            roots.addView(view);
            topImage.setAlpha(0.99f);
            downImage.setAlpha(0.99f);
        }
    };

    public void findViewInit() {
        roots = (FrameLayout) findViewById(R.id.root);
        menuFrameLayout = (FrameLayout) findViewById(R.id.frameLayout_menu);
        menuLayout = (FrameLayout) findViewById(R.id.menuLayout);
        foodMenuLayout = (FrameLayout) findViewById(R.id.foodMenuLayout);
        topImage = (ImageView) findViewById(R.id.topImage);
        downImage = (ImageView) findViewById(R.id.downImage);
    }

}
