package com.example.a13110091.github;

import java.util.ArrayList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONObject;

public class GraphActivity extends AppCompatActivity {

    PieChart pieChart ;
    ArrayList<Entry> entries ;
    ArrayList<String> PieEntryLabels ;
    PieDataSet pieDataSet ;
    PieData pieData ;

    String p, one, two, three, four, five;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        setTitle("원형 그래프 보기");

        // 날짜 받았음
        Intent intent = getIntent();
        Log.e("DATE",intent.getExtras().getString("DATE"));

        try{
            //res 값 가져옴
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("DATE"));
            // php에서 앞에 단 대쉬글 가져오기
            JSONArray jsonArray = jsonObject.getJSONArray("res");
            //생성해주고 포문 돌리기

//            String p, one, two, three, four, five;
            for (int i =0; i < jsonArray.length(); i++){
                JSONObject object = jsonArray.getJSONObject(i);
                p = object.getString("p");
                one = object.getString("one");
                two = object.getString("two");
                three = object.getString("three");
                four = object.getString("four");
                five = object.getString("five");

                Log.e("되나요?", p + one + two + three + four + five);
//                tv2.setText(p + one + two + three + four + five);
            }
            Log.e("33333", jsonArray+"");
        }catch (Exception e){
            e.printStackTrace();
        }

        pieChart = (PieChart) findViewById(R.id.chart1);

        entries = new ArrayList<>();

        PieEntryLabels = new ArrayList<String>();

        AddValuesToPIEENTRY();

        AddValuesToPieEntryLabels();

        pieDataSet = new PieDataSet(entries, "");

        pieData = new PieData(PieEntryLabels, pieDataSet);

        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        pieChart.setData(pieData);

        pieChart.animateY(3000);

    }

    public void AddValuesToPIEENTRY(){

        entries.add(new BarEntry(Float.parseFloat(p), 0)); // 숫자값 2f 4f 6f 8f 7f
        entries.add(new BarEntry(Float.parseFloat(one), 1));
        entries.add(new BarEntry(Float.parseFloat(two), 2));
        entries.add(new BarEntry(Float.parseFloat(three), 3));
        entries.add(new BarEntry(Float.parseFloat(five), 4));

    }

    public void AddValuesToPieEntryLabels(){
        /* 앉은 자세에 대한 색상별로 확인할것 */
        PieEntryLabels.add("올바른자세"); /* January */
        PieEntryLabels.add("왼쪽 기울임"); /* February */
        PieEntryLabels.add("오른쪽 기울임"); /* March */
        PieEntryLabels.add("앞 기울임"); /* April */
        PieEntryLabels.add("뒤로 기울임"); /* May */

    }
}