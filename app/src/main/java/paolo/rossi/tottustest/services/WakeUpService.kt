package paolo.rossi.tottustest.services

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.os.PowerManager
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import paolo.rossi.tottustest.R
import paolo.rossi.tottustest.interfaces.Constants
import paolo.rossi.tottustest.ui.activities.main.MainActivity


class WakeUpService : Service() {

    private var wake_lock: PowerManager.WakeLock? = null
    private var is_service_started = false
    private var get_from_background = false


    override fun onBind(intent: Intent): IBinder? {
        return null
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent != null) {
            val action = intent.action
            when (action) {
                "START" -> startService()
                "STOP" -> stopService()
                else -> Log.d(
                    Constants.TAG,
                    "This should never happen. No action in the received intent"
                )
            }
        } else {
            Log.d(
                Constants.TAG,
                "with a null intent. It has been probably restarted by the system."
            )
        }
        // by returning this we make sure the service is restarted if the system kills the service
        return START_STICKY
    }


    override fun onCreate() {
        super.onCreate()
        var notification = createNotification()
        startForeground(1, notification)
    }


    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "Service destroyed", Toast.LENGTH_SHORT).show()
    }


    private fun startService() {
        if (is_service_started) return
        Toast.makeText(this, "Service starting its task", Toast.LENGTH_SHORT).show()
        is_service_started = true

        // we need this lock so our service gets not affected by Doze Mode
        wake_lock =
            (getSystemService(Context.POWER_SERVICE) as PowerManager).run {
                newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "WakeUpService::lock").apply {
                    acquire()
                }
            }

        // we're starting a loop in a coroutine
        GlobalScope.launch {
            while (is_service_started) {
                launch(Dispatchers.IO) {
                    if(!appInForeground(this@WakeUpService)) {
                        val main_intent = Intent(this@WakeUpService, MainActivity::class.java)
                        main_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(main_intent)
                    }
                }
                delay(1 * 20 * 1000)
            }
        }
    }


    private fun stopService() {
        Toast.makeText(this, "Service stopping", Toast.LENGTH_SHORT).show()
        try {
            wake_lock?.let {
                if (it.isHeld) {
                    it.release()
                }
            }
            stopForeground(true)
            stopSelf()
        } catch (e: Exception) {
            Log.d(Constants.TAG, "Service stopped without being started: ${e.message}")
        }
        is_service_started = false
    }


    private fun createNotification(): Notification {
        val notificationChannelId = "Tottus Test"

        // depending on the Android API that we're dealing with we will have
        // to use a specific method to create the notification
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager;
            val channel = NotificationChannel(
                notificationChannelId,
                "Looping wake up",
                NotificationManager.IMPORTANCE_HIGH
            ).let {
                it.description = "Looping wake up channel"
                it.enableLights(true)
                it.lightColor = Color.RED
                it.enableVibration(true)
                it.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
                it
            }
            notificationManager.createNotificationChannel(channel)
        }

        val pendingIntent: PendingIntent = Intent(this, MainActivity::class.java).let { notificationIntent ->
            PendingIntent.getActivity(this, 0, notificationIntent, 0)
        }

        val builder: Notification.Builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) Notification.Builder(
            this,
            notificationChannelId
        ) else Notification.Builder(this)

        return builder
            .setContentTitle("Automatic Wake up")
            .setContentText("When in foreground the app will try to endlessly go back up")
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setTicker("Lol")
            .setPriority(Notification.PRIORITY_HIGH) // for under android 26 compatibility
            .build()
    }


    private fun appInForeground(context: Context): Boolean {
        val activity_manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val running_app_processes = activity_manager.runningAppProcesses ?: return false
        return running_app_processes.any { it.processName == context.packageName && it.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND }
    }

}
