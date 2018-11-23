package com.mekki.eng_mohamed.tempapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ContactActivity extends AppCompatActivity {
    private TextView tvcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        tvcon=findViewById(R.id.tvcon);
        tvcon.setText("Contact us on :- \n Our Phone : 01234568 \n Email:temp@gmail.com \n Address : Cairo");
    }
}
