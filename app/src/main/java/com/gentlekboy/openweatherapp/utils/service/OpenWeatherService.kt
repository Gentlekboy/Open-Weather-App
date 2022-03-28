package com.gentlekboy.openweatherapp.utils.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.gentlekboy.openweatherapp.R
import com.gentlekboy.openweatherapp.data.model.service.WeatherServiceModel
import com.gentlekboy.openweatherapp.ui.fragment.DashboardFragment
import com.gentlekboy.openweatherapp.utils.CHANNEL_ID
import com.gentlekboy.openweatherapp.utils.CHANNEL_NAME
import com.gentlekboy.openweatherapp.utils.FAVOURITE_CITY

class OpenWeatherService : Service() {
    override fun onBind(p0: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(serviceChannel)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val weatherInfo = intent?.getParcelableExtra<WeatherServiceModel>(FAVOURITE_CITY)
        val notificationIntent = Intent(this, DashboardFragment::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("${weatherInfo?.temperature}\u2103 in ${weatherInfo?.city}")
            .setContentText(weatherInfo?.weatherDescription)
            .setSmallIcon(R.drawable.cloud)
            .setContentIntent(pendingIntent)
            .build()

        startForeground(1, notification)
        return super.onStartCommand(intent, flags, startId)
    }
}