package com.candroid.firebasepushnotification

import com.candroid.firebasepushnotification.Constant.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
companion object {
    val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api by lazy {
        retrofit.create(NotificationApi::class.java)
    }
}
}