package com.mekki.eng_mohamed.tempapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //private TextView tvtemp;

    private ListView lvtemps;
    private MyHelper helper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // tvtemp=findViewById(R.id.tvtemp);
        lvtemps=findViewById(R.id.lvtemps);
        helper= new MyHelper(MainActivity.this);
        db=helper.getWritableDatabase();


// Instantiate the RequestQueue.

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://samples.openweathermap.org/data/2.5/box/city?bbox=12,32,15,37,10&appid=b6907d289e10d714a6e88b30761fae22";
        final ArrayList<String> temps = new ArrayList<>();
// Request a string response from the provided URL.
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,temps);
        lvtemps.setAdapter(adapter);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject root = new JSONObject(response);
                            JSONArray list = root.getJSONArray("list");
                            for (int i=0;i<list.length();i++){
                                JSONObject main = list.getJSONObject(i);
                                JSONObject pp = main.getJSONObject("main");
                                String dg = Double.toString(pp.getDouble("temp"));
                                String dt = main.getString("name");
                                // String m = Integer.toString(dg);
                                temps.add(dg+" "+"degree"+"/"+dt);


                            }
                            adapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTextView.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
    public void savedata(View view ){
       // String name = adapter.getContext().toString();
        String name = lvtemps.getAdapter().toString();
        //String nn = dt.getBytes().toString();
        ContentValues row = new ContentValues();
        //row.put("temper",degree);
        row.put("name",name);
        long id = db.insert("weather",null,row);
        if (id !=-1) {
            Toast.makeText(MainActivity.this, "Your Data has been saved", Toast.LENGTH_SHORT).show();
        }

    }
}
