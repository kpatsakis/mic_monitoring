package com.unipi.talepis.schedulesound4;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.MODE_PRIVATE;

public class MyChargingReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equalsIgnoreCase(Intent.ACTION_POWER_CONNECTED)){
            //Toast.makeText(context,"Started Charging",Toast.LENGTH_LONG).show();
            SharedPreferences sharedPreferences = context.getSharedPreferences("my_prefs",MODE_PRIVATE);
            boolean serviceEnabled = sharedPreferences.getBoolean("serviceEnabled",false);
            if (serviceEnabled) {

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

                Toast.makeText(context,"Re-enabling school sound service!",Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(context, MyService.class);
                intent2.putExtra("timeinmillis", MainActivity.whenToStop);
                AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

                PendingIntent pendingIntent1 = PendingIntent.getService(context, 171, intent, 0);
                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                        calendar1.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY, pendingIntent1);

                PendingIntent pendingIntent2 = PendingIntent.getService(context, 172, intent, 0);
                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                        calendar2.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY, pendingIntent2);

                PendingIntent pendingIntent3 = PendingIntent.getService(context, 173, intent, 0);
                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                        calendar3.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY, pendingIntent3);

                PendingIntent pendingIntent4 = PendingIntent.getService(context, 174, intent, 0);
                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                        calendar4.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY, pendingIntent4);

                PendingIntent pendingIntent5 = PendingIntent.getService(context, 175, intent, 0);
                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                        calendar5.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY, pendingIntent5);

                PendingIntent pendingIntent6 = PendingIntent.getService(context, 176, intent, 0);
                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                        calendar6.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY, pendingIntent6);

                PendingIntent pendingIntent7 = PendingIntent.getService(context, 177, intent, 0);
                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                        calendar7.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY, pendingIntent7);

                Log.d("Alarm", "Set");
            }
        }
    }
}
