package com.example.harryjiang.volleyexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;
    String serverUrl = "http://192.168.1.68/greetings.php";
    // RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.bn);
        textView = (TextView) findViewById(R.id.txt);
        // Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
        // Network network = new BasicNetwork(new HurlStack());
        // requestQueue = new RequestQueue(cache, network);
        // requestQueue.start();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, serverUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textView.setText(response);
                        // requestQueue.stop();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText("Error...");
                        error.printStackTrace();
                        // requestQueue.stop();
                    }
                });
                // requestQueue.add(stringRequest);
                MySingleton.getMInstance(getApplicationContext()).addToRequestQueue(stringRequest);
            }
        });
    }
}
