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


    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        // 네이버 지도 검색 API 이용하는 함수
        @Override
        protected Void doInBackground(Void... params) {
            Log.i("JSON", "시작");
            String clientId = "kEZOwXlvRFPihiPU8fVJ";//애플리케이션 클라이언트 아이디값";
            String clientSecret = "wbyTPTRhuT";//애플리케이션 클라이언트 시크릿값";
            try {
                String text = URLEncoder.encode(inputText.getText().toString(), "UTF-8");
                String apiURL = "https://openapi.naver.com/v1/search/image.json?query=" + text + "&display=2&start=1&sort=sim"; // json 결과
                //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과
                URL url = new URL(apiURL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("X-Naver-Client-Id", clientId);
                con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
                int responseCode = con.getResponseCode();
                BufferedReader br;
                if (responseCode == 200) { // 정상 호출
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                } else {  // 에러 발생
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                ReceiveFoodInfoUsingJSON(response.toString());

                System.out.println(response);

            } catch (Exception e) {
                System.out.println(e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

        }
    }

    // JSON Data 받기
    private void ReceiveFoodInfoUsingJSON(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response.toString());   // JSONObject 생성
            String a = jsonObject.getString("items");

            JSONArray jarray = new JSONArray(a);   // JSONArray 생성
            for (int i = 0; i < 1; i++) {
                JSONObject jObject = jarray.getJSONObject(i);  // JSONObject 추출
                foodThumbnail = jObject.getString("thumbnail");
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
