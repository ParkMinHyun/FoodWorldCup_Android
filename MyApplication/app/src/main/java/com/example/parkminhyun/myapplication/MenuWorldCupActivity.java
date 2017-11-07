package com.example.parkminhyun.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class MenuWorldCupActivity extends AppCompatActivity {

    View view;
    FrameLayout roots, menuLayout;
    ImageView topImageCheck, downImageCheck;
    ImageView topImage, downImage;

    private List<String> foodTournerment_menuList = new ArrayList<String>();
    private String mDrawableName1, mDrawableName2;
    private int resID1, resID2;
    private int foodIndex = 0;
    private boolean quarterfinal_flag = false, semifinal_flag = false, final_flag = false;

    private Animation translateUpAnim;
    private LinearLayout resultPage;
    private KonfettiView konfettiView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_world_cup);

        view = findViewById(R.id.includeFrame);

        propertyInit();
        menuListInit();
        foodImageViewSetting();

        konfettiView = (KonfettiView)findViewById(R.id.konfettiView);

    }

    // id 추가 및 기타 속성 초기화
    public void propertyInit() {
        roots = (FrameLayout) findViewById(R.id.root);
        menuLayout = (FrameLayout) findViewById(R.id.menuLayout);
        topImage = (ImageView) findViewById(R.id.topImage);
        downImage = (ImageView) findViewById(R.id.downImage);
        resultPage = (LinearLayout) findViewById(R.id.result_page);// 슬라이딩으로보여질레이아웃객체참조

        topImageCheck = (ImageView) findViewById(R.id.topImageCheck);
        downImageCheck = (ImageView) findViewById(R.id.downImageCheck);

        // 애니메이션객체로딩
        translateUpAnim = AnimationUtils.loadAnimation(this, R.anim.translate_up);

        // 애니메이션객체에리스너설정
        SlidingPageAnimationListener animListener = new SlidingPageAnimationListener();
        translateUpAnim.setAnimationListener(animListener);
    }

    // 애니메이션 리스너 정의
    private class SlidingPageAnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }

        // 애니메이션이 끝날때 호출되는 메소드
        public void onAnimationEnd(Animation animation) {
        }
    }

    public void foodImageViewSetting() {

        // 16강, 8강, 4강 진행
        if (foodIndex == 8 && quarterfinal_flag == false) {
            foodIndex = 0;
            quarterfinal_flag = true;
        } else if (foodIndex == 4 && quarterfinal_flag == true) {
            foodIndex = 0;
            semifinal_flag = true;
        } else if (foodIndex == 2 && semifinal_flag == true) {
            foodIndex = 0;
            final_flag = true;
        }

        // 문자열로 drawable에 있는 음식 이미지 가져오기
        mDrawableName1 = foodTournerment_menuList.get(foodIndex++).toString();
        resID1 = getResources().getIdentifier(mDrawableName1, "drawable", getPackageName());
        topImage.setImageResource(resID1);

        mDrawableName2 = foodTournerment_menuList.get(foodIndex++).toString();
        resID2 = getResources().getIdentifier(mDrawableName2, "drawable", getPackageName());
        downImage.setImageResource(resID2);

        // 이미지 클릭 활성화 및 투명도 초기화
        topImage.setAlpha(1f);
        downImage.setAlpha(1f);
    }

    public void menuListInit() {
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

    // Food 토너먼트 중 음식 이미지를 클릭했을 경우
    private void FoodImageClick_Result(String mode) {

        // Click Event 비활성화
        topImage.setClickable(false);
        downImage.setClickable(false);

        // 투명도 설정 + String 배열 삭제
        if (mode.equals("topImageClick")) {
            downImage.setAlpha(0.3f);
            topImageCheck.bringToFront();
            topImageCheck.setVisibility(view.VISIBLE);
            foodTournerment_menuList.remove((foodIndex--) - 1);
        } else {
            topImage.setAlpha(0.3f);
            downImageCheck.bringToFront();
            downImageCheck.setVisibility(view.VISIBLE);
            foodTournerment_menuList.remove((foodIndex--) - 2);
        }
    }

    // 윗 이미지 클릭시
    public void topImageClick(View v) throws InterruptedException {
        FoodImageClick_Result("topImageClick");

        // 1초 뒤 Handler 실행
        Handler myHandler = new Handler();
        myHandler.postDelayed(mMyRunnable1, 1000);
    }

    // 아래 이미지 클릭시
    public void downImageClick(View v) {
        FoodImageClick_Result("downImageClick");

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

            // check 이미지 Invisible
            topImageCheck.setVisibility(view.INVISIBLE);
            downImageCheck.setVisibility(view.INVISIBLE);

            // 결승전 진행할 때 결과창 Activity 띄우기
            if (final_flag) {
                resultPage.setVisibility(view.VISIBLE);
                resultPage.startAnimation(translateUpAnim);

                // 0.1초 뒤 Handler 실행
                Handler Handler = new Handler();
                Handler.postDelayed(mMyRunnable4, 1000);
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

            // topImage, downImage 다시 클릭 이벤트 활성화
            downImage.setClickable(true);
            topImage.setClickable(true);
        }
    };


    // view 삭제 실행
    private Runnable mMyRunnable4 = new Runnable() {
        @Override
        public void run() {

            konfettiView.build()
                    .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                    .setDirection(0.0, 359.0)
                    .setSpeed(1f, 5f)
                    .setFadeOutEnabled(true)
                    .setTimeToLive(2000L)
                    .addShapes(Shape.RECT, Shape.CIRCLE)
                    .addSizes(new Size(16, 6f))
                    .setPosition(konfettiView.getX() + konfettiView.getWidth()/2, konfettiView.getY()+konfettiView.getHeight()/3)
                    .burst(500);
        }
    };
}
