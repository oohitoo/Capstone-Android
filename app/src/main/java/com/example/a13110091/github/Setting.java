package com.example.a13110091.github;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class Setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Switch toggle = (Switch) findViewById(R.id.wifi_switch);

        toggle.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    toggleWiFi(true);

                    Toast.makeText(getApplicationContext(), R.string.wifi_start, Toast.LENGTH_LONG).show();

                } else {

                    toggleWiFi(false);

                    Toast.makeText(getApplicationContext(), R.string.wifi_end, Toast.LENGTH_LONG).show();

                }

            }

        });

    }



    public void toggleWiFi(boolean status) {

        WifiManager wifiManager = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if (status == true && !wifiManager.isWifiEnabled()) {

            wifiManager.setWifiEnabled(true);

        } else if (status == false && wifiManager.isWifiEnabled()) {

            wifiManager.setWifiEnabled(false);

        }

    }



    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu, menu);

        return true;

    }



}