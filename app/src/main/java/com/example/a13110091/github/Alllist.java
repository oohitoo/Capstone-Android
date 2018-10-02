package com.example.a13110091.github;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

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

public class Alllist extends AppCompatActivity {
    /* 이미지 뷰 바뀌는거*/
    ImageView img;

    TextView txtview, result, leftavg, rightavg, runavg, backavg, centeravg;
    phpdo task;
    String str;
    //Json 으로 받을때 씀
    double left, right, run, back, center;

    private int i = 0;
    private TextView myi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alllist);

        txtview = (TextView) findViewById(R.id.txtView);
        myi = (TextView) findViewById(R.id.i);

        img = (ImageView) findViewById(R.id.img);
        result = (TextView) findViewById(R.id.result);

        leftavg = (TextView) findViewById(R.id.leftavg);
        rightavg = (TextView) findViewById(R.id.rightavg);
        centeravg = (TextView) findViewById(R.id.centeravg);
        runavg = (TextView) findViewById(R.id.runavg);
        backavg = (TextView) findViewById(R.id.backavg);

//        task = new phpdo();
//        task.execute();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            updateThread();
            task = new phpdo();
            task.execute();
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Thread myThread = new Thread(new Runnable() {
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
        Log.d(i + "번째", i + "번째");
        myi.setText(String.valueOf(i));
    }

    private class phpdo extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... arg0) {
            try {
//                String id = arg0[0];
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
            getdata();
        }
    }

    public void getdata() {
        Log.e("여기는", str);
        try {

            //res 값 가져옴
//                    JSONObject jsonObject = new JSONObject(txtview.getText().toString());
            JSONObject jsonObject = new JSONObject(str);
            // php에서 앞에 단 대쉬글 가져오기
            JSONArray jsonArray = jsonObject.getJSONArray("res");
            //생성해주고 포문 돌리기

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                left = Double.parseDouble(object.getString("왼쪽"));
                right = Double.parseDouble(object.getString("오른쪽"));
                run = Double.parseDouble(object.getString("앞쪽"));
                back = Double.parseDouble(object.getString("뒤쪽"));
                center = Double.parseDouble(object.getString("바른자세"));

                result.setText(left + "/" + " " + right + "/" + " " + run + "/" + " " + back + "/" + " " + center + "/" + " ");
                // 이제 계산 으로 실행 드감
                calculation();

            }
            Log.e("33333", jsonArray + "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void calculation() {
        double sum = left + right + run + back + center;
        double leftag = (left / sum) * 100;
        double rightag = (right / sum) * 100;
        double runag = (run / sum) * 100;
        double backtag = (back / sum) * 100;
        double centerag = (center / sum) * 100;

        leftavg.setText(String.valueOf(Math.round(leftag)) + "%");
        rightavg.setText(String.valueOf(Math.round(rightag)) + "%");
        centeravg.setText(String.valueOf(Math.round(centerag)) + "%");
        runavg.setText(String.valueOf(Math.round(runag)) + "%");
        backavg.setText(String.valueOf(Math.round(backtag)) + "%");

        double array[] = {leftag, rightag, runag, backtag, centerag};
        double max = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            } else if (array[i] == max) {
                max = array[i];
            }
        }
        Log.e("max", max + "");

        if (max == leftag) {
            img.setImageResource(R.drawable.sitleft);
        } else if (max == rightag) {
            img.setImageResource(R.drawable.sitright);
        } else if (max == runag) {
            img.setImageResource(R.drawable.situp);
        } else if (max == backtag) {
            img.setImageResource(R.drawable.sitback);
        } else {
            img.setImageResource(R.drawable.success);
        }

    }
}
