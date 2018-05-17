package com.example.a13110091.github;

import android.util.Log;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 13110091 on 2018-05-15.
 */

public class DateRequest extends StringRequest{

    final static private String URL = "http://210.119.85.219/senserList.php";
    private Map<String, String> parameters;

    public DateRequest(TextView tv2, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("data", tv2+"");
    }
    public Map<String, String> getParams(){
        Log.d("parameters", parameters+"");
        return parameters;
    }
}
