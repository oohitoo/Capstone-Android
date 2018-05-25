package com.example.a13110091.github;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 13110091 on 2018-05-14.
 */

public class SenserListAdapter extends BaseAdapter{

    private Context context;
    private List<Senser> senserList;
    private List<Senser> saveList;

    public SenserListAdapter(Context context, List<Senser> senserList, List<Senser> saveList){
        this.context = context;
        this.senserList = senserList;
        this.saveList = saveList;
    }

    @Override
    public int getCount() {
        return senserList.size();
    }

    @Override
    public Object getItem(int i) {
        return senserList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view.inflate(context, R.layout.user, null);
        TextView senser1 = (TextView)v.findViewById(R.id.senser1);
        TextView senser2 = (TextView)v.findViewById(R.id.senser2);
        TextView senser3 = (TextView)v.findViewById(R.id.senser3);
        TextView senser4 = (TextView)v.findViewById(R.id.senser4);
        TextView senser5 = (TextView)v.findViewById(R.id.senser5);
        TextView senser6 = (TextView)v.findViewById(R.id.senser6);
        TextView data = (TextView)v.findViewById(R.id.data);

        senser1.setText(senserList.get(i).getSenser1());
        senser2.setText(senserList.get(i).getSenser2());
        senser3.setText(senserList.get(i).getSenser3());
        senser4.setText(senserList.get(i).getSenser4());
        senser5.setText(senserList.get(i).getSenser5());
        senser6.setText(senserList.get(i).getSenser6());
        data.setText(senserList.get(i).getData_hora());

        v.setTag(senserList.get(i).getSenser1());
        return v;
    }
}
