package com.bhongj.rc_week5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bhongj.rc_week5.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}