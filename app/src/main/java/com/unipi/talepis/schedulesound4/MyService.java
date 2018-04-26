package com.unipi.talepis.schedulesound4;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.v4.app.ActivityCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MyService extends Service {
    long starttime, timetofinish, endtime;
    LocationManager locationManager;
    MyLocationListener myLocationListener;
    PowerManager.WakeLock wakeLock;
    FirebaseDatabase database;
    DatabaseReference myRef,logRef;
    volatile String userID;
    volatile List<Double> soundlevelList;
    volatile String timeStamp;
    volatile Location currentLocation;
    volatile String recognized_voice;
    private static final long RECORDING_INTERVAL = 60000; // 1 MINUTE
    private static final long SAMPLING_INTERVAL = 250;    // 4 SAMPLES PER SECOND

    @Override
    public void onDestroy() {
        //Log.d("Service","Stopped");
        super.onDestroy();
    }

    public MyService() {
        timeStamp=null;
        recognized_voice="";
        currentLocation=null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Log.d("Service","Started");
        if (isWeekend()){
            stopSelf();
            return START_NOT_STICKY;
        }

        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                "MyWakelock");
        wakeLock.acquire();
        timetofinish = intent.getLongExtra("timeinmillis", 60000);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            stopSelf();
            return START_NOT_STICKY;
        }
        database = FirebaseDatabase.getInstance();
        myLocationListener = new MyLocationListener();

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("my_prefs",MODE_PRIVATE);
        if (sharedPreferences.contains("userID"))
            userID = sharedPreferences.getString("userID","");
        else {
            userID = "User_"+database.getReference().child("Users").push().getKey();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("userID",userID);
            editor.commit();
        }
        if (userID=="")
            stopSelf();

        myRef = database.getReference().child("Users").child(userID);

        logRef = database.getReference().child("Logs").child(userID);
        DatabaseReference newlogRef = logRef.push();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatted = sdf.format(new Date(System.currentTimeMillis()));
        newlogRef.setValue(formatted);

        soundlevelList = new ArrayList<>();
        gorec();
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, myLocationListener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, myLocationListener);
        CountDownTimer countDownTimer1 = new CountDownTimer(timetofinish,timetofinish) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                recognized_voice = dummyActivity.result;
                locationManager.removeUpdates(myLocationListener);
                if (timeStamp!=null){
                    SensorEvent sensorEvent = new SensorEvent(currentLocation,timeStamp,recognized_voice);
                    DatabaseReference newref = myRef.push();
                    newref.setValue(sensorEvent);
                    //myRef.push().setValue(valueToWrite+" Val:"+String.valueOf(soundAverage));
                }
                wakeLock.release();
                stopSelf();
            }
        };
        countDownTimer1.start();
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    private class MyLocationListener implements LocationListener{

        @Override
        public void onLocationChanged(Location location) {
            //Log.d("Service","LOCATION CHANGE!");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formatted = sdf.format(new Date(location.getTime()));
            timeStamp = formatted;
            currentLocation=location;
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    }
    private double calculateAverage(List<Double> values) {
        Double sum = 0.0;
        if(!values.isEmpty()) {
            for (Double value : values) {
                sum += value;
            }
            return sum / values.size();
        }
        return sum;
    }

    private void gorec(){
        startActivity(new Intent(this,dummyActivity.class));
    }
    private boolean isWeekend() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY);
    }
}
