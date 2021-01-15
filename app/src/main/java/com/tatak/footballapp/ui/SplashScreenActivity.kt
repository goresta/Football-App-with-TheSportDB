package com.tatak.footballapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.wang.avi.AVLoadingIndicatorView
import com.tatak.footballapp.R

class SplashScreenActivity : AppCompatActivity() {

    private val avi: AVLoadingIndicatorView
        get() = findViewById(R.id.avi)

    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        avi.setIndicator(getString(R.string.loading_indicator))

        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
            finishAffinity()
        },6000)
    }
}

