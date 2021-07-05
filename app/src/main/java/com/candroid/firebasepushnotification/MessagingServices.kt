package com.candroid.firebasepushnotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingRegistrar
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.random.Random
const val channelId="My_channel"
class MessagingServices(): FirebaseMessagingService() {
  companion object
  {
      var sharedPreferences:SharedPreferences?=null
      var token:String?
      get() {
          return sharedPreferences?.getString("token","")
      }
      set(value) {

          sharedPreferences?.edit()?.putString("token",value)?.apply()
      }

  }

    override fun onNewToken(newToken: String) {
        super.onNewToken(newToken)
        token=newToken
    }


    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)



        val intent=Intent(this,MainActivity::class.java)
        var notificationManager=getSystemService(Context.NOTIFICATION_SERVICE)as NotificationManager
           var notificationId=Random.nextInt()
intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent=PendingIntent.getActivity(this,0,intent,FLAG_ONE_SHOT)
val notification=NotificationCompat.Builder(this, channelId)
    .setContentTitle(message.data["title"])
    .setContentText(message.data["message"])
    .setSmallIcon(R.drawable.ic_launcher_foreground)
    .setAutoCancel(true)
    .setContentIntent(pendingIntent)
    .build()
        notificationManager.notify(notificationId,notification)
    }
@RequiresApi(Build.VERSION_CODES.O)
fun createNotificationChannel(notificationManager: NotificationManager){
    val channelName="channelName"
 val channel=NotificationChannel(channelId,channelName,IMPORTANCE_HIGH).apply {
     description="My Channel Description"
         enableLights(true)
     lightColor=Color.GREEN
 }
 notificationManager.createNotificationChannel(channel)

}


}


