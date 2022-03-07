package com.bhongj.rc_week5;

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bhongj.rc_week5.databinding.ActivityLoginBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ObjectAnimator.ofFloat(binding.imgMoveBack, View.TRANSLATION_X, -1000f).apply {
            duration = 15000L
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.RESTART
            start()
        }

        binding.btnLoginKakao.setOnClickListener {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this@LoginActivity)) {
                UserApiClient.instance.loginWithKakaoTalk(this@LoginActivity, callback = callback)
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
                UserApiClient.instance.loginWithKakaoAccount(this@LoginActivity, callback = callback)
                Log.d("로그인", "2")
            }
        }

        binding.btnLoginApple.setOnClickListener {
            UserApiClient.instance.unlink { error ->
                if (error != null) {
                    Log.d("연결", "연결 끊기 실패", error)
                }
                else {
                    Log.d("연결", "연결 끊기 성공. SDK에서 토큰 삭제 됨")
                }
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
}