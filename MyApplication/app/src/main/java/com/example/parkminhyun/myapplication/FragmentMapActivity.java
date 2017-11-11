package com.example.parkminhyun.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FragmentMapActivity extends FragmentActivity {
    Button deleteBtn;
    EditText inputLocation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        propertyInit();
        showNaverMapView();

    }

    // id 추가 및 기타 속성 초기화
    public void propertyInit() {
        inputLocation = (EditText)findViewById(R.id.search);
        inputLocation.setCompoundDrawables(null, null, getResources().getDrawable(R.drawable.close), null);

        deleteBtn = (Button)findViewById(R.id.deleteBtn);
    }

    public void showNaverMapView(){
        Fragment1 fragment1 = new Fragment1();
        fragment1.setArguments(new Bundle());
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.fragmentHere, fragment1);
        fragmentTransaction.commit();
    }

    public void deleteBtnClicked(View v){
        inputLocation.setText("");
    }
}