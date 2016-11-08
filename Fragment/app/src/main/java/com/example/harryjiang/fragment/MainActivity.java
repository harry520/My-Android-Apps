package com.example.harryjiang.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    boolean status = false;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if (!status) {
                    FragmentOne fragmentOne = new FragmentOne();
                    fragmentTransaction.add(R.id.fragment_container, fragmentOne);
                    fragmentTransaction.commit();
                    button.setText("Load Second Fragment");
                    status = true;
                } else {
                    FragmentTwo fragmentTwo = new FragmentTwo();
                    fragmentTransaction.add(R.id.fragment_container, fragmentTwo);
                    button.setText("Load First Fragment");
                    status = false;
                }
            }
        });
    }
}
