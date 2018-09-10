package com.example.a13110091.github;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StartActive extends AppCompatActivity {

    private long time=0;

    /* 추가한 라인 새로운 파싱법 */
    String myJSON;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_CASS = "posture";

    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> personList;

    ListView list;

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_active);
        /* 화면 가로 세로 고정 */
        setTitle("나의 자세는");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //가로 고정
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //세로 고정

        /* WIFI 자동으로 연결 해주는 코드*/
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if(wifiManager != null) {
            if (!wifiManager.isWifiEnabled()) {
                wifiManager.setWifiEnabled(true);
            }
        }

        //새로운 파싱법인 부분
        list = (ListView)findViewById(R.id.listView);
        personList = new ArrayList<HashMap<String, String>>();
        getData("http://210.119.85.219/PHP_connection.php"); //수정 필요

        //추가한 라인
        FirebaseMessaging.getInstance().subscribeToTopic("news");
        FirebaseInstanceId.getInstance().getToken();

        Button startbt1 = (Button)findViewById(R.id.startbt1);
        Button startbt2 = (Button)findViewById(R.id.startbt2);
        iv = (ImageView)findViewById(R.id.caseimage);

        startbt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActive.this,MainActivity.class);
                startActivity(intent);
            }
        });
        startbt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActive.this,ExerciseActivity.class);
                startActivity(intent);
            }
        });
    }
    /* 뒤로가기 두번 하면 앱 종료 시키는것 */
    @Override
    public void onBackPressed() {
        if(System.currentTimeMillis() - time >= 2000){
            time = System.currentTimeMillis();
            Toast.makeText(getApplicationContext(), "뒤로 버튼을 한번 더 누르면 종료 됩니다.", Toast.LENGTH_LONG).show();
        }else if(System.currentTimeMillis() - time < 2000){
            finish();
        }
    }
    /* 메뉴바 메뉴버튼 누르면 설정창 뛰울수 있게 하였음 */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
//            case R.id.action_save:
//                Toast.makeText(this, "save", Toast.LENGTH_LONG).show();
//                return true;
            case R.id.action_bar_setting:
                Toast.makeText(this, "setting", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, Setting.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            updateThread();
            getData("http://210.119.85.219/PHP_connection.php"); //수정 필요
            showList();
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

    protected void showList(){
        try{

            Log.d("222",myJSON);
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);


            for(int i =0; i < peoples.length(); i++){
                JSONObject c = peoples.getJSONObject(i);
                String cass = c.getString(TAG_CASS);

                HashMap<String, String> persons = new HashMap<String, String>();

                persons.put(TAG_CASS, cass);

                Log.d("머가잇니", String.valueOf(persons));
                Log.d("PPPPP", cass);

                switch(cass){
                    case "올바른": //p면 이거
                        iv.setImageResource(R.drawable.success); //success
                        Log.e("1번 :", iv+"");
                        break;
                    case  "왼쪽": // 1
                        iv.setImageResource(R.drawable.sitleft); //sitleft
                        Log.e("2번:", iv+"");
                        break;
                    case  "오른쪽": // 2
                        iv.setImageResource(R.drawable.sitright); //sitright
                        Log.e("3번:", iv+"");
                        break;
                    case  "앞": // 3
                        iv.setImageResource(R.drawable.situp); //situp
                        Log.e("4번:", iv+"");
                        break;
                    case  "뒤": // 4
                        iv.setImageResource(R.drawable.sitback); //sitback
                        Log.e("5번:", iv+"");
                        break;
                }
                if(!personList.isEmpty()){
                   personList.remove(0);
                }
                personList.add(persons);

                Log.d("1111" , personList+"");
            }

            ListAdapter adapter = new SimpleAdapter(StartActive.this, personList, R.layout.list_item, new String[]{TAG_CASS}, new int[]{R.id.posture});

//            if( == "posture"){
//                Log.e("123", "되냐");
//                iv.setImageResource(R.drawable.intro);
//            }

            list.setAdapter(adapter);

        }catch (Exception e){
            Log.d("222", "여기까지 되니?");
            e.printStackTrace();
        }
    }
    public void getData(String url){

        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... strings) {

                String uri = strings[0];

                BufferedReader bufferedReader = null;
                try{
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection)url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine()) != null){
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                }catch (Exception e){
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result) {
                myJSON = result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }
}
