package com.example.parkminhyun.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class DrawingLotActivity extends AppCompatActivity {

    ImageView foodImage1,foodImage2,foodImage3,foodImage4,foodImage5,foodImage6 ;
    EditText inputText;

    String foodThumbnail;
    int foodNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing_lot);

        initProperty();


    }
    private void initProperty(){
        foodImage1 = (ImageView)findViewById(R.id.image1);
        foodImage2 = (ImageView)findViewById(R.id.image2);
        foodImage3 = (ImageView)findViewById(R.id.image3);
        foodImage4 = (ImageView)findViewById(R.id.image4);
        foodImage5 = (ImageView)findViewById(R.id.image5);
        foodImage6 = (ImageView)findViewById(R.id.image6);

        inputText = (EditText)findViewById(R.id.editFoodText);

    }

    public void plusBtnClicked(View view) {
        switch (foodNum){
            case 0: foodImage1.setVisibility(View.VISIBLE); break;
            case 1: foodImage2.setVisibility(View.VISIBLE); break;
            case 2: foodImage3.setVisibility(View.VISIBLE); break;
            case 3: foodImage4.setVisibility(View.VISIBLE); break;
            case 4: foodImage5.setVisibility(View.VISIBLE); break;
            case 5: foodImage6.setVisibility(View.VISIBLE); break;
            default: break;
        }

        // 네이버 검색 API 어싱크로 동작시키기
        DrawingLotActivity.JsoupAsyncTask jsoupAsyncTask = new DrawingLotActivity.JsoupAsyncTask();
        jsoupAsyncTask.execute();

    }

}
