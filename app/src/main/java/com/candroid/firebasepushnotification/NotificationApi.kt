package com.candroid.firebasepushnotification

import com.candroid.firebasepushnotification.Constant.Companion.CONTENT_TYPE
import com.candroid.firebasepushnotification.Constant.Companion.SERVER_KEY
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotificationApi {

    @Headers("Authorization :key=${SERVER_KEY}","CONTENT_TYPE:${CONTENT_TYPE}")
    @POST("fcm/send")
//here we create our api that we to our server
    suspend fun postNotification(

    @Body notification: PushNotification
):Response<ResponseBody>

}