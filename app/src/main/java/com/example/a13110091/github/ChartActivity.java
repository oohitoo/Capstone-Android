package com.example.a13110091.github;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity {

    private ListView listView;
    private SenserListAdapter adapter;
    private List<Senser> senserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

//        TextView userListTextView = (TextView)findViewById(R.id.userListTextView);
        Intent intent = getIntent();
//        userListTextView.setText(intent.getStringExtra("userList"));

        listView = (ListView)findViewById(R.id.listView);
        senserList = new ArrayList<Senser>();

//         senserList.add(new Senser("11","11","1","11","11","11","11"));
//         senserList.add(new Senser("22","11","1","11","11","11","22"));
//         senserList.add(new Senser("33","11","1","11","11","11","33"));

         adapter = new SenserListAdapter(getApplicationContext(), senserList);
         listView.setAdapter(adapter);

         try{
             JSONObject jsonObject = new JSONObject(intent.getStringExtra("userList"));
             JSONArray jsonArray = jsonObject.getJSONArray("response");
             int count= 0;
             String senser1, senser2, senser3, senser4, senser5, senser6, data_hora;
//             String senser1;
             while (count < jsonArray.length()){
                 JSONObject object = jsonArray.getJSONObject(count);
                 senser1 = object.getString("senser1");
                 senser2 = object.getString("senser2");
                 senser3 = object.getString("senser3");
                 senser4 = object.getString("senser4");
                 senser5 = object.getString("senser5");
                 senser6 = object.getString("senser6");
                 data_hora = object.getString("data_hora");
                 Senser senser = new Senser(senser1, senser2, senser3, senser4, senser5, senser6, data_hora);
//                 Senser senser = new Senser(senser1);
                 senserList.add(senser);
                 count++;
             }
         }catch (Exception e){
             e.printStackTrace();
         }
    }
}
