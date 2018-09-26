package com.example.a13110091.github;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Alllist extends AppCompatActivity {

    private int i = 0;
    private TextView myi;

    TextView txtview, result;
    phpdo task;

    String str;

    int left,  right, run, back, center;
    double sum, leftavg;

    int leftavg1;

    ListView simpleList;
    String  Item[] = {"올바른 자세", "왼쪽으로 오래 앉아 있었습니다.", "오른쪽으로 오래 앉아 있었습니다.", "앞쪽으로 오래 앉아 있었습니다.", "뒤쪽으로 오래 앉아 있었습니다.", /*"Avocado"*/};
    String  SubItem[] = {left+"","왼쪽 자세 설명","오른쪽 자세 설명","앞쪽 자세 설명","뒤쪽 자세 설명"};
    int flags[] = {R.drawable.success, R.drawable.sitleft, R.drawable.sitright, R.drawable.situp, R.drawable.sitback};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alllist);

        simpleList = (ListView)findViewById(R.id.ListView);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), Item,SubItem, flags);
        simpleList.setAdapter(customAdapter);


        myi = (TextView) findViewById(R.id.i);
        result = (TextView) findViewById(R.id.result);

//        task = new phpdo();
        txtview = (TextView) findViewById(R.id.txtView);
//        task.execute();

//        listView = (ListView)this.findViewById(R.id.listView);

    }

    private class phpdo extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... arg0) {

            try {
//                String id =  arg0[0];
//                String name = arg0[1];

                String link = "http://210.119.85.219/Alllist.php";
                URL url = new URL(link);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link));
                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                StringBuffer sb = new StringBuffer("");
                String line = "";

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                in.close();
                return sb.toString();
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }

        }

        @Override
        protected void onPostExecute(String result) {
//            txtview.setText(result);
            str = result;
            getData();
        }
    }

    public void getData() {
        Log.d("여기는 머냐", str);
        try {
            //돌고 온값 저장 하는곳
            JSONObject jsonObject = new JSONObject(str);
            JSONArray jsonArray = jsonObject.getJSONArray("res");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                left = Integer.parseInt(object.getString("왼쪽"));
                right = Integer.parseInt(object.getString("오른쪽"));
                run = Integer.parseInt(object.getString("앞쪽"));
                back = Integer.parseInt(object.getString("뒤쪽"));
                center = Integer.parseInt(object.getString("바른자세"));

                result.setText(left + "/" + " " + right + "/" + " " + run + "/" + " " + back + "/" + " " + center + "/" + " ");

                listitem();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            task = new phpdo();
            task.execute();
            updateThread();
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        handler.sendMessage(handler.obtainMessage());
                        Thread.sleep(1000);
                    } catch (Throwable t) {

                    }
                }
            }
        });
        myThread.start();
    }

    private void updateThread() {
        i++;
        myi.setText(String.valueOf(i));
    }

    public void listitem() {
        sum = left + right + run + back + center;
        leftavg = (left / sum) * 100;

        leftavg1 = Integer.parseInt(String.valueOf(Math.round(leftavg)));

    }

}
