package com.example.a13110091.github;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    DatePickerDialog dlg;
    TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button chart = (Button)findViewById(R.id.mainbt1);
        Button grape = (Button)findViewById(R.id.mainbt2);
        tv2 = (TextView)findViewById(R.id.tv2);
        ImageButton img = (ImageButton)findViewById(R.id.imgbtn);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("tag","된다");
                dlg.show();
            }
        });

        dlg = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                tv2.setText(String.format("%4d-%2d-%2d", year,month+1,day));
            }
        },2018,5,10);

        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChartActivity.class);
                //액티비티 시작!
                startActivity(intent);
            }
        });
        grape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GraphActivity.class);
                startActivity(intent);
            }
        });
    }
}
