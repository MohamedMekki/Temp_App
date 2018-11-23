package com.mekki.eng_mohamed.tempapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
    }
    public void gotolog(View view){
        Intent i = new Intent (LogActivity.this,MainActivity.class);
        startActivity(i);

    }
    public void showdata(View view){
        Intent i = new Intent(LogActivity.this,ShowActivity.class);
        startActivity(i);
    }

    public void contactus(View view){
        Intent i = new Intent(LogActivity.this,ContactActivity.class);
        startActivity(i);
    }

}
