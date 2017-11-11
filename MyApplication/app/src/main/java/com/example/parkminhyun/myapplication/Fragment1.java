package com.example.parkminhyun.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhn.android.maps.NMapContext;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapLocationManager;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;

public class Fragment1 extends Fragment {

    public NMapView mapView;
    private NMapContext mMapContext;
    private NGeoPoint nGeoPoint;

    NMapLocationManager nMapLocationManager;
    // 맵 컨트롤러
    NMapController mMapController = null;
    private static final String CLIENT_ID = "_i9KY4aNfTTMFa386pMB";// 애플리케이션 클라이언트 아이디 값

    public Fragment1(){
        nGeoPoint = new NGeoPoint();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment1, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMapContext =  new NMapContext(super.getActivity());
        mMapContext.onCreate();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mapView = (NMapView)getView().findViewById(R.id.mapView);
        mapView.setClientId(CLIENT_ID);// 클라이언트 아이디 설정
        mapView.setClickable(true);
        mMapContext.setupMapView(mapView);


        nMapLocationManager = new NMapLocationManager(getActivity());
        double a = nMapLocationManager.getMyLocation().getLatitude();

//        Toast.makeText(getContext(),String.valueOf(nMapLocationManager.getMyLocation().getLatitude()),Toast.LENGTH_SHORT).show();
        // 지도 객체로부터 컨트롤러 추출
        mMapController = mapView.getMapController();
//        mMapController.setMapCenter(
//                new NGeoPoint(nGeoPoint.getLongitude(),nGeoPoint.getLatitude()),  14);
    }

    public NMapController getmMapController(){
        return mMapController;
    }

    @Override
    public void onStart(){
        super.onStart();
        mMapContext.onStart();
    }
    @Override
    public void onResume() {
        super.onResume();
        mMapContext.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        mMapContext.onPause();
    }
    @Override
    public void onStop() {
        mMapContext.onStop();
        super.onStop();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    @Override
    public void onDestroy() {
        mMapContext.onDestroy();
        super.onDestroy();
    }
}