package com.bhongj.rc_week5

import com.bhongj.rc_week5.model.GyungkiRestrntResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantInterface {
    @GET("SafetyRestrntInfo")
    fun getRestaurantResponse(
        @Query("Key") Key:String,
        @Query("Type") Type:String,
        @Query("pIndex") pIndex:Int,
        @Query("pSize") pSize:Int,
    ) : Call<GyungkiRestrntResponse>
}