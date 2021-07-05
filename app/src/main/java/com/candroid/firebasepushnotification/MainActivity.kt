package com.candroid.firebasepushnotification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceIdReceiver
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService

import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
const val topic="/topics//myTopic"
class MainActivity : AppCompatActivity() {
    val Tag="MyActivity"
    lateinit var firebaseMessaging: FirebaseMessaging
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
MessagingServices.sharedPreferences=getSharedPreferences("sher", MODE_PRIVATE)
        firebaseMessaging= FirebaseMessaging.getInstance()

//here we subscribe our topic
        firebaseMessaging.subscribeToTopic(topic)
//here we send message to particular user who subscribe that specific topic
        firebaseMessaging.token.addOnSuccessListener {
   MessagingServices.token=it
    etToken.setText(it)
}

    btnSend.setOnClickListener {
        val title=etTitle.text.toString()
        val message=etMessage.text.toString()
val token=etToken.text.toString()
        if(title.isNotEmpty()&&message.isNotEmpty()&&token.isNotEmpty()){
         PushNotification(NotificationData(title,message),token).also {
             sendMessage(it)
         }


        }

    }


    }
    //in this function we actual call network request to server
fun sendMessage(notification: PushNotification)= CoroutineScope(Dispatchers.IO).launch{
    try{
//here we get response from notification Api here we also use api instance
        val response=RetrofitInstance.api.postNotification(notification)
  if(response.isSuccessful){
      Log.d(Tag, Gson().toJson(response))
  }
else{
      Log.e(Tag, response.errorBody().toString())


}
    }catch (e:Exception){
        Log.d(Tag,e.toString())
    }


}

}