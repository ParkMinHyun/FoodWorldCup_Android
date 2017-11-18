package com.example.parkminhyun.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class ResultFoodMenuActivity extends AppCompatActivity {

    private KonfettiView konfettiView;

    FoodInfomation foodInfomation;
    private TextView foodNameTextView;
    private ImageView resultFoodImageView;
    private int resID;
    String resultFoodName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_world_cup_result);

        foodInfomation = FoodInfomation.getInstance();
        konfettiView = (KonfettiView) findViewById(R.id.konfettiView);
        foodNameTextView = (TextView) findViewById(R.id.foodNameTextView);
        resultFoodImageView = (ImageView) findViewById(R.id.resultFoodImageView);

        // 문자열로 drawable에 있는 음식 이미지 가져오기
        resultFoodName = getIntent().getExtras().getString("resultFood");
        resID = getResources().getIdentifier(resultFoodName, "drawable", getPackageName());
        resultFoodImageView.setImageResource(resID);

        foodNameTextView.setText(foodInfomation.map.get(resultFoodName));
        resultFoodImageView.setAdjustViewBounds(true);
        resultFoodImageView.setScaleType(ImageView.ScaleType.FIT_XY);

        // 0.1초 뒤 Handler 실행
        Handler Handler = new Handler();
        Handler.postDelayed(mMyRunnable4, 1000);
    }


    // Material Design 폭죽 이벤트 실행
    private Runnable mMyRunnable4 = new Runnable() {
        @Override
        public void run() {

            // 폭죽 생성
            konfettiView.bringToFront();
            konfettiView.build()
                    .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                    .setDirection(0.0, 359.0)
                    .setSpeed(1f, 5f)
                    .setFadeOutEnabled(true)
                    .setTimeToLive(1000L)
                    .addShapes(Shape.RECT, Shape.CIRCLE)
                    .addSizes(new Size(12, 5f))
                    .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                    .stream(300, 2000L);
        }
    };

    public void findFoodStoreImageClicked(View v){
        Intent intent = new Intent(getApplicationContext(),ResultFoodMapActivity.class);
        intent.putExtra("resultFood",resultFoodName);
        startActivity(intent);

    }
}