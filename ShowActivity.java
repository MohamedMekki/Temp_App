package com.mekki.eng_mohamed.tempapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {
private TextView tvtem;
    private MyHelper helper;
    private SQLiteDatabase db;
    private ListView lvshow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        tvtem=findViewById(R.id.tvtem);
        lvshow=findViewById(R.id.lvshow);
        helper = new MyHelper(ShowActivity.this);
        db= helper.getWritableDatabase();
        Cursor pointer = db.query("weather",null,null,null,null,null,null);
           ArrayList<String> list = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ShowActivity.this,android.R.layout.simple_list_item_1,list);
        lvshow.setAdapter(adapter);
        String data = lvshow.getAdapter().toString();
        while (pointer.moveToNext()==true){

            data += pointer.getString(1)+"\n ";

        }

        //lvshow.setTag(1,adapter);
        adapter.notifyDataSetChanged();
        tvtem.setText(data);
    }
}
