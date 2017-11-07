package com.example.parkminhyun.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MenuWorldCupActivity extends AppCompatActivity {

    View view;
    FrameLayout roots, menuLayout;
    ImageView topImage, downImage;


    private StringBuilder[] foodNameStringBuilder = {
            new StringBuilder("bibimbab"),
            new StringBuilder("cake"),
            new StringBuilder("chicken"),
            new StringBuilder("dduckppoki"),
            new StringBuilder("donggas"),
            new StringBuilder("gimbab"),
            new StringBuilder("hamburger"),
            new StringBuilder("ramen"),
            new StringBuilder("spagetti"),
            new StringBuilder("steak"),
            new StringBuilder("sushi"),
            new StringBuilder("zazang")};

    private String mDrawableName1, mDrawableName2;
    private int resID1, resID2;
    private int foodIndex = 0;
    int a = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_world_cup);

        view = findViewById(R.id.includeFrame);
        findViewInit();
        foodImageViewSetting();
    }

    public void foodImageViewSetting() {
        // 문자열로 이미지 가져오기
        mDrawableName1 = foodNameStringBuilder[foodIndex++].toString();
        resID1 = getResources().getIdentifier(mDrawableName1 , "drawable", getPackageName());
        topImage.setImageResource(resID1);

        mDrawableName2 = foodNameStringBuilder[foodIndex++].toString();
        resID2 = getResources().getIdentifier(mDrawableName2 , "drawable", getPackageName());
        downImage.setImageResource(resID2);

        topImage.setAlpha(1f);
        downImage.setAlpha(1f);
    }

    public void topImageClick(View v) throws InterruptedException {
        downImage.setAlpha(0.3f);

        // 1초 뒤 Handler 실행
        Handler myHandler = new Handler();
        myHandler.postDelayed(mMyRunnable1, 1000);
    }

    public void downImageClick(View v) {
        topImage.setAlpha(0.3f);
    }

    private Runnable mMyRunnable1 = new Runnable() {
        @Override
        public void run() {
            menuLayout.startAnimation(new Animate());

            // 0.1초 뒤 Handler 실행
            Handler Handler = new Handler();
            Handler.postDelayed(mMyRunnable2, 100);
        }
    };

    private Runnable mMyRunnable2 = new Runnable() {
        @Override
        public void run() {
            roots.removeView(view);

            // 0.1초 뒤 Handler 실행
            Handler Handler = new Handler();
            Handler.postDelayed(mMyRunnable3, 100);
        }
    };

    private Runnable mMyRunnable3 = new Runnable() {
        @Override
        public void run() {

            // view 다시 추가 한 뒤 ImageView 채우기
            roots.addView(view);
            foodImageViewSetting();

            // 서서히 나타나는 애니메이션 적용
            Animation fade_in_animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
            view.startAnimation(fade_in_animation);

            Toast.makeText(getApplicationContext(), String.valueOf(a++),Toast.LENGTH_SHORT).show();
        }
    };

    public void findViewInit() {
        roots = (FrameLayout) findViewById(R.id.root);
        menuLayout = (FrameLayout) findViewById(R.id.menuLayout);
        topImage = (ImageView) findViewById(R.id.topImage);
        downImage = (ImageView) findViewById(R.id.downImage);
    }

}
