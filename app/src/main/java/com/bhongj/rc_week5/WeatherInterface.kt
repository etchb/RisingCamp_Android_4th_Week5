package com.bhongj.rc_week5

import com.bhongj.rc_week5.models.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherInterface {
    @GET("weather")
    fun getWeather(
        @Query("q") q:String,
        @Query("appid") appid:String
    ) : Call<WeatherResponse>
}