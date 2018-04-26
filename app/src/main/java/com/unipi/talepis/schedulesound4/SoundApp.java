package com.unipi.talepis.schedulesound4;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by timmy on 10/21/2017.
 */

public class SoundApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
