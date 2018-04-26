package com.unipi.talepis.schedulesound4;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    //We are not going to use the interval below, since we have a requirement regarding time schedule
    //public static final long interval = 4020000; //alarm will trigger every interval/1000 seconds
    public static final long whenToStop = 60000; //send message to service to stop after whenToStop/1000 seconds have passed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("my_prefs",MODE_PRIVATE);
        boolean serviceEnabled = sharedPreferences.getBoolean("serviceEnabled",false);
        if (serviceEnabled)
            ((Button)findViewById(R.id.button2)).setText("Enabled");
        else ((Button)findViewById(R.id.button2)).setText("Disabled");
        if ((ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},187);
        }
    }
    public void go(View view){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent();
            String packageName = getPackageName();
            PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
            if (!pm.isIgnoringBatteryOptimizations(packageName)) {
                intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setData(Uri.parse("package:" + packageName));
                startActivity(intent);
            }
        }
        boolean serviceEnabled = sharedPreferences.getBoolean("serviceEnabled",false);
        if (!serviceEnabled) {

            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(Calendar.HOUR_OF_DAY, 8); // First call of alarm
            calendar1.set(Calendar.MINUTE, 45);
            calendar1.set(Calendar.SECOND, 0);
            if (System.currentTimeMillis() > calendar1.getTimeInMillis()) {
                calendar1.add(Calendar.DAY_OF_YEAR, 1);
            }

            Calendar calendar2 = Calendar.getInstance();
            calendar2.set(Calendar.HOUR_OF_DAY, 9); // Second call of alarm
            calendar2.set(Calendar.MINUTE, 35);
            calendar2.set(Calendar.SECOND, 0);
            if (System.currentTimeMillis() > calendar2.getTimeInMillis()) {
                calendar2.add(Calendar.DAY_OF_YEAR, 1);
            }

            Calendar calendar3 = Calendar.getInstance();
            calendar3.set(Calendar.HOUR_OF_DAY, 10); // Third call of alarm
            calendar3.set(Calendar.MINUTE, 25);
            calendar3.set(Calendar.SECOND, 0);
            if (System.currentTimeMillis() > calendar3.getTimeInMillis()) {
                calendar3.add(Calendar.DAY_OF_YEAR, 1);
            }

            Calendar calendar4 = Calendar.getInstance();
            calendar4.set(Calendar.HOUR_OF_DAY, 11); // Fourth call of alarm
            calendar4.set(Calendar.MINUTE, 15);
            calendar4.set(Calendar.SECOND, 0);
            if (System.currentTimeMillis() > calendar4.getTimeInMillis()) {
                calendar4.add(Calendar.DAY_OF_YEAR, 1);
            }

            Calendar calendar5 = Calendar.getInstance();
            calendar5.set(Calendar.HOUR_OF_DAY, 12); // Fifth call of alarm
            calendar5.set(Calendar.MINUTE, 5);
            calendar5.set(Calendar.SECOND, 0);
            if (System.currentTimeMillis() > calendar5.getTimeInMillis()) {
                calendar5.add(Calendar.DAY_OF_YEAR, 1);
            }

            Calendar calendar6 = Calendar.getInstance();
            calendar6.set(Calendar.HOUR_OF_DAY, 12); // Sixth call of alarm
            calendar6.set(Calendar.MINUTE, 55);
            calendar6.set(Calendar.SECOND, 0);
            if (System.currentTimeMillis() > calendar6.getTimeInMillis()) {
                calendar6.add(Calendar.DAY_OF_YEAR, 1);
            }

            Calendar calendar7 = Calendar.getInstance();
            calendar7.set(Calendar.HOUR_OF_DAY, 13); // Seventh call of alarm
            calendar7.set(Calendar.MINUTE, 45);
            calendar7.set(Calendar.SECOND, 0);
            if (System.currentTimeMillis() > calendar7.getTimeInMillis()) {
                calendar7.add(Calendar.DAY_OF_YEAR, 1);
            }


            Intent intent = new Intent(this, MyService.class);
            intent.putExtra("timeinmillis", whenToStop);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

            PendingIntent pendingIntent1 = PendingIntent.getService(this, 171, intent, 0);
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                    calendar1.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent1);

            PendingIntent pendingIntent2 = PendingIntent.getService(this, 172, intent, 0);
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                    calendar2.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent2);

            PendingIntent pendingIntent3 = PendingIntent.getService(this, 173, intent, 0);
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                    calendar3.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent3);

            PendingIntent pendingIntent4 = PendingIntent.getService(this, 174, intent, 0);
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                    calendar4.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent4);

            PendingIntent pendingIntent5 = PendingIntent.getService(this, 175, intent, 0);
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                    calendar5.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent5);

            PendingIntent pendingIntent6 = PendingIntent.getService(this, 176, intent, 0);
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                    calendar6.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent6);

            PendingIntent pendingIntent7 = PendingIntent.getService(this, 177, intent, 0);
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                    calendar7.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent7);

            Log.d("Alarm","Set");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("serviceEnabled",true);
            editor.commit();
            ((Button)findViewById(R.id.button2)).setText("Enabled");
        } else {
            Intent intent = new Intent(this, MyService.class);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

            PendingIntent pendingIntent1 = PendingIntent.getService(this, 171, intent, 0);
            alarmManager.cancel(pendingIntent1);

            PendingIntent pendingIntent2 = PendingIntent.getService(this, 172, intent, 0);
            alarmManager.cancel(pendingIntent2);

            PendingIntent pendingIntent3 = PendingIntent.getService(this, 173, intent, 0);
            alarmManager.cancel(pendingIntent3);

            PendingIntent pendingIntent4 = PendingIntent.getService(this, 174, intent, 0);
            alarmManager.cancel(pendingIntent4);

            PendingIntent pendingIntent5 = PendingIntent.getService(this, 175, intent, 0);
            alarmManager.cancel(pendingIntent5);

            PendingIntent pendingIntent6 = PendingIntent.getService(this, 176, intent, 0);
            alarmManager.cancel(pendingIntent6);

            PendingIntent pendingIntent7 = PendingIntent.getService(this, 177, intent, 0);
            alarmManager.cancel(pendingIntent7);

            Log.d("Alarm","Stopped");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("serviceEnabled",false);
            editor.commit();
            ((Button)findViewById(R.id.button2)).setText("Disabled");
        }
    }
}
