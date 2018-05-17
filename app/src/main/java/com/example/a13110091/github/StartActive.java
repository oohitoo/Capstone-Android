package com.example.a13110091.github;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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

    /* 추가한 라인 새로운 파싱법 */
    String myJSON;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_CASS = "posture";

    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> personList;

    ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_active);

        //새로운 파싱법인 부분
        list = (ListView)findViewById(R.id.listView);
        personList = new ArrayList<HashMap<String, String>>();
        getData("http://210.119.85.219/PHP_connection.php"); //수정 필요

        //추가한 라인
        FirebaseMessaging.getInstance().subscribeToTopic("news");
        FirebaseInstanceId.getInstance().getToken();

        Button startbt1 = (Button)findViewById(R.id.startbt1);
        Button startbt2 = (Button)findViewById(R.id.startbt2);

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
    protected void showList(){
        try{
            Log.d("222",myJSON);
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            Log.d("111", "여기까지 되니?");

            for(int i =0; i < peoples.length(); i++){
                JSONObject c = peoples.getJSONObject(i);
                String cass = c.getString(TAG_CASS);

                HashMap<String, String> persons = new HashMap<String, String>();
                persons.put(TAG_CASS, cass);

                personList.add(persons);
                Log.d("1111" , personList+"");
            }

            ListAdapter adapter = new SimpleAdapter(StartActive.this, personList, R.layout.list_item, new String[]{TAG_CASS}, new int[]{R.id.posture});

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
