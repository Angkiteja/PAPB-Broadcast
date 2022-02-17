package com.example.broadcastproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private CustomReceiver mCustomReceiver = new CustomReceiver();
    private Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mendaftarkan event yg akan kita terima
        IntentFilter intentFilter = new IntentFilter();
        //mau nerima broadcast apa sih? set nya disini
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);

        //event diatas mau saya terima tp yg nerima receivernya
        //trs yg ke trigger onReceive
        //setelah event telah didefine, harus ada penerimanya (listenernya)
        registerReceiver(mCustomReceiver, intentFilter);

        sendBtn = findViewById(R.id.send_button);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mengirim custom broadcast, dibawah ini event
                Intent intent = new Intent("ACTION_CUSTOM_BROADCAST");
                intent.putExtra("Data", "This is the data");
                LocalBroadcastManager.getInstance(view.getContext()).sendBroadcast(intent);
            }
        });

        //mendaftarkan event
        IntentFilter intentFilter1 = new IntentFilter("ACTION_CUSTOM_BROADCAST");
        LocalBroadcastManager.getInstance(this).registerReceiver(mCustomReceiver, intentFilter1);
    }
    //karena udah register jangan lupa destroy nya
    @Override
    protected  void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mCustomReceiver);

        LocalBroadcastManager.getInstance(this).unregisterReceiver(mCustomReceiver);
    }
}