package com.bhongj.rc_week5

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bhongj.rc_week5.databinding.ActivityMainBinding
import com.bhongj.rc_week5.models.WeatherResponse
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
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

        var keyHash = Utility.getKeyHash(this)
        Log.d("keyHash", keyHash)

        binding.btnGetTemp.setOnClickListener {
            val cityName = binding.edtCity.text.toString()
            getWeatherData(cityName, API_KEY)

//            UserApiClient.instance.logout { error ->
//                if (error != null) {
//                    Log.d("로그아웃", "로그아웃 실패. SDK에서 토큰 삭제됨", error)
//                }
//                else {
//                    Log.d("로그아웃", "로그아웃 성공. SDK에서 토큰 삭제됨")
//                }
//            }

            UserApiClient.instance.unlink { error ->
                if (error != null) {
                    Log.d("연결", "연결 끊기 실패", error)
                }
                else {
                    Log.d("연결", "연결 끊기 성공. SDK에서 토큰 삭제 됨")
                }
            }
        }

        binding.btnLoginKakao.setOnClickListener {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this@MainActivity)) {
                UserApiClient.instance.loginWithKakaoTalk(this@MainActivity, callback = callback)
                Log.d("로그인", "1")

                UserApiClient.instance.me { user, error ->
                    if (error != null) {
                        Log.d("사용자 정보", "사용자 정보 요청 실패", error)
                    }
                    else if (user != null) {
                        Log.d("사용자 정보", "사용자 정보 요청 성공" +
                                "\n회원번호: ${user.id}" +
                                "\n이메일: ${user.kakaoAccount?.email}" +
                                "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                                "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(this@MainActivity, callback = callback)
                Log.d("로그인", "2")
            }
        }

    }

    internal val callback : (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.d("로그인 실패", "- $error")
        } else if (token != null) {
            UserApiClient.instance.me { user, error ->
                val kakaoId = user!!.id
//                viewModel?.addKakaoUser(token.accessToken, kakaoId)
            }
//            Log.d("로그인성공", "- 토큰 ${authManager.token}")
            Log.d("로그인성공", "- 토큰 ")
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