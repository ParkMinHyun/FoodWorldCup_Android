package com.example.parkminhyun.myapplication;

import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ResultFoodMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GPSInfo gpsInfo;

    private GoogleMap gMap;

    private double latitude;
    private double longitude;
    private Address currentDong;

    private String searchText;

    GeoPoint convertedGeoPoint;
    private List<String> foodStoreName;
    private List<String> foodStoreAddr;
    private List<String> foodStoreMapX;
    private List<String> foodStoreMapY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_food_map);

        foodStoreName = new ArrayList<>();
        foodStoreAddr = new ArrayList<>();
        foodStoreMapX = new ArrayList<String>();
        foodStoreMapY = new ArrayList<String>();

        gpsInfo = new GPSInfo(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        // 현재 위치 이동
        LatLng currentPos = new LatLng(gpsInfo.getLatitude(), gpsInfo.getLongitude());
        gMap.addMarker(new MarkerOptions().position(currentPos).title("Marker in Sydney"));
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPos,17));

        latitude = gpsInfo.getLatitude();
        longitude = gpsInfo.getLongitude();

        Geocoder gCoder = new Geocoder(getApplicationContext());
        List<Address> addr = null;
        try {
            addr = gCoder.getFromLocation(latitude, longitude, 2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 현재 위치 ex) 신림동 가져오기
        currentDong = addr.get(1);
        // 검색 문구 생성
        searchText = currentDong.getSubLocality() + ' ' + currentDong.getThoroughfare() + ' ' + "백반";

        // 네이버 검색 API 어싱크로 동작시키기
        ResultFoodMapActivity.JsoupAsyncTask jsoupAsyncTask = new ResultFoodMapActivity.JsoupAsyncTask();
        jsoupAsyncTask.execute();


//        a.getAdminArea()+" "+a.getLocality()+" "+a.getThoroughfare();
//


    }

    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        // 네이버 지도 검색 API 이용하는 함수
        @Override
        protected Void doInBackground(Void... params) {
            Log.i("JSON","시작");
            String clientId = "kEZOwXlvRFPihiPU8fVJ";//애플리케이션 클라이언트 아이디값";
            String clientSecret = "wbyTPTRhuT";//애플리케이션 클라이언트 시크릿값";
            try {
                String text = URLEncoder.encode(searchText, "UTF-8");
                String apiURL = "https://openapi.naver.com/v1/search/local.json?query=" + text; // json 결과
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

                // JSON 파싱 변환하기
                ReceiveFoodInfoUsingJSON(response.toString());

            } catch (Exception e) {
                System.out.println(e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // marker 생성 완료
            gMap.addMarker(new MarkerOptions().position(new LatLng(convertedGeoPoint.y,convertedGeoPoint.x)).title("새롭게 추가된 놈"));
        }
    }

    private void ReceiveFoodInfoUsingJSON(String response){
        StringBuffer sb = new StringBuffer();
        try {
            JSONObject jsonObject = new JSONObject(response.toString());   // JSONObject 생성
            String a = jsonObject.getString("items");

            JSONArray jarray = new JSONArray(a);   // JSONArray 생성
            for(int i=0; i < jarray.length(); i++){
                JSONObject jObject = jarray.getJSONObject(i);  // JSONObject 추출

                foodStoreName.add(jObject.getString("title"));
                foodStoreAddr.add(jObject.getString("address"));
                foodStoreMapX.add(jObject.getString("mapx"));
                foodStoreMapY.add(jObject.getString("mapy"));
            }

            GeoPoint geoPoint = new GeoPoint( Double.parseDouble(foodStoreMapX.get(0)),Double.parseDouble(foodStoreMapY.get(0)));
            convertedGeoPoint = GeoTrans.convert(1,0,geoPoint);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
