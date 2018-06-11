package com.example.a13110091.github;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    DatePickerDialog dlg;
    TextView tv2;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("상세보기");

        Button chart = (Button)findViewById(R.id.mainbt1);
        Button grape = (Button)findViewById(R.id.mainbt2);
        tv2 = (TextView)findViewById(R.id.data);
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
                String Smonth = null;
                if(month <10){
                    Smonth = "0" + String.valueOf(month + 1);
                    Log.e("3333", Smonth);
                }
                tv2.setText(String.format("%4d-%2s-%2d",year,Smonth,day));
                str = tv2.getText().toString();
            }
        },2018,05,10);

        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // Intent intent1 = new Intent(MainActivity.this, ChartActivity.class);

                //startActivity(intent1);
                //new BackgroundTask().execute();
                if(str == null){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("날짜를 선택하여주세요.").setNegativeButton("다시시도", null).create().show();
                }else {
                    Intent intent = new Intent(MainActivity.this, ChartActivity.class);
                    startActivity(intent);
                    new BackgroundTask().execute();
                }
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
    class BackgroundTask extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            target = "http://210.119.85.219/senserList.php";
//            target = "http://210.119.85.219/test.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            String param = "data="+str+""; // 인풋 파라메터값 생성

            try {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                /*추가한 라인*/
//                httpURLConnection.setRequestMethod("POST");
//                httpURLConnection.setDoInput(true);
//                httpURLConnection.connect();
                /* 안드로이드 -> 서버 파라메타값 전달*/
//                OutputStream outs = httpURLConnection.getOutputStream();
//                outs.write(str.getBytes("UTF-8"));
//                outs.flush();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 8*1024);
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + "\n");
                    Log.d("temp :", temp+""); // temp 저장됨
                }

                bufferedReader.close();
                inputStream.close();
                //추가한 라인
//                outs.close();
//                outputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        public void onPostExecute(String result) {
            Intent intent = new Intent(MainActivity.this, ChartActivity.class);
            intent.putExtra("userList", result);
            intent.putExtra("1234", str);
            MainActivity.this.startActivity(intent);
        }
    }
}