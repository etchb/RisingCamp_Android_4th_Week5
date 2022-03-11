package com.bhongj.rc_week5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bhongj.rc_week5.databinding.ActivityMangoPlaceBinding
import com.bhongj.rc_week5.model.GyungkiRestrntResponse
import com.bhongj.rc_week5.model.RestrntData
import com.bhongj.rc_week5.model.RestrntDataSize
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MangoPlaceActivity : AppCompatActivity() {
    lateinit var binding: ActivityMangoPlaceBinding
    var nextActivity = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMangoPlaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.btnGet.setOnClickListener {
//
//            saveRestaurantData()
//            getRestaurantSize(getString(R.string.API_KEY), "json")
//        }

//        binding.btnRead.setOnClickListener {
//            Log.d("TEST data", RestrntData.toString())
//            Log.d("TEST size", RestrntData.size.toString())
//        }

        getRestaurantSize(getString(R.string.API_KEY), "json")
        getRestaurantDataList()

        binding.btnGet.setOnClickListener {
            Log.d("TEST data", RestrntDataSize.toString())
        }

    }

    private fun getRestaurantDataList() {
        Thread() {
            while (true) {
                Thread.sleep(100)
                Log.d("TEST", "RestrntDataSize = ${RestrntDataSize}")
                if (RestrntDataSize >= 0) {
                    break
                }
            }
            if (RestrntDataSize > 0) {
                for (pageIdx in 1 .. (RestrntDataSize/1000).toInt()) {
                    getRestaurantData(getString(R.string.API_KEY), "json",pageIdx,1000)
                }
                getRestaurantData(getString(R.string.API_KEY), "json",(RestrntDataSize/1000).toInt() + 1,RestrntDataSize%1000)
                val dataSizeMin = 5000
//                while (RestrntData.size != RestrntDataSize) {
                while (RestrntData.size < dataSizeMin) {
                    Thread.sleep(100)
                    Log.d("TEST", "RestrntData.size < dataSizeMin")
                }
                nextActivity = true
                Log.d("TEST", "RestrntData.size = ${RestrntData.size}")
                startActivity(Intent(this, MainActivity::class.java))
            }
        }.start()
    }

    private fun getRestaurantSize(Key: String, Type: String) {
        val restaurantInterface = RetrofitClient.sRetrofit.create(RestaurantInterface::class.java)

        restaurantInterface.getRestaurantResponse(Key, Type, 1, 1).enqueue(object : Callback<GyungkiRestrntResponse> {
            override fun onResponse(
                call: Call<GyungkiRestrntResponse>,
                response: Response<GyungkiRestrntResponse>
            ) {
                if(response.isSuccessful) {
                    val result = response.body() as GyungkiRestrntResponse
                    RestrntDataSize = result.SafetyRestrntInfo[0].head[0].list_total_count
                    Log.d("TEST", "RestrntDataSize = ${RestrntDataSize}")
                } else {
                }
            }

            override fun onFailure(call: Call<GyungkiRestrntResponse>, t: Throwable) {
                Log.d("TEST", "getRestaurantData - onFailure ${t.message}")
            }
        })
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
//                    Log.d("TEST get", result.SafetyRestrntInfo[1].row[0].SAFETY_RESTRNT_NO.toString())
//                    Log.d("TEST get", result.SafetyRestrntInfo[0].head[0].list_total_count.toString())
                    RestrntData.addAll(result.SafetyRestrntInfo[1].row)
                } else {
                }
            }

            override fun onFailure(call: Call<GyungkiRestrntResponse>, t: Throwable) {
                Log.d("TEST", "getRestaurantData - onFailure ${t.message}")
            }
        })
    }
}