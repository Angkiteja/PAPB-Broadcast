package com.example.broadcastproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){
        String msg ="";
        switch (intent.getAction()){
            case Intent.ACTION_POWER_CONNECTED:
                msg = "the power is connected";
                break;
            case Intent.ACTION_POWER_DISCONNECTED:
                msg = "the power is disconnected";
                break;
            case "ACTION_CUSTOM_BROADCAST":
                msg = "this is custom broadcast";
                msg = intent.getStringExtra("Data");
            default:
                break;

        }
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
