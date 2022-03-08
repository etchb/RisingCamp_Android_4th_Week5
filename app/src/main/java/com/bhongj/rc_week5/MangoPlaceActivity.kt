package com.bhongj.rc_week5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bhongj.rc_week5.databinding.ActivityMangoPlaceBinding
import com.bhongj.rc_week5.model.GyungkiRestrntResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MangoPlaceActivity : AppCompatActivity() {
    lateinit var binding: ActivityMangoPlaceBinding

    val API_KEY = "6fd25b60a9944270b8b68a78f5e4f9d4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMangoPlaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGet.setOnClickListener {
            getRestaurantData(API_KEY, "json",1,2)
        }
    }

    private fun getRestaurantData(Key: String, Type: String, pIndex: Int, pSize: Int) {
        val restaurantInterface = RetrofitClient.sRetrofit.create(RestaurantInterface::class.java)

        restaurantInterface.getRestaurantResponse(Key, Type, pIndex, pSize).enqueue(object : Callback<GyungkiRestrntResponse> {
            override fun onResponse(
                call: Call<GyungkiRestrntResponse>,
                response: Response<GyungkiRestrntResponse>
            ) {
                if(response.isSuccessful) {
                    val result = response.body() as GyungkiRestrntResponse
                    Log.d("result", result.SafetyRestrntInfo[1].row[0].SAFETY_RESTRNT_NO.toString())
                } else {
                }
            }

            override fun onFailure(call: Call<GyungkiRestrntResponse>, t: Throwable) {
                Log.d("TEST", "getRestaurantData - onFailure ${t.message}")
            }
        })
    }
}