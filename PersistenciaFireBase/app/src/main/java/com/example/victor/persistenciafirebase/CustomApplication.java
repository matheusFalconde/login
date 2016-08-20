package com.example.victor.persistenciafirebase;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Victor on 22/05/2016.
 */
public class CustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);

    }

}
