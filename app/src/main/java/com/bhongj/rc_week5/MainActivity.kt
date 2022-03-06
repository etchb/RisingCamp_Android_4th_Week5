package com.bhongj.rc_week5

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bhongj.rc_week5.databinding.ActivityMainBinding
import com.bhongj.rc_week5.models.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val API_KEY = "fb80023520e73661e24631ea5a73030c"

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetTemp.setOnClickListener {
            val cityName = binding.edtCity.text.toString()
            getWeatherData(cityName, API_KEY)
        }

    }

    private fun getWeatherData(city: String, key: String) {
        val weatherInterface = RetrofitClient.sRetrofit.create(WeatherInterface::class.java)
        weatherInterface.getWeather(city, key).enqueue(object : Callback<WeatherResponse>{
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if(response.isSuccessful) {
                    val result = response.body() as WeatherResponse
                    binding.txtResultTemp.text = result.main.temp.toString() + "도"
                } else {
                    Log.d("MainActivity", "getWeatherData - onResponse")
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.d("MainActivity", "getWeatherData - onFailure")
            }
        })
    }
}