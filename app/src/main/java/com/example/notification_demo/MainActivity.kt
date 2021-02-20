package com.example.notification_demo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {
    val btn_launchintent by lazy <Button> { findViewById(R.id.btn_launch)}
    val notificationManager by lazy <NotificationManagerCompat> { NotificationManagerCompat.from(this)}
    val notificationChannelId by lazy<String> { getString(R.string.channel_name) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_launchintent.setOnClickListener(){
            val notificationsOk = notificationManager.areNotificationsEnabled()
            if (notificationsOk) {
                createNotificationChannel()
                generateBigStyleNotification()
            }
        }
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(notificationChannelId, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                    getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    fun generateBigStyleNotification(){
        val notification = NotificationCompat.Builder(this, notificationChannelId)// BIG_TEXT_STYLE sets title and content for API 16 (4.1 and after).
                //.setStyle(bigTextStyle) // Title for API <16 (4.0 and below) devices.
                .setContentTitle("This is My Application's notification.") // Content for API <24 (7.0 and below) devices.
                .setContentText("This is a test notification")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                //.setLargeIcon(BitmapFactory.decodeResource( resources, R.drawable.ic_launcher_foreground))
                //.setContentIntent(notifyPendingIntent)
                //.setDefaults(NotificationCompat.DEFAULT_ALL) // Set primary color (important for Wear 2.0 Notifications).
                //.setColor(ContextCompat.getColor(applicationContext, R.color.colorPrimary)) // SIDE NOTE: Auto-bundling is enabled for 4 or more notifications on API 24+ (N+)
                // devices and all Wear devices. If you have more than one notification and
                // you prefer a different summary notification, set a group key and create a
                // summary notification via
                // .setGroupSummary(true)
                // .setGroup(GROUP_KEY_YOUR_NAME_HERE)
                //.setCategory(Notification.CATEGORY_REMINDER) // Sets priority for 25 and below. For 26 and above, 'priority' is deprecated for
                // 'importance' which is set in the NotificationChannel. The integers representing
                // 'priority' are different from 'importance', so make sure you don't mix them.
                //.setPriority(bigTextStyleReminderAppData.getPriority()) // Sets lock-screen visibility for 25 and below. For 26 and above, lock screen
                // visibility is set in the NotificationChannel.
                //.setVisibility(bigTextStyleReminderAppData.getChannelLockscreenVisibility()) // Adds additional actions specified above.
                //.addAction(snoozeAction)
                //.addAction(dismissAction)
                .build()

        val NOTIFICATION_ID = 888
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

}