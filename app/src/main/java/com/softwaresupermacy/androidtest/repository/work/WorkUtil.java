package com.softwaresupermacy.androidtest.repository.work;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;

import com.softwaresupermacy.androidtest.R;

import java.util.concurrent.TimeUnit;

public final class WorkUtil {

    private WorkUtil(){}

    public static final String DATA_INPUT_ID = "data_input_id";

    private static final Constraints cons =
            new Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build();

    public static PeriodicWorkRequest periodicWorkRequest(Data data){
            return new PeriodicWorkRequest.Builder(DBUpdaterWork.class, 16, TimeUnit.MINUTES)
                    .setInitialDelay(16, TimeUnit.MINUTES)
                    .setInputData(data)
                    .setConstraints(cons)
                    .build();
    }
    public static void makeStatusNotification(Context context) {

        // Make a channel if necessary
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            CharSequence name = "update data";
            String description = "channel ";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel =
                    new NotificationChannel("123", name, importance);
            channel.setDescription(description);

            // Add the channel
            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        // Create the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "123")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Update Movies information")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(new long[0]);

        // Show the notification
        NotificationManagerCompat.from(context).notify(1, builder.build());
    }

}
