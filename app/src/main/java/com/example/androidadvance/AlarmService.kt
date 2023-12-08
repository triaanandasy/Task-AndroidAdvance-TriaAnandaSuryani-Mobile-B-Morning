package com.example.androidadvance

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import androidx.core.app.NotificationCompat

class AlarmService : Service() {
    companion object{
        const val ACTION_STOP = "com.yourpackage.ACTION_STOP"
    }

    private var mediaPlayer: MediaPlayer? = null
    private val channelId = "music_channel"

    override fun onBind(intent: Intent): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)
        mediaPlayer?.isLooping = true

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                channelId,
                "MusicChannel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val stopIntent = Intent(this, AlarmService::class.java)
        stopIntent.action = ACTION_STOP
        val pendingStopIntent = PendingIntent.getService(this, 0, stopIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Alarm manager")
            .setContentText("Alarm Berikutnya")
            .setSmallIcon(R.drawable.baseline_access_alarm_24)
            .addAction(R.drawable.baseline_stop_24, "stop", pendingStopIntent)
            .build()
        if(intent?.action == ACTION_STOP){
            stopMusic()
        }

        startForeground(1, notification)

        mediaPlayer?.start()
        return START_STICKY
    }

    fun stopMusic() {
        mediaPlayer?.stop()
        stopForeground(true)
        stopSelf()
    }
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
        mediaPlayer?.release()
    }
}