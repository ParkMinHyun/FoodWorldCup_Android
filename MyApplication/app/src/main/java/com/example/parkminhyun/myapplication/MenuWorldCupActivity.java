package com.example.parkminhyun.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MenuWorldCupActivity extends AppCompatActivity {

    View view;
    FrameLayout roots, menuLayout;
    ImageView topImage, downImage;

    private List<String> foodTournerment_menuList = new ArrayList<String>();
    private String mDrawableName1, mDrawableName2;
    private int resID1, resID2;
    private int foodIndex = 0;
    private boolean quarterfinal_flag = false, semifinal_flag = false, final_flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_world_cup);

        view = findViewById(R.id.includeFrame);
        findViewInit();
        menuListInit();
        foodImageViewSetting();
    }

    public void findViewInit() {
        roots = (FrameLayout) findViewById(R.id.root);
        menuLayout = (FrameLayout) findViewById(R.id.menuLayout);
        topImage = (ImageView) findViewById(R.id.topImage);
        downImage = (ImageView) findViewById(R.id.downImage);
    }

    public void foodImageViewSetting() {

        // 16강, 8강, 4강 진행
        if(foodIndex == 8 && quarterfinal_flag == false) {
            foodIndex = 0;
            quarterfinal_flag =true;
        }
        else if(foodIndex == 4 && quarterfinal_flag == true){
            foodIndex = 0;
            semifinal_flag = true;
        }
        else if(foodIndex == 2 && semifinal_flag == true){
            foodIndex = 0;
            final_flag = true;
        }

        // 문자열로 drawable에 있는 음식 이미지 가져오기
        mDrawableName1 = foodTournerment_menuList.get(foodIndex++).toString();
        resID1 = getResources().getIdentifier(mDrawableName1 , "drawable", getPackageName());
        topImage.setImageResource(resID1);

        mDrawableName2 = foodTournerment_menuList.get(foodIndex++).toString();
        resID2 = getResources().getIdentifier(mDrawableName2 , "drawable", getPackageName());
        downImage.setImageResource(resID2);

        // 이미지 클릭 활성화 및 투명도 초기화
        topImage.setClickable(true);
        downImage.setClickable(true);
        topImage.setAlpha(1f);
        downImage.setAlpha(1f);
    }

    public void menuListInit(){
        foodTournerment_menuList.add("baekban");
        foodTournerment_menuList.add("bibimbab");
        foodTournerment_menuList.add("bread");
        foodTournerment_menuList.add("cake");
        foodTournerment_menuList.add("chicken");
        foodTournerment_menuList.add("dduckppoki");
        foodTournerment_menuList.add("donggas");
        foodTournerment_menuList.add("doughnut");
        foodTournerment_menuList.add("gimbab");
        foodTournerment_menuList.add("hamburger");
        foodTournerment_menuList.add("ramen");
        foodTournerment_menuList.add("sandwich");
        foodTournerment_menuList.add("spagetti");
        foodTournerment_menuList.add("steak");
        foodTournerment_menuList.add("sushi");
        foodTournerment_menuList.add("zazang");
    }

    // 윗 이미지 클릭시
    public void topImageClick(View v) throws InterruptedException {
        downImage.setAlpha(0.3f);
        foodTournerment_menuList.remove((foodIndex--) -1);

        // 1초 뒤 Handler 실행
        Handler myHandler = new Handler();
        myHandler.postDelayed(mMyRunnable1, 1000);
    }

    // 아래 이미지 클릭시
    public void downImageClick(View v) {

        topImage.setAlpha(0.3f);
        foodTournerment_menuList.remove((foodIndex--)-2);

        // 1초 뒤 Handler 실행
        Handler myHandler = new Handler();
        myHandler.postDelayed(mMyRunnable1, 1000);
    }

    // left Move 애니메이션 실행
    private Runnable mMyRunnable1 = new Runnable() {
        @Override
        public void run() {

            // left Move animation 실행
            menuLayout.startAnimation(new Animate());

            // 결승전 진행할 때 결과창 Activity 띄우기
            if(final_flag)
            {
                Intent intent = new Intent(getApplicationContext(),FoodWorldCupResultActivity.class);
                startActivity(intent);
                MenuWorldCupActivity.this.finish();
            }
            // 결승전 아닐 경우 Handler2 실행
            else {
                // 0.1초 뒤 Handler 실행
                Handler Handler = new Handler();
                Handler.postDelayed(mMyRunnable2, 100);
            }
        }
    };

    // view 삭제 실행
    private Runnable mMyRunnable2 = new Runnable() {
        @Override
        public void run() {
            roots.removeView(view);

            // 0.1초 뒤 Handler 실행
            Handler Handler = new Handler();
            Handler.postDelayed(mMyRunnable3, 100);
        }
    };

    // view 추가 및 fade in 애니메이션 실행
    private Runnable mMyRunnable3 = new Runnable() {
        @Override
        public void run() {

            // view 다시 추가 한 뒤 ImageView 채우기
            roots.addView(view);
            foodImageViewSetting();

            // 서서히 나타나는 애니메이션 적용
            Animation fade_in_animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
            view.startAnimation(fade_in_animation);


        }
    };
}
