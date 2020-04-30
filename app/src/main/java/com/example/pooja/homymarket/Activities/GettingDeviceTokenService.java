package com.example.pooja.homymarket.Activities;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;

public class GettingDeviceTokenService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String s) {
        Log.d("DEVICE_TOKEN:",s);

    }
}
