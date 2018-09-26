package com.example.a13110091.github;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

public class t123123 extends AppCompatActivity {

    String Weight, link;// 몸무게 저장 한곳

    TextView txtview;
    phpdo1 task1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t123123);

        Button btn1 = (Button)findViewById(R.id.btn1);

        final TextView tv = (TextView)findViewById(R.id.textView1);
        Spinner s = (Spinner)findViewById(R.id.spinner1);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                tv.setText(String.valueOf(parent.getItemAtPosition(position)));

                Weight = String.valueOf(parent.getItemAtPosition(position));
                Log.d("body", Weight); // 몸무게 받아왔음

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task1 = new phpdo1();
                txtview = (TextView)findViewById(R.id.txtView);
                task1.execute(Weight);
            }
        });

    }

    private class phpdo1 extends AsyncTask<String,Void,String> {

        protected void onPreExecute(){
            link = "http://210.119.85.219/bodyWeight.php?Weight="+Weight;
        }
        @Override
        protected String doInBackground(String... arg0) {

            try {
//                String DATE =  arg0[0];

//                String link = "http://210.119.85.219/DATEtest.php?DATE=" + DATE;
                URL url = new URL(link);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link));
                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                StringBuffer sb = new StringBuffer("");
                String line = "";

                while ((line = in.readLine()) != null) {
                    sb.append(line + "\n");
                    break;
                }
                in.close();
                return sb.toString();
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }

        }

        @Override
        protected void onPostExecute(String result){
            //txtview.setText("Login Successful");
            txtview.setText(result);
            //리절트에 값있음
        }
    }

}