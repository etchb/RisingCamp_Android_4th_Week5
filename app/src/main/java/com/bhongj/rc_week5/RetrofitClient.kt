package com.bhongj.rc_week5

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    var gson = GsonBuilder().setLenient().create()
    val sRetrofit = initRetrofit()
    private const val RESTAURANT_URL = "https://openapi.gg.go.kr/"

    private fun initRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(RESTAURANT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}